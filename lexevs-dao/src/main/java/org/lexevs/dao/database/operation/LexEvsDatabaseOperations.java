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
package org.lexevs.dao.database.operation;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.lexevs.dao.database.key.incrementer.PrimaryKeyIncrementer;
import org.lexevs.dao.database.operation.transitivity.TransitivityBuilder.TransitivityTableState;
import org.lexevs.dao.database.prefix.PrefixResolver;
import org.lexevs.dao.database.type.DatabaseType;
import org.lexevs.dao.database.utility.DatabaseUtility;
import org.springframework.transaction.PlatformTransactionManager;

/**
 * The Interface PersistenceConnectionManager.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public interface LexEvsDatabaseOperations {

	public enum RootOrTail {ROOT,TAIL}
	
	public enum TraverseAssociations {TOGETHER,INDIVIDUALLY}
	
	public void createCommonTables();
	
	public void createCodingSchemeTables();
	
	public void createValueSetsTables();
	
	public void createValueSetHistoryTables();
	
	public void createCodingSchemeHistoryTables();
	
	public void createCodingSchemeHistoryTables(String prefix);
	
	public void createCodingSchemeTables(String prefix);
	
	public void createNciHistoryTables();

	public void dropAllTables();
	
	public void createAllTables();
	
	public void dropCommonTables();
	
	public void dropValueSetsTables();
	
	public void dropValueSetHistoryTables();
	
	public void dropCodingSchemeHistoryTables(String codingSchemeUri, String version);
	
	public void dropCodingSchemeTables(String codingSchemeUri, String version);
	
	public void dropCodingSchemeTablesByPrefix(String prefix);
	
	public void dropCodingSchemeHistoryTables();
	
	public void dropCodingSchemeTables();
	
	public void dropNciHistoryTables();
	
	public void dumpSqlScripts(DatabaseType databaseType, String outputPath, String prefix) throws IOException;
	
	public boolean isCodingSchemeLoaded(String codingSchemeUri, String version);
	
	public void reComputeTransitiveTable(String codingSchemeUri,
			String codingSchemeVersion) throws SQLException;
	
	public TransitivityTableState isTransitiveTableComputed(String codingSchemeUri,
			String codingSchemeVersion) throws SQLException;

	/**
	 * Gets the database utility.
	 * 
	 * @return the database utility
	 */
	public DatabaseUtility getDatabaseUtility();
	
	public PrimaryKeyIncrementer getPrimaryKeyIncrementer();
	
	/**
	 * Gets the data source.
	 * 
	 * @return the data source
	 */
	public DataSource getDataSource();
	
	/**
	 * Gets the transaction manager.
	 * 
	 * @return the transaction manager
	 */
	public PlatformTransactionManager getTransactionManager();
	
	/**
	 * Gets the prefix resolver.
	 * 
	 * @return the prefix resolver
	 */
	public PrefixResolver getPrefixResolver();
	
	/**
	 * Gets the database type.
	 * 
	 * @return the database type
	 */
	public DatabaseType getDatabaseType();
	
	/**
	 * Compute transitive table.
	 * 
	 * @param codingSchemeName the coding scheme name
	 * @param codingSchemeUri the coding scheme uri
	 * @param version the version
	 * @throws SQLException 
	 */
	public void computeTransitiveTable(String codingSchemeUri, String codingSchemeVersion) throws SQLException;
	
	public void addRootRelationNode(String codingSchemeUri, String codingSchemeVersion, 
			List<String> associationNames, String relationContainerName, 
			RootOrTail rootOrTail, TraverseAssociations traverse) throws SQLException;

	void dropCodingSchemeHistoryTablesByPrefix(String prefix);

	
}