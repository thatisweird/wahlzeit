package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.apache.tools.ant.types.Assertions;
import org.apache.tools.ant.types.resources.selectors.InstanceOf;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.wahlzeit.model.PizzaPhoto.PizzaShape;
import org.wahlzeit.model.PizzaPhoto.PizzaSize;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class PizzaPhotoTest {
	private Location loc;

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
		loc = new Location();
		pizzaPhotoA = new PizzaPhoto();
		pizzaPhotoB = new PizzaPhoto(PhotoId.getNextId());
		pizzaPhotoC = new PizzaPhoto(PhotoId.getNextId(), loc);
		pizzaPhotoD = new PizzaPhoto(PhotoId.getNextId(), loc, PizzaSize.LARGE, PizzaShape.RECTANGULAR);
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
	public void testDefaultAttributes() {
		assertEquals(pizzaPhotoD.getSize(), PizzaSize.LARGE);
		assertEquals(pizzaPhotoD.getShape(), PizzaShape.RECTANGULAR);

		pizzaPhotoA = new PizzaPhoto(null, null, null, null);
		assertNotNull(pizzaPhotoA);
		assertEquals(pizzaPhotoA.getSize(), PizzaSize.SMALL);
		assertEquals(pizzaPhotoA.getShape(), PizzaShape.CIRCULAR);

	}

}
