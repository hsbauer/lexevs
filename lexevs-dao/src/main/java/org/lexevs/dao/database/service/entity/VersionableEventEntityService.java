package org.lexevs.dao.database.service.entity;

import java.util.ArrayList;
import java.util.List;

import org.LexGrid.commonTypes.Property;
import org.LexGrid.concepts.Entity;
import org.lexevs.dao.database.access.property.batch.PropertyBatchInsertItem;
import org.lexevs.dao.database.service.AbstractDatabaseService;
import org.lexevs.dao.database.service.property.PropertyService;
import org.springframework.transaction.annotation.Transactional;

public class VersionableEventEntityService extends AbstractDatabaseService implements EntityService {

	private PropertyService propertyService;
	
	@Transactional
	public void insertEntity(String codingSchemeUri, String version,
			Entity entity) {
		String codingSchemeId = this.getDaoManager().
			getCodingSchemeDao(codingSchemeUri, version).
			getCodingSchemeIdByUriAndVersion(codingSchemeUri, version);
		
		this.getDaoManager().
			getEntityDao(codingSchemeUri, version).
				insertEntity(codingSchemeId, entity);
	}

	@Transactional
	public void insertBatchEntities(String codingSchemeUri, String version,
			List<? extends Entity> entities) {
		String codingSchemeId = this.getDaoManager().
			getCodingSchemeDao(codingSchemeUri, version).
			getCodingSchemeIdByUriAndVersion(codingSchemeUri, version);
		
		this.getDaoManager().getEntityDao(codingSchemeUri, version).
			insertBatchEntities(codingSchemeId, entities);
	}

	@Transactional
	public void updateEntity(String codingSchemeUri, String version,
			String enityCode, String entityCodeNamespace, Entity entity) {
		// TODO Auto-generated method stub
		
	}

	@Transactional
	public Entity getEntity(String codingSchemeUri, String version,
			String entityCode, String entityCodeNamespace) {
		String codingSchemeId = this.getDaoManager().
			getCodingSchemeDao(codingSchemeUri, version).
			getCodingSchemeIdByUriAndVersion(codingSchemeUri, version);
		
		return this.getDaoManager().
			getEntityDao(codingSchemeUri, version).getEntityByCodeAndNamespace(codingSchemeId, entityCode, entityCodeNamespace);
	}

	public List<? extends Entity> getEntities(String codingSchemeUri, String version,
			int start, int pageSize) {
		String codingSchemeId = this.getDaoManager().
			getCodingSchemeDao(codingSchemeUri, version).
			getCodingSchemeIdByUriAndVersion(codingSchemeUri, version);
		
		List<? extends Entity> entities = this.getDaoManager().getEntityDao(codingSchemeUri, version).
			getAllEntitiesOfCodingScheme(codingSchemeId, start, pageSize);
		
		return entities;
	}

	@Override
	public int getEntityCount(String codingSchemeUri, String version) {
		String codingSchemeId = this.getDaoManager()
			.getCurrentCodingSchemeDao().
			getCodingSchemeIdByUriAndVersion(codingSchemeUri, version);
		return this.getDaoManager().getCurrentEntityDao().getEntityCount(codingSchemeId);
	}
}
