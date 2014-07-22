package com.hypefree.ingenuity.problem2;

import java.util.Comparator;

/**
 * Compares two Ranges and returns: less than zero if the first one is before the second one,
 * greater than zero if the first one is after the second one and zero if the ranges overlap.
 */
class RangeComparator implements Comparator<Range> {
	@Override
	public int compare(Range range1, Range range2) {
		if (range1.getEnd() < range2.getStart()) { 
			return -1;
		}
		if (range2.getEnd() < range1.getStart()) {
			return 1;
		}
		return 0;
	}
}
