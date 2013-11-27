package com.plainvanilla.organix.engine.test.integration;

import static com.plainvanilla.organix.engine.test.integration.TestConstants.*;
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

import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.services.DatabaseService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-config-test.xml")
@TransactionConfiguration(defaultRollback=false)
public class DatabaseServiceIntegrationTest {

	
	@Autowired
	private DatabaseService service;
	
	@Test
	public void testContext() {
		assertNotNull(service);
	}
	
	@Test
	@Transactional
	public void testAddObjectInstancesAndConnections() {
		
		ObjectInstance person1 = service.addObjectInstance(PERSON_TYPE_ID, "Jan Novak");
		ObjectInstance person2 = service.addObjectInstance(PERSON_TYPE_ID, "Karel Polak");
		ObjectInstance person3 = service.addObjectInstance(PERSON_TYPE_ID, "Ivan Hrozny");
		ObjectInstance person4 = service.addObjectInstance(PERSON_TYPE_ID, "Pater Noster");
		ObjectInstance person5 = service.addObjectInstance(PERSON_TYPE_ID, "Michal David");
		
		ObjectInstance itSystem1 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-01-01");
		ObjectInstance itSystem2 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-02-01");
		ObjectInstance itSystem3 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-03-01");
		ObjectInstance itSystem4 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-04-01");
		ObjectInstance itSystem5 = service.addObjectInstance(IT_SYSTEM_TYPE_ID, "01-05-01");
			
		ObjectInstance pdTeam1 = service.addObjectInstance(PD_TEAM_TYPE_ID, "PD Team 1");
		ObjectInstance pdTeam2 = service.addObjectInstance(PD_TEAM_TYPE_ID, "PD Team 2");
		ObjectInstance pdTeam3 = service.addObjectInstance(PD_TEAM_TYPE_ID, "PD Team 3");
				
		ObjectInstance productCluster1 = service.addObjectInstance(PC_TYPE_ID, "Product Cluster 1");
		ObjectInstance productCluster2 = service.addObjectInstance(PC_TYPE_ID, "Product Cluster 2");
		ObjectInstance productCluster3 = service.addObjectInstance(PC_TYPE_ID, "Product Cluster 3");
				
		ObjectInstance operationCluster1 = service.addObjectInstance(OC_TYPE_ID, "Operation Cluster 1");
		ObjectInstance operationCluster2 = service.addObjectInstance(OC_TYPE_ID, "Operation Cluster 2");
		ObjectInstance operationCluster3 = service.addObjectInstance(OC_TYPE_ID, "Operation Cluster 3");
		
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
				
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem1, productCluster1));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem2, productCluster1));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem3, productCluster1));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem4, productCluster2));
		assertNotNull(service.addConnection(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, itSystem5, productCluster3));

		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem1, operationCluster3));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem2, operationCluster2));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem3, operationCluster1));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem4, operationCluster3));
		assertNotNull(service.addConnection(BELONGS_TO_OPERATION_CLUSTER_CTYPE, itSystem5, operationCluster1));

		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem1, pdTeam1));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem2, pdTeam3));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem3, pdTeam2));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem4, pdTeam3));
		assertNotNull(service.addConnection(BELONGS_TO_PD_TEAM_CTYPE, itSystem5, pdTeam1));

		assertNotNull(service.addConnection(MANAGES_PD_TEAM_CTYPE, person1, pdTeam1));
		assertNotNull(service.addConnection(MANAGES_PD_TEAM_CTYPE, person2, pdTeam2));
		assertNotNull(service.addConnection(MANAGES_PD_TEAM_CTYPE, person3, pdTeam3));

		assertNotNull(service.addConnection(MANAGES_PRODUCT_CLUSTER_CTYPE, person2, productCluster1));
		assertNotNull(service.addConnection(MANAGES_PRODUCT_CLUSTER_CTYPE, person1, productCluster2));
		assertNotNull(service.addConnection(MANAGES_PRODUCT_CLUSTER_CTYPE, person1, productCluster3));

		assertNotNull(service.addConnection(MANAGES_OPERATION_CLUSTER_CTYPE, person2, operationCluster1));
		assertNotNull(service.addConnection(MANAGES_OPERATION_CLUSTER_CTYPE, person3, operationCluster2));
		assertNotNull(service.addConnection(MANAGES_OPERATION_CLUSTER_CTYPE, person4, operationCluster3));
		
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem1, person4));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem2, person2));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem3, person2));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem4, person1));
		assertNotNull(service.addConnection(HAS_BUSINESS_COORDINATOR_CTYPE, itSystem5, person4));
		
		assertNotNull(service.addConnection(IS_PDMD_CTYPE, person5, pdTeam1));
		assertNotNull(service.addConnection(IS_PDMD_CTYPE, person4, pdTeam1));
		
		assertNotNull(service.addConnection(IS_PCMD_CTYPE, person1, productCluster1));
		assertNotNull(service.addConnection(IS_PCMD_CTYPE, person2, productCluster1));		
	
		assertNotNull(service.addConnection(IS_OCMD_CTYPE, person3, operationCluster3));
		assertNotNull(service.addConnection(IS_OCMD_CTYPE, person4, operationCluster3));
		
	}
	
	@Test
	@Transactional
	public void testFindObjects() {
				
		List<ObjectInstance> found = null; 
		
		found = service.findObjectsByTypeName(PERSON);
		assertEquals(found.size(), 5);
		
		found = service.findObjectsByTypeId(PD_TEAM_TYPE_ID);		
		assertEquals(found.size(), 3);
					
		found = service.findObjectsByName("aK");
		assertEquals(found.size(), 2);

		found = service.findObjectsByTypeIdAndName(OC_TYPE_ID, "ClUstEr");
		assertEquals(found.size(), 3);
	}
	
	
	@Test
	@Transactional
	public void testFindConnections() {
		
		List<Connection> found = null;
		
		found = service.findConnectionsByTypeId(BELONGS_TO_PRODUCT_CLUSTER_CTYPE);
		assertEquals(found.size(), 5);
		
		found = service.findConnectionsByTypeName("Ges PD TeAm");
		assertEquals(found.size(), 3);
	}
	
}
