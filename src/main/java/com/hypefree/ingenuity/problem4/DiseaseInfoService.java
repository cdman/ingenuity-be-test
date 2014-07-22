package com.hypefree.ingenuity.problem4;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.xml.rpc.ServiceException;

public class DiseaseInfoService {

	static Map<String, Integer> name2Id = new HashMap<String, Integer>();
	static Map<Integer, DiseaseInfo> id2Info = new HashMap<Integer, DiseaseInfo>();

	static {
		AnnotationFactory.initDiseaseInfo(name2Id, id2Info);
	}

	private DocumentDao documentDao;

	public DiseaseInfoService() {
		documentDao = new FileSystemDocumentDao();
	}

	public DiseaseInfo getDiseaseInfoByName(String name) {
		Integer diseaseId = name2Id.get(name);
		return (diseaseId != null) ? id2Info.get(diseaseId) : null;
	}

	public Set<DiseaseInfo> getDiseasesForDataset(int datasetId)
			throws ServiceException {
		try {
			Set<DiseaseInfo> diseases = new HashSet<>();

			DataSet ds = documentDao.getDocument(datasetId);

			if (ds.getType() == DataSet.Type.VCF) {
				for (int i = 0; i < ds.getRowCount(); i++) {
					DataSetRow row = ds.getRow(i);
					String name = row.getColumn(3);

					Integer diseaseId = name2Id.get(name);
					diseases.add(id2Info.get(diseaseId));
				}
			} else if (ds.getType() == DataSet.Type.MSF) {
				for (int i = 0; i < ds.getRowCount(); i++) {
					DataSetRow row = ds.getRow(i);
					String name = row.getColumn(10);

					Integer diseaseId = name2Id.get(name);
					diseases.add(id2Info.get(diseaseId));
				}
			}

			return diseases;
		} catch (Exception e) {
			throw new ServiceException("Error processing dataset.", e);
		}
	}

	public void setDocumentDao(DocumentDao xmlDocumentDao) {
		this.documentDao = xmlDocumentDao;
	}
}