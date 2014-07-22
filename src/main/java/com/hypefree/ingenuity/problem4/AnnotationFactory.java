package com.hypefree.ingenuity.problem4;

import java.util.Map;

final class AnnotationFactory {
	public static void initDiseaseInfo(Map<String, Integer> name2Id,
			Map<Integer, DiseaseInfo> id2Info) {
		name2Id.put("foo", 1);
		id2Info.put(1, new DiseaseInfo("bar"));
	}
}
