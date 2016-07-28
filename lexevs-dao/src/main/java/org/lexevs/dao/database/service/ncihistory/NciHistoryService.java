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
package org.lexevs.dao.database.service.ncihistory;

import java.net.URI;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.LexGrid.LexBIG.DataModel.Collections.CodingSchemeVersionList;
import org.LexGrid.LexBIG.DataModel.Collections.NCIChangeEventList;
import org.LexGrid.LexBIG.DataModel.Collections.SystemReleaseList;
import org.LexGrid.LexBIG.DataModel.Core.ConceptReference;
import org.LexGrid.LexBIG.DataModel.InterfaceElements.SystemReleaseDetail;
import org.LexGrid.LexBIG.DataModel.NCIHistory.NCIChangeEvent;
import org.LexGrid.versions.CodingSchemeVersion;
import org.LexGrid.versions.SystemRelease;

/**
 * The Interface NciHistoryService.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public interface NciHistoryService {
	
	/** The date format. */
	public static SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yy");

	/**
	 * Insert system release.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @param systemRelease the system release
	 * @throws SQLException 
	 */
	public void insertSystemRelease(String codingSchemeUri, SystemRelease systemRelease) throws SQLException;
	
	/**
	 * Insert nci change event.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @param changeEvent the change event
	 */
	public void insertNCIChangeEvent(String codingSchemeUri, NCIChangeEvent changeEvent) throws SQLException;
	
	/**
	 * Gets the base lines.
	 * 
	 * @param uri the uri
	 * @param releasedAfter the released after
	 * @param releasedBefore the released before
	 * 
	 * @return the base lines
	 */
	public SystemReleaseList getBaseLines(String uri, Date releasedAfter, Date releasedBefore) throws SQLException;
	
	/**
	 * Gets the earliest base line.
	 * 
	 * @param uri the uri
	 * 
	 * @return the earliest base line
	 */
	public SystemRelease getEarliestBaseLine(String uri)  throws SQLException;
	
	/**
	 * Gets the latest base line.
	 * 
	 * @param uri the uri
	 * 
	 * @return the latest base line
	 */
	public SystemRelease getLatestBaseLine(String uri) throws SQLException;
	
	/**
	 * Gets the system release.
	 * 
	 * @param uri the uri
	 * @param releaseURN the release urn
	 * 
	 * @return the system release
	 */
	public SystemReleaseDetail getSystemRelease(String uri, URI releaseURN) throws SQLException;

	/**
	 * Gets the edits the action list.
	 * 
	 * @param uri the uri
	 * @param conceptReference the concept reference
	 * @param date the date
	 * 
	 * @return the edits the action list
	 */
	public NCIChangeEventList getEditActionList(String uri, ConceptReference conceptReference,
			Date date) throws SQLException;

	/**
	 * Gets the edits the action list.
	 * 
	 * @param uri the uri
	 * @param conceptReference the concept reference
	 * @param beginDate the begin date
	 * @param endDate the end date
	 * 
	 * @return the edits the action list
	 */
	public NCIChangeEventList getEditActionList(String uri, ConceptReference conceptReference, Date beginDate,
			Date endDate) throws SQLException;

	/**
	 * Gets the edits the action list.
	 * 
	 * @param uri the uri
	 * @param conceptReference the concept reference
	 * @param releaseURN the release urn
	 * 
	 * @return the edits the action list
	 */
	public NCIChangeEventList getEditActionList(String uri, ConceptReference conceptReference, URI releaseURN) throws SQLException;

	/**
	 * Gets the concept creation version.
	 * 
	 * @param urn the urn
	 * @param conceptReference the concept reference
	 * 
	 * @return the concept creation version
	 */
	public CodingSchemeVersion getConceptCreationVersion(String urn, ConceptReference conceptReference) throws SQLException;

	/**
	 * Gets the concept change versions.
	 * 
	 * @param urn the urn
	 * @param conceptReference the concept reference
	 * @param beginDate the begin date
	 * @param endDate the end date
	 * 
	 * @return the concept change versions
	 */
	public CodingSchemeVersionList getConceptChangeVersions(String urn, ConceptReference conceptReference,
			Date beginDate, Date endDate) throws SQLException;

	/**
	 * Gets the descendants.
	 * 
	 * @param uri the uri
	 * @param conceptReference the concept reference
	 * 
	 * @return the descendants
	 */
	public NCIChangeEventList getDescendants(String uri, ConceptReference conceptReference) throws SQLException;

	/**
	 * Gets the ancestors.
	 * 
	 * @param uri the uri
	 * @param conceptReference the concept reference
	 * 
	 * @return the ancestors
	 */
	public NCIChangeEventList getAncestors(String uri, ConceptReference conceptReference) throws SQLException;
	
	/**
	 * Removes the nci history.
	 * 
	 * @param codingSchemeUri the coding scheme uri
	 * @throws SQLException 
	 */
	public void removeNciHistory(String codingSchemeUri) throws SQLException;
}