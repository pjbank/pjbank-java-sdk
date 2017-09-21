package br.com.pjbank.sdk.enums;

public enum TipoAnexo {
    BOLETO("boleto"),
    COMPROVANTE("comprovante"),
    FATURA("fatura"),
    OUTROS("outros"),
    NOTAFISCAL("notafiscal"),
    PROPOSTA("proposta");

    private String name;

    TipoAnexo(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static TipoAnexo fromString(String name) {
        for (TipoAnexo obj : TipoAnexo.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
