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
package org.lexevs.dao.database.access.association;

import java.sql.SQLException;

import org.LexGrid.relations.AssociationData;
import org.LexGrid.relations.AssociationSource;
import org.lexevs.dao.database.access.LexGridSchemaVersionAwareDao;
import org.lexevs.dao.database.inserter.Inserter;

public interface AssociationDataDao extends LexGridSchemaVersionAwareDao {

	public String insertAssociationData(String codingSchemeUId,
			String associationPredicateUId, AssociationSource source,
			AssociationData data) throws SQLException;

	public String getAssociationDataUId(String codingSchemeUId,
			String associationInstanceId) throws SQLException;

	public String insertHistoryAssociationData(String codingSchemeUId,
			String associationDataUId, Boolean assnQualExist,
			Boolean contextExist) throws SQLException;

	public String updateAssociationData(String codingSchemeUId,
			String associationDataUId,
			AssociationData data) throws SQLException;

	public void deleteAssociationData(String codingSchemeUId,
			String associationDataUId) throws SQLException;

	public String updateVersionableChanges(String codingSchemeUId,
			String associationDataUId,
			AssociationData data) throws SQLException;

	public void deleteAllAssocQualsByAssocDataUId(String codingSchemeUId,
			String associationDataUId) throws SQLException;

	public String getLatestRevision(String csUId, String assocDataUId) throws SQLException;
	
	public boolean entryStateExists(String codingSchemeUId, String entryStateUId) throws SQLException;

	public String insertAssociationData(String codingSchemeUId,
			String associationPredicateUId, AssociationSource source,
			AssociationData data, Inserter inserter) throws SQLException;

	public AssociationData getAssociationDataByUid(
			String codingSchemeUId,
			String associationDataUid) throws SQLException;

	public String getEntryStateUId(String codingSchemeUId, String associationDataUid) throws SQLException;

	public AssociationData getHistoryAssociationDataByRevision(
			String codingSchemeUId, 
			String associationDataUid, 
			String revisionId) throws SQLException;

	public void insertAssociationData(
			String codingSchemeUId,
			String associationPredicateUId, 
			String sourceEntityCode,
			String sourceEntityCodeNamespace, 
			AssociationData data) throws SQLException;

	public AssociationSource getTripleByUid(String codingSchemeUId, String tripleUid) throws SQLException;

}