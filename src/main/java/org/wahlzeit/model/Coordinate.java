package org.wahlzeit.model;

import java.lang.Math;

public class Coordinate {

	private double x;
	private double y;
	private double z;
	
	static private double delta = 0.00000001;

	/**
	 * @methodtype constructor
	 */
	public Coordinate() {
		x = 0.0;
		y = 0.0;
		z = 0.0;
	}

	/**
	 * @methodtype constructor
	 */
	public Coordinate(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
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
	 * calculates the euclidian distance between this coordinate and the target
	 */
	public double getDistance(Coordinate target) {
		if (null == target) {
			throw new IllegalArgumentException("passed target parameter must not be null");
		}
		if (isEqual(target)) {
			return 0.0;
		}

		double tmp = 0;
		tmp += Math.pow((target.getX() - this.x), 2.0);
		tmp += Math.pow((target.getY() - this.y), 2.0);
		tmp += Math.pow((target.getZ() - this.z), 2.0);

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
		if (Math.abs(this.x - other.getX()) <= Coordinate.delta 
				&& Math.abs(this.y - other.getY()) <= Coordinate.delta
				&& Math.abs(this.z - other.getZ()) <= Coordinate.delta) {
			return true;
		}
		return false;
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
