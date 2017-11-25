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
	public CartesianCoordinate asCartesianCoordinate() {
		return this;
	}

	@Override
	public SphericCoordinate asSphericCoordinate() {
		double r = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
		double lati = Math.acos(this.z / r);
		double longi = Math.atan2(this.y, this.x);
		return new SphericCoordinate(r, longi, lati);
	}

	/**
	 * compares this coordinate values with the target's ones, up to an accuracy of
	 * delta assumptions NaN != NaN, INFINITY != INFINITY, +0.0 == -0.0
	 * 
	 * @param other
	 * @return
	 */
	@Override
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
	 * generates a hash code for a coordinate object does not address the double
	 * rounding error problem, still more accurate than the standard hashCode method
	 */
	@Override
	public int hashCode() {
		final int prime = 41;
		int res = 1;
		res = prime * res + Double.hashCode(this.x);
		res = prime * res + Double.hashCode(this.y);
		res = prime * res + Double.hashCode(this.z);

		return res;
	}

	@Override
	public String toString() {
		String s = "x: " + this.x + " y: " + this.y + " z: " + this.z;
		return s;
	}

}
