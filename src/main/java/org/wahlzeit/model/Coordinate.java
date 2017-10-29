package org.wahlzeit.model;

import java.lang.Math;

public class Coordinate {

	private double x;
	private double y;
	private double z;

	public Coordinate(){
		x = 0;
		y = 0;
		z = 0;
	}

	public Coordinate(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public double getX(){
		return x;
	}
	
	public double getY(){
		return y;
	}
	
	public double getZ(){
		return z;
	}

	public void setX(double x){
		this.x = x;
	}

	public void setY(double y){
		this.y = y;
	}

	public void setZ(double z){
		this.z = z;
	}

	public double getDistance(Coordinate target){
		//TODO
		double tmp = Math.pow((this.getX() - target.getX()), 2.0);
		tmp += Math.pow((this.getY() - target.getY()), 2.0);
		tmp += Math.pow((this.getZ() - target.getZ()), 2.0);
		
		return Math.sqrt(tmp);
	}
	
	public boolean isEqual(Coordinate other){
		if(null == other){
			return false;
		}
		if(this == other){
			return true;
		}
		if(this.x == other.getX() && this.y == other.getY() && this.z == other.getZ()){
			return 
		
		return false;
	}
	//TODO
	public boolean equals(Object other){
		return isEquals(other);
	}
	
}
