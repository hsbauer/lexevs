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
package org.lexevs.dao.database.service.listener;

import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.concepts.Entity;
import org.apache.commons.lang.StringUtils;
import org.lexevs.locator.LexEvsServiceLocator;


/**
 * The listener interface for receiving nullEntityNamespace events.
 * The class that is interested in processing a nullEntityNamespace
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addNullEntityNamespaceListener<code> method. When
 * the nullEntityNamespace event occurs, that object's appropriate
 * method is invoked.
 * 
 * @see NullEntityNamespaceEvent
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public class NullEntityNamespaceListener extends AbstractPreEntityInsertValidatingListener{

	/* (non-Javadoc)
	 * @see org.lexevs.dao.database.service.listener.AbstractPreEntityInsertValidatingListener#doValidate(java.lang.String, java.lang.String, org.LexGrid.concepts.Entity)
	 */
	protected boolean doValidate(String uri, String version, Entity entity) {

		if(StringUtils.isBlank(entity.getEntityCodeNamespace())){
			CodingScheme cs = LexEvsServiceLocator.getInstance().
			getDatabaseServiceManager().
			getCodingSchemeService().getCodingSchemeByUriAndVersion(uri, version);

			entity.setEntityCodeNamespace(cs.getCodingSchemeName());
		}

		return true;
	}
}