package br.com.pjbank.sdk.enums;

/**
 * @author Vinícius Silva <vinicius.silva@superlogica.com>
 * @version 1.0
 * @since 1.0
 */
public enum StatusTransacao {
    APROVADA("Aprovada"), PENDENTE("Pendente"), RECUSADA("Recusada");

    private String name;

    StatusTransacao(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static StatusTransacao fromString(String name) {
        for (StatusTransacao obj : StatusTransacao.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
