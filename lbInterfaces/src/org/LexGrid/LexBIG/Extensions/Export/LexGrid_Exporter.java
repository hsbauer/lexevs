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
package org.LexGrid.LexBIG.Extensions.Export;

import java.net.URI;
import java.util.HashMap;

import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.valueSets.ValueSetDefinition;

/**
 * Exports content to LexGrid cononical XML format.
 */
public interface LexGrid_Exporter extends Exporter {

	/**
	 * Return a reference to the XML Schema that this loader supports.
	 */
	public URI getSchemaURL();

	/**
	 * Return the version identifier of the schema that this loader supports.
	 * (e.g. 2004/02, 2005/01, ...).
	 */
	public String getSchemaVersion();

	/**
	 * Export content from the underlying LexGrid repository.
	 * 
	 * An exception is raised if resources cannot be accessed or another load
	 * operation is already in progress.
	 * 
	 * @param source
	 *            The absolute version identifier of the coding scheme to export.
	 * @param destination
	 *            URI corresponding to the XML file to write.
	 * @param overwrite
	 *            True indicates to overwrite an existing file if present. False
	 *            indicates to stop if the destination file already exists.
	 * @param stopOnErrors
	 *            True means stop if any export error is detected. False means
	 *            attempt to continue writing what can be exported if
	 *            recoverable errors are encountered.
	 * @param async
	 *            Flag controlling whether export occurs in the calling thread.
	 *            If true, the export will occur in a separate asynchronous
	 *            process. If false, this method blocks until the export
	 *            operation completes or fails. Regardless of setting, the
	 *            getStatus and getLog calls are used to fetch results.
	 * @throws LBException
	 */
	public void export(AbsoluteCodingSchemeVersionReference source,
			URI destination, boolean overwrite, boolean stopOnErrors, boolean async)
			throws LBException;
	
	/**
	 * Export Value Set Definition from the underlying LexGrid repository.
	 * 
	 * An exception is raised if resources cannot be accessed or another load
	 * operation is already in progress.
	 * 
	 * @param valueSetDefinitionURI
	 *            URI of the value set definition to export.
	 * @param valueSetDefinitionRevisionId
	 * 			  RevisionId of the value set definition to export.
	 * @param destination
	 *            URI corresponding to the XML file to write.
	 * @param overwrite
	 *            True indicates to overwrite an existing file if present. False
	 *            indicates to stop if the destination file already exists.
	 * @param stopOnErrors
	 *            True means stop if any export error is detected. False means
	 *            attempt to continue writing what can be exported if
	 *            recoverable errors are encountered.
	 * @param async
	 *            Flag controlling whether export occurs in the calling thread.
	 *            If true, the export will occur in a separate asynchronous
	 *            process. If false, this method blocks until the export
	 *            operation completes or fails. Regardless of setting, the
	 *            getStatus and getLog calls are used to fetch results.
	 * @throws LBException
	 */
	public void exportValueSetDefinition(URI valueSetDefinitionURI, String valueSetDefinitionRevisionId, 
			URI destination, boolean overwrite, boolean stopOnErros, boolean async)
			throws LBException;
	
	/**
	 * Exports expanded contents of Value Set Definition from the underlying LexGrid repository.
	 * 
	 * An exception is raised if resources cannot be accessed or another load
	 * operation is already in progress.
	 * 
	 * @param valueSetDefinitionURI
	 *            URI of the value set definition to export.
	 * @param valueSetDefinitionRevisionId
	 * 			  RevisionId of the value set definition to export.
	 * @param destination
	 *            URI corresponding to the XML file to write.
	 * @param overwrite
	 *            True indicates to overwrite an existing file if present. False
	 *            indicates to stop if the destination file already exists.
	 * @param stopOnErrors
	 *            True means stop if any export error is detected. False means
	 *            attempt to continue writing what can be exported if
	 *            recoverable errors are encountered.
	 * @param async
	 *            Flag controlling whether export occurs in the calling thread.
	 *            If true, the export will occur in a separate asynchronous
	 *            process. If false, this method blocks until the export
	 *            operation completes or fails. Regardless of setting, the
	 *            getStatus and getLog calls are used to fetch results.
	 * @throws LBException
	 */
	public void exportValueSetResolution(URI valueSetDefinitionURI, String valueSetDefinitionRevisionId, 
			URI destination, boolean overwrite, boolean stopOnErros, boolean async)
			throws LBException;
	
	/**
	 * Exports expanded contents of supplied Value Set Definition object.
	 * 
	 * An exception is raised if resources cannot be accessed or another load
	 * operation is already in progress.
	 * 
	 * @param valueSetDefinition
	 *            value set definition object to be resolved and export.
	 * @param referencedVSDs
	 * 			  List of ValueSetDefinitions referenced by valueSetDefinition. 
	 * 			  If provided, these ValueSetDefinitions will be used to resolve vsDef.	
	 * @param destination
	 *            URI corresponding to the XML file to write.
	 * @param overwrite
	 *            True indicates to overwrite an existing file if present. False
	 *            indicates to stop if the destination file already exists.
	 * @param stopOnErrors
	 *            True means stop if any export error is detected. False means
	 *            attempt to continue writing what can be exported if
	 *            recoverable errors are encountered.
	 * @param async
	 *            Flag controlling whether export occurs in the calling thread.
	 *            If true, the export will occur in a separate asynchronous
	 *            process. If false, this method blocks until the export
	 *            operation completes or fails. Regardless of setting, the
	 *            getStatus and getLog calls are used to fetch results.
	 * @throws LBException
	 */
	public void exportValueSetResolution(ValueSetDefinition valueSetDefinition, HashMap<String, ValueSetDefinition> referencedVSDs, 
			URI destination, boolean overwrite, boolean stopOnErros, boolean async)
			throws LBException;
	
	/**
	 * Export content of PickList Definition from the underlying LexGrid repository.
	 * 
	 * An exception is raised if resources cannot be accessed or another load
	 * operation is already in progress.
	 * 
	 * @param pickListId
	 *            ID of the pick list definition to export.
	 * @param destination
	 *            URI corresponding to the XML file to write.
	 * @param overwrite
	 *            True indicates to overwrite an existing file if present. False
	 *            indicates to stop if the destination file already exists.
	 * @param stopOnErrors
	 *            True means stop if any export error is detected. False means
	 *            attempt to continue writing what can be exported if
	 *            recoverable errors are encountered.
	 * @param async
	 *            Flag controlling whether export occurs in the calling thread.
	 *            If true, the export will occur in a separate asynchronous
	 *            process. If false, this method blocks until the export
	 *            operation completes or fails. Regardless of setting, the
	 *            getStatus and getLog calls are used to fetch results.
	 * @throws LBException
	 */
	public void exportPickListDefinition(String pickListId, 
			URI destination, boolean overwrite, boolean stopOnErros, boolean async)
			throws LBException;
}