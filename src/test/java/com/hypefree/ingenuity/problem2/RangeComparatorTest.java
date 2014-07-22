package com.hypefree.ingenuity.problem2;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public final class RangeComparatorTest {
	private RangeComparator comparator;

	@Test
	public void testFirstRangeBeforeSecond() {
		assertTrue(comparator.compare(new Range(0, 1), new Range(2, 3)) < 0);
	}

	@Test
	public void testFirstRangeAfterSecond() {
		assertTrue(comparator.compare(new Range(2, 3), new Range(0, 1)) > 0);
	}

	@Test
	public void testRangesOverlap() {
		assertEquals(0, comparator.compare(new Range(0, 3), new Range(2, 5)));
		assertEquals(0, comparator.compare(new Range(2, 5), new Range(0, 3)));
		assertEquals(0, comparator.compare(new Range(0, 3), new Range(3, 5)));
		assertEquals(0, comparator.compare(new Range(2, 5), new Range(5, 7)));
	}

	@Before
	public void setUp() {
		comparator = new RangeComparator();
	}
}
