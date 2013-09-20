package com.heroku.devcenter;

/**
 * Created with IntelliJ IDEA.
 * User: Artur
 * Date: 9/20/13
 * Time: 12:10 AM
 * To change this template use File | Settings | File Templates.
 */
public enum DeviceEnum {
  DEVICE_1("Device1"),DEVICE_2("Device2"),DEVICE_3("Device3");

    public String getDeviceName() {
        return device;
    }

    private final String device;
    private DeviceEnum(String device) {
        this.device=device;

    }
}
