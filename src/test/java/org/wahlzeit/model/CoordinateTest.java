package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//TODO fix and adapt tests

public class CoordinateTest {
	private Coordinate carCoordA;
	private Coordinate carCoordB;
	private Coordinate spherCoordA;
	private Coordinate spherCoordB;

	@Before
	public void initCoordinate() {
		carCoordA = new CartesianCoordinate(1.0, 1.0, 1.0);
		carCoordB = new CartesianCoordinate(2.0, 2.0, 2.0);
		spherCoordA = new SphericCoordinate(6371000, -0.116773, 51.510357);
		spherCoordB = new SphericCoordinate(6371000, -77.009003, 38.889931);
	}
	
	@Test
	public void constructorTests() {
		assertNotNull(carCoordA);
		assertNotNull(carCoordB);
		assertEquals(carCoordA.asCartesianCoordinate().getX(), 1.0, 0.01);
		assertEquals(carCoordA.asCartesianCoordinate().getY(), 1.0, 0.01);
		assertEquals(carCoordA.asCartesianCoordinate().getZ(), 1.0, 0.01);
		assertNotNull(spherCoordA);
		assertEquals(spherCoordA.asSphericCoordinate().getRadius(), 6371000, 0.01);
		assertEquals(spherCoordA.asSphericCoordinate().getLongitude(), -0.116773 + 360.0, 0.01);
		assertEquals(spherCoordA.asSphericCoordinate().getLatitude(), 51.510357, 0.01);
		assertNotNull(spherCoordB);
	}
	
	
	
	@Test
	public void setGetCartesianTests() {

		carCoordA.asCartesianCoordinate().setX(0.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getX(), 0.0));
		carCoordA.asCartesianCoordinate().setY(0.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getY(), 0.0));
		carCoordA.asCartesianCoordinate().setZ(0.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getZ(), 0.0));
		
		carCoordA.asCartesianCoordinate().setXYZ(1.0, 1.0, 1.0);
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getX(), 1.0));
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getY(), 1.0));
		assertTrue(0 == Double.compare(carCoordA.asCartesianCoordinate().getZ(), 1.0));
	}

	@Test
	public void setGetSphericTests() {

		spherCoordA.asSphericCoordinate().setRadius(0.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getRadius(), 0.0));
		spherCoordA.asSphericCoordinate().setLongitude(0.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLongitude(), 0.0));
		spherCoordA.asSphericCoordinate().setLatitude(0.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLatitude(), 0.0));
		
		spherCoordA.asSphericCoordinate().setRLL(1.0, 2.0, 3.0);
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getRadius(), 1.0));
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLongitude(), 2.0));
		assertTrue(0 == Double.compare(spherCoordA.asSphericCoordinate().getLatitude(), 3.0));
		
		spherCoordA.asSphericCoordinate().setRadius(0.0);
		assertEquals(spherCoordA.asSphericCoordinate().getRadius(), 0.0, 0.001);
		assertEquals(spherCoordA.asSphericCoordinate().getLongitude(), 0.0, 0.001);
		assertEquals(spherCoordA.asSphericCoordinate().getLatitude(), 0.0, 0.001);
		
		spherCoordB.asSphericCoordinate().setLatitude(1.0);
		spherCoordB.asSphericCoordinate().setLongitude(0.0);
		assertEquals(spherCoordB.asSphericCoordinate().getLatitude(), 0.0, 0.001);
		assertEquals(spherCoordB.asSphericCoordinate().getLongitude(), 0.0, 0.001);	
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullShouldThrowExceptionCartesianTest() {
		carCoordA.getDistance(null);
	}	
	
	@Test(expected = IllegalArgumentException.class)
	public void nullShouldThrowExceptionSphericTest() {
		spherCoordA.getDistance(null);
	}

	@Test
	public void sphericDistanceTest() {
		assertEquals(5897658.289, spherCoordA.getSphericDistance(spherCoordB), 0.001);
	}
	
	@Test
	public void cartesianDistanceTests() {
		assertEquals(carCoordA.getDistance(carCoordB), Math.sqrt(3.0), 0.001);
		assertTrue(0 == Double.compare(carCoordA.getDistance(carCoordA), 0.0));
		
		carCoordA.asCartesianCoordinate().setXYZ(1.0, 2.0, 3.0);
		carCoordB.asCartesianCoordinate().setXYZ(1.0, 2.0, 3.0);
		assertTrue(0 == Double.compare(carCoordA.getDistance(carCoordB), 0.0));
		
		carCoordA.asCartesianCoordinate().setXYZ(Double.POSITIVE_INFINITY, 2.0, 3.0);
		assertTrue(0 == Double.compare(carCoordA.getDistance(spherCoordA), Double.POSITIVE_INFINITY));
	}

	/**
	 * should not crash or throw exceptions
	 */

	@Test
	public void equalsCartesianTests() {
		assertTrue(carCoordA.isEqual(carCoordA));
		assertFalse(carCoordA.isEqual(null));
		assertFalse(carCoordA.isEqual(spherCoordA));
		
		carCoordA.asCartesianCoordinate().setXYZ(Double.NaN, 0.0, 0.0);
		carCoordB.asCartesianCoordinate().setXYZ(0.0/0.0, 0.0, 0.0);
		assertFalse(carCoordA.isEqual(spherCoordA));
		
		carCoordA = new CartesianCoordinate(Double.POSITIVE_INFINITY, 0.0, 0.0);
		carCoordB = new CartesianCoordinate(1.0/0.0, 0.0, 0.0);
		assertFalse(carCoordA.isEqual(spherCoordA));
		
		carCoordA = new CartesianCoordinate(0.0, 0.0, 0.0);
		carCoordB = new CartesianCoordinate(-0.0, -0.0, -0.0);
		assertTrue(carCoordA.isEqual(carCoordB));
	}
	
	@Test
	public void equalsConversionSCSTests() {
		carCoordA = new CartesianCoordinate(10.0, 10.0, 10.0);
		//System.out.println("carCoordA: " + carCoordA);
		//System.out.println("carCoordA: " + carCoordA.asSphericCoordinate());
		//System.out.println("carCoordA: " + carCoordA.asSphericCoordinate().asCartesianCoordinate());
		assertTrue(carCoordA.equals(carCoordA.asSphericCoordinate().asCartesianCoordinate()));
	}
	
	@Test
	public void roundingErrorsEqualTest() {
		carCoordA.asCartesianCoordinate().setXYZ(0.5, 0.01, 0.0001);
		carCoordB.asCartesianCoordinate().setXYZ(1.0/2.0, 0.1*0.1, (1.0001 - 1));
		assertTrue(carCoordA.isEqual(carCoordB));
	}
	
}
