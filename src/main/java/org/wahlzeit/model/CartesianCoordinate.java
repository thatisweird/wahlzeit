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

import java.lang.Math;

public class CartesianCoordinate extends AbstractCoordinate {

	private double x;
	private double y;
	private double z;

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate() {
		x = 0.0;
		y = 0.0;
		z = 0.0;

		assertClassInvariatns();
	}

	/**
	 * @methodtype constructor
	 */
	public CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariatns();
	}

	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	public SphericCoordinate asSphericCoordinate() {
		assertClassInvariatns();

		double r = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
		if(r <= EPSILON) {
			return new SphericCoordinate(0, 0, 0);
		}
		double lati = Math.acos(this.z / r);
		double longi = Math.atan2(this.y, this.x);

		assertClassInvariatns();

		return new SphericCoordinate(r, longi, lati);
	}

	/**
	 * compares this coordinate values with the target's ones, up to an accuracy of
	 * delta assumptions NaN != NaN, INFINITY != INFINITY, +0.0 == -0.0
	 * 
	 * @param other
	 * @return
	 */
	public boolean isEqual(Coordinate other) {
		assertClassInvariatns();
	
		if (null == other) {
			return false;
		}
		if (this == other) {
			return true;
		}

		CartesianCoordinate carOther = other.asCartesianCoordinate();

		boolean retval = false;
		if (Math.abs(this.x - carOther.getX()) <= CartesianCoordinate.EPSILON
				&& Math.abs(this.y - carOther.getY()) <= CartesianCoordinate.EPSILON
				&& Math.abs(this.z - carOther.getZ()) <= CartesianCoordinate.EPSILON) {
			retval = true;
		}

		assertClassInvariatns();
		return retval;
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
		assertClassInvariatns();

		this.x = x;

		assertClassInvariatns();
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
		assertClassInvariatns();

		this.y = y;

		assertClassInvariatns();
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
		assertClassInvariatns();

		this.z = z;

		assertClassInvariatns();
	}

	/**
	 * @methodtype set
	 */
	public void setXYZ(double x, double y, double z) {
		
		assertClassInvariatns();

		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariatns();
	}

	/**
	 * generates a hash code for a coordinate object does not address the double
	 * rounding error problem, still more accurate than the standard hashCode method
	 */
	@Override
	public int hashCode() {
		assertClassInvariatns();

		final int prime = 41;
		int res = 1;
		res = prime * res + Double.hashCode(this.x);
		res = prime * res + Double.hashCode(this.y);
		res = prime * res + Double.hashCode(this.z);

		assertClassInvariatns();

		return res;
	}

	@Override
	public String toString() {
		String s = "x: " + this.x + " y: " + this.y + " z: " + this.z;
		return s;
	}

	private void assertClassInvariatns(){
		String error = "CartesianCoordiante class invariant violation: ";
		assert !Double.isNaN(this.x) : error + "x must not be NaN";
		assert !Double.isNaN(this.y) : error + "y must not be NaN";
		assert !Double.isNaN(this.z) : error + "z must not be NaN";
	}

}
