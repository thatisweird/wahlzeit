package org.wahlzeit.model.pizza;

import java.util.HashSet;
import java.util.Iterator;

import org.wahlzeit.model.Location;
import org.wahlzeit.model.pizza.Pizza.PizzaShape;
import org.wahlzeit.model.pizza.Pizza.PizzaSize;

public class PizzaType {

	private PizzaType superType;
	protected HashSet<PizzaType> subTypes = new HashSet<PizzaType>();

	private String name;
	private HashSet<Pizza> instances = new HashSet<Pizza>(); 
	
	public PizzaType(String name) {
		this.name = name;
	}

	public PizzaType(String name, PizzaManager pm) {
		this.name = name;
	}
	
	public String getName() {
		return this.name;
	}
	
	public Pizza createInstance() {
		return new Pizza(this);
	}
	
	public Pizza createInstance(Location location) {
		return new Pizza(this, location);
	}
	
	public Pizza createInstance(Location location, PizzaSize size, PizzaShape shape) {
		return new Pizza(this, location, size, shape);
	}
	
	public PizzaType getSuperType() {
		return this.superType;
	}
	
	public void setSuperType(PizzaType pt) {
		this.superType = pt;
	}

	public void addSubType(PizzaType pt) {
		assert (pt != null) : "null is an invalid sub-type";
		pt.setSuperType(this);
		subTypes.add(pt);
	}
	
	public Iterator<PizzaType> getSubtypeIterator(){
		return this.subTypes.iterator();
	}
	
	public boolean hasInstance(Pizza pizza) {
	    assert (pizza != null) : "asked about null object";
	    if (pizza.getType() == this) {
	      return true;
	    }
	    for (PizzaType type : subTypes) {
	      if (type.hasInstance(pizza)) {
	        return true;
	      }
	    }
	    return false;
	}
	
	/*
	 * checks is this type is a subclass
	 */
	public boolean isSubtype() {
		return null != this.getSuperType();
	}
	
	/*
	 * checks if this type is a subclass of the given type
	 */
	public boolean isSubtypeOf(PizzaType pizzaType) {
		if(!this.isSubtype()) {
			return false;
		}

		if(this.superType == pizzaType) {
			return true;
		}

		return superType.isSubtypeOf(pizzaType);
	}
}
