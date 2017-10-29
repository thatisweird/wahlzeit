package org.wahlzeit.model;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;


public class LocationTest {
	private Coordinate cord;
	private Location locA;
	private Location locB;
	
	@Before
	public void initLocAndCoord() {
		cord = new Coordinate(1, 2, 3);
		locA = new Location();
		locB = new Location(cord);
	}
	
	@Test
	public void mustHaveCoordinate() {
		assertTrue(null != locA.getCoordinate());
		assertTrue(null != locB.getCoordinate());
	}
	
	@Test
	public void getSetTest() {
		assertTrue(cord.equals(locB.getCoordinate()));
		
		locB.setCoordinate(new Coordinate());
		assertFalse(cord.equals(locB.getCoordinate()));
	}

}
