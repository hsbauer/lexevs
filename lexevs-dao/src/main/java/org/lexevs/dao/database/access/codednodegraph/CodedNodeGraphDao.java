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
package org.lexevs.dao.database.access.codednodegraph;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.LexGrid.LexBIG.DataModel.Core.ConceptReference;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.lexevs.dao.database.access.LexGridSchemaVersionAwareDao;
import org.lexevs.dao.database.access.association.model.Node;
import org.lexevs.dao.database.ibatis.codednodegraph.model.EntityReferencingAssociatedConcept;
import org.lexevs.dao.database.operation.LexEvsDatabaseOperations.TraverseAssociations;
import org.lexevs.dao.database.service.codednodegraph.CodedNodeGraphService.Sort;
import org.lexevs.dao.database.service.codednodegraph.model.CountConceptReference;
import org.lexevs.dao.database.service.codednodegraph.model.GraphQuery.CodeNamespacePair;
import org.lexevs.dao.database.service.codednodegraph.model.GraphQuery.QualifierNameValuePair;

/**
 * The Interface CodedNodeGraphDao.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public interface CodedNodeGraphDao extends LexGridSchemaVersionAwareDao {
	
	public enum TripleNode {SUBJECT, OBJECT}
	
	public List<String> listCodeRelationships(
			String codingSchemeUid,
			String relationsContainerName,
			String sourceEntityCode,
			String sourceEntityCodeNamespace, 
			String targetEntityCode,
			String targetEntityCodeNamespace, 
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveSourceCodes,
			List<CodeNamespacePair> mustHaveTargetCodes,
			List<String> mustHaveSourceNamespace,
			List<String> mustHaveTargetNamespace,
			List<String> mustHaveEntityType,
			Boolean restrictToAnonymous,
			boolean useTransitive) throws SQLException;
	
	public List<String> getTripleUidsContainingSubject(
			String codingSchemeUid,
			String associationPredicateUid,
			String subjectEntityCode,
			String subjectEntityCodeNamespace, 
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveObjectCodes,
			List<String> mustHaveObjectNamespace,
			List<String> mustHaveObjectEntityType,
			Boolean restrictToAnonymous,
			List<Sort> sorts,
			int start, 
			int pageSize) throws SQLException;
	
	public Map<String,Integer> getTripleUidsContainingSubjectCount(
			String codingSchemeUid,
			String relationsContainerName,
			String subjectEntityCode,
			String subjectEntityCodeNamespace,
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveObjectCodes,
			List<String> mustHaveObjectNamespace,
			List<String> mustHaveObjectEntityType,
			Boolean restrictToAnonymous) throws SQLException;
	
	public List<CountConceptReference> getCountConceptReferencesContainingSubject(
			String codingSchemeUid,
			String relationsContainerName,
			List<ConceptReference> subjects,
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveObjectCodes,
			List<String> mustHaveObjectNamespace,
			List<String> mustHaveObjectEntityType,
			Boolean restrictToAnonymous) throws SQLException;
			
	public List<ConceptReference> getConceptReferencesContainingSubject(
			String codingSchemeUid,
			String relationsContainerName,
			List<ConceptReference> subjects,
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveObjectCodes,
			List<String> mustHaveObjectNamespace,
			List<String> mustHaveObjectEntityType,
			Boolean restrictToAnonymous,
			List<Sort> sorts,
			int start,
			int pageSize) throws SQLException;
	
	public List<String> getTripleUidsContainingObject(
			String codingSchemeUid,
			String associationPredicateUid,
			String objectEntityCode,
			String objectEntityCodeNamespace, 
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveSubjectCodes,
			List<String> mustHaveSubjectNamespace,
			List<String> mustHaveSubjectEntityType,
			Boolean restrictToAnonymous,
			List<Sort> sorts,
			int start, 
			int pageSize) throws SQLException;
	
	public Map<String,Integer> getTripleUidsContainingObjectCount(
			String codingSchemeUid,
			String relationsContainerName,
			String objectEntityCode,
			String objectEntityCodeNamespace,
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveSubjectCodes,
			List<String> mustHaveSubjectNamespace,
			List<String> mustHaveSubjectEntityType,
			Boolean restrictToAnonymous) throws SQLException;
	
	public List<ConceptReference> getConceptReferencesContainingObject(
			String codingSchemeUid,
			String relationsContainerName,
			List<ConceptReference> objects,
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveSubjectCodes,
			List<String> mustHaveSubjectNamespace,
			List<String> mustHaveSubjectEntityType,
			Boolean restrictToAnonymous,
			List<Sort> sorts,
			int start,
			int pageSize) throws SQLException;
			
	public List<CountConceptReference> getCountConceptReferencesContainingObject(
			String codingSchemeUid,
			String relationsContainerName,
			List<ConceptReference> objects,
			List<String> associationNames,
			List<QualifierNameValuePair> associationQualifiers,
			List<CodeNamespacePair> mustHaveSubjectCodes,
			List<String> mustHaveSubjectNamespace,
			List<String> mustHaveObjectEntityType,
			Boolean restrictToAnonymous) throws SQLException;
	
	public List<EntityReferencingAssociatedConcept> getAssociatedConceptsFromUid(
			String codingSchemeUid, 
			List<String> tripleUids, 
			List<Sort> sorts, 
			TripleNode tripleNode) throws SQLException;
	
	public List<ConceptReference> getConceptReferencesFromUid(
			String codingSchemeUid, 
			List<String> tripleUids, 
			TripleNode tripleNode, 
			List<Sort> sorts) throws SQLException;
	
	public List<String> getAssociationPredicateNamesForCodingSchemeUid(
			String codingSchemeUid,
			String relationsContainerName) throws SQLException;
	
	public List<Node> getDistinctSourceNodesForAssociationPredicate(
			String codingSchemeUid, 
			String associationPredicateUid) throws SQLException;
	
	public List<Node> getTargetNodesForSource(
			String codingSchemeUid, 
			String associationPredicateUid, 
			String sourceEntityCode, 
			String sourceEntityCodeNamespace) throws SQLException;

	public List<ConceptReference> getTailNodes(
			String codingSchemeUid,
			List<String> associationPredicateUids, 
			List<QualifierNameValuePair> qualifiers, 
			List<String> mustHaveSubjectNamespace,
			List<String> mustHaveObjectNamespace,
			TraverseAssociations traverse,
			List<Sort> sorts, 
			int start,
			int pageSize) throws SQLException;
	
	public List<ConceptReference> getRootNodes(
			String codingSchemeUid,
			List<String> associationPredicateUids, 
			List<QualifierNameValuePair> qualifiers, 
			List<String> mustHaveSubjectNamespace,
			List<String> mustHaveObjectNamespace,
			TraverseAssociations traverse,
			List<Sort> sorts, 
			int start,
			int pageSize) throws SQLException;

	public List<String> getTripleUidsForMappingRelationsContainer(
			String mappingCodingSchemeUid, 
			String sourceCodingSchemeUid,
			String targetCodingSchemeUid, 
			String relationsContainerName,
			List<Sort> sortOptionList,
			int start, 
			int pageSize) throws SQLException;
	
	public List<String> getTripleUidsForMappingRelationsContainerAndCodes(
			String mappingCodingSchemeUid, 
			String sourceCodingSchemeUid,
			String targetCodingSchemeUid, 
			String relationsContainerName,
			List<ConceptReference> sourceConceptReferences,
			List<ConceptReference> targetConceptReferences,
			List<ConceptReference> sourceOrTargetConceptReferences,
			List<Sort> sortList, 
			int start, 
			int pageSize) throws SQLException;
	
	public List<String> getTripleUidsForMappingRelationsContainerAndCodes(
			String mappingCodingSchemeUid, 
			String relationsContainerName,
			List<ConceptReference> sourceConceptReferences,
			List<ConceptReference> targetConceptReferences,
			List<ConceptReference> sourceOrTargetConceptReferences) throws SQLException;
	
	public List<? extends ResolvedConceptReference> getTriplesForMappingRelationsContainer(
			String mappingCodingSchemeUid, 
			String sourceCodingSchemeUid,
			String targetCodingSchemeUid, 
			String relationsContainerName,
			List<String> tripleUids) throws SQLException;
	
	public int getTriplesForMappingRelationsContainerAndCodesCount(
			String mappingCodingSchemeUid, 
			String relationsContainerName,
			List<ConceptReference> sourceConceptReferences,
			List<ConceptReference> targetConceptReferences,
			List<ConceptReference> sourceOrTargetConceptReferences) throws SQLException;
	
	public int getTriplesForMappingRelationsContainerCount(
			String mappingCodingSchemeUid, 
			String relationsContainerName) throws SQLException;

	public boolean doesEntityParticipateInRelationships(
			String mappingCodingSchemeUid,			
			String relationsContainerName,
			String code, 
			String namespace) throws SQLException;

	public int getTransitiveTableCount(String codingSchemeUid) throws SQLException;
	
	public int deleteFromTransitiveTableByCodingSchemeUid(String codingSchemeUid) throws SQLException;
}