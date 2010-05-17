/*
 * Copyright: (c) 2004-2009 Mayo Foundation for Medical Education and 
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

import java.util.List;

import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.commonTypes.Property;

/**
 * The Interface PropertyService.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public interface PropertyService {
	public static final String INSERT_BATCH_PROPERTY_ERROR = "INSERT-BATCH-PROPERTY-ERROR";
	
	public static final String INSERT_CODINGSCHEME_PROPERTY_ERROR = "INSERT-CODING-SCHEME-PROPERTY-ERROR";
	public static final String INSERT_CODINGSCHEME_PROPERTY_VERSIONABLE_CHANGES_ERROR = "INSERT-CODINGSCHEME-PROPERTY-VERSIONABLE-CHANGES-ERROR";
	public static final String UPDATE_CODINGSCHEME_PROPERTY_ERROR = "UPDATE-CODINGSCHEME-PROPERTY-ERROR";
	public static final String REMOVE_CODINGSCHEME_PROPERTY_ERROR = "REMOVE-CODINGSCHEME-PROPERTY-ERROR";
	
	public static final String INSERT_ENTITY_PROPERTY_ERROR = "INSERT-ENTITY-PROPERTY-ERROR";
	public static final String INSERT_ENTITY_PROPERTY_VERSIONABLE_CHANGES_ERROR = "INSERT-ENTITY-PROPERTY-VERSIONABLE-CHANGES-ERROR";
	public static final String UPDATE_ENTITY_PROPERTY_ERROR = "UPDATE-ENTITY-PROPERTY-ERROR";
	public static final String REMOVE_ENTITY_PROPERTY_ERROR = "REMOVE-ENTITY-PROPERTY-ERROR";
	
	public static final String INSERT_RELATION_PROPERTY_ERROR = "INSERT-RELATION-PROPERTY-ERROR";
	public static final String INSERT_RELATION_PROPERTY_VERSIONABLE_CHANGES_ERROR = "INSERT-RELATION-PROPERTY-VERSIONABLE-CHANGES-ERROR";
	public static final String UPDATE_RELATION_PROPERTY_ERROR = "UPDATE-RELATION-PROPERTY-ERROR";
	public static final String REMOVE_RELATION_PROPERTY_ERROR = "REMOVE-RELATION-PROPERTY-ERROR";
	
	/**
	 * Insert coding scheme property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param property
	 *            the coding scheme property.
	 */
	public void insertCodingSchemeProperty(String codingSchemeUri,
			String version, Property property);

	/**
	 * update coding schme property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param property
	 *            the coding scheme property.
	 */
	public void updateCodingSchemeProperty(String codingSchemeUri,
			String version, Property property);

	/**
	 * remove coding schme property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param property
	 *            the coding scheme property.
	 */
	public void removeCodingSchemeProperty(String codingSchemeUri,
			String version, Property property);

	/**
	 * insert versionable changes to coding schme property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param property
	 *            the coding scheme property.
	 */
	public void insertCodingSchemePropertyVersionableChanges(
			String codingSchemeUri, String version, Property property);

	/**
	 * Insert entity property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri
	 * @param version
	 *            the version
	 * @param entityCode
	 *            the entity code
	 * @param entityCodeNamespace
	 *            the entity code namespace
	 * @param property
	 *            the property
	 */
	public void insertEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property);

	/**
	 * Update entity property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri
	 * @param version
	 *            the version
	 * @param entityCode
	 *            the entity code
	 * @param entityCodeNamespace
	 *            the entity code namespace
	 * @param property
	 *            the property
	 */
	public void updateEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property);

	/**
	 * Remove entity property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri
	 * @param version
	 *            the version
	 * @param entityCode
	 *            the entity code
	 * @param entityCodeNamespace
	 *            the entity code namespace
	 * @param property
	 *            the property
	 */
	public void removeEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property);

	/**
	 * Insert versionable changes to entity property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri
	 * @param version
	 *            the version
	 * @param entityCode
	 *            the entity code
	 * @param entityCodeNamespace
	 *            the entity code namespace
	 * @param property
	 *            the property
	 */
	public void insertEntityPropertyVersionableChanges(String codingSchemeUri,
			String version, String entityCode, String entityCodeNamespace,
			Property property);

	/**
	 * Insert batch entity properties.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri
	 * @param version
	 *            the version
	 * @param entityCode
	 *            the entity code
	 * @param entityCodeNamespace
	 *            the entity code namespace
	 * @param batch
	 *            the batch
	 */
	public void insertBatchEntityProperties(String codingSchemeUri,
			String version, String entityCode, String entityCodeNamespace,
			List<Property> batch);

	/**
	 * Insert relation property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param relationContainerName
	 *            the relations container name.
	 * 
	 * @param property
	 *            the relation property object.
	 */
	public void insertRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property);

	/**
	 * Update relation property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param relationContainerName
	 *            the relations container name.
	 * 
	 * @param property
	 *            the relation property object.
	 */
	public void updateRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property);

	/**
	 * Remove relation property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param relationContainerName
	 *            the relations container name.
	 * 
	 * @param property
	 *            the relation property object.
	 */
	public void removeRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property);

	/**
	 * Insert relation property versionable changes.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param relationContainerName
	 *            the relations container name.
	 * @param property
	 *            the relation property object.
	 */
	public void insertRelationPropertyVersionableChanges(
			String codingSchemeUri, String version,
			String relationContainerName, Property property);

	/**
	 * Revise a coding scheme property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param property
	 *            the coding scheme property object.
	 * @throws LBException
	 */
	public void reviseCodingSchemeProperty(String codingSchemeUri,
			String version, Property property) throws LBException;

	/**
	 * Revise a entity property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param entityCode
	 *            the entity code
	 * @param entityCodeNamespace
	 *            the entity code namespace
	 * @param property
	 *            the entity property object.
	 * @throws LBException
	 */
	public void reviseEntityProperty(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace, Property property)
			throws LBException;

	/**
	 * Revise a relations property.
	 * 
	 * @param codingSchemeUri
	 *            the coding scheme uri.
	 * @param version
	 *            the coding scheme version.
	 * @param relationContainerName
	 *            the relations container name.
	 * @param property
	 *            the relation property object.
	 * @throws LBException
	 */
	public void reviseRelationProperty(String codingSchemeUri, String version,
			String relationContainerName, Property property) throws LBException;
}
