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

public class CartesianCoordinate implements Coordinate{
	
	private double x;
	private double y;
	private double z;
	
	static private double EPSILON = 0.00000001; 
	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	@Override
	public CartesianCoordinate asCartesianCoordinate(){
		return this;
	}
	
	@Override
	public double getCartesianDistance(Coordinate target){
		return this.getDistance(target);
	}
	
	@Override
	public SphericCoordinate asSphericCoordinate(){
		//TODO
		return null;
	}
	
	@Override
	public double getSphericDistance(Coordinate target){
		//TODO
		return 0.0;
	}	

	/**
	 * calculates the euclidian distance between this coordinate and the target
	 */
	public double getDistance(Coordinate target) {
		if (null == target) {
			throw new IllegalArgumentException("passed target parameter must not be null");
			//might be another option
			//return Double.POSITIVE_INFINITY;
		}

		if (isEqual(target)) {
			return 0.0;
		}
		
		CartesianCoordinate carTarget = target.asCartesianCoordinate();

		double tmp = 0;
		tmp += Math.pow((carTarget.getX() - this.x), 2.0);
		tmp += Math.pow((carTarget.getY() - this.y), 2.0);
		tmp += Math.pow((carTarget.getZ() - this.z), 2.0);

		return Math.sqrt(tmp);
	}
	
	/**
	 * compares this coordinate values with the target's ones, up to an accuracy of delta
	 * assumptions NaN != NaN, INFINITY != INFINITY, +0.0 == -0.0
	 * @param other
	 * @return
	 */

	public boolean isEqual(Coordinate other) {
		if (null == other) {
			return false;
		}
		if (this == other) {
			return true;
		}
		
		CartesianCoordinate carOther = other.asCartesianCoordinate();
		
		if (Math.abs(this.x - carOther.getX()) <= CartesianCoordinate.EPSILON 
				&& Math.abs(this.y - carOther.getY()) <= CartesianCoordinate.EPSILON
				&& Math.abs(this.z - carOther.getZ()) <= CartesianCoordinate.EPSILON) {
			return true;
		}
		return false;
	}
	
	/**
	 * @methodtype get
	 */
	public double getX() {
		return x;
	}

	/**
	 * @methodtype set
	 */
	public void setX(double x) {
		this.x = x;
	}

	/**
	 * @methodtype get
	 */
	public double getY() {
		return y;
	}

	/**
	 * @methodtype set
	 */
	public void setY(double y) {
		this.y = y;
	}

	/**
	 * @methodtype get
	 */
	public double getZ() {
		return z;
	}

	/**
	 * @methodtype set
	 */
	public void setZ(double z) {
		this.z = z;
	}

	/**
	 * @methodtype set
	 */
	public void setXYZ(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}



	/**
	 * equals forwarded to isEqual
	 */
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
		res = prime*res + Double.hashCode(this.x);
		res = prime*res + Double.hashCode(this.y);
		res = prime*res + Double.hashCode(this.z);
		
		return res;
	}

}
