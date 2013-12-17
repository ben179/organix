package com.plainvanilla.organix.engine.test.integration;

import static com.plainvanilla.organix.engine.test.integration.TestConstants.BELONGS_TO_OPERATION_CLUSTER_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.BELONGS_TO_PD_TEAM_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.BELONGS_TO_PRODUCT_CLUSTER_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.HAS_BUSINESS_COORDINATOR_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.IS_OCMD_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.IS_PCMD_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.IS_PDMD_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.IT_SYSTEM_TYPE_ID;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.MANAGES_OPERATION_CLUSTER_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.MANAGES_PD_TEAM_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.MANAGES_PRODUCT_CLUSTER_CTYPE;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.OC_TYPE_ID;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.PC_TYPE_ID;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.PD_TEAM_TYPE_ID;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.PERSON;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.PERSON_TYPE_ID;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.CONFIG_NAME;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.plainvanilla.organix.engine.model.Configuration;
import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.Database;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.services.ConfigurationService;
import com.plainvanilla.organix.engine.services.DatabaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-config-test.xml")
@TransactionConfiguration(defaultRollback=false)
public class DatabaseServiceIntegrationTest {

	private static Database db = null;
	
	@Autowired
	private ConfigurationService configService;
	
	@Autowired
	private DatabaseService service;
	
	@Test
	public void testContext() {
		assertNotNull(service);
	}
	
	@Test
	@Transactional
	public void testCreateDatabase() {
		
		Configuration config = configService.getConfiguration(CONFIG_NAME, 1);
		assertNotNull(config);
		
		db = configService.createNewDatabase("testDB", config.getId());
		assertNotNull(db);		
	}
	
	
	@Test
	@Transactional
	public void testAddObjectInstancesAndConnections() {
		
		ObjectInstance person1 = service.addObjectInstance(PERSON_TYPE_ID, "Jan Novak", db.getId());
		ObjectInstance person2 = service.addObjectInstance(PERSON_TYPE_ID, "Karel Polak", db.getId());
		ObjectInstance person3 = service.addObjectInstance(PERSON_TYPE_ID, "Ivan Hrozny", db.getId());
		ObjectInstance person4 = service.addObjectInstance(PERSON_TYPE_ID, "Pater Noster", db.getId());
		ObjectInstance person5 = service.addObjectInstance(PERSON_TYPE_ID, "Michal David", db.getId());
		
		ObjectInstance itSystem1 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-01-01", db.getId());
		ObjectInstance itSystem2 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-02-01", db.getId());
		ObjectInstance itSystem3 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-03-01", db.getId());
		ObjectInstance itSystem4 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-04-01", db.getId());
		ObjectInstance itSystem5 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-05-01", db.getId());
			
		ObjectInstance pdTeam1 = service.addObjectInstance(PD_TEAM_TYPE_ID, "PD Team 1", db.getId());
		ObjectInstance pdTeam2 = service.addObjectInstance(PD_TEAM_TYPE_ID, "PD Team 2", db.getId());
		ObjectInstance pdTeam3 = service.addObjectInstance(PD_TEAM_TYPE_ID, "PD Team 3", db.getId());
				
		ObjectInstance productCluster1 = service.addObjectInstance(PC_TYPE_ID, "Product Cluster 1", db.getId());
		ObjectInstance productCluster2 = service.addObjectInstance(PC_TYPE_ID, "Product Cluster 2", db.getId());
		ObjectInstance productCluster3 = service.addObjectInstance(PC_TYPE_ID, "Product Cluster 3", db.getId());
				
		ObjectInstance operationCluster1 = service.addObjectInstance(OC_TYPE_ID, "Operation Cluster 1", db.getId());
		ObjectInstance operationCluster2 = service.addObjectInstance(OC_TYPE_ID, "Operation Cluster 2", db.getId());
		ObjectInstance operationCluster3 = service.addObjectInstance(OC_TYPE_ID, "Operation Cluster 3", db.getId());
		
		assertNotNull(person1);
		assertNotNull(person2);
		assertNotNull(person3);
		assertNotNull(person4);
		assertNotNull(person5);
				
		assertNotNull(itSystem1);
		assertNotNull(itSystem2);
		assertNotNull(itSystem3);
		assertNotNull(itSystem4);
		assertNotNull(itSystem5);
		
		assertNotNull(pdTeam1);
		assertNotNull(pdTeam2);
		assertNotNull(pdTeam3);
		
		assertNotNull(productCluster1);
		assertNotNull(productCluster2);
		assertNotNull(productCluster3);
		
		assertNotNull(operationCluster1);
		assertNotNull(operationCluster2);
		assertNotNull(operationCluster3);
				
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem1, productCluster1, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem2, productCluster1, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem3, productCluster1, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem4, productCluster2, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem5, productCluster3, db.getId()));

		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem1, operationCluster3, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem2, operationCluster2, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem3, operationCluster1, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem4, operationCluster3, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem5, operationCluster1, db.getId()));

		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem1, pdTeam1, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem2, pdTeam3, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem3, pdTeam2, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem4, pdTeam3, db.getId()));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem5, pdTeam1, db.getId()));

		assertNotNull(service.addConnection(MANAGES_PD_TEAM_CTYPE, person1, pdTeam1, db.getId()));
		assertNotNull(service.addConnection(MANAGES_PD_TEAM_CTYPE, person2, pdTeam2, db.getId()));
		assertNotNull(service.addConnection(MANAGES_PD_TEAM_CTYPE, person3, pdTeam3, db.getId()));

		assertNotNull(service.addConnection(MANAGES_PRODUCT_CLUSTER_CTYPE, person2, productCluster1, db.getId()));
		assertNotNull(service.addConnection(MANAGES_PRODUCT_CLUSTER_CTYPE, person1, productCluster2, db.getId()));
		assertNotNull(service.addConnection(MANAGES_PRODUCT_CLUSTER_CTYPE, person1, productCluster3, db.getId()));

		assertNotNull(service.addConnection(MANAGES_OPERATION_CLUSTER_CTYPE, person2, operationCluster1, db.getId()));
		assertNotNull(service.addConnection(MANAGES_OPERATION_CLUSTER_CTYPE, person3, operationCluster2, db.getId()));
		assertNotNull(service.addConnection(MANAGES_OPERATION_CLUSTER_CTYPE, person4, operationCluster3, db.getId()));
		
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem1, person4, db.getId()));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem2, person2, db.getId()));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem3, person2, db.getId()));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem4, person1, db.getId()));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem5, person4, db.getId()));
		
		assertNotNull(service.addConnection(IS_PDMD_CTYPE, person5, pdTeam1, db.getId()));
		assertNotNull(service.addConnection(IS_PDMD_CTYPE, person4, pdTeam1, db.getId()));
		
		assertNotNull(service.addConnection(IS_PCMD_CTYPE, person1, productCluster1, db.getId()));
		assertNotNull(service.addConnection(IS_PCMD_CTYPE, person2, productCluster1, db.getId()));		
	
		assertNotNull(service.addConnection(IS_OCMD_CTYPE, person3, operationCluster3, db.getId()));
		assertNotNull(service.addConnection(IS_OCMD_CTYPE, person4, operationCluster3, db.getId()));
		
	}
	
	@Test
	@Transactional
	public void testFindObjects() {
				
		List<ObjectInstance> found = null; 
		
		found = service.findObjectsByTypeName(PERSON, db.getId());
		assertEquals(found.size(), 5);
		
		found = service.findObjectsByTypeId(PD_TEAM_TYPE_ID, db.getId());		
		assertEquals(found.size(), 3);
					
		found = service.findObjectsByName("aK", db.getId());
		assertEquals(found.size(), 2);

		found = service.findObjectsByTypeIdAndName(OC_TYPE_ID, "ClUstEr", db.getId());
		assertEquals(found.size(), 3);
	}
	
	
	@Test
	@Transactional
	public void testFindConnections() {
		
		List<Connection> found = null;
		
		found = service.findConnectionsByTypeId(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, db.getId());
		assertEquals(found.size(), 5);
		
		found = service.findConnectionsByTypeName("Ges PD TeAm", db.getId());
		assertEquals(found.size(), 3);
	}
	
}
