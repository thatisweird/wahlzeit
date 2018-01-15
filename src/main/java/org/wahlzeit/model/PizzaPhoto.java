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

import org.wahlzeit.model.pizza.Pizza;
import org.wahlzeit.utils.DesignPattern;

import com.googlecode.objectify.annotation.Serialize;
import com.googlecode.objectify.annotation.Subclass;

@Subclass
@DesignPattern(patternName = "Abstract Factory", participants = {"Concrete Product"})
public class PizzaPhoto extends Photo{
	
	@Serialize
	private Pizza pizza = null;

	/**
	 * @methodtype constructor
	 */
	public PizzaPhoto() {
		super();
	}

	/**
	 * @methodtype constructor
	 */
	public PizzaPhoto(PhotoId myId) {
		super(myId);
	}

	/**
	 * @methodtype constructor, uses default attributes for null parameter
	 */
	public PizzaPhoto(PhotoId myId, Location location) throws IllegalArgumentException{
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
	}

	/**
	 * @methodtype constructor
	 */
	public PizzaPhoto(Pizza pizza) {
		super();
		this.pizza = pizza;
		pizza.addPizzaPhotoToPizza(this);
	}
		
	/**
	 * @methodtype constructor
	 */
	public PizzaPhoto(PhotoId myId, Pizza pizza) {
		super(myId);
		this.pizza = pizza;
		pizza.addPizzaPhotoToPizza(this);
	}

	
	/**
	 * @methodtype constructor, uses default attributes for null parameter
	 */
	public PizzaPhoto(PhotoId myId, Location location, Pizza pizza) throws IllegalArgumentException{
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
		
		this.pizza = pizza;
		pizza.addPizzaPhotoToPizza(this);
	}
	
	public Pizza getPizza() {
		return this.pizza;
	}
	
	public void setPizza(Pizza pizza) {
		if(null == pizza) {
			removePizza();
			return;
		}

		this.pizza = pizza;
		pizza.addPizzaPhotoToPizza(this);
	}
	
	public void removePizza() {
		this.pizza.removePhoto(this);
		this.pizza = null;
	}

}
