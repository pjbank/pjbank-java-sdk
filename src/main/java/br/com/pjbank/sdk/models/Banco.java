package br.com.pjbank.sdk.models;

public class Banco {
    /**
     * Código do banco
     */
    private String codigo;
    /**
     * Nome do banco
     */
    private String nome;

    public Banco(String codigo, String nome) {
        this.codigo = codigo;
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
