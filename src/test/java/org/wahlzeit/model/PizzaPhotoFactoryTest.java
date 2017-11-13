package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class PizzaPhotoFactoryTest {

	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(0));

	PizzaPhotoFactory factoryA;
	PhotoFactory factoryB;
	
	@Before
	public void init() {
		helper.setUp();
		factoryA = new PizzaPhotoFactory();
		factoryB = PizzaPhotoFactory.getInstance();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void constructorTest() {
		assertNotNull(factoryA);
		assertNotNull(factoryB);
		
		//assertTrue(factoryB instanceof PizzaPhotoFactory);
		assertTrue(factoryA instanceof PizzaPhotoFactory);
	}

}
