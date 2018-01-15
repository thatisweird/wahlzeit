package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.pizza.Pizza;
import org.wahlzeit.model.pizza.Pizza.PizzaShape;
import org.wahlzeit.model.pizza.Pizza.PizzaSize;
import org.wahlzeit.model.pizza.PizzaManager;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class PizzaPhotoTest {
	private Location loc = new Location();

	private PizzaPhoto pizzaPhotoA;
	private PizzaPhoto pizzaPhotoB;
	private PizzaPhoto pizzaPhotoC;
	private PizzaPhoto pizzaPhotoD;

	private Photo photoA;

	// source:
	// https://cloud.google.com/appengine/docs/standard/java/tools/localunittesting/javadoc/com/google/appengine/tools/development/testing/LocalServiceTestHelper
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(0));

	/*
	 * a bit of an overkill at this point but the test will probably be extended in the future
	 */
	@Before
	public void setUp() {
		helper.setUp();
		// datastore = DatastoreServiceFactory.getDatastoreService();

		photoA = new Photo();

		pizzaPhotoA = new PizzaPhoto();
		pizzaPhotoB = new PizzaPhoto(PhotoId.getNextId());
		pizzaPhotoC = new PizzaPhoto(PhotoId.getNextId(), loc);
		
		Pizza p = PizzaManager.getInstance().createPizza("hawaii");
		p.setSize(PizzaSize.LARGE);
		p.setShape(PizzaShape.RECTANGULAR);
		pizzaPhotoD = new PizzaPhoto(PhotoId.getNextId(), loc, p);
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	@Test
	public void testConstructors() {
		assertNotNull(photoA);
		assertNotNull(pizzaPhotoA);
		assertNotNull(pizzaPhotoB);
		assertNotNull(pizzaPhotoC);
		assertNotNull(pizzaPhotoD);

		assertFalse(photoA instanceof PizzaPhoto);
		assertTrue(pizzaPhotoA instanceof PizzaPhoto);
		assertTrue(pizzaPhotoB instanceof PizzaPhoto);
		assertTrue(pizzaPhotoC instanceof PizzaPhoto);
		assertTrue(pizzaPhotoD instanceof PizzaPhoto);

		assertTrue(pizzaPhotoA instanceof Photo);
		assertTrue(pizzaPhotoB instanceof Photo);
		assertTrue(pizzaPhotoC instanceof Photo);
		assertTrue(pizzaPhotoD instanceof Photo);
	}

	@Test
	public void defaultAttributesTest() {
		assertEquals(pizzaPhotoD.getPizza().getSize(), PizzaSize.LARGE);
		assertEquals(pizzaPhotoD.getPizza().getShape(), PizzaShape.RECTANGULAR);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void badAttributesTest() {
		pizzaPhotoA = new PizzaPhoto(null, null, null);
	}

}
