package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

//TODO fix and adapt tests
public class LocationTest {
	private Coordinate coord;
	private Location locA;
	private Location locB;
	
	@Before
	public void initLocAndCoord() {
		coord = CartesianCoordinate.createCartesianCoordinate(1, 2, 3);
		locA = new Location();
		locB = new Location(coord);
	}
	
	@Test
	public void mustHaveCoordinate() {
		assertTrue(null != locA.getCoordinate());
		assertTrue(null != locB.getCoordinate());
	}
	
	@Test
	public void getSetTest() {
		assertTrue(coord.equals(locB.getCoordinate()));
		
		locB.setCoordinate(CartesianCoordinate.createCartesianCoordinate());
		assertFalse(coord.equals(locB.getCoordinate()));
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void nullPointerShouldThrowException() {
		locA.setCoordinate(null);
	}

}
