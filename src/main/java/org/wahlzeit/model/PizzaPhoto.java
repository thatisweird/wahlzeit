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

import org.wahlzeit.utils.DesignPattern;

import com.googlecode.objectify.annotation.Subclass;

@Subclass
@DesignPattern(patternName = "Abstract Factory", participants = {"Concrete Product"})
public class PizzaPhoto extends Photo{
	public enum PizzaSize{SMALL, MEDIUM, LARGE};
	public enum PizzaShape{CIRCULAR, RECTANGULAR};
	
	private PizzaSize size;
	private PizzaShape shape;

	/**
	 * @methodtype constructor
	 */
	public PizzaPhoto() {
		super();
		this.size = PizzaSize.SMALL;
		this.shape = PizzaShape.CIRCULAR;
	}
		
	/**
	 * @methodtype constructor
	 */
	public PizzaPhoto(PhotoId myId) {
		super(myId);
		this.size = PizzaSize.SMALL;
		this.shape = PizzaShape.CIRCULAR;
	}
	
	/**
	 * @methodtype constructor
	 */
	public PizzaPhoto(PhotoId myId, Location location) throws IllegalArgumentException{
		super(myId, location);

		this.size = PizzaSize.SMALL;
		this.shape = PizzaShape.CIRCULAR;
	}

	/**
	 * @methodtype constructor, uses default attributes for null parameter
	 */
	public PizzaPhoto(PhotoId myId, Location location, PizzaSize size, PizzaShape shape) throws IllegalArgumentException{
		if(null == location) {
			throw new IllegalArgumentException("the given Location must not be null, you might use the default constructor without a location argument");
		}

		if(null == myId) {
			id = PhotoId.getNextId();
		}else {
			id = myId;
		}
		incWriteCount();
		
		this.location = location;
		
		if(null == size) {
			this.size = PizzaSize.SMALL;
		}else {
			this.size = size;
		}
		
		if (null == shape) {
			this.shape = PizzaShape.CIRCULAR;
		}else {
			this.shape = shape;
		}
	}
	

	public PizzaSize getSize() {
		return size;
	}

	public void setSize(PizzaSize size) {
		this.size = size;
	}

	public PizzaShape getShape() {
		return shape;
	}

	public void setShape(PizzaShape shape) {
		this.shape = shape;
	}
	
	
	
}
