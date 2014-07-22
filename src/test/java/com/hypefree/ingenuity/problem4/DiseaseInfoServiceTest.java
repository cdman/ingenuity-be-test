package com.hypefree.ingenuity.problem4;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import org.junit.Before;
import org.junit.Test;

import com.hypefree.ingenuity.problem4.DataSet.Type;

public final class DiseaseInfoServiceTest {
	private DiseaseInfoService service;

	@Test
	public void testGetDiseaseInfoByName() {
		assertEquals("bar", service.getDiseaseInfoByName("foo").getName());
	}

	@Test
	public void testGetDiseaseInfoByNameNullForNonExistingName() {
		assertNull(service.getDiseaseInfoByName("fizzbuzz"));
	}

	@Test
	public void testGetDiseasesForDatasetVCF() throws Exception {
		DocumentDao documentDao = createMockDocumentDao(DataSet.Type.VCF, 3);
		service.setDocumentDao(documentDao);

		Set<DiseaseInfo> result = service.getDiseasesForDataset(1);

		assertEquals(Collections.singleton(service.getDiseaseInfoByName("foo")), result);
	}

	@Test
	public void testGetDiseasesForDatasetMSF() throws Exception {
		DocumentDao documentDao = createMockDocumentDao(DataSet.Type.MSF, 10);
		service.setDocumentDao(documentDao);

		Set<DiseaseInfo> result = service.getDiseasesForDataset(1);

		assertEquals(Collections.singleton(service.getDiseaseInfoByName("foo")), result);
	}

	@Test
	public void testGetDiseasesForDatasetUnknown() throws Exception {
		DocumentDao documentDao = createMockDocumentDao(null, 0);
		service.setDocumentDao(documentDao);

		Set<DiseaseInfo> result = service.getDiseasesForDataset(1);

		assertEquals(Collections.emptySet(), result);
	}

	@Test(expected=ServiceException.class)
	public void testGetDiseasesThrowsServiceException() throws Exception {
		DocumentDao documentDao = mock(DocumentDao.class);
		when(documentDao.getDocument(1)).thenThrow(new NullPointerException());
		service.setDocumentDao(documentDao);

		service.getDiseasesForDataset(1);
	}

	private DocumentDao createMockDocumentDao(Type dataSetType,
			int expectedColumn) {
		DocumentDao documentDao = mock(DocumentDao.class);

		DataSet ds = mock(DataSet.class);
		when(documentDao.getDocument(1)).thenReturn(ds);

		DataSetRow row = mock(DataSetRow.class);
		when(ds.getRow(0)).thenReturn(row);
		when(ds.getType()).thenReturn(dataSetType);
		when(ds.getRowCount()).thenReturn(1);

		when(row.getColumn(expectedColumn)).thenReturn("foo");

		return documentDao;
	}

	@Before
	public void setUp() {
		service = new DiseaseInfoService();
	}
}
