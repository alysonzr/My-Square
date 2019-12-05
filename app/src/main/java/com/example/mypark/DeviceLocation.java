package com.example.mypark;

public class DeviceLocation {

	private Number latitudeDevice;
	private Number longitudeDevide;


	public DeviceLocation(Number latitudeDevice, Number longitudeDevide) {
		this.latitudeDevice = latitudeDevice;
		this.longitudeDevide = longitudeDevide;
	}

	public Number getLatitudeDevice() {
		return latitudeDevice;
	}

	public void setLatitudeDevice(Number latitudeDevice) {
		this.latitudeDevice = latitudeDevice;
	}

	public Number getLongitudeDevide() {
		return longitudeDevide;
	}

	public void setLongitudeDevide(Number longitudeDevide) {
		this.longitudeDevide = longitudeDevide;
	}
}
