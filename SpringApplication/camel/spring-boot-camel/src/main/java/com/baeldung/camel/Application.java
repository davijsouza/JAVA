package com.baeldung.camel;

import javax.ws.rs.core.MediaType;

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.servlet.CamelHttpTransportServlet;
import org.apache.camel.impl.DefaultCamelContext;
import org.apache.camel.model.rest.RestBindingMode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.aop.AopAutoConfiguration;
import org.springframework.boot.autoconfigure.security.oauth2.resource.servlet.OAuth2ResourceServerAutoConfiguration;
import org.springframework.boot.autoconfigure.web.embedded.EmbeddedWebServerFactoryCustomizerAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.servlet.WebSocketServletAutoConfiguration;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = { WebSocketServletAutoConfiguration.class, AopAutoConfiguration.class, OAuth2ResourceServerAutoConfiguration.class, EmbeddedWebServerFactoryCustomizerAutoConfiguration.class })
@ComponentScan(basePackages = "com.baeldung.camel")
public class Application {

    @Value("${server.port}")
    String serverPort;

    @Value("${baeldung.api.path}")
    String contextPath;

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean
    ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean servlet = new ServletRegistrationBean(new CamelHttpTransportServlet(), contextPath + "/*");
        servlet.setName("CamelServlet");
        return servlet;
    }

    @Component
    class RestApi extends RouteBuilder {

        @Override
        public void configure() {

            CamelContext context = new DefaultCamelContext();

            // http://localhost:8080/camel/api-doc
            restConfiguration().contextPath(contextPath) //
                .port(serverPort)
                .enableCORS(true)
                .apiContextPath("/api-doc")
                .apiProperty("api.title", "Test REST API")
                .apiProperty("api.version", "v1")
                .apiProperty("cors", "true") // cross-site
                .apiContextRouteId("doc-api")
                .component("servlet")
                .bindingMode(RestBindingMode.json)
                .dataFormatProperty("prettyPrint", "true");
            /** 
            The Rest DSL supports automatic binding json/xml contents to/from 
            POJOs using Camels Data Format.
            By default the binding mode is off, meaning there is no automatic 
            binding happening for incoming and outgoing messages.
            You may want to use binding if you develop POJOs that maps to 
            your REST services request and response types. 
            */

            rest("/api/")
                .description("Teste REST Service")
                .id("restId-route-post")
                .post("/bean")
                    .produces(MediaType.APPLICATION_JSON)
                    .consumes(MediaType.APPLICATION_JSON)
                    .bindingMode(RestBindingMode.auto)
                    .type(MyBean.class)
                    .enableCORS(true)
                    .to("direct:post-service");


            rest("/api/")
                .description("Teste REST Service")
                .id("restId-route-get")
                .get("/bean")
                    .produces(MediaType.APPLICATION_JSON)
                    .consumes(MediaType.APPLICATION_JSON)
                    .bindingMode(RestBindingMode.auto)
                    .enableCORS(true)
                    .outType(OutBean.class)
                    .to("direct:get-service");


            from("direct:post-service")
                .routeId("idPost-direct-route")
                .tracing()
                .log(">>> ${body.id}")
                .log(">>> ${body.name}")
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        MyBean bodyIn = (MyBean) exchange.getIn()
                            .getBody();

                        ExampleServices.example(bodyIn);

                        exchange.getIn()
                            .setBody(bodyIn);
                    }
                })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(201));


            from("direct:get-service")
                .routeId("idGet-direct-route")
                .tracing()
                .process(new Processor() {
                    @Override
                    public void process(Exchange exchange) throws Exception {
                        OutBean out = new OutBean();
                        out.setId(1);
                        out.setName("Test");
                        exchange.getIn().setBody(out);

                        OutBean payload = exchange.getIn().getBody(OutBean.class);
                        exchange.getIn().setBody(payload);
                    }
                })
                .setHeader(Exchange.HTTP_RESPONSE_CODE, constant(200));
        }

    }
}
