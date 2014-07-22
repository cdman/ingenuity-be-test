package com.hypefree.ingenuity.problem2;

/**
 * Represents an (inclusive) range of integers
 */
final class Range {
	private final long start;
	private final long end;

	Range(long start, long end) {
		this.start = start;
		this.end = end;
		if (start > end) {
			throw new IllegalArgumentException(String.format(
					"Starts needs to be less or equal than end. Got: %d > %d",
					start, end));
		}
	}

	long getStart() {
		return start;
	}

	long getEnd() {
		return end;
	}
	
	@Override
	public String toString() {
		return String.format("Range[%d, %d]", start, end);
	}
}
