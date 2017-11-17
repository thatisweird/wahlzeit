package org.wahlzeit.model;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.annotation.Subclass;

public class PizzaPhotoManagerTest{
	
	private PhotoManager manager;
	private Photo photoA;
	private PizzaPhoto pizzaPhotoA;

	// source:
	// https://cloud.google.com/appengine/docs/standard/java/tools/localunittesting/javadoc/com/google/appengine/tools/development/testing/LocalServiceTestHelper
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(0));
	
	@Before
	public void init() {
		helper.setUp();
		manager = PizzaPhotoManager.getInstance();
		photoA = new Photo();
		pizzaPhotoA = new PizzaPhoto();
	}

	@After
	public void tearDown() {
		helper.tearDown();
	}

	
	
	@Test
	public void instanceTest() {
		assertTrue(manager instanceof PhotoManager);
		assertTrue(manager instanceof PizzaPhotoManager);
	}
	
	
	
	@Test
	public void addPhotosTest() {
		try {
			manager.addPhoto(photoA);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		
		try {
			manager.addPhoto(pizzaPhotoA);
		} catch (IOException e) {
			e.printStackTrace();
			fail();
		}
		assertNotNull(PhotoManager.getInstance().getPhoto(pizzaPhotoA.getId()));
	}
	
	@Test(expected = IllegalStateException.class)
	public void addBadPhotoTest() throws IOException {
		assertNotNull(photoA.getId());
		
		manager.addPhoto(photoA);
		assertEquals(1, manager.photoCache.size());
		assertEquals(photoA, PhotoManager.getInstance().getPhotoFromId(photoA.getId()));
		manager.addPhoto(photoA);
		assertEquals(1, manager.photoCache.size());
		
	}
	
	

}
