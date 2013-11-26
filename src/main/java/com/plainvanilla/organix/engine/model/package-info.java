/**
 * Root package of model components for Organix
 * 
 * @author Martin Bencik
 *
 */

@org.hibernate.annotations.NamedQueries({
	
	@org.hibernate.annotations.NamedQuery(		
			name = "findConnectionByTypeId",
			query = "from Connection c where c.type.typeNumber = :typeId"			
			),
	
	@org.hibernate.annotations.NamedQuery(		
			name = "findConnectionByTypeName",
			query = "from Connection c where lower(c.type.sourceEnd.roleName) like :typeName or lower(c.type.targetEnd.roleName) like :typeName"			
			),
			
	@org.hibernate.annotations.NamedQuery(
			name = "findConnectionTypeByName", 
			query = "from ConnectionType c where lower(c.sourceEnd.roleName) like :role or lower(c.targetEnd.roleName) like :role"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByTypeId", 
			query = "from ObjectInstance i where i.type.typeNumber = :typeId"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByTypeName", 
			query = "from ObjectInstance i where lower(i.type.name) like :typeName"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByTypeIdAndName", 
			query = "from ObjectInstance i where lower(i.name) like :name and i.type.typeNumber = :typeId"
		),

	@org.hibernate.annotations.NamedQuery(
			name = "findObjectInstanceByName", 
			query = "from ObjectInstance i where lower(i.name) like :name"
		)
})


package com.plainvanilla.organix.engine.model;