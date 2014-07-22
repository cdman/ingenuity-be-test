package com.hypefree.ingenuity.problem2;

import org.junit.Test;

public final class RangeTest {
	@Test(expected=IllegalArgumentException.class)
	public void testRangeStartMustBeLessThanEnd() {
		new Range(2, 1);
	}
}
