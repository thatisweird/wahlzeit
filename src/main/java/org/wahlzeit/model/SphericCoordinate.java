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

public class SphericCoordinate implements Coordinate{
	private double latitude;
	private double longitude;
	private double radius;
	
	public SphericCoordinate(){
		latitude = 0.0;
		longitude = 0.0;
		radius = 0.0;
	}

	@Override
	public CartesianCoordinate asCartesianCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getCartesianDistance(Coordinate target) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public SphericCoordinate asSphericCoordinate() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double getSphericDistance(Coordinate target) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getDistance(Coordinate target) {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public boolean isEqual(Coordinate coord) {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public boolean equals(Object other){
		//TODO
		return false;
	}
	
	@Override
	public int hashCode(){
		//TODO
		return 0;
	}
	
	
	
	public double getLatitude() {
		return latitude;
	}
	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}
	public double getLongitude() {
		return longitude;
	}
	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}
	public double getRadius() {
		return radius;
	}
	public void setRadius(double radius) {
		this.radius = radius;
	}
	
	
	
}
