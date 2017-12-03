/*
 * Copyright (c) 2017 Paul Nickles JÃ¤kel
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

public class SphericCoordinate extends AbstractCoordinate{

	// r >= 0, longitude in [0°, 360°), latitude [0°, 180°]

	private double radius;
	private double longitude;
	private double latitude;

	public SphericCoordinate() {
		radius = 0.0;
		longitude = 0.0;
		latitude = 0.0;

		assertClassInvariatns();
	}

	public SphericCoordinate(double radius, double longitude, double latitude) {
		this.radius = Math.abs(radius);
		this.longitude = longitude < 0.0 ? longitude % 360 + 360.0 : longitude % 360;
		this.latitude = latitude < 0.0 ? latitude % 180 + 180.0
				: Math.abs(latitude - 180.0) < Coordinate.EPSILON ? 180.0 : latitude % 180;

		// special values
		if (this.longitude < Coordinate.EPSILON || Math.abs(this.longitude - 180) < Coordinate.EPSILON) {
			this.latitude = 0.0;
		}

		if (this.radius < Coordinate.EPSILON) {
			this.longitude = this.latitude = 0.0;
		}
		
		assertClassInvariatns();
	}

	public CartesianCoordinate asCartesianCoordinate() {
		assertClassInvariatns();

		double x = this.radius * Math.sin(latitude) * Math.cos(longitude);
		double y = this.radius * Math.sin(latitude) * Math.sin(longitude);
		double z = this.radius * Math.cos(latitude);

		assertClassInvariatns();
		
		return new CartesianCoordinate(x, y, z);
	}

	public SphericCoordinate asSphericCoordinate() {
		return this;
	}

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

	/**
	 * generates a hash code for a coordinate object does not address the double
	 * rounding error problem, still more accurate than the standard hashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 41;
		int res = 1;
		res = prime * res + Double.hashCode(this.radius);
		res = prime * res + Double.hashCode(this.longitude);
		res = prime * res + Double.hashCode(this.latitude);

		return res;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		assertClassInvariatns();

		this.latitude = latitude < 0.0 ? latitude % 180 + 180.0
				: Math.abs(latitude - 180.0) < Coordinate.EPSILON ? 180.0 : latitude % 180;

		assertClassInvariatns();
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		assertClassInvariatns();

		this.longitude = longitude < 0.0 ? longitude % 360 + 360.0 : longitude % 360;

		if (this.longitude < Coordinate.EPSILON || Math.abs(this.longitude - 180) < Coordinate.EPSILON) {
			this.latitude = 0.0;
		}

		assertClassInvariatns();
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		assertClassInvariatns();

		this.radius = Math.abs(radius);
		if (this.radius < Coordinate.EPSILON) {
			this.longitude = this.latitude = 0.0;
		}
		
		assertClassInvariatns();
	}

	public void setRLL(double radius, double longitude, double latitude) {
		assertClassInvariatns();
		
		this.setRadius(radius);
		this.setLongitude(longitude);
		this.setLatitude(latitude);

		assertClassInvariatns();
	}

	@Override
	public String toString() {
		return "Radius: " + this.radius + " Longitutde: " + this.longitude + " Latitude: " + this.latitude;
	}
	
	private void assertClassInvariatns(){
		String error = "SphericCoordiante class invariant violation: ";
		assert this.radius >= 0.0 : error + "radius must be >= 0";
		assert this.longitude >= 0.0 && this.longitude < 360.0 : error + "longitude must be within [0.0, 360.0)";
		assert this.latitude  >= 0.0 && this.latitude <= 180.0 : error + "latitude must be within [0.0, 180.0";

		assert !Double.isNaN(radius) : error + "radius must not be NaN";
		assert !Double.isNaN(longitude) : error + "latitude must not be NaN";
		assert !Double.isNaN(latitude) : error + "longitude must not be NaN";
	}

}
