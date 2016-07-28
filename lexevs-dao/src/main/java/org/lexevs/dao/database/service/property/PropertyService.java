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
package org.lexevs.dao.database.service.property;

import java.sql.SQLException;
import java.util.List;

import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.commonTypes.Property;

/**
 * The Interface PropertyService.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public interface PropertyService {
	
	/** The Constant INSERT_BATCH_PROPERTY_ERROR. */
	public static final String INSERT_BATCH_PROPERTY_ERROR = "INSERT-BATCH-PROPERTY-ERROR";
	
	/** The Constant INSERT_CODINGSCHEME_PROPERTY_ERROR. */
	public static final String INSERT_CODINGSCHEME_PROPERTY_ERROR = "INSERT-CODING-SCHEME-PROPERTY-ERROR";
	
	/** The Constant INSERT_CODINGSCHEME_PROPERTY_VERSIONABLE_CHANGES_ERROR. */
	public static final String INSERT_CODINGSCHEME_PROPERTY_VERSIONABLE_CHANGES_ERROR = "INSERT-CODING-SCHEME-PROPERTY-VERSIONABLE-CHANGES-ERROR";
	
	/** The Constant UPDATE_CODINGSCHEME_PROPERTY_ERROR. */
	public static final String UPDATE_CODINGSCHEME_PROPERTY_ERROR = "UPDATE-CODING-SCHEME-PROPERTY-ERROR";
	
	/** The Constant REMOVE_CODINGSCHEME_PROPERTY_ERROR. */
	public static final String REMOVE_CODINGSCHEME_PROPERTY_ERROR = "REMOVE-CODING-SCHEME-PROPERTY-ERROR";
	
	/** The Constant INSERT_ENTITY_PROPERTY_ERROR. */
	public static final String INSERT_ENTITY_PROPERTY_ERROR = "INSERT-ENTITY-PROPERTY-ERROR";
	
	/** The Constant INSERT_ENTITY_PROPERTY_VERSIONABLE_CHANGES_ERROR. */
	public static final String INSERT_ENTITY_PROPERTY_VERSIONABLE_CHANGES_ERROR = "INSERT-ENTITY-PROPERTY-VERSIONABLE-CHANGES-ERROR";
	
	/** The Constant UPDATE_ENTITY_PROPERTY_ERROR. */
	public static final String UPDATE_ENTITY_PROPERTY_ERROR = "UPDATE-ENTITY-PROPERTY-ERROR";
	
	/** The Constant REMOVE_ENTITY_PROPERTY_ERROR. */
	public static final String REMOVE_ENTITY_PROPERTY_ERROR = "REMOVE-ENTITY-PROPERTY-ERROR";
	
	/** The Constant INSERT_RELATION_PROPERTY_ERROR. */
	public static final String INSERT_RELATION_PROPERTY_ERROR = "INSERT-RELATION-PROPERTY-ERROR";
	
	/** The Constant INSERT_RELATION_PROPERTY_VERSIONABLE_CHANGES_ERROR. */
	public static final String INSERT_RELATION_PROPERTY_VERSIONABLE_CHANGES_ERROR = "INSERT-RELATION-PROPERTY-VERSIONABLE-CHANGES-ERROR";
	
	/** The Constant UPDATE_RELATION_PROPERTY_ERROR. */
	public static final String UPDATE_RELATION_PROPERTY_ERROR = "UPDATE-RELATION-PROPERTY-ERROR";
	
	/** The Constant REMOVE_RELATION_PROPERTY_ERROR. */
	public static final String REMOVE_RELATION_PROPERTY_ERROR = "REMOVE-RELATION-PROPERTY-ERROR";
	
	/**
	 * Insert coding scheme property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param property the coding scheme property.
	 */
	public void insertCodingSchemeProperty(String codingSchemeUri,
			String version, Property property) throws SQLException;

	/**
	 * update coding schme property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param property the coding scheme property.
	 */
	public void updateCodingSchemeProperty(String codingSchemeUri,
			String version, Property property) throws SQLException;

	/**
	 * remove coding schme property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param property the coding scheme property.
	 * @throws SQLException 
	 */
	public void removeCodingSchemeProperty(String codingSchemeUri,
			String version, Property property) throws SQLException;

	/**
	 * Insert entity property.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @param version the version
	 * @param entityCode the entity code
	 * @param entityCodeNamespace the entity code namespace
	 * @param property the property
	 */
	public void insertEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property) throws SQLException;

	/**
	 * Update entity property.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @param version the version
	 * @param entityCode the entity code
	 * @param entityCodeNamespace the entity code namespace
	 * @param property the property
	 */
	public void updateEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property) throws SQLException;

	/**
	 * Remove entity property.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @param version the version
	 * @param entityCode the entity code
	 * @param entityCodeNamespace the entity code namespace
	 * @param property the property
	 * @throws SQLException 
	 */
	public void removeEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property) throws SQLException;

	/**
	 * Insert batch entity properties.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @param version the version
	 * @param entityCode the entity code
	 * @param entityCodeNamespace the entity code namespace
	 * @param batch the batch
	 */
	public void insertBatchEntityProperties(String codingSchemeUri,
			String version, String entityCode, String entityCodeNamespace,
			List<Property> batch) throws SQLException;

	/**
	 * Insert relation property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param relationContainerName the relations container name.
	 * @param property the relation property object.
	 */
	public void insertRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property) throws SQLException;

	/**
	 * Update relation property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param relationContainerName the relations container name.
	 * @param property the relation property object.
	 */
	public void updateRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property) throws SQLException;

	/**
	 * Remove relation property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param relationContainerName the relations container name.
	 * @param property the relation property object.
	 */
	public void removeRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property) throws SQLException;

	/**
	 * Revise a coding scheme property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param property the coding scheme property object.
	 * 
	 * @throws LBException the LB exception
	 * @throws SQLException 
	 */
	public void reviseCodingSchemeProperty(String codingSchemeUri,
			String version, Property property) throws LBException, SQLException;

	/**
	 * Revise a entity property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param entityCode the entity code
	 * @param entityCodeNamespace the entity code namespace
	 * @param property the entity property object.
	 * 
	 * @throws LBException the LB exception
	 * @throws SQLException 
	 */
	public void reviseEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property)
			throws LBException, SQLException;

	/**
	 * Revise a relations property.
	 * 
	 * @param codingSchemeUri the coding scheme uri.
	 * @param version the coding scheme version.
	 * @param relationContainerName the relations container name.
	 * @param property the relation property object.
	 * 
	 * @throws LBException the LB exception
	 * @throws SQLException 
	 */
	public void reviseRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property) throws LBException, SQLException;
	
	/**
	 * Resolve properties of coding scheme by revision.
	 * 
	 * @param codingSchemeURI the coding scheme uri
	 * @param version the version
	 * @param revisionId the revision id
	 * 
	 * @return the list< property>
	 */
	public List<Property> resolvePropertiesOfCodingSchemeByRevision(
			String codingSchemeURI,
			String version,
			String revisionId) throws SQLException;
	
	/**
	 * Resolve properties of entity by revision.
	 * 
	 * @param codingSchemeURI the coding scheme uri
	 * @param version the version
	 * @param entityCode the entity code
	 * @param entityCodeNamespace the entity code namespace
	 * @param revisionId the revision id
	 * 
	 * @return the list< property>
	 */
	public List<Property> resolvePropertiesOfEntityByRevision(
			String codingSchemeURI,
			String version, 
			String entityCode, 
			String entityCodeNamespace,
			String revisionId) throws SQLException;
	
	/**
	 * Resolve properties of relation by revision.
	 * 
	 * @param codingSchemeURI the coding scheme uri
	 * @param version the version
	 * @param relationsName the relations name
	 * @param revisionId the revision id
	 * 
	 * @return the list< property>
	 */
	public List<Property> resolvePropertiesOfRelationByRevision(
			String codingSchemeURI,
			String version, 
			String relationsName,
			String revisionId) throws SQLException;
}