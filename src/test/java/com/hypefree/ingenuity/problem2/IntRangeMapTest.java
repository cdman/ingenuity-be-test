package com.hypefree.ingenuity.problem2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public final class IntRangeMapTest {
	private IntRangeMap<String> map;

	@Test
	public void testGivenExample() {
		map.add(1, 5, "A");
		map.add(6, 10, "B");
		map.add(11, 20, "C");
		map.add(21, 45, "D");
		map.add(50, 70, "E");
		map.add(79, 100, "F");

		assertEquals("A", map.get(4));
		assertEquals("A", map.get(2));
		assertEquals("D", map.get(31));
		assertEquals("F", map.get(100));
		assertNull(map.get(77));
	}

	@Test(expected=IllegalArgumentException.class)
	public void testOverlappingRangesNotAllowed() {
		map.add(1, 5, "A");
		map.add(1, 5, "B");
	}
	
	@Before
	public void setUp() {
		map = new IntRangeMap<>();
	}
}
