package org.wahlzeit.model;

public class Location {
	public Coordinate coordinate;

	/**
	 * @methodtype constructor
	 */
	public Location(){
		this.coordinate = CartesianCoordinate.createCartesianCoordinate();
	}
	
	/**
	 * @methodtype constructor
	 */
	public Location(Coordinate coordinate) throws IllegalArgumentException{
		if(null == coordinate) {
			throw new IllegalArgumentException("the given Coordinate must not be null, you might use the default constructor without arguments");
		}
		this.coordinate = coordinate;
	}

	/**
	 * @methodtype get
	 */
	public Coordinate getCoordinate(){
		return coordinate;
	}

	/**
	 * @methodtype set
	 */
	public void setCoordinate(Coordinate coordinate) throws IllegalArgumentException{
		if (null == coordinate) {
			throw new IllegalArgumentException("passed coordinate parameter must not be null");
		}
		this.coordinate = coordinate;
	}
}
