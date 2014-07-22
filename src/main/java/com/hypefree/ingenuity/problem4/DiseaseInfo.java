package com.hypefree.ingenuity.problem4;

import java.util.Objects;

final class DiseaseInfo {
	private final String name;

	DiseaseInfo(String name) {
		this.name = name;
	}

	String getName() {
		return name;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof DiseaseInfo)) {
			return false;
		}
		DiseaseInfo that = (DiseaseInfo) obj;
		return this.getName().equals(that.getName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getName());
	}
}
