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

import junit.framework.TestCase;

import java.sql.SQLException;

import org.LexGrid.LexBIG.Impl.testUtility.ServiceHolder;
import org.LexGrid.LexBIG.LexBIGService.LexBIGService;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.versions.EntryState;
import org.lexevs.dao.database.access.DaoManager;
import org.lexevs.dao.database.access.association.AssociationTargetDao;
import org.lexevs.dao.database.access.entity.EntityDao;
import org.lexevs.dao.database.access.property.PropertyDao;
import org.lexevs.dao.database.ibatis.codingscheme.IbatisCodingSchemeDao;
import org.lexevs.dao.database.service.DatabaseServiceManager;
import org.lexevs.dao.database.service.codingscheme.CodingSchemeService;
import org.lexevs.dao.database.service.daocallback.DaoCallbackService;
import org.lexevs.dao.database.service.daocallback.DaoCallbackService.DaoCallback;
import org.lexevs.locator.LexEvsServiceLocator;

public class TestEntryStateAccess extends TestCase {
	protected LexBIGService service;
	protected DatabaseServiceManager dbManager;
	protected CodingSchemeService csService;
	protected LexEvsServiceLocator locator;
	protected EntityDao entityDao;
	protected IbatisCodingSchemeDao csDao;
	protected AssociationTargetDao assDao;
	protected PropertyDao propsDao;
	protected DaoCallbackService daoCallbackService;
	protected EntryState es;
	public TestEntryStateAccess (String serverName) {
		super(serverName);
	}

	public void setUp() {
		ServiceHolder.configureForSingleConfig();
		service = ServiceHolder.instance().getLexBIGService();
		locator = LexEvsServiceLocator.getInstance();
		dbManager = locator.getDatabaseServiceManager();
		csService = dbManager.getCodingSchemeService();
		daoCallbackService = dbManager.getDaoCallbackService();
	}
	
	public void testEntryState() throws SQLException{
		assertTrue(getEntryStateForCodingScheme().getContainingRevision().equals("testRelease2010Feb_testData"));
	}
	
	private EntryState getEntryStateForCodingScheme() throws SQLException{
        return daoCallbackService.executeInDaoLayer(new DaoCallback<EntryState>() {

            public EntryState execute(DaoManager daoManager) throws SQLException {
              
                csDao = (IbatisCodingSchemeDao)daoManager.getCodingSchemeDao("urn:oid:22.22.0.2", "2.0");
                CodingScheme cs = csDao .getCodingSchemeByUriAndVersion("urn:oid:22.22.0.2", "2.0");
                return cs.getEntryState();
            }
        });
	}
}