package br.com.pjbank.sdk.enums;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public enum FormatoExtrato {
    JSON("json");

    private String name;

    FormatoExtrato(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static FormatoExtrato fromString(String name) {
        for (FormatoExtrato obj : FormatoExtrato.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
