package org.lexgrid.loader.meta.reader.support;

import org.lexgrid.loader.meta.constants.MetaLoaderConstants;
import org.lexgrid.loader.reader.support.SkipPolicy;
import org.lexgrid.loader.rrf.model.Mrsat;

public class MrsatPropertySkipPolicy implements SkipPolicy<Mrsat>{

	public boolean toSkip(Mrsat item) {
		if(item.getStype().equals(MetaLoaderConstants.RUI) || 
				item.getStype().equals(MetaLoaderConstants.SRUI)){
			return true;
		} else {
			return false;
		}
	}
}
