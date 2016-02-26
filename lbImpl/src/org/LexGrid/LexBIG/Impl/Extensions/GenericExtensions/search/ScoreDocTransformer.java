package org.LexGrid.LexBIG.Impl.Extensions.GenericExtensions.search;

import java.util.List;

import org.LexGrid.LexBIG.DataModel.Collections.ResolvedConceptReferenceList;
import org.LexGrid.LexBIG.DataModel.Core.AbsoluteCodingSchemeVersionReference;
import org.LexGrid.LexBIG.DataModel.Core.ResolvedConceptReference;
import org.LexGrid.LexBIG.Impl.helpers.AbstractListBackedResolvedConceptReferencesIterator.Transformer;
import org.LexGrid.LexBIG.Utility.Constructors;
import org.apache.commons.lang.StringUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.search.ScoreDoc;
import org.lexevs.locator.LexEvsServiceLocator;

public class ScoreDocTransformer implements Transformer<ScoreDoc> {
    private List<AbsoluteCodingSchemeVersionReference> references;
    
    public ScoreDocTransformer(List<AbsoluteCodingSchemeVersionReference> references){
        this.references = references;
    }

    private static final long serialVersionUID = 7176335324999288237L;

    @Override
    public ResolvedConceptReferenceList transform(Iterable<ScoreDoc> items) {
        ResolvedConceptReferenceList list = new ResolvedConceptReferenceList();
        for(ScoreDoc item : items){
            list.addResolvedConceptReference(this.doTransform(item));
        }
        
        return list;
    }
    
    protected ResolvedConceptReference doTransform(ScoreDoc item) {
        Document doc = 
            LexEvsServiceLocator.getInstance().
                getIndexServiceManager().
                getEntityIndexService().
                getDocumentFromCommonIndexById(references, item.doc);
        
        String code = doc.get("entityCode");
        String namespace = doc.get("entityCodeNamespace");
        String[] types = doc.getValues("type");
        String description = doc.get("entityDescription");
        String codingSchemeUri = doc.get("codingSchemeUri");
        String codingSchemeName = doc.get("codingSchemeName");
        String codingSchemeVersion = doc.get("codingSchemeVersion");
        
        ResolvedConceptReference ref = new ResolvedConceptReference();
        ref.setCode(code);
        ref.setCodeNamespace(namespace);
        ref.setEntityType(types);
        ref.setCodingSchemeName(codingSchemeName);
        ref.setCodingSchemeURI(codingSchemeUri);
        ref.setCodingSchemeVersion(codingSchemeVersion);
        if(StringUtils.isNotBlank(description)){
            ref.setEntityDescription(Constructors.createEntityDescription(description));
        }
        
        return ref;
    }
    
}