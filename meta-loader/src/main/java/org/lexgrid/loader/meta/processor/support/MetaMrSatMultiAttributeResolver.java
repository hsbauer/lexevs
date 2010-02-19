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
package org.lexgrid.loader.meta.processor.support;


import org.LexGrid.persistence.model.EntityPropertyMultiAttrib;
import org.LexGrid.persistence.model.EntityPropertyMultiAttribId;
import org.lexgrid.loader.processor.CodingSchemeIdAwareProcessor;
import org.lexgrid.loader.processor.support.MultiAttribResolver;
import org.lexgrid.loader.rrf.model.Mrsat;

/**
 * @author <a href="mailto:scott.bauer@mayo.edu">Scott Bauer</a>
 * @param <I>
 */
public class MetaMrSatMultiAttributeResolver<I>  extends CodingSchemeIdAwareProcessor {

	/**
	 * @param resolver
	 * @param item
	 * @return propertyQualifier:  the qualifier resolved from a MRSAT row.  Returns null
	 * if the value of SUPPRESS is 'N'
	 */
	public EntityPropertyMultiAttrib getEntitPropMultiAttrib(MultiAttribResolver<Mrsat> resolver, Mrsat item) {
		EntityPropertyMultiAttrib propertyQualifier = new EntityPropertyMultiAttrib();
		EntityPropertyMultiAttribId qualifierId = new EntityPropertyMultiAttribId();
		qualifierId.setCodingSchemeName(getCodingSchemeIdSetter().getCodingSchemeName());
		qualifierId.setEntityCodeNamespace(getCodingSchemeIdSetter().getCodingSchemeName());
		qualifierId.setEntityCode(resolver.getEntityCode(item));	
		qualifierId.setTypeName(resolver.getTypeName());
		qualifierId.setPropertyId(resolver.getId(item));
		qualifierId.setAttributeValue(resolver.getAttributeValue(item));
		qualifierId.setVal1(resolver.getVal1(item));
		propertyQualifier.setId(qualifierId);
		if(qualifierId.getVal1() == null) {
			return null;
		}
		return propertyQualifier;
	}
}
