package List.domain;

import java.util.Objects;

public class Manga implements Comparable<Manga> {
    private Long id;
    private String nome;
    private double preco;
    private int quantidade;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public Manga(Long id, String nome, double preco) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
    }

    public Manga(Long id, String nome, double preco, int quantidade) {
        this(id, nome, preco);
        this.quantidade = quantidade;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Manga manga = (Manga) o;
        return Objects.equals(id, manga.id) && Objects.equals(nome, manga.nome);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, nome);
    }

    @Override
    public String toString() {
        return "Manga{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", preco=" + preco +
                ", quantidade=" + quantidade +
                '}';
    }


    /*@Override
    public int compareTo(Manga o) {
        if (this.id < o.getId()) {
            return -1;
        }
        if (this.id.equals(o.getId())) {
            return 0;
        }
        return 1;
    }*/

    /**
     * Aqui podemos utilizar de outra forma a organização com Wrappers
     * que já existe uma validação do Long compareTo agregada.
     */

    @Override
    public int compareTo(Manga o) {
        // Exemplo por preço.
        //return Double.compare(preco, o.getPreco());
        //Double.valueOf(preco).compareTo(o.getPreco());
        //return this.id.compareTo(o.getId());
        return this.nome.compareTo(o.getNome());
    }

}
