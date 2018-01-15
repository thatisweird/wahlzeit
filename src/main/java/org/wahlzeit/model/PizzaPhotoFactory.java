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

import java.util.logging.Logger;

import org.wahlzeit.model.pizza.Pizza;
import org.wahlzeit.services.LogBuilder;
import org.wahlzeit.utils.DesignPattern;

@DesignPattern(patternName = "Abstract Factory", participants = {"Concrete Factory"})
public class PizzaPhotoFactory extends PhotoFactory{

	private static final Logger log = Logger.getLogger(PizzaPhotoFactory.class.getName());
	
	private static PizzaPhotoFactory instance = null;
	
	
	/**
	 *
	 */
	protected PizzaPhotoFactory() {
		// do nothing
	}
	
	/**
	 * Public singleton access method.
	 */
	public static synchronized PizzaPhotoFactory getInstance() {
		if(null == instance) {
			log.config(LogBuilder.createSystemMessage().addAction("setting generic PizzaPhotoFactory").toString());
			setInstance(new PizzaPhotoFactory());
		}
		return instance;
	}
		
	/**
	 * Method to set the singleton instance of PhotoFactory.
	 */
	protected static synchronized void setInstance(PizzaPhotoFactory pizzaPhotoFactory) {
		if (null != instance) {
			throw new IllegalStateException("attempt to initalize PizzaPhotoFactory twice");
		}

		instance = pizzaPhotoFactory;
	}
	

	/**
	 * @methodtype factory
	 */
	public PizzaPhoto createPhoto() {
		return new PizzaPhoto();
	}
	

	/**
	 * Creates a new photo with the specified id
	 */
	public PizzaPhoto createPhoto(PhotoId id) {
		return new PizzaPhoto(id);
	}

	/**
	 * Creates a new photo with the specified id and location
	 */
	public PizzaPhoto createPhoto(PhotoId id, Location loc) {
		return new PizzaPhoto(id, loc);
	}

	
	/**
	 * @methodtype factory, with pizza
	 */
	public PizzaPhoto createPhoto(Pizza pizza) {
		return new PizzaPhoto(pizza);
	}

	/**
	 * Creates a new photo with the specified id and pizza
	 */
	public PizzaPhoto createPhoto(PhotoId id, Pizza pizza) {
		return new PizzaPhoto(id, pizza);
	}

	/**
	 * Creates a new photo with the specified id, location and pizza
	 */
	public PizzaPhoto createPhoto(PhotoId id, Location loc, Pizza pizza) {
		return new PizzaPhoto(id, loc, pizza);
	}
	
	
	
}
