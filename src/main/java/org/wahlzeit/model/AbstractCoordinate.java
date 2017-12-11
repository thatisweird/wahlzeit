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

public abstract class AbstractCoordinate implements Coordinate {

	/*
	 * calculates the euclidean distance between this Coordinate and the given target Coordinate 
	 */
	public double getCartesianDistance(Coordinate target) throws IllegalArgumentException, CoordinateException{
	
		assertIsNotNullArgument(target);
		assertClassInvariatns();

		if (this.isEqual(target)) {
			return 0.0;
		}

		CartesianCoordinate carStart = this.asCartesianCoordinate();
		CartesianCoordinate carTarget = target.asCartesianCoordinate();

		double res = 0;
		res += Math.pow((carTarget.getX() - carStart.getX()), 2.0);
		res += Math.pow((carTarget.getY() - carStart.getY()), 2.0);
		res += Math.pow((carTarget.getZ() - carStart.getZ()), 2.0);
		
		double dist = Math.sqrt(res);
		
		assertIsValidDistance(dist);
		assertClassInvariatns();

		return dist;
	}

	/*
	 * there are various ways to calculate the distance between two spherical
	 * coordinates https://www.movable-type.co.uk/scripts/latlong.html here
	 * haversine is used
	 */
	public double getSphericDistance(Coordinate target) throws IllegalArgumentException, CoordinateException{
	
		assertIsNotNullArgument(target);
		assertClassInvariatns();

		if (this.isEqual(target)) {
			return 0.0;
		}

		SphericCoordinate spherStart = this.asSphericCoordinate();
		SphericCoordinate spherTarget = target.asSphericCoordinate();

		double phi1 = Math.toRadians(spherStart.getLatitude());
		double phi2 = Math.toRadians(spherTarget.getLatitude());
		double deltaPhi = Math.toRadians(spherStart.getLatitude() - spherTarget.getLatitude());
		double deltaLam = Math.toRadians(spherStart.getLongitude() - spherTarget.getLongitude());

		double a = Math.sin(deltaPhi / 2.0) * Math.sin(deltaPhi / 2.0)
				+ Math.cos(phi1) * Math.cos(phi2) * Math.sin(deltaLam / 2.0) * Math.sin(deltaLam / 2.0);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1.0 - a));
		
		double dist = Math.max(spherStart.getRadius(), spherTarget.getRadius()) * c;
		
		assertIsValidDistance(dist);
		assertClassInvariatns();
		
		return dist;
	}

	/*
	 * for all Coordinate implementations getDistance returns the direct distance between two CartesianCoordinates
	 */
	public double getDistance(Coordinate target) throws IllegalArgumentException, CoordinateException{
		return this.getCartesianDistance(target);
	}

	/*
	 * equals forwarded to the more specific isEqual functions
	 */
	@Override
	public boolean equals(Object obj) {
		if (null == obj) {
			return false;
		}
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Coordinate)) {
			return false;
		}

		return isEqual((Coordinate) obj);
	}
	
	protected void assertIsNotNullArgument(Object obj){
			if (null == obj)
				throw new IllegalArgumentException("passed argument must not be null");
	}
	
	protected void assertIsValidDistance(Double dist){
		if (0.0 > dist)
			throw new CoordinateException("The calculated distance should be positive but was:	" + dist);
	}
	
	@Override
	public abstract int hashCode();
	
	abstract void assertClassInvariatns();
}
