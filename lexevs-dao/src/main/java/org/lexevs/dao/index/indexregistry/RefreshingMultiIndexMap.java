package org.lexevs.dao.index.indexregistry;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.lucene.index.DirectoryReader;
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
	
	public LuceneIndexTemplate getRefreshedTemplate(String key){
	LuceneIndexTemplate template = map.get(key);
	try {
		DirectoryReader.openIfChanged((DirectoryReader) ((MultiBaseLuceneIndexTemplate)template).getIndexReader());
	} catch (IOException e) {
		throw new RuntimeException("The following index failed to be refreshed in the cache: " + template.getIndexName(), e);
	}
	return template;
	}
	
	public boolean containsKey(String key){
		return map.containsKey(key);
	}
	
	public LuceneIndexTemplate get(String key){
		return this.getRefreshedTemplate(key);
	}
	

}
