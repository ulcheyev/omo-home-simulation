package cvut.omo.entity.device;


import lombok.Getter;

/**
 * Class represents source typ of devices.
 * Contains current price of source type and unit if measurement.
 */
public enum SourceType {
    WATER("M3", 3),
    ENERGY("KWH", 4),
    NOT_CONSUME("", 0);

    @Getter
    private final String UNIT_OF_MEASUREMENT;
    @Getter
    private final double PRICE_FOR_UNIT;

    /**
     *
     * @param unit_of_measurement unit of measurement of source type
     * @param price_for_unit price for one unit
     */
    SourceType(String unit_of_measurement, double price_for_unit) {
        UNIT_OF_MEASUREMENT = unit_of_measurement;
        PRICE_FOR_UNIT = price_for_unit;
    }
}
