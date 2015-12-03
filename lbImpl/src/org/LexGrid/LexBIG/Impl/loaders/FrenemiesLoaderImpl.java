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
package org.LexGrid.LexBIG.Impl.loaders;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URI;

import org.LexGrid.LexBIG.DataModel.InterfaceElements.ExtensionDescription;
import org.LexGrid.LexBIG.Exceptions.LBException;
import org.LexGrid.LexBIG.Exceptions.LBInvocationException;
import org.LexGrid.LexBIG.Exceptions.LBParameterException;
import org.LexGrid.LexBIG.Extensions.Load.FrenemiesLoader;
import org.LexGrid.LexBIG.Extensions.Load.OBO_Loader;
import org.LexGrid.LexBIG.Extensions.Load.OntologyFormat;
import org.LexGrid.LexBIG.Extensions.Load.options.OptionHolder;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.LexGrid.codingSchemes.CodingScheme;
import org.LexGrid.relations.Relations;
import org.jdom.input.SAXBuilder;
import org.lexevs.dao.database.operation.LexEvsDatabaseOperations.RootOrTail;
import org.lexevs.dao.database.operation.LexEvsDatabaseOperations.TraverseAssociations;
import org.lexevs.lexgrid.loader.tutorial.Frenemies2LGMain;
import org.springframework.util.Assert;

import edu.mayo.informatics.lexgrid.convert.directConversions.obo1_2.OBO2LGMain;
import edu.mayo.informatics.lexgrid.convert.directConversions.obo1_2.OBOFormatValidator;
import edu.mayo.informatics.lexgrid.convert.exceptions.ConnectionFailure;
import edu.mayo.informatics.lexgrid.convert.utility.ManifestUtil;
import edu.mayo.informatics.lexgrid.convert.utility.URNVersionPair;

/**
 * Class to load OBO files.
 * 
 * @author <A HREF="mailto:armbrust.daniel@mayo.edu">Dan Armbrust</A>
 * @author <A HREF="mailto:erdmann.jesse@mayo.edu">Jesse Erdmann</A>
 * @version subversion $Revision: $ checked in on $Date: $
 */
public class FrenemiesLoaderImpl extends BaseLoader implements FrenemiesLoader {
    private static final long serialVersionUID = 8418561158634673381L;

    public FrenemiesLoaderImpl() {
        super();
        this.setDoApplyPostLoadManifest(false);
    }



    protected ExtensionDescription buildExtensionDescription(){
        ExtensionDescription temp = new ExtensionDescription();
        temp.setExtensionBaseClass(FrenemiesLoaderImpl.class.getInterfaces()[0].getName());
        temp.setExtensionClass(FrenemiesLoaderImpl.class.getName());
        temp.setDescription(description);
        temp.setName(name);
        temp.setVersion("1.0");

        return temp;
    }


    public void load(URI uri, URI metaSource, boolean stopOnErrors, boolean async) throws LBParameterException,
            LBInvocationException {
        this.getOptions().getBooleanOption(FAIL_ON_ERROR_OPTION).setOptionValue(stopOnErrors);
        this.getOptions().getBooleanOption(ASYNC_OPTION).setOptionValue(async);
        
        this.load(uri);
    }

    @Override
    protected OptionHolder declareAllowedOptions(OptionHolder holder) {
        return holder;
    }

    @Override
    protected URNVersionPair[] doLoad() throws Exception {
      
        Frenemies2LGMain mainTxfm = new Frenemies2LGMain();
        CodingScheme codingScheme = mainTxfm.map(this.getResourceUri());

        this.persistCodingSchemeToDatabase(codingScheme);
        
        URNVersionPair urnVersion = new URNVersionPair(codingScheme.getCodingSchemeURI(), codingScheme.getRepresentsVersion());
     
        this.buildRootNode(
                Constructors.createAbsoluteCodingSchemeVersionReference(
                codingScheme.getCodingSchemeURI(), codingScheme.getRepresentsVersion()), 
                null, 
                getRelationsContainerName(codingScheme), 
                RootOrTail.TAIL,
                TraverseAssociations.TOGETHER);
        
        return new URNVersionPair[]{urnVersion};
    }
    
    private String getRelationsContainerName(CodingScheme codingScheme) {
        Relations[] relations = codingScheme.getRelations();
        Assert.state(relations.length == 1);
        
        return relations[0].getContainerName();
    }
    
//    @Override
//    public OntologyFormat getOntologyFormat() {
//        return OntologyFormat.OBO;
//    }

    public void finalize() throws Throwable {
        getLogger().loadLogDebug("Freeing FrenemiesLoaderImpl");
        super.finalize();
    }



    @Override
    public void validate(URI sourceDir, int validationLevel) throws LBException {
        // TODO Auto-generated method stub
        
    }

}