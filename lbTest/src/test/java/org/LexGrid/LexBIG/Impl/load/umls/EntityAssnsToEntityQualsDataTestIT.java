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
package org.LexGrid.LexBIG.Impl.load.umls;

import junit.framework.JUnit4TestAdapter;

import org.LexGrid.LexBIG.DataModel.Collections.NameAndValueList;
import org.LexGrid.LexBIG.DataModel.Core.AssociatedConcept;
import org.LexGrid.LexBIG.Impl.function.LexBIGServiceTestCase;
import org.LexGrid.LexBIG.Impl.testUtility.DataTestUtils;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.junit.Before;
import org.junit.Test;
import org.lexgrid.loader.rrf.constants.RrfLoaderConstants;

/**
 * The Class EntityAssnsToEntityQualsDataTestIT.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class EntityAssnsToEntityQualsDataTestIT extends DataLoadTestBase {
	
	/** The graph focus. */
	private AssociatedConcept[] associatedConcept;
	
	/**
	 * Builds the test entity.
	 * 
	 * @throws Exception the exception
	 */
	@Before
	public void setUp() throws Exception {
		super.setUp();
		associatedConcept = cng.resolveAsList(Constructors.createConceptReference("ACRMG", 
				LexBIGServiceTestCase.AIR_URN), true, true, 1, 1, null, null, null, -1)
				.getResolvedConceptReference(0)
				.getSourceOf()
				.getAssociation()[0]
				.getAssociatedConcepts()
				.getAssociatedConcept();
	}
	
	@Test
	public void testQualsNotNull() throws Exception {	
		NameAndValueList quals = DataTestUtils.getConceptReference(associatedConcept, "U000010").getAssociationQualifiers();
		assertNotNull(quals);
	}
	
	@Test
	public void testRelaQual() throws Exception {	
		NameAndValueList quals = DataTestUtils.getConceptReference(associatedConcept, "U000010").getAssociationQualifiers();
		assertTrue(DataTestUtils.isQualifierNameAndValuePresent(RrfLoaderConstants.RELA_QUALIFIER, "mapped_from", quals));
	}
	
	public static junit.framework.Test suite() {  
		return new JUnit4TestAdapter(EntityAssnsToEntityQualsDataTestIT.class);  
	}  
}