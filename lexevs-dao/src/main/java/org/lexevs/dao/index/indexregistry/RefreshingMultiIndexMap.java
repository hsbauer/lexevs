package org.lexevs.dao.index.indexregistry;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
import org.lexevs.dao.index.lucenesupport.LuceneDirectoryFactory.NamedDirectory;
import org.lexevs.dao.index.lucenesupport.MultiBaseLuceneIndexTemplate;
import org.lexevs.dao.index.lucenesupport.LuceneIndexTemplate;

public class RefreshingMultiIndexMap {
	
	private Map<String,LuceneIndexTemplate> map;

	public RefreshingMultiIndexMap() {
		map = new HashMap<String,LuceneIndexTemplate>();
	}
	
	public LuceneIndexTemplate put(String key, LuceneIndexTemplate value){
		return map.put(key, value);
	}
	
	private LuceneIndexTemplate getRefreshedTemplate(String key){
	boolean refreshed = false;
	LuceneIndexTemplate template = map.get(key);

		 List<NamedDirectory> directories = ((MultiBaseLuceneIndexTemplate)template).getNamedDirectories();
		 for(NamedDirectory dir: directories){
			 if(dir.isReaderClosed()){
				 dir.refresh();
				 refreshed = true;
			 }
		 }
    if(refreshed){return new MultiBaseLuceneIndexTemplate(directories);}
	return template;
	}
	
	public boolean containsKey(String key){
		return map.containsKey(key);
	}
	
	public LuceneIndexTemplate get(String key){
		return this.getRefreshedTemplate(key);
	}
	

}
