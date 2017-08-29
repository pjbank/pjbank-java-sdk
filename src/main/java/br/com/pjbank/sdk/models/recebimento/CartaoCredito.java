package br.com.pjbank.sdk.models.recebimento;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public class CartaoCredito {
    private String nome;
    private String cpfCnpj;
    private String numero;
    private int mesVencimento;
    private int anoVencimento;
    private String cvv;
    private String email;
    private long celular;

    public CartaoCredito() {
    }

    public CartaoCredito(String nome, String cpfCnpj, String numero, int mesVencimento, int anoVencimento, String cvv, String email, long celular) {
        this.nome = nome;
        this.cpfCnpj = cpfCnpj;
        this.numero = numero;
        this.mesVencimento = mesVencimento;
        this.anoVencimento = anoVencimento;
        this.cvv = cvv;
        this.email = email;
        this.celular = celular;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public int getMesVencimento() {
        return mesVencimento;
    }

    public void setMesVencimento(int mesVencimento) {
        this.mesVencimento = mesVencimento;
    }

    public int getAnoVencimento() {
        return anoVencimento;
    }

    public void setAnoVencimento(int anoVencimento) {
        this.anoVencimento = anoVencimento;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public long getCelular() {
        return celular;
    }

    public void setCelular(long celular) {
        this.celular = celular;
    }
}
