package br.com.pjbank.sdk.enums;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public enum TipoConta {
    CORRENTE("corrente"), POUPANCA("poupanca");

    private String name;

    TipoConta(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TipoConta fromString(String name) {
        for (TipoConta obj : TipoConta.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
