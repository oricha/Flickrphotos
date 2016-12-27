package com.example;


public class Geolocation {
	
	private final String id_photo;
    
    private float longitude;
    private float latitude;
    private int accuracy;

    public Geolocation(String id) {
        this.id_photo = id;
    }

    public Geolocation(String id, float longitude, float latitude, int accuracy) {
    	this.id_photo = id;
    	this.longitude = longitude;
    	this.latitude = latitude;
    	this.accuracy = accuracy;
	}

	public String getId() {
        return id_photo;
    }

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public int getAccuracy() {
		return accuracy;
	}

	public void setAccuracy(int accuracy) {
		this.accuracy = accuracy;
	}

}
