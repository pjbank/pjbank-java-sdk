package br.com.pjbank.sdk.models.contadigital;

import br.com.pjbank.sdk.enums.TipoConta;

import java.util.Date;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class TransacaoTransferencia extends Transacao {
    private String bancoFavorecido;
    private String agenciaFavorecido;
    private String contaFavorecido;
    private TipoConta tipoContaFavorecido;
    private String nomeFavorecido;
    private String cnpjFavorecido;
    private String identificador;
    private String descricao;
    private String solicitante;

    public TransacaoTransferencia() {
    }

    public TransacaoTransferencia(Date dataPagamento, Date dataVencimento, double valor, String bancoFavorecido, String agenciaFavorecido, String contaFavorecido, TipoConta tipoContaFavorecido, String nomeFavorecido, String cnpjFavorecido, String identificador, String descricao, String solicitante) {
        super(dataPagamento, dataVencimento, valor);
        this.bancoFavorecido = bancoFavorecido;
        this.agenciaFavorecido = agenciaFavorecido;
        this.contaFavorecido = contaFavorecido;
        this.tipoContaFavorecido = tipoContaFavorecido;
        this.nomeFavorecido = nomeFavorecido;
        this.cnpjFavorecido = cnpjFavorecido;
        this.identificador = identificador;
        this.descricao = descricao;
        this.solicitante = solicitante;
    }

    public String getBancoFavorecido() {
        return bancoFavorecido;
    }

    public void setBancoFavorecido(String bancoFavorecido) {
        this.bancoFavorecido = bancoFavorecido;
    }

    public String getAgenciaFavorecido() {
        return agenciaFavorecido;
    }

    public void setAgenciaFavorecido(String agenciaFavorecido) {
        this.agenciaFavorecido = agenciaFavorecido;
    }

    public String getContaFavorecido() {
        return contaFavorecido;
    }

    public void setContaFavorecido(String contaFavorecido) {
        this.contaFavorecido = contaFavorecido;
    }

    public TipoConta getTipoContaFavorecido() {
        return tipoContaFavorecido;
    }

    public void setTipoContaFavorecido(TipoConta tipoContaFavorecido) {
        this.tipoContaFavorecido = tipoContaFavorecido;
    }

    public String getNomeFavorecido() {
        return nomeFavorecido;
    }

    public void setNomeFavorecido(String nomeFavorecido) {
        this.nomeFavorecido = nomeFavorecido;
    }

    public String getCnpjFavorecido() {
        return cnpjFavorecido;
    }

    public void setCnpjFavorecido(String cnpjFavorecido) {
        this.cnpjFavorecido = cnpjFavorecido;
    }

    public String getIdentificador() {
        return identificador;
    }

    public void setIdentificador(String identificador) {
        this.identificador = identificador;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSolicitante() {
        return solicitante;
    }

    public void setSolicitante(String solicitante) {
        this.solicitante = solicitante;
    }
}
