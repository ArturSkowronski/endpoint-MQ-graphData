package com.heroku.devcenter;

/**
 * Created with IntelliJ IDEA.
 * User: Artur
 * Date: 9/20/13
 * Time: 12:33 AM
 * To change this template use File | Settings | File Templates.
 */
public enum WeatherEnum {
    Weather_Sun("SUN"),Weather_Cloud("CLOUD"),Weather_Rain("RAIN");

    public String getSun() {
        return sun;
    }

    String sun;
    WeatherEnum(String sun) {
this.sun=sun;
    }


}
