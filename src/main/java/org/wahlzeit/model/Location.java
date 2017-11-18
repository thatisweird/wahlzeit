package org.wahlzeit.model;

public class Location {
	public Coordinate coordinate;

	/**
	 * @methodtype constructor
	 */
	public Location(){
		coordinate = new CartesianCoordinate();
	}
	
	/**
	 * @methodtype constructor
	 */
	public Location(Coordinate coordinate){
		this.coordinate = coordinate;
		if(null == this.coordinate) {
			this.coordinate = new CartesianCoordinate();
		}
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
	public void setCoordinate(Coordinate coordinate){
		if (null == coordinate) {
			throw new IllegalArgumentException("passed coordinate parameter must not be null");
		}
		this.coordinate = coordinate;
	}
}
