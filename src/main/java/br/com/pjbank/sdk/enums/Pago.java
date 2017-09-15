package br.com.pjbank.sdk.enums;

/**
 * 
 * @author Paulo Vitor Braga Pessoa <foxpv213@gmail.com>
 *
 */
public enum Pago {
	PAGO("1"),
	ABERTO("0");

    private String name;

    Pago(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static Pago fromString(String name) {
        for (Pago obj : Pago.values()) {
            if (obj.name.equalsIgnoreCase(name)) {
                return obj;
            }
        }
        return null;
    }
}
