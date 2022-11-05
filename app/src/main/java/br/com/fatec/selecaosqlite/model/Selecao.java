package br.com.fatec.selecaosqlite.model;

public class Selecao {

    private int id;
    private String nome;
    private int titulos;
    private String continente;

    public Selecao(String nome, int titulos, String continente) {
        this.nome = nome;
        this.titulos = titulos;
        this.continente = continente;
    }

    public Selecao(int id, String nome, int titulos, String continente) {
        this(nome, titulos, continente);
        this.id = id;
    }

    public Selecao() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTitulos() {
        return titulos;
    }

    public void setTitulos(int titulos) {
        this.titulos = titulos;
    }

    public String getContinente() {
        return continente;
    }

    public void setContinente(String continente) {
        this.continente = continente;
    }

    @Override
    public String toString() {
        return nome + " - " + titulos;
    }
}
