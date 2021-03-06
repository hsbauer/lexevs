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
package org.lexevs.dao.index.indexer;

import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.concepts.Entity;

/**
 * The Interface IndexCreator.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public interface IndexCreator {

	public enum IndexOption {ENTITY,SEARCH,BOTH}
	
	public String index(AbsoluteCodingSchemeVersionReference reference);

	public String index(AbsoluteCodingSchemeVersionReference reference, IndexOption option);
	
	public String index(AbsoluteCodingSchemeVersionReference reference, EntityIndexerProgressCallback callback);
	
	public String index(AbsoluteCodingSchemeVersionReference reference, EntityIndexerProgressCallback callback, IndexOption option);

	public String index(AbsoluteCodingSchemeVersionReference reference, EntityIndexerProgressCallback callback, boolean onlyRegister);
	
	public String index(AbsoluteCodingSchemeVersionReference reference, EntityIndexerProgressCallback callback, boolean onlyRegister, IndexOption option);
	
	public interface EntityIndexerProgressCallback {
		public void onEntityIndex(Entity entity);
	}
}