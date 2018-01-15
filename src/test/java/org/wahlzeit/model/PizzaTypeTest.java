package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.pizza.PizzaType;

public class PizzaTypeTest {
	PizzaType pt0;
	PizzaType pt1;
	PizzaType pt2;
	
	@Before
	public void init() {
		pt0 = new PizzaType("choclate");
		pt1 = new PizzaType("choclateBanana");
		pt2 = new PizzaType("choclateBananaPineapple");
		pt0.addSubType(pt1);
		pt1.addSubType(pt2);
	}
	

	@Test
	public void creationTest() {
		assertNotNull(pt0);
		assertNotNull(pt1);
		assertNotNull(pt2);
		
		assertNull(pt0.getSuperType());
		assertEquals(pt0, pt1.getSuperType());
		assertEquals(pt1, pt2.getSuperType());
	}
	
	@Test
	public void subTypeTest() {
		assertFalse(pt0.isSubtype());
		assert(pt1.isSubtype());
		assert(pt2.isSubtype());
		
		assert(pt1.isSubtypeOf(pt0));
		assert(pt2.isSubtypeOf(pt0));
		assert(pt2.isSubtypeOf(pt1));
	}

}
