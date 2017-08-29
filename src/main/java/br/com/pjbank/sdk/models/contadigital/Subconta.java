package br.com.pjbank.sdk.models.contadigital;

import br.com.pjbank.sdk.models.common.Boleto;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class Subconta {
    /**
     * Dados para requisição
     */
    private String nome;
    private String documento;
    private Date dataNascimento;
    private char sexo;
    private String email;
    private String produto;
    private double valor;

    /**
     * Dados para resposta
     */
    private String numero;
    private String token;
    private Boleto boleto;

    public Subconta() {
    }

    public Subconta(String nome, String documento, Date dataNascimento, char sexo, String email, String produto, double valor) {
        this.nome = nome;
        this.documento = documento;
        this.dataNascimento = dataNascimento;
        this.sexo = sexo;
        this.email = email;
        this.produto = produto;
        this.valor = valor;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = Character.toUpperCase(sexo);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Boleto getBoleto() {
        return boleto;
    }

    public void setBoleto(Boleto boleto) {
        this.boleto = boleto;
    }
}
