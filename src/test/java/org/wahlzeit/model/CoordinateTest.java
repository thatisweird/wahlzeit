package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CoordinateTest {
	private Coordinate carCoordA;
	private Coordinate carCoordB;
	private Coordinate spherCoordA;
	private Coordinate spherCoordB;

	@Before
	public void initCoordinate() {
		carCoordA = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		carCoordB = CartesianCoordinate.createCartesianCoordinate(2.0, 2.0, 2.0);
		spherCoordA = SphericCoordinate.createSphericCoordinate(6371000, -0.116773, 51.510357);
		spherCoordB = SphericCoordinate.createSphericCoordinate(6371000, -77.009003, 38.889931);
	}
	
	@Test
	public void constructorTests() {
		assertNotNull(carCoordA);
		assertNotNull(carCoordB);
		assertEquals(carCoordA.asCartesianCoordinate().getX(), 1.0, 0.00001);
		assertEquals(carCoordA.asCartesianCoordinate().getY(), 1.0, 0.00001);
		assertEquals(carCoordA.asCartesianCoordinate().getZ(), 1.0, 0.00001);
		assertNotNull(spherCoordA);
		assertEquals(spherCoordA.asSphericCoordinate().getRadius(), 6371000, 0.01);
		assertEquals(spherCoordA.asSphericCoordinate().getLongitude(), -0.116773 + 360.0, 0.01);
		assertEquals(spherCoordA.asSphericCoordinate().getLatitude(), 51.510357, 0.01);
		assertNotNull(spherCoordB);
	}

	@Test(expected = CoordinateException.class)
	public void badNaNCartesianConstructorTest() {
		carCoordA = CartesianCoordinate.createCartesianCoordinate(Double.NaN, 0, 0);
	}
	
	@Test(expected = CoordinateException.class)
	public void badInfiniyCartesianConstructorTest() {
		carCoordA = CartesianCoordinate.createCartesianCoordinate(Double.NEGATIVE_INFINITY, 0, 0);
	}
	
	@Test(expected = CoordinateException.class)
	public void badNaNSphericConstructorTest() {
		spherCoordA = SphericCoordinate.createSphericCoordinate(Double.NaN, 0, 0);
	}	

	@Test(expected = CoordinateException.class)
	public void badInfiniySphericConstructorTest() {
		spherCoordA = SphericCoordinate.createSphericCoordinate(Double.NEGATIVE_INFINITY, 0, 0);
	}

	@Test
	public void setGetCartesianTests() {
		carCoordA = carCoordA.asCartesianCoordinate().setX(0.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getX(), 0.0));
		carCoordA = carCoordA.asCartesianCoordinate().setY(0.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getY(), 0.0));
		carCoordA = carCoordA.asCartesianCoordinate().setZ(0.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getZ(), 0.0));
		
		carCoordA = CartesianCoordinate.createCartesianCoordinate(1.0, 1.0, 1.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getX(), 1.0));
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getY(), 1.0));
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getZ(), 1.0));
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void badNaNSphericSetTest() {
		carCoordA = carCoordA.asCartesianCoordinate().setX(Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badInfinitySphericSetTest() {
		carCoordA = carCoordA.asCartesianCoordinate().setX(Double.NEGATIVE_INFINITY);
	}
	
	@Test
	public void setGetSphericTests() {

		spherCoordA = spherCoordA.asSphericCoordinate().setRadius(0.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getRadius(), 0.0));
		spherCoordA = spherCoordA.asSphericCoordinate().setLongitude(0.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLongitude(), 0.0));
		spherCoordA = spherCoordA.asSphericCoordinate().setLatitude(0.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLatitude(), 0.0));
		
		spherCoordA = SphericCoordinate.createSphericCoordinate(1.0, 2.0, 3.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getRadius(), 1.0));
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLongitude(), 2.0));
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLatitude(), 3.0));
		
		spherCoordA = spherCoordA.asSphericCoordinate().setRadius(0.0);
		assertEquals(spherCoordA.asSphericCoordinate().getRadius(), 0.0, 0.001);
		assertEquals(spherCoordA.asSphericCoordinate().getLongitude(), 0.0, 0.001);
		assertEquals(spherCoordA.asSphericCoordinate().getLatitude(), 0.0, 0.001);
		
		spherCoordB = spherCoordB.asSphericCoordinate().setLatitude(1.0);
		spherCoordB = spherCoordB.asSphericCoordinate().setLongitude(0.0);
		assertEquals(spherCoordB.asSphericCoordinate().getLatitude(), 0.0, 0.001);
		assertEquals(spherCoordB.asSphericCoordinate().getLongitude(), 0.0, 0.001);	
	}

	@Test(expected = IllegalArgumentException.class)
	public void badNaNCartesianSetTest() {
		carCoordA.asSphericCoordinate().setRadius(Double.NaN);
	}

	@Test(expected = IllegalArgumentException.class)
	public void badInfinityCartesianSetTest() {
		carCoordA.asSphericCoordinate().setRadius(Double.NEGATIVE_INFINITY);
	}
	
	
	@Test(expected = IllegalArgumentException.class)
	public void nullShouldThrowExceptionCartesianTest() {
		carCoordA.getCartesianDistance(null);
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void nullShouldThrowExceptionSphericTest() {
		spherCoordA.getSphericDistance(null);
	}

	@Test
	public void sphericDistanceTest() {
		assertEquals(5897658.289, ((AbstractCoordinate) spherCoordA).getSphericDistance(spherCoordB), 0.001);
	}
	
	@Test
	public void cartesianDistanceTests() {
		assertEquals(carCoordA.getDistance(carCoordB), Math.sqrt(3.0), 0.001);
		assertTrue(0 == Double.compare(carCoordA.getDistance(carCoordA), 0.0));
		
		carCoordA = CartesianCoordinate.createCartesianCoordinate(1.0, 2.0, 3.0);
		carCoordB = CartesianCoordinate.createCartesianCoordinate(1.0, 2.0, 3.0);
		assertTrue(0 == Double.compare(carCoordA.getDistance(carCoordB), 0.0));
	}

	/**
	 * should not crash or throw exceptions
	 */

	@Test
	public void equalsCartesianTests() {
		assertTrue(carCoordA.equals(carCoordA));
		assertFalse(carCoordA.equals(null));
		assertFalse(carCoordA.equals(spherCoordA));
		
		carCoordA = CartesianCoordinate.createCartesianCoordinate(0.0, 0.0, 0.0);
		carCoordB = CartesianCoordinate.createCartesianCoordinate(-0.0, -0.0, -0.0);
		assertTrue(carCoordA.equals(carCoordB));
	}


	@Test
	public void equalsSphercalTests() {
		assertTrue(spherCoordA.equals(spherCoordA));
		assertFalse(spherCoordA.equals(null));
		assertFalse(spherCoordA.equals(carCoordA));
		
		spherCoordA = SphericCoordinate.createSphericCoordinate(0.0, 0.0, 0.0);
		spherCoordB = SphericCoordinate.createSphericCoordinate(-0.0, -0.0, -0.0);
		assertTrue(spherCoordA.equals(spherCoordB));
	}
	
	
	@Test
	public void equalsConversionSCSTests() {
		carCoordA = CartesianCoordinate.createCartesianCoordinate(10.0, 10.0, 10.0);
		assertTrue(carCoordA.equals(carCoordA.asSphericCoordinate().asCartesianCoordinate()));
	}
	
	@Test
	public void roundingErrorsEqualTest() {
		carCoordA = CartesianCoordinate.createCartesianCoordinate(0.5, 0.01, 0.0001);
		carCoordB = CartesianCoordinate.createCartesianCoordinate(1.0/2.0, 0.1*0.1, (1.0001 - 1));
		assertTrue(carCoordA.isEqual(carCoordB));
		assertTrue(carCoordA == carCoordB);
	}
	
	@Test
	public void hashCodeCheck() {
		assertEquals(carCoordA.asCartesianCoordinate().hashCode(), carCoordA.asSphericCoordinate().hashCode());
	}
	
}
