package com.vistar.media.model;

/**
 * @author Rohan Tigadi
 */

public class GeoLocation {
    private double latitude, longitude;

    public GeoLocation(double p, double q) {
	latitude = p;
	longitude = q;
    }

    public double getLatitude() {
	return latitude;
    }

    public double getLongitude() {
	return longitude;
    }

    @Override
    public String toString() {
	return "GeoLocation [latitude=" + latitude + ", longitude=" + longitude + "]";
    }
}
