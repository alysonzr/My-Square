package com.example.mypark;

public class DeviceLocation {

	private double latitudeDevice;
	private double longitudeDevide;


	public DeviceLocation(double latitudeDevice, double longitudeDevide) {
		this.latitudeDevice = latitudeDevice;
		this.longitudeDevide = longitudeDevide;
	}

	public Number getLatitudeDevice() {
		return latitudeDevice;
	}

	public void setLatitudeDevice(double latitudeDevice) {
		this.latitudeDevice = latitudeDevice;
	}

	public Number getLongitudeDevide() {
		return longitudeDevide;
	}

	public void setLongitudeDevide(double longitudeDevide) {
		this.longitudeDevide = longitudeDevide;
	}
}
