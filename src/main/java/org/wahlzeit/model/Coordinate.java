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


interface Coordinate {
	static final int PLACES = 8;
	static final double EPSILON = 1.0/Math.pow(10.0, PLACES); 

	public CartesianCoordinate asCartesianCoordinate() throws CoordinateException;
	public double getCartesianDistance(Coordinate target) throws IllegalArgumentException, CoordinateException;
	public SphericCoordinate asSphericCoordinate() throws CoordinateException;
	public double getSphericDistance(Coordinate target) throws IllegalArgumentException, CoordinateException;
	public double getDistance(Coordinate target) throws IllegalArgumentException, CoordinateException;
	public boolean isEqual(Coordinate coord);
}	