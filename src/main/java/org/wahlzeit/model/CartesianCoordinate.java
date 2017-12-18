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
import java.util.HashSet;
import java.util.Iterator;

public class CartesianCoordinate extends AbstractCoordinate {
	private static HashSet<CartesianCoordinate>existingCoordinates = new HashSet<CartesianCoordinate>();

	String className = this.getClass().getName();

	private double x;
	private double y;
	private double z;
	
	public static CartesianCoordinate createCartesianCoordinate() {
		return CartesianCoordinate.createCartesianCoordinate(0, 0, 0);
	}
	
	public static CartesianCoordinate createCartesianCoordinate(double x, double y, double z) {
		CartesianCoordinate tmp = new CartesianCoordinate(x, y, z);
		
		if(!existingCoordinates.add(tmp)) {
			Iterator<CartesianCoordinate> iter = existingCoordinates.iterator();

			while(iter.hasNext()){
				Coordinate c = iter.next();
				if(c.equals(tmp)) {
					return c.asCartesianCoordinate();
				}
			}
			
		}
		
		return tmp;
	}

	/**
	 * @methodtype constructor
	 */
	private CartesianCoordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;

		assertClassInvariatns();
	}

	public CartesianCoordinate asCartesianCoordinate() throws CoordinateException{
		assertClassInvariatns();

		return this;
	}

	public SphericCoordinate asSphericCoordinate() throws CoordinateException{
		assertClassInvariatns();

		double r = Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2) + Math.pow(this.z, 2));
		if(r <= EPSILON) {
			return SphericCoordinate.createSphericCoordinate(0, 0, 0);
		}
		double lati = Math.acos(this.z / r);
		double longi = Math.atan2(this.y, this.x);

		assertClassInvariatns();

		return SphericCoordinate.createSphericCoordinate(r, longi, lati);
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
	public CartesianCoordinate setX(double x) {
		String error = "In " + className + "precondition violation: ";
		if(!isValidCoordinateAttribute(x)) {
			throw new IllegalArgumentException(error + "x was: " + x + " but must be finite");
		}
		assertClassInvariatns();

		return createCartesianCoordinate(x, this.y, this.z);
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
	public Coordinate setY(double y) {
		String error = "In " + className + "precondition violation: ";
		if(!isValidCoordinateAttribute(y)) {
			throw new CoordinateException(error + "y was: " + y + " but must be finite");
		}
		assertClassInvariatns();

		return createCartesianCoordinate(this.x, y, this.z);
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
	public CartesianCoordinate setZ(double z) {
		String error = "In " + className + "precondition violation: ";
		if(!isValidCoordinateAttribute(z)) {
			throw new CoordinateException(error + "z was: " + z + " but must be finite");
		}
		assertClassInvariatns();

		return createCartesianCoordinate(this.x, this.y, z);
	}

	@Override
	public String toString() {
		String s = "x: " + this.x + " y: " + this.y + " z: " + this.z;
		return s;
	}

	void assertClassInvariatns(){
		String error = "In " + className + "class invariant violation: ";
		if(!isValidCoordinateAttribute(this.x)) {
			throw new CoordinateException(error + "x was: " + this.x + " but must be finite");
		}
		if(!isValidCoordinateAttribute(this.y)) {
			throw new CoordinateException(error + "y was " + this.y + "but must be finite");
		}
		if(!isValidCoordinateAttribute(this.z)) {
			throw new CoordinateException(error + "z was " + this.z + "but must be finite");
		}
	}
	
	private boolean isValidCoordinateAttribute(Double ca) {
		return Double.isFinite(ca);
	}

}
