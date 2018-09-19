package br.com.pjbank.sdk.enums;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public enum TipoTransacao {
    CREDITO("crédito"), DEPOSITO("depósito"), PAGAMENTO("pagamento"), TRANSFERENCIA("transferência"), TRANSFERENCIA_CONTA_DIGITAL("transferencia_conta_digital");

    private String name;

    TipoTransacao(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TipoTransacao fromString(String name) {
        for (TipoTransacao obj : TipoTransacao.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
