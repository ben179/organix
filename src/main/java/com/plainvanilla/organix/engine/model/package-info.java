/**
 * Root package of model components for Organix
 * 
 * @author ben179
 *
 */

@org.hibernate.annotations.NamedQueries({

	@org.hibernate.annotations.NamedQuery(		
			name = "removeObjectInstance",
			query = "delete from ObjectInstance i where i.id = :id and i.database.id = :dbId"			
			),

	@org.hibernate.annotations.NamedQuery(		
			name = "removeConnectionInstance",
			query = "delete from Connection i where i.id = :id and i.database.id = :dbId"			
			),			
			
	@org.hibernate.annotations.NamedQuery(
			name = "findObjectTypeByTypeId",
			query = "from ObjectType t where t.typeNumber = :typeId and t.configuration.id = :configId"			
			),
	
	@org.hibernate.annotations.NamedQuery(
			name = "findConnectionTypeByTypeId",
			query = "from ConnectionType t where t.typeNumber = :typeId and t.configuration.id = :configId"			
			),
	
	@org.hibernate.annotations.NamedQuery(		
			name = "findConfigurationByName",
			query = "from Configuration c where c.name = :name"			
			),
			
	@org.hibernate.annotations.NamedQuery(		
			name = "findConfigurationByNameAndVersion",
			query = "from Configuration c where c.name = :name and c.version = :version"			
			),
	
	@org.hibernate.annotations.NamedQuery(		
			name = "removeAllObjectTypes",
			query = "delete from ObjectType t where t.configuration.id = :configId"			
			),

	@org.hibernate.annotations.NamedQuery(
			name = "removeAllConnectionTypes", 
			query = "delete from ConnectionType c where c.configuration.id = :configId"
			),
			
	@org.hibernate.annotations.NamedQuery(		
			name = "findMaxObjectTypeId",
			query = "select max(c.typeNumber) from ObjectType c where c.configuration.id = :configId"			
			),	
	
	@org.hibernate.annotations.NamedQuery(		
			name = "findMaxConnectionTypeId",
			query = "select max(c.typeNumber) from ConnectionType c where c.configuration.id = :configId"			
			),
	
	@org.hibernate.annotations.NamedQuery(		
			name = "findConnectionByTypeId",
			query = "from Connection c where c.type.typeNumber = :typeId and c.database.id = :dbId"			
			),
	
	@org.hibernate.annotations.NamedQuery(		
			name = "findConnectionByTypeName",
			query = "from Connection c where c.database.id = :dbId and (lower(c.type.sourceEnd.roleName) like :typeName or lower(c.type.targetEnd.roleName) like :typeName)"			
			),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectTypeByName", 
			query = "from ObjectType t where lower(t.name) like :name and t.configuration.id = :configId"
			),		
			
	@org.hibernate.annotations.NamedQuery(
			name = "findConnectionTypeByName", 
			query = "from ConnectionType c where (lower(c.sourceEnd.roleName) like :role or lower(c.targetEnd.roleName) like :role) and c.configuration.id = :configId"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByTypeId", 
			query = "from ObjectInstance i where i.type.typeNumber = :typeId and i.database.id = :dbId"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByTypeName", 
			query = "from ObjectInstance i where lower(i.type.name) like :typeName and i.database.id = :dbId"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByTypeIdAndName", 
			query = "from ObjectInstance i where lower(i.name) like :name and i.type.typeNumber = :typeId and i.database.id = :dbId"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByName", 
			query = "from ObjectInstance i where lower(i.name) like :name and i.database.id = :dbId"
		)
})


package com.plainvanilla.organix.engine.model;