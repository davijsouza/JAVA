package DESIGNPATTERNS.DesignPatternII.Decorators.NovoImpostoCodigoRuim.http;

import java.util.Map;

public interface HttpAdapter {

	void post(String url, Map<String, Object> dados);

}
