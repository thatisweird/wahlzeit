package org.wahlzeit.model;

public class PizzaPhoto extends Photo{
	public enum PizzaSize{SMALL, MEDIUM, LARGE};
	public enum PizzaShape{CIRCULAR, RECTANGULAR};
	
	public PizzaSize size;
	public PizzaShape shape;

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
	public PizzaPhoto(PhotoId myId, Location location) {
		super(myId, location);
		this.size = PizzaSize.SMALL;
		this.shape = PizzaShape.CIRCULAR;
	}

	/**
	 * @methodtype constructor, uses default attributes for null parameter
	 */
	public PizzaPhoto(PhotoId myId, Location loaction, PizzaSize size, PizzaShape shape) {
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
