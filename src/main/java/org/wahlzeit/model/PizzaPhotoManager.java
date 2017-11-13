package org.wahlzeit.model;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;


public class PizzaPhotoManager extends PhotoManager{
	private static final Logger log = Logger.getLogger(PizzaPhotoManager.class.getName());
	
	/*
	 * this did not work very well
	protected static final PizzaPhotoManager instance = new PizzaPhotoManager();
	protected Map<PhotoId, PizzaPhoto> photoCache = new HashMap<PhotoId, PizzaPhoto>(); 
	 */


	public PizzaPhotoManager() {
		photoTagCollector = PizzaPhotoFactory.getInstance().createPhotoTagCollector();
	}
	
	
	public static PizzaPhotoManager getInstance() {
		return instance;
	}
	
public Photo getPhotoFromId(PhotoId id) {
		if (id == null) {
			return null;
		}

		Photo result = doGetPhotoFromId(id);

		if (result == null) {
			result = PizzaPhotoFactory.getInstance().loadPhoto(id);
			if (result != null) {
				doAddPhoto(result);
			}
		}

		return result;
	}
}
