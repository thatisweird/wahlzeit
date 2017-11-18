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
	static final double EPSILON = 0.00000001; 

	public CartesianCoordinate asCartesianCoordinate();
	public double getCartesianDistance(Coordinate target);
	public SphericCoordinate asSphericCoordinate();
	public double getSphericDistance(Coordinate target);
	public double getDistance(Coordinate target);
	public boolean isEqual(Coordinate coord);
}