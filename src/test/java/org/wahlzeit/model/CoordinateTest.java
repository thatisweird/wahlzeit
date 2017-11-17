package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

//TODO fix and adapt tests

public class CoordinateTest {
	private Coordinate acoord;
	private Coordinate bcoord;

	@Before
	public void initCoordinate() {
		acoord = new Coordinate(1.0, 1.0, 1.0);
		bcoord = new Coordinate(1.0, 1.0, 2.0);
	}
	
	@Test
	public void setGetTests() {
		acoord.setX(0.0);
		assertTrue(0 == Double.compare(acoord.getX(), 0.0));
		acoord.setY(0.0);
		assertTrue(0 == Double.compare(acoord.getY(), 0.0));
		acoord.setZ(0.0);
		assertTrue(0 == Double.compare(acoord.getZ(), 0.0));
		
		acoord.setXYZ(1.0, 1.0, 1.0);
		assertTrue(0 == Double.compare(acoord.getX(), 1.0));
		assertTrue(0 == Double.compare(acoord.getY(), 1.0));
		assertTrue(0 == Double.compare(acoord.getZ(), 1.0));
	}

	
	@Test(expected = IllegalArgumentException.class)
	public void nullPointerShouldThrowException() {
		acoord.getDistance(null);
	}
	
	@Test
	public void distanceTests() {
		assertTrue(0 == Double.compare(acoord.getDistance(bcoord), 1.0));
		assertTrue(0 == Double.compare(acoord.getDistance(acoord), 0.0));
		
		acoord.setXYZ(1.0, 2.0, 3.0);
		bcoord.setXYZ(1.0, 2.0, 3.0);
		assertTrue(0 == Double.compare(acoord.getDistance(bcoord), 0.0));
		
		bcoord.setXYZ(Double.POSITIVE_INFINITY, 2.0, 3.0);
		assertTrue(0 == Double.compare(acoord.getDistance(bcoord), Double.POSITIVE_INFINITY));
	}

	/**
	 * should not crash or throw exceptions
	 */
	@Test
	public void equalTests() {
		assertTrue(acoord.isEqual(acoord));
		assertFalse(acoord.isEqual(null));
		assertFalse(acoord.isEqual(bcoord));
		
		acoord = new Coordinate(Double.NaN, 0.0, 0.0);
		bcoord = new Coordinate(0.0/0.0, 0.0, 0.0);
		assertFalse(acoord.isEqual(bcoord));
		
		acoord = new Coordinate(Double.POSITIVE_INFINITY, 0.0, 0.0);
		bcoord = new Coordinate(1.0/0.0, 0.0, 0.0);
		assertFalse(acoord.isEqual(bcoord));
		
		acoord = new Coordinate(0.0, 0.0, 0.0);
		bcoord = new Coordinate(-0.0, -0.0, -0.0);
		assertTrue(acoord.isEqual(bcoord));
	}
	
	@Test
	public void roundingErrorsEqualTest() {
		acoord.setXYZ(0.5, 0.01, 0.0001);
		bcoord.setXYZ(1.0/2.0, 0.1*0.1, (1.0001 - 1));
		assertTrue(acoord.isEqual(bcoord));
	}
}
