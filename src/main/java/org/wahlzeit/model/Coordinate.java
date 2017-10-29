package org.wahlzeit.model;

import java.lang.Math;

public class Coordinate {

	private double x;
	private double y;
	private double z;

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

	public boolean isEqual(Coordinate other) {
		double delta = 0.00000001;
		if (null == other) {
			return false;
		}
		if (this == other) {
			return true;
		}
		if (Math.abs(this.x - other.getX()) <= delta 
				&& Math.abs(this.y - other.getY()) <= delta
				&& Math.abs(this.z - other.getZ()) <= delta) {
			return true;
		}
		return false;
	}

	/**
	 * equals forwarded to isEqual
	 */
	public boolean equals(Coordinate other) {
		return isEqual(other);
	}

}
