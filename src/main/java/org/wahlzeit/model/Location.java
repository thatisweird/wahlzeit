package org.wahlzeit.model;

public class Location {
	public Coordinate coordinate;

	public Location(){
		coordinate = new Coordinate();
	}
	
	public Location(Coordinate coordinate){
		this.coordinate = coordinate;
	}

	public Coordinate getCoordinate(){
		return coordinate;
	}

	public void setCoordinate(Coordinate coordinate){
		this.coordinate = coordinate;
	}
}
