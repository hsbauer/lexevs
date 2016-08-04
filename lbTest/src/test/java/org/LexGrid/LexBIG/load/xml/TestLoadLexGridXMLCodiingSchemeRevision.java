/*
 * Copyright: (c) 2004-2010 Mayo Foundation for Medical Education and 
 * Research (MFMER). All rights reserved. MAYO, MAYO CLINIC, and the
 * triple-shield Mayo logo are trademarks and service marks of MFMER.
 *
 * Except as contained in the copyright notice above, or as used to identify 
 * MFMER as the author of this software, the trade names, trademarks, service
 * marks, or product names of the copyright holder shall not be used in
 * advertising, promotion or otherwise in connection with this software without
 * prior written authorization of the copyright holder.
 * 
 * Licensed under the Eclipse Public License, Version 1.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at 
 * 
 * 		http://www.eclipse.org/legal/epl-v10.html
 * 
 */
package org.LexGrid.LexBIG.load.xml;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;

import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.DataModel.Core.CodingSchemeVersionOrTag;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.types.ProcessState;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.LexBIG.Impl.LexBIGServiceImpl;
import org.LexGrid.LexBIG.Impl.loaders.LexGridMultiLoaderImpl;
import org.LexGrid.LexBIG.Impl.testUtility.ServiceHolder;
import org.LexGrid.LexBIG.LexBIGService.CodedNodeGraph;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.LexBIG.LexBIGService.LexBIGServiceManager;
import org.LexGrid.LexBIG.Utility.ConvenienceMethods;
import org.LexGrid.LexBIG.Utility.LBConstants;
import org.lexevs.dao.database.service.DatabaseServiceManager;
import org.lexevs.dao.database.service.codingscheme.CodingSchemeService;
import org.lexevs.dao.database.service.version.AuthoringService;
import org.lexevs.locator.LexEvsServiceLocator;

import junit.framework.TestCase;

public class TestLoadLexGridXMLCodiingSchemeRevision extends TestCase {

	protected LexBIGService service;
	protected DatabaseServiceManager dbManager;
	protected CodingSchemeService csService;
	protected LexEvsServiceLocator locator;
	protected ArrayList<String> revisionGuid;


	public void setUp() {
		service = LexBIGServiceImpl.defaultInstance();
		locator = LexEvsServiceLocator.getInstance();
		dbManager = locator.getDatabaseServiceManager();
		csService = dbManager.getCodingSchemeService();
		revisionGuid = new ArrayList<String>();
	}

	public void testLoadScheme4AssocTargetRevision()
			throws LBParameterException, LBInvocationException,
			InterruptedException, LBException {

		LexBIGServiceManager lbsm = service.getServiceManager(null);

		LexGridMultiLoaderImpl loader = (LexGridMultiLoaderImpl) lbsm
				.getLoader("LexGrid_Loader");

		loader.load(
				new File(
						"resources/testData/csRevision/OriginalAutombilesCS.xml")
						.toURI(), true, true);

		while (loader.getStatus().getEndTime() == null) {
			Thread.sleep(500);
		}

		assertTrue(loader.getStatus().getState().equals(ProcessState.COMPLETED));
		assertFalse(loader.getStatus().getErrorsLogged().booleanValue());

		lbsm.activateCodingSchemeVersion(loader.getCodingSchemeReferences()[0]);

		lbsm.setVersionTag(loader.getCodingSchemeReferences()[0],
				LBConstants.KnownTags.PRODUCTION.toString());

	}

	public void testValidateSchemeLoad() throws LBException {
		CodingSchemeVersionOrTag csvt = new CodingSchemeVersionOrTag();
		csvt.setVersion("1.1");
		CodedNodeGraph cng = service.getNodeGraph("urn:oid:22.2urn:oid:22.22.0.4.3", csvt,
				"rel");
		cng = cng.restrictToAssociations(
				ConvenienceMethods.createNameAndValueList("hasSubtype"), null);
		ResolvedConceptReferenceList rcrl = cng.resolveAsList(
				ConvenienceMethods.createConceptReference("GM", "Automobiles"),
				true, true, -1, -1, null, null, null, -1);
		ResolvedConceptReference rcr = rcrl.getResolvedConceptReference(0);
		assertNotNull(rcr);
	}

	public void testLoadTargetRevision1() throws LBParameterException,
			LBInvocationException, InterruptedException, LBException {

		LexBIGServiceManager lbsm = service.getServiceManager(null);

		LexGridMultiLoaderImpl loader = (LexGridMultiLoaderImpl) lbsm
				.getLoader("LexGrid_Loader");

		loader.load(new File("resources/testData/csRevision/RevisionCS.1.xml")
				.toURI(), true, true);

		while (loader.getStatus().getEndTime() == null) {
			Thread.sleep(500);
		}

		assertTrue(loader.getStatus().getState().equals(ProcessState.COMPLETED));
		assertFalse(loader.getStatus().getErrorsLogged().booleanValue());

		lbsm.activateCodingSchemeVersion(loader.getCodingSchemeReferences()[0]);

		lbsm.setVersionTag(loader.getCodingSchemeReferences()[0],
				LBConstants.KnownTags.PRODUCTION.toString());
	}

	public void testLoadTargetRevision2() throws LBParameterException,
			LBInvocationException, InterruptedException, LBException {

		LexBIGServiceManager lbsm = service.getServiceManager(null);

		LexGridMultiLoaderImpl loader = (LexGridMultiLoaderImpl) lbsm
				.getLoader("LexGrid_Loader");

		loader.load(new File("resources/testData/csRevision/RevisionCS.2.xml")
				.toURI(), true, true);

		while (loader.getStatus().getEndTime() == null) {
			Thread.sleep(500);
		}

		assertTrue(loader.getStatus().getState().equals(ProcessState.COMPLETED));
		assertFalse(loader.getStatus().getErrorsLogged().booleanValue());

		lbsm.activateCodingSchemeVersion(loader.getCodingSchemeReferences()[0]);

		lbsm.setVersionTag(loader.getCodingSchemeReferences()[0],
				LBConstants.KnownTags.PRODUCTION.toString());
	}

	public void testLoadTargetRevision3() throws LBParameterException,
			LBInvocationException, InterruptedException, LBException {

		LexBIGServiceManager lbsm = service.getServiceManager(null);

		LexGridMultiLoaderImpl loader = (LexGridMultiLoaderImpl) lbsm
				.getLoader("LexGrid_Loader");

		loader.load(new File("resources/testData/csRevision/RevisionCS.3.xml")
				.toURI(), true, true);

		while (loader.getStatus().getEndTime() == null) {
			Thread.sleep(500);
		}

		assertTrue(loader.getStatus().getState().equals(ProcessState.COMPLETED));
		assertFalse(loader.getStatus().getErrorsLogged().booleanValue());

		lbsm.activateCodingSchemeVersion(loader.getCodingSchemeReferences()[0]);

		lbsm.setVersionTag(loader.getCodingSchemeReferences()[0],
				LBConstants.KnownTags.PRODUCTION.toString());
	}

	public void testLoadTargetRevision4() throws LBParameterException,
			LBInvocationException, InterruptedException, LBException {

		LexBIGServiceManager lbsm = service.getServiceManager(null);

		LexGridMultiLoaderImpl loader = (LexGridMultiLoaderImpl) lbsm
				.getLoader("LexGrid_Loader");

		loader.load(new File("resources/testData/csRevision/RevisionCS.4.xml")
				.toURI(), true, true);

		while (loader.getStatus().getEndTime() == null) {
			Thread.sleep(500);
		}

		assertTrue(loader.getStatus().getState().equals(ProcessState.COMPLETED));
		assertFalse(loader.getStatus().getErrorsLogged().booleanValue());

		lbsm.activateCodingSchemeVersion(loader.getCodingSchemeReferences()[0]);

		lbsm.setVersionTag(loader.getCodingSchemeReferences()[0],
				LBConstants.KnownTags.PRODUCTION.toString());
	}

	public void testLoadTargetRevision5() throws LBParameterException,
			LBInvocationException, InterruptedException, LBException {

		LexBIGServiceManager lbsm = service.getServiceManager(null);

		LexGridMultiLoaderImpl loader = (LexGridMultiLoaderImpl) lbsm
				.getLoader("LexGrid_Loader");

		loader.load(new File("resources/testData/csRevision/RevisionCS.5.xml")
				.toURI(), true, true);

		while (loader.getStatus().getEndTime() == null) {
			Thread.sleep(500);
		}

		assertTrue(loader.getStatus().getState().equals(ProcessState.COMPLETED));
		assertFalse(loader.getStatus().getErrorsLogged().booleanValue());

		lbsm.activateCodingSchemeVersion(loader.getCodingSchemeReferences()[0]);

		lbsm.setVersionTag(loader.getCodingSchemeReferences()[0],
				LBConstants.KnownTags.PRODUCTION.toString());
	}
	public void testRemoveAutombiles() throws LBException, SQLException {
		LexBIGServiceManager lbsm = service.getServiceManager(null);

		AbsoluteCodingSchemeVersionReference a = ConvenienceMethods
				.createAbsoluteCodingSchemeVersionReference(
						"urn:oid:22.22.0.4", "1.1");

		lbsm.deactivateCodingSchemeVersion(a, null);

		lbsm.removeCodingSchemeVersion(a);
		AuthoringService authServ = LexEvsServiceLocator.getInstance()
				.getDatabaseServiceManager().getAuthoringService();
		assertTrue(authServ
				.removeRevisionRecordbyId("ScottCSRelease2010"));
		assertTrue(authServ
				.removeRevisionRecordbyId("ScottCSRelease2010Feb"));
		assertTrue(authServ
				.removeRevisionRecordbyId("ScottCSRelease2010Mar"));
		assertTrue(authServ
				.removeRevisionRecordbyId("ScottCSRelease2010April"));
		assertTrue(authServ
				.removeRevisionRecordbyId("ScottCSRelease2010May"));
	}
}