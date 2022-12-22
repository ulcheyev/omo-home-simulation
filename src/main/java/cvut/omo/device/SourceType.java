package cvut.omo.device;


import lombok.Getter;

public enum SourceType {
    WATER("M3", 14),
    ENERGY("KWH", 14),
    NOT_CONSUME("", 0);

    @Getter
    private final String UNIT_OF_MEASUREMENT;
    @Getter
    private final double PRICE_FOR_UNIT;

    SourceType(String unit_of_measurement, double price_for_unit) {
        UNIT_OF_MEASUREMENT = unit_of_measurement;
        PRICE_FOR_UNIT = price_for_unit;
    }
}
