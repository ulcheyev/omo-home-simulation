package cvut.omo.app_utils;


import com.google.common.collect.ImmutableList;

import java.util.List;

/**
 *
 */

public class Constants {

    public final static int DAY_IN_HOUR = 24;
    public final static char CURRENCY = '$';

    public final static double COMPUTER_RUN_STATE_ELECTRICITY_CONSUMPTION = 8;
    public final static double TV_ELECTRICITY_RUN_STATE_CONSUMPTION = 7;
    public final static double SENSOR_ELECTRICITY_RUN_STATE_CONSUMPTION = 1.2;
    public final static double OVEN_ELECTRICITY_RUN_STATE_CONSUMPTION = 11;
    public final static double WASHING_MACHINE_RUN_STATE_ELECTRICITY_CONSUMPTION = 7.5;
    public final static double WASHING_MACHINE_RUN_STATE_WATER_CONSUMPTION = 2.5;
    public final static double FRIDGE_RUN_ELECTRICITY_CONSUMPTION = 7;

    public final static double COMPUTER_IDDLE_STATE_ELECTRICITY_CONSUMPTION = 3;
    public final static double TV_ELECTRICITY_IDDLE_STATE_CONSUMPTION = 2;
    public final static double SENSOR_ELECTRICITY_IDDLE_STATE_CONSUMPTION = 0.2;
    public final static double OVEN_ELECTRICITY_IDDLE_STATE_CONSUMPTION = 2;
    public final static double WASHING_MACHINE_IDDLE_STATE_ELECTRICITY_CONSUMPTION = 1;
    public final static double WASHING_MACHINE_IDDLE_STATE_WATER_CONSUMPTION = 0;
    public final static double FRIDGE_IDDLE_STATE_ELECTRICITY_CONSUMPTION = 1.3;

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
