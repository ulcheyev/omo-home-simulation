package cvut.omo.app_utils;


import com.google.common.collect.ImmutableList;

/**
 * Class contains constants for using in application.
 */
public class Constants {

    public final static int DAY_IN_HOUR = 24;
    public final static char CURRENCY = '$';

    public final static double COMPUTER_RUN_STATE_ELECTRICITY_CONSUMPTION = 250;
    public final static double TV_ELECTRICITY_RUN_STATE_CONSUMPTION = 166;
    public final static double SENSOR_ELECTRICITY_RUN_STATE_CONSUMPTION = 101.2;
    public final static double OVEN_ELECTRICITY_RUN_STATE_CONSUMPTION = 160.3;
    public final static double WASHING_MACHINE_RUN_STATE_ELECTRICITY_CONSUMPTION = 124.2;
    public final static double WASHING_MACHINE_RUN_STATE_WATER_CONSUMPTION = 55.7;
    public final static double FRIDGE_RUN_ELECTRICITY_CONSUMPTION = 201.2;


    public final static double COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION = 50;
    public final static double TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION = 5.3;
    public final static double SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION = 55.6;
    public final static double OVEN_ELECTRICITY_IDDLE_STATE_CONSUMPTION = 2.1;
    public final static double WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION = 24.1;
    public final static double WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION = 24.7;
    public final static double FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION = 103;


    public final static double COMPUTER_LIFE_TIME = 15;
    public final static double FRIDGE_LIFE_TIME = 20;
    public final static double TV_LIFE_TIME = 10;
    public final static double SENSOR_LIFE_TIME = 2;
    public final static double CIRCUIT_BREAKER_LIFE_TIME = 25;
    public final static double OVEN_LIFE_TIME = 10;
    public final static double WASHING_MACHINE_LIFE_TIME = 10;

    public final static double DEVICE_OFF_STATE_ELECTRICITY = 0.1;
    public final static double DEVICE_OFF_STATE_WATER = 0;

    public final static double DEVICE_BROKEN_STATE = 0;
    public final static double DEVICE_DOES_NOT_CONSUME = 0;

    public final static String CONSUMPTION_REPORT_HEADER = "******************\nCONSUMPTION REPORT\n******************\n\n";
    public final static String HOME_CONFIG_REPORT_HEADER = "*************************\nHOME CONFIGURATION REPORT\n*************************\n\n";
    public final static String EVENT_REPORT_HEADER = "*************************\nEVENTS REPORT\n*************************\n\n";
    public final static String ACTIVITY_REPORT_HEADER = "*************************\nACTIVITY REPORT\n*************************\n\n";

    public final static String PDF_DOCUMENTATION_HEADER = "********************\nDOCUMENTATION\n********************\n\n";

    public final static String STARS_UP = "\n\n************************************************************************************\n";
    public final static String STARS = "\n************************************************************************************\n";
    public final static String STARS_DOWN = "************************************************************************************";


    //FLAGS
    public final static String ON = "ON";
    public final static String OFF = "OFF";
    public final static String RUN = "RUN";
    public final static String REPAIR = "REPAIR";
    public final static String PAUSE = "PAUSE";
    public final static String BREAK = "BREAK";
    public final static ImmutableList<String> flags = ImmutableList.of(ON, OFF, RUN, REPAIR, PAUSE, BREAK);



}
