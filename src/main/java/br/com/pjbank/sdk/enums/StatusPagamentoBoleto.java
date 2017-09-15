package br.com.pjbank.sdk.enums;

/**
 * 
 * @author Paulo Vitor Braga Pessoa <foxpv213@gmail.com>
 *
 */
public enum StatusPagamentoBoleto {
	PAGO("1"),
	ABERTO("0");

    private String name;

    StatusPagamentoBoleto(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static StatusPagamentoBoleto fromString(String name) {
        for (StatusPagamentoBoleto obj : StatusPagamentoBoleto.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
