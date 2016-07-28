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
package org.lexevs.dao.database.ibatis.batch;

import java.sql.SQLException;

import org.lexevs.dao.database.inserter.Inserter;

import com.ibatis.sqlmap.client.SqlMapClient;


/**
 * The Class SqlMapClientTemplateInserter.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class SqlMapClientTemplateInserter implements Inserter{

	/** The sql map client template. */
	private SqlMapClient sqlMapClientTemplate;
	
	/**
	 * Instantiates a new sql map client template inserter.
	 * 
	 * @param sqlMapClientTemplate the sql map client template
	 */
	public SqlMapClientTemplateInserter(SqlMapClient sqlMapClientTemplate){
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}
	
	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.ibatis.batch.Inserter#insert(java.lang.String, java.lang.Object)
	 */
	public void insert(String sql, Object parameter) throws SQLException {
		sqlMapClientTemplate.insert(sql, parameter);
	}

	/**
	 * Sets the sql map client template.
	 * 
	 * @param sqlMapClientTemplate the new sql map client template
	 */
	public void setSqlMapClientTemplate(SqlMapClient sqlMapClientTemplate) {
		this.sqlMapClientTemplate = sqlMapClientTemplate;
	}

	/**
	 * Gets the sql map client template.
	 * 
	 * @return the sql map client template
	 */
	public SqlMapClient getSqlMapClientTemplate() {
		return sqlMapClientTemplate;
	}

}