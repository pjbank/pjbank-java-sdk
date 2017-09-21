package br.com.pjbank.sdk.enums;

public enum FormatoArquivo {
    JPEG("image/jpeg"), PDF("application/pdf"), PNG("image/png");

    private String name;

    FormatoArquivo(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static FormatoArquivo fromString(String name) {
        for (FormatoArquivo obj : FormatoArquivo.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
