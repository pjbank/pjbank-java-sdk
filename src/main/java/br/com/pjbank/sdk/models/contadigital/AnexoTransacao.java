package br.com.pjbank.sdk.models.contadigital;

import br.com.pjbank.sdk.enums.FormatoArquivo;
import br.com.pjbank.sdk.enums.TipoAnexo;

import java.util.Date;

public class AnexoTransacao {
    private String url;
    private TipoAnexo tipo;
    private String nome;
    private FormatoArquivo formato;
    private long tamanho;
    private Date data;

    public AnexoTransacao() {
    }

    public AnexoTransacao(String url, TipoAnexo tipo, String nome, FormatoArquivo formato, long tamanho, Date data) {
        this.url = url;
        this.tipo = tipo;
        this.nome = nome;
        this.formato = formato;
        this.tamanho = tamanho;
        this.data = data;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public TipoAnexo getTipo() {
        return tipo;
    }

    public void setTipo(TipoAnexo tipo) {
        this.tipo = tipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public FormatoArquivo getFormato() {
        return formato;
    }

    public void setFormato(FormatoArquivo formato) {
        this.formato = formato;
    }

    public long getTamanho() {
        return tamanho;
    }

    public void setTamanho(long tamanho) {
        this.tamanho = tamanho;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
}
