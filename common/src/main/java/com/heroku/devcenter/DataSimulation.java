package com.heroku.devcenter;

import java.io.Serializable;
import java.security.SecureRandom;

/**
 * A model class for a big, imaginary, expensive operation
 * that a user submits via the web, but is processed async
 * by a worker.
 */
public class DataSimulation implements Serializable {

    public DeviceEnum getDeviceEnum() {
        return deviceEnum;
    }

    public double getTariff() {
        return tariffEnum;
    }

    public User getUser() {
        return user;
    }

    public long getTime() {
        return time;
    }


    private DeviceEnum deviceEnum ;
    private WeatherEnum weatherEnum;
    private double tariffEnum;
    private User user;
    private long time;
    private static final SecureRandom random = new SecureRandom();

    public static <T extends Enum<?>> T randomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }

    public DataSimulation() {
        deviceEnum =randomEnum(DeviceEnum.class);
        tariffEnum= 7.0 + random.nextDouble() * 8.0;
        weatherEnum=randomEnum(WeatherEnum.class);;
        user=randomEnum(User.class);;
        time=System.currentTimeMillis() / 1000L;
    }

    @Override
    public String toString() {
        return "DataSimulation{" +
                "device='" + getDeviceEnum().getDeviceName() + '\'' +
                "tarrif='" + getTariff() + '\'' +
                "user='" + getUser().getId() + '\'' + '\''+
                "weather"+ getWeatherEnum().getSun()+ '\''+
                "time='" + getTime() + "'}";
    }

    public WeatherEnum getWeatherEnum() {
        return weatherEnum;
    }
}
