package br.com.pjbank.sdk.enums;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public enum StatusCartaoCorporativo {
    ATIVO("Ativo"), BLOQUEADO("Bloqueado"), INATIVO("Inativo"), PENDENTE("Pendente");

    private String name;

    StatusCartaoCorporativo(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static StatusCartaoCorporativo fromString(String name) {
        for (StatusCartaoCorporativo obj : StatusCartaoCorporativo.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
