package org.wahlzeit.model.pizza;

import java.util.HashSet;

import org.wahlzeit.model.Location;
import org.wahlzeit.model.Photo;

public class Pizza {
	public enum PizzaSize{SMALL, MEDIUM, LARGE};
	public enum PizzaShape{CIRCULAR, RECTANGULAR};
	
	private PizzaSize size;
	private PizzaShape shape;
	
	private HashSet<Photo> photos = new HashSet<Photo>();
	private PizzaType type = null;
	private Location birthOvenLocation;
	
	
	protected Pizza() {
		setType(new PizzaType("Magherita"));
		this.birthOvenLocation = new Location();
		this.setSize(PizzaSize.SMALL);
		this.setShape(PizzaShape.CIRCULAR);
		
	}
	
	protected Pizza(PizzaType type) {
		super();
		if(null != type) {
			this.setType(type);
		}
	}
	
	protected Pizza(PizzaType type, Location location) {
		this(type);
		this.birthOvenLocation = location;
	}
	
	protected Pizza(PizzaType type, Location location, PizzaSize size, PizzaShape shape) {
		this(type, location);
		this.setSize(size);
		this.setShape(shape);
	}
	
	public Location getLocation() {
		return this.birthOvenLocation;
	}
	
	public void setLocation(Location location) {
		this.birthOvenLocation = location;
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
	
	public void addPizzaPhotoToPizza(Photo photo) {
		this.photos.add(photo);
	}
	
	public void removePhoto(Photo photo) {
		this.photos.remove(photo);
	}
	
	public HashSet<Photo> getPhotos(){
		return this.photos;
	}

	public PizzaType getType() {
		return type;
	}

	public void setType(PizzaType type) {
		this.type = type;
	}
}