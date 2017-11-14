package org.wahlzeit.model;

import java.util.logging.Logger;

import org.wahlzeit.model.PizzaPhoto.*;
import org.wahlzeit.services.LogBuilder;

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
	 * Creates a new photo with the specified id and location
	 */
	public PizzaPhoto createPhoto(PhotoId id, Location loc, PizzaSize size, PizzaShape shape) {
		return new PizzaPhoto(id, loc, size, shape);
	}
	
	
}
