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
package org.lexevs.dao.database.access.valuesets;

import java.sql.SQLException;

import org.LexGrid.LexBIG.Exceptions.LBRevisionException;
import org.LexGrid.valueSets.PickListEntryNode;
import org.lexevs.dao.database.access.LexGridSchemaVersionAwareDao;

public interface PickListEntryNodeDao extends LexGridSchemaVersionAwareDao {

/**
	 * Insert pick list entry.
	 * 
	 * @param pickListGuid the pick list definition GUID
	 * @param entryNode the pick list entry node
	 * 
	 * @return the string
 * @throws SQLException 
	 */
public String insertPickListEntry(String pickListGuid, PickListEntryNode entryNode) throws SQLException;

	public String getPickListEntryNodeUId(String pickListId, String pickListEntryNodeId) throws SQLException;

	public void removeAllPickListEntryNodeMultiAttributes(String pickListEntryNodeUId) throws SQLException;

	public String insertHistoryPickListEntryNode(String pickListEntryNodeUId) throws SQLException;

	public String updatePickListEntryNode(String pickListEntryNodeUId,
			PickListEntryNode pickListEntryNode) throws SQLException;

	public String updateVersionableAttributes(String pickListEntryNodeUId,
			PickListEntryNode pickListEntryNode) throws SQLException;

	public String getPickListEntryStateUId(String pickListEntryNodeUId) throws SQLException;

	public void updateEntryStateUId(String pickListEntryNodeUId,
			String entryStateUId) throws SQLException;

	public void createEntryStateIfAbsent(String entryStateUId, String vsPLEntryUId) throws SQLException;

	public String getLatestRevision(String pickListEntryNodeUId) throws SQLException;

	public void deletePLEntryNodeByUId(String pickListEntryNodeUId) throws SQLException;

	public PickListEntryNode resolvePLEntryNodeByRevision(
			String pickListId, String plEntryId, String revisionId) throws LBRevisionException, SQLException;

}