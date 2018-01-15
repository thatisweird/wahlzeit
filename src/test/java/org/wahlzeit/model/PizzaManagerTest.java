package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.pizza.PizzaManager;

public class PizzaManagerTest {
	
	PizzaManager pm = PizzaManager.getInstance();
	
	@Before
	public void init() {
		pm.types.clear();
		pm.pizzas.clear();
		assertNotNull(pm.createPizza("hawaii"));
		assertNotNull(pm.createPizza("hawaii"));
		assertNotNull(pm.createPizza("burnsTwice"));
	}

	@Test
	public void creationTest() {
		assertEquals(2, pm.types.size());
		assertEquals(3, pm.pizzas.size());
	}

}
