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

import java.util.HashSet;
import java.util.Iterator;

public class SphericCoordinate extends AbstractCoordinate{
	private static HashSet<SphericCoordinate> existingCoordinates = new HashSet<SphericCoordinate>();
	
	String className = this.getClass().getName();

	// r >= 0, longitude in [0°, 360°), latitude [0°, 180°]

	private double radius;
	private double longitude;
	private double latitude;

	public static SphericCoordinate createSphericCoordinate() {
		return SphericCoordinate.createSphericCoordinate(0.0, 0.0, 0.0);
	}
	
	public static SphericCoordinate createSphericCoordinate(double x, double y, double z) {
		SphericCoordinate tmp = new SphericCoordinate(x, y, z);
		
		if(!existingCoordinates.add(tmp)) {
			Iterator<SphericCoordinate> iter = existingCoordinates.iterator();

			while(iter.hasNext()){
				Coordinate c = iter.next();
				if(c.equals(tmp)) {
					return c.asSphericCoordinate();
				}
			}
			
		}
		
		return tmp;
	}

	private SphericCoordinate(double radius, double longitude, double latitude) {
		this.radius = radius;
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

	public CartesianCoordinate asCartesianCoordinate() throws CoordinateException{
		assertClassInvariatns();

		double x = this.radius * Math.sin(latitude) * Math.cos(longitude);
		double y = this.radius * Math.sin(latitude) * Math.sin(longitude);
		double z = this.radius * Math.cos(latitude);

		assertClassInvariatns();
		
		return CartesianCoordinate.createCartesianCoordinate(x, y, z);
	}

	public SphericCoordinate asSphericCoordinate() throws CoordinateException{
		assertClassInvariatns();

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

	public double getLatitude() {
		return latitude;
	}

	public SphericCoordinate setLatitude(double latitude) {
		String error = "In " + className + "pre condition violation: ";
		if(!isValidLatitude(latitude)){
			throw new CoordinateException(error + "the received latitude was " + latitude + " must be a finite double within [0.0, 180.0)");
		}
		assertClassInvariatns();

		return createSphericCoordinate(this.radius, this.longitude, latitude);
	}

	public double getLongitude() {
		return longitude;
	}

	public SphericCoordinate setLongitude(double longitude) {
		String error = "In " + className + "pre condition violation: ";
		if(!isValidLongitude(longitude)){
			throw new CoordinateException(error + "the received longitude was: " + longitude + " must be a finite double within [0.0, 360.0)");
		}
		assertClassInvariatns();

		return createSphericCoordinate(this.radius, longitude, this.latitude);
	}

	public double getRadius() {
		return radius;
	}

	public SphericCoordinate setRadius(double radius) {
		String error = "In " + className + "pre condition violation: ";
		if(!isValidRadius(radius)) {
			throw new IllegalArgumentException(error + "the received radius was: " + radius + " but must be a finite double >= 0.0");
		}
		assertClassInvariatns();

		if (radius < Coordinate.EPSILON) {
			return createSphericCoordinate();
		}
		
		return createSphericCoordinate(radius, this.longitude, this.latitude);
	}

	@Override
	public String toString() {
		return "Radius: " + this.radius + " Longitutde: " + this.longitude + " Latitude: " + this.latitude;
	}
	
	void assertClassInvariatns(){
		String error = "In " + className + "class invariant violation: ";
		if(!isValidRadius(this.radius)) {
			throw new CoordinateException(error + "the raius must be a finite double >= 0.0");
		}
		if(!isValidLongitude(this.longitude)){
			throw new CoordinateException(error + "the longitude must be a finite double within [0.0, 360.0)");
		}
		if(!isValidLatitude(this.latitude)){
			throw new CoordinateException(error + "the latitude must be a finite double within [0.0, 180.0)");
		}
	}
	 
	private boolean isValidLongitude(double l) {
		return(Double.isFinite(l) && l >= 0.0 && l < 360.0);
	}

	private boolean isValidLatitude(double l) {
		return(Double.isFinite(l) && l  >= 0.0 && l <= 180.0);
	}
	
	private boolean isValidRadius(double r) {
		return (Double.isFinite(r) && r >= 0.0);
	}

}
