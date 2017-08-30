package br.com.pjbank.sdk.enums;

public enum TipoConta {
    CORRENTE("corrente"), POUPANCA("Poupança");

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
