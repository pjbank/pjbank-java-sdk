package br.com.pjbank.sdk.models.contadigital;

import br.com.pjbank.sdk.enums.StatusCartaoCorporativo;
import br.com.pjbank.sdk.models.common.Endereco;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class CartaoCorporativo {
    private String nome;
    private String documento;
    private String numero;
    private String email;
    private Date dataInicio;
    private Date dataBloqueio;
    private Endereco endereco;
    private int ddd;
    private long telefone;
    private StatusCartaoCorporativo status;
    private int qtdBoletosCargaPendentes;

    public CartaoCorporativo() {
    }

    public CartaoCorporativo(String nome, String documento, String numero, String email, Date dataInicio, Date dataBloqueio, Endereco endereco, int ddd, long telefone, StatusCartaoCorporativo status, int qtdBoletosCargaPendentes) {
        this.nome = nome;
        this.documento = documento;
        this.numero = numero;
        this.email = email;
        this.dataInicio = dataInicio;
        this.dataBloqueio = dataBloqueio;
        this.endereco = endereco;
        this.ddd = ddd;
        this.telefone = telefone;
        this.status = status;
        this.qtdBoletosCargaPendentes = qtdBoletosCargaPendentes;
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

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(Date dataInicio) {
        this.dataInicio = dataInicio;
    }

    public Date getDataBloqueio() {
        return dataBloqueio;
    }

    public void setDataBloqueio(Date dataBloqueio) {
        this.dataBloqueio = dataBloqueio;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public int getDdd() {
        return ddd;
    }

    public void setDdd(int ddd) {
        this.ddd = ddd;
    }

    public long getTelefone() {
        return telefone;
    }

    public void setTelefone(long telefone) {
        this.telefone = telefone;
    }

    public StatusCartaoCorporativo getStatus() {
        return status;
    }

    public void setStatus(StatusCartaoCorporativo status) {
        this.status = status;
    }

    public int getQtdBoletosCargaPendentes() {
        return qtdBoletosCargaPendentes;
    }

    public void setQtdBoletosCargaPendentes(int qtdBoletosCargaPendentes) {
        this.qtdBoletosCargaPendentes = qtdBoletosCargaPendentes;
    }
}
