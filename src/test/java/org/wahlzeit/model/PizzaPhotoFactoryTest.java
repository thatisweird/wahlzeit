package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class PizzaPhotoFactoryTest {
	PizzaPhotoFactory factoryA;
	PhotoFactory factoryB;
	
	@Before
	public void init() {
		factoryA = new PizzaPhotoFactory();
		factoryB = PizzaPhotoFactory.getInstance();
	}

	@Test
	public void constructorTest() {
		assertNotNull(factoryA);
		assertNotNull(factoryB);
		
		assertTrue(factoryB instanceof PizzaPhotoFactory);
		assertTrue(factoryA instanceof PizzaPhotoFactory);
	}

}
