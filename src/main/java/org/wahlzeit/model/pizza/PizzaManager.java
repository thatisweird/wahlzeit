package org.wahlzeit.model.pizza;

import java.util.HashMap;
import java.util.HashSet;

import org.wahlzeit.services.ObjectManager;

public class PizzaManager extends ObjectManager{
	
	public HashMap<String, PizzaType> types;
	public HashSet<Pizza> pizzas;
	
	protected static final PizzaManager instance = new PizzaManager();
	
	private PizzaManager() {
		types = new HashMap<String, PizzaType>();
		pizzas = new HashSet<Pizza>();
	}

	private PizzaType getPizzaType(String typeName) {
		PizzaType pt = this.types.get(typeName);
		if(null == pt) {
			pt = new PizzaType(typeName);
			types.put(typeName, pt);
		}
		return pt;
	}

	public Pizza createPizza(String typeName) {
		PizzaType pizzaType = getPizzaType(typeName);
		Pizza pizza = pizzaType.createInstance();
		pizzas.add(pizza);
		return pizza;
	}

	public  static PizzaManager getInstance() {
		return instance;
	}
}
