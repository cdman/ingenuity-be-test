package com.hypefree.ingenuity.problem2;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;

final class IntRangeMap<V> extends AbstractMap<Range, V> implements
		Map<Range, V> {
	private final Comparator<Range> COMPARATOR = new RangeComparator();

	private final List<Range> keys = new ArrayList<>();
	private final List<V> values = new ArrayList<>();

	@Override
	public V put(Range range, V value) {
		int insertIdx = Collections.binarySearch(keys, range, COMPARATOR);
		if (insertIdx >= 0) {
			throw new IllegalArgumentException(
					String.format(
							"Overlapping ranges are not allowed. New range %s overlaps with %s",
							range, keys.get(insertIdx)));
		}

		insertIdx = -insertIdx - 1;
		keys.add(insertIdx, range);
		values.add(insertIdx, value);

		return value;
	}

	public void add(long start, long stop, V value) {
		put(new Range(start, stop), value);
	}

	public V get(long key) {
		int idx = Collections.binarySearch(keys, new Range(key, key),
				COMPARATOR);
		if (idx < 0) {
			return null;
		}
		return values.get(idx);
	}

	@Override
	public Set<java.util.Map.Entry<Range, V>> entrySet() {
		throw new UnsupportedOperationException();
	}
}
