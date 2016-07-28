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
package org.lexevs.paging;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.LexGrid.annotations.LgProxyClass;
import org.springframework.util.Assert;

/**
 * The Class AbstractPageableIterator.
 * 
 * @author <a href="mailto:kevin.peterson@mayo.edu">Kevin Peterson</a>
 */
public abstract class AbstractPageableIterator<T> implements Iterator<T>, Iterable<T>, Serializable{

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = -5398591025205732109L;

	/** The DEFAUL t_ pag e_ size. */
	private static int DEFAULT_PAGE_SIZE = 100;
	
	/** The cache. */
	private List<? extends T> cache = new ArrayList<T>();
	
	/** The page size. */
	private int pageSize;
	
	/** The global position. */
	private int globalPosition = 0;
	
	/** The in cache position. */
	private int inCachePosition = 0;
	
	/** The pager. */
	private Pager<T> pager;
	
	/** The next decorator. */
	private NextDecorator<T> nextDecorator;
	
	/** The decorate next. */
	private boolean decorateNext = false;
	
	private boolean isExhausted = false;
	
	/**
	 * Instantiates a new abstract pageable iterator.
	 */
	protected AbstractPageableIterator(){
		this(DEFAULT_PAGE_SIZE);
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Iterable#iterator()
	 */
	@Override
	public Iterator<T> iterator() {
		return this;
	}

	/**
	 * Instantiates a new abstract pageable iterator.
	 * 
	 * @param pageSize the page size
	 */
	public AbstractPageableIterator(int pageSize){
		Assert.isTrue(pageSize > 0, "Cannot specify a Page Size less than 0.");
		this.pageSize = pageSize;
		
		this.pager = new Pager<T>();
		this.nextDecorator = new NextDecorator<T>();
	}
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		if(isExhausted){
			return false;
		}
		
		try {
			pageIfNecessary();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Exception on paging in hasNext()", e);
		}
		
		if(cache == null || cache.size() == 0) {
			isExhausted = true;
			return false;
		}
		
		int cacheSize = cache.size();
		
		boolean hasNext = inCachePosition < cacheSize;
		
		isExhausted = !hasNext;
		
		return hasNext;
		
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public T next() {
		try {
			pageIfNecessary();
		} catch (SQLException e) {
			throw new RuntimeException("SQL Exception on paging in next()", e);
		}
		
		T returnItem = cache.get( inCachePosition );
		
		globalPosition++;
		inCachePosition++;
		
		if(this.decorateNext) {
			return this.nextDecorator.decorateNext(this, returnItem);
		} else {
			return returnItem;
		}
	}
	
	/**
	 * Page if necessary.
	 * @throws SQLException 
	 */
	protected void pageIfNecessary() throws SQLException {
		if(isPageNeeded()) {
			page();
		}
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#remove()
	 */
	@Override
	public void remove() {
		throw new UnsupportedOperationException();
	}
	
	
	/**
	 * Checks if is page needed.
	 * 
	 * @return true, if is page needed
	 */
	protected boolean isPageNeeded() {
		boolean page = inCachePosition > ( cache.size() - 1 );
		return page;
	}
	
	/**
	 * Page.
	 * @throws SQLException 
	 */
	protected final void page() throws SQLException {
		cache = doExecutePage();

		inCachePosition = 0;
	}
	
	/**
	 * Do execute page.
	 * 
	 * @return the list<? extends t>
	 * @throws SQLException 
	 */
	protected List<? extends T> doExecutePage() throws SQLException{
		List<? extends T> returnList = this.pager.doPage(this, globalPosition, pageSize);

		return returnList;
	}
	
	/**
	 * Returns a page of results.
	 * 
	 * NOTE: 'pageSize' is not binding -- it is the suggested page size.
	 * Implementing classes may return more or less than the suggested
	 * 'pageSize' parameter, although it is generally recommended to abide
	 * by the 'pageSize' parameter when possible.
	 * 
	 * A null or empty list returned from this method will signify
	 * that the underlying results are exhausted and paging should halt.
	 * 
	 * @param currentPosition the current position
	 * @param pageSize the page size
	 * 
	 * @return the list<? extends t>
	 * @throws SQLException 
	 */
	protected abstract List<? extends T> doPage(int currentPosition, int pageSize) throws SQLException;
	
	/**
	 * Decorate next.
	 * 
	 * @param item the item
	 * 
	 * @return the t
	 */
	protected T decorateNext(T item) {
		//no-op -- for sublcasses
		return item;
	}
	
	/**
	 * Sets the decorate next.
	 * 
	 * @param decorateNext the new decorate next
	 */
	protected void setDecorateNext(boolean decorateNext) {
		this.decorateNext = decorateNext;
	}

	/**
	 * Checks if is decorate next.
	 * 
	 * @return true, if is decorate next
	 */
	protected boolean isDecorateNext() {
		return decorateNext;
	}

	/**
	 * The Class Pager.
	 */
	@LgProxyClass
	public static class Pager<T> implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 6142588013131141095L;

		/**
		 * Instantiates a new pager.
		 */
		public Pager() {
			super();
		}
		
		/**
		 * Do page.
		 * 
		 * @param abstractPageableIterator the abstract pageable iterator
		 * @param currentPosition the current position
		 * @param pageSize the page size
		 * 
		 * @return the list<? extends t>
		 * @throws SQLException 
		 */
		public List<? extends T> doPage(AbstractPageableIterator<T> abstractPageableIterator, int currentPosition, int pageSize) throws SQLException{
			List<? extends T> returnList = abstractPageableIterator.doPage(currentPosition, pageSize);
			
			return returnList;
		}
	}
	
	
	/**
	 * The Class NextDecorator.
	 */
	@LgProxyClass
	public static class NextDecorator<T> implements Serializable {

		/** The Constant serialVersionUID. */
		private static final long serialVersionUID = 6142588013131141095L;

		/**
		 * Instantiates a new next decorator.
		 */
		public NextDecorator() {
			super();
		}
		
		/**
		 * Decorate next.
		 * 
		 * @param abstractPageableIterator the abstract pageable iterator
		 * @param item the item
		 * 
		 * @return the t
		 */
		public T decorateNext(AbstractPageableIterator<T> abstractPageableIterator, T item){
			return abstractPageableIterator.decorateNext(item);
		}
	}


	/**
	 * Gets the page size.
	 * 
	 * @return the page size
	 */
	protected int getPageSize() {
		return pageSize;
	}

	/**
	 * Gets the global position.
	 * 
	 * @return the global position
	 */
	protected int getGlobalPosition() {
		return globalPosition;
	}	
}