package org.lexevs.dao.index.indexregistry;

import java.util.HashMap;
import java.util.Map;

import org.lexevs.dao.index.lucenesupport.BaseLuceneIndexTemplate;
import org.lexevs.dao.index.lucenesupport.LuceneDirectoryFactory.NamedDirectory;
import org.lexevs.dao.index.lucenesupport.LuceneIndexTemplate;

public class RefreshingLuceneIndexNameToTemplateMap {
	
	private Map<String, LuceneIndexTemplate> map;
	private boolean refreshed = false;

	public RefreshingLuceneIndexNameToTemplateMap() {
			map = new HashMap<String,LuceneIndexTemplate>();
		}
		
		public LuceneIndexTemplate put(String key, LuceneIndexTemplate value){
			return map.put(key, value);
		}
		
		private LuceneIndexTemplate getRefreshedTemplate(String key){
		boolean refreshed = false;
		LuceneIndexTemplate template = map.get(key);

			 NamedDirectory directory = ((BaseLuceneIndexTemplate)template).getNamedDirectory();

				 if(directory.isReaderClosed()){
					 directory.refresh();
					 refreshed = true;
				 }
	    if(refreshed){return new BaseLuceneIndexTemplate(directory);}
		return template;
		}
		
		public boolean containsKey(String key){
			return map.containsKey(key);
		}
		
		public LuceneIndexTemplate get(String key){
			return this.getRefreshedTemplate(key);
		}

		public boolean isRefreshed() {
			return refreshed;
		}

}
