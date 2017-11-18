/*
 * Copyright (c) 2017 Paul Nickles Jäkel
 *  *
 * This file is part of the Wahlzeit photo rating application.
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public
 * License along with this program. If not, see
 * <http://www.gnu.org/licenses/>.
 */

package org.wahlzeit.model;

public class SphericCoordinate implements Coordinate {

	// r >= 0, longitude in [0�, 360�), latitude [0�, 180�]

	private double radius;
	private double longitude;
	private double latitude;

	public SphericCoordinate() {
		radius = 0.0;
		longitude = 0.0;
		latitude = 0.0;
	}

	public SphericCoordinate(double radius, double longitude, double latitude) {
		this.radius = Math.abs(radius);
		this.longitude = longitude < 0.0 ? longitude % 360 + 360.0 : longitude % 360;
		this.latitude = latitude < 0.0 ? latitude % 180 + 180.0 : Math.abs(latitude - 180.0) < Coordinate.EPSILON ? 180.0 : latitude % 180;
		

		// special values
		if (this.longitude < Coordinate.EPSILON || Math.abs(this.longitude - 180) < Coordinate.EPSILON) {
			this.latitude = 0.0;
		}

		if (this.radius < Coordinate.EPSILON) {
			this.longitude = this.latitude = 0.0;
		}
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		double x = this.radius * Math.sin(latitude) * Math.cos(longitude);
		double y = this.radius * Math.sin(latitude) * Math.sin(longitude);
		double z = this.radius * Math.cos(latitude);

		return new CartesianCoordinate(x, y, z);
	}

	/*
	 * use the distance calculation in the CartesianCoordinate class
	 */
	@Override
	public double getCartesianDistance(Coordinate target) {
		CartesianCoordinate a = this.asCartesianCoordinate();
		return a.getCartesianDistance(target);
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

	@Override
	public double getSphericDistance(Coordinate target) {
		return this.getDistance(target);
	}
	
	/*
	 * there are various ways to calculate the distance between two spherical coordinates
	 * https://www.movable-type.co.uk/scripts/latlong.html
	 * here haversine is used
	 */

	@Override
	public double getDistance(Coordinate target) {
		if (null == target) {
			throw new IllegalArgumentException("passed target parameter must not be null");
			//might be another option
			//return Double.POSITIVE_INFINITY;
		}

		if (this.isEqual(target)) {
			return 0.0;
		}
		
		SphericCoordinate spherTarget = target.asSphericCoordinate();

		double phi1 = Math.toRadians(this.latitude);
		double phi2 = Math.toRadians(spherTarget.getLatitude());
		double deltaPhi = Math.toRadians(this.latitude - spherTarget.getLatitude()); 
		double deltaLam = Math.toRadians(this.longitude-spherTarget.getLongitude());
		
		double a = Math.sin(deltaPhi/2.0) * Math.sin(deltaPhi/2.0) +
		        Math.cos(phi1) * Math.cos(phi2) *
		        Math.sin(deltaLam/2.0) * Math.sin(deltaLam/2.0);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0-a));

		return Math.max(this.radius, spherTarget.getRadius()) * c;
	}

	@Override
	public boolean isEqual(Coordinate other) {
		if (null == other) {
			return false;
		}
		if (this == other) {
			return true;
		}
		
		SphericCoordinate carOther = other.asSphericCoordinate();
		
		if (Math.abs(this.radius - carOther.getRadius()) <= CartesianCoordinate.EPSILON 
				&& Math.abs(this.longitude - carOther.getLongitude()) <= CartesianCoordinate.EPSILON
				&& Math.abs(this.latitude - carOther.getLatitude()) <= CartesianCoordinate.EPSILON) {
			return true;
		}
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if(!(obj instanceof Coordinate)) {
			return false;
		}
		
		return isEqual((Coordinate) obj);
	}
	
	/**
	 * generates a hash code for a coordinate object
	 * does not address the double rounding error problem, still more accurate than the standard hashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 41;
		int res = 1;
		res = prime*res + Double.hashCode(this.radius);
		res = prime*res + Double.hashCode(this.longitude);
		res = prime*res + Double.hashCode(this.latitude);
		
		return res;
	}


	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude < 0.0 ? latitude % 180 + 180.0 : Math.abs(latitude - 180.0) < Coordinate.EPSILON ? 180.0 : latitude % 180;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude < 0.0 ? longitude % 360 + 360.0 : longitude % 360;
		
		if (this.longitude < Coordinate.EPSILON || Math.abs(this.longitude - 180) < Coordinate.EPSILON) {
			this.latitude = 0.0;
		}
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = Math.abs(radius);
		if (this.radius < Coordinate.EPSILON) {
			this.longitude = this.latitude = 0.0;
		}
	}
	
	public void setRLL(double radius, double longitude, double latitude) {
		this.setRadius(radius);
		this.setLongitude(longitude);
		this.setLatitude(latitude);
	}
	
	@Override
	public String toString() {
		String s = "Radius: " + this.radius + " Longitutde: " + this.longitude + " Latitude: " + this.latitude;
		return s;
	}

}
