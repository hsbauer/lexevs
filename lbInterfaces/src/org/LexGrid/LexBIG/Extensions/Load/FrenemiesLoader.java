package org.LexGrid.LexBIG.Extensions.Load;

import java.net.URI;

import org.LexGrid.LexBIG.Exceptions.LBException;

public interface FrenemiesLoader extends Loader {
	
	public final static String name = "FrenimiesLoader";
	public final static String description = "friends and enemies terminology loader";
	
	 /** Load content from a candidate resource. This will also result in implicit
	 * generation of standard indices required by the LexBIG runtime.
	 * 
	 * An exception is raised if resources cannot be accessed or another load
	 * operation is already in progress.
	 * 
	 * @param source
	 *            URI corresponding to the MedDRA file.
     * @param metaSource
     *            URI corresponding to the MedDRA metadata XML file.  Optional.           
	 * @param stopOnErrors
	 *            True means stop if any load error is detected. False means
	 *            attempt to load what can be loaded if recoverable errors are
	 *            encountered.
	 * @param async
	 *            Flag controlling whether load occurs in the calling thread.  
	 *            If true, the load will occur in a separate asynchronous process.
	 *            If false, this method blocks until the load operation
	 *            completes or fails. Regardless of setting, the getStatus and
	 *            getLog calls are used to fetch results.
	 * @throws LBException
	 */
	public void load(URI sourceDir, URI CUISourceFile, boolean stopOnErrors, boolean async) throws LBException;

	/**
	 * Validate content for a candidate resource without performing a load.
	 * 
	 * Returns without exception if validation succeeds.
	 * 
	 * @throws LBException
	 * 
	 * @param source
	 *            URI corresponding to the MedDRA directory which contains datafiles.
	 * @param validationLevel
	 * 			  Currently ignored
	 * @throws LBException
	 */
	public void validate(URI sourceDir, int validationLevel) throws LBException;


}
