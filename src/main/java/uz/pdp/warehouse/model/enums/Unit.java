package uz.pdp.warehouse.model.enums;


import com.fasterxml.jackson.annotation.JsonValue;

public enum Unit {
    PCS("Pieces"),
    KG("Kilograms"),
    LITER("Liters"),
    METER("Meters"),
    BOX("Boxes");

    private final String displayName;

    Unit(String displayName) {
        this.displayName = displayName;
    }

    @JsonValue
    public String getDisplayName() {
        return displayName;
    }
}
