package com.plainvanilla.organix.engine.test.integration;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static com.plainvanilla.organix.engine.test.integration.TestConstants.*;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectType;
import com.plainvanilla.organix.engine.model.exception.OrganixIllegalConfigurationException;
import com.plainvanilla.organix.engine.services.DatabaseConfigurationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-config-test.xml")
@TransactionConfiguration(defaultRollback=false)
public class DatabaseConfigurationServiceIntegrationTest {
	

	@Autowired
	private DatabaseConfigurationService service;

	@Test
	public void testContext() {
		assertNotNull(service);
	}
	
	@Test
	@Transactional
	public void testAddObjectTypes() {
		service.addObjectType(IT_SYSTEM_TYPE_ID, IT_SYSTEM);
		service.addObjectType(PD_TEAM_TYPE_ID, PRODUCT_DELIVERY_TEAM);
		service.addObjectType(OC_TYPE_ID, OPERATION_CLUSTER);
		service.addObjectType(PC_TYPE_ID, PRODUCT_CLUSTER);
		service.addObjectType(PERSON_TYPE_ID, PERSON);
	}
	
	@Test(expected=OrganixIllegalConfigurationException.class)
	@Transactional	
	public void testAddAmbigiousObjectType() {
		service.addObjectType(IT_SYSTEM_TYPE_ID, "Another type");
	}
	
	@Test
	@Transactional
	public void testAddConnectionTypes() {
		
		ObjectType itSystem = service.getObjectType(IT_SYSTEM_TYPE_ID);
		ObjectType person = service.getObjectType(PERSON_TYPE_ID);
		ObjectType productCluster = service.getObjectType(PC_TYPE_ID);
		ObjectType operationCluster = service.getObjectType(OC_TYPE_ID);
		ObjectType pdTeam = service.getObjectType(PD_TEAM_TYPE_ID);
		
		assertNotNull(itSystem);
		assertNotNull(person);
		assertNotNull(productCluster);
		assertNotNull(operationCluster);
		assertNotNull(pdTeam);
		
		assertEquals(itSystem.getName(), IT_SYSTEM);
		assertEquals(person.getName(), PERSON);
		assertEquals(productCluster.getName(), PRODUCT_CLUSTER);
		assertEquals(operationCluster.getName(), OPERATION_CLUSTER);
		assertEquals(pdTeam.getName(), PRODUCT_DELIVERY_TEAM);
		
		service.addConnectionType(BELONGS_TO_PRODUCT_CLUSTER_CTYPE, "Belongs to Product Cluster", itSystem.getTypeNumber(), true, false, "Contains IT-Product", productCluster.getTypeNumber(), false, false);
		service.addConnectionType(BELONGS_TO_OPERATION_CLUSTER_CTYPE, "Belongs to Operation Cluster", itSystem.getTypeNumber(), true, false, "Contains IT-Product", operationCluster.getTypeNumber(), false, false);
		service.addConnectionType(BELONGS_TO_PD_TEAM_CTYPE, "Belongs to PD Team", itSystem.getTypeNumber(), true, false, "Contains IT-Product", pdTeam.getTypeNumber(), false, false);
		service.addConnectionType(MANAGES_PRODUCT_CLUSTER_CTYPE, "Manages Product Cluster", person.getTypeNumber(), false, false, "Is Managed By", productCluster.getTypeNumber(), true, true);		
		service.addConnectionType(IS_PCMD_CTYPE, "Is PCMD", person.getTypeNumber(), false, false, "Has PCDM", productCluster.getTypeNumber(), false, false);		
		service.addConnectionType(MANAGES_OPERATION_CLUSTER_CTYPE, "Manages Operation Cluster", person.getTypeNumber(), false, false, "Is Managed By", operationCluster.getTypeNumber(), true, true);		
		service.addConnectionType(IS_OCMD_CTYPE, "Is OCMD", person.getTypeNumber(), false, false, "Has OCDM", operationCluster.getTypeNumber(), false, false);		
		service.addConnectionType(MANAGES_PD_TEAM_CTYPE, "Manages PD Team", person.getTypeNumber(), false, false, "Is Managed By", pdTeam.getTypeNumber(), true, true);		
		service.addConnectionType(IS_PDMD_CTYPE, "Is PDMD", person.getTypeNumber(), false, false, "Has PDMD", pdTeam.getTypeNumber(), false, false);		
		service.addConnectionType(HAS_BUSINESS_COORDINATOR_CTYPE, "Has Business Coordinator", itSystem.getTypeNumber(), true, true, "Is Business Coordinator of", person.getTypeNumber(), false, false);
	
	}
	
	@Test(expected=OrganixIllegalConfigurationException.class)
	@Transactional
	public void testAddAmbigiousConnectionType() {
		service.addConnectionType(220, "Belongs to Product Cluster", IT_SYSTEM_TYPE_ID, true, false, "Contains IT-Product", PC_TYPE_ID, false, false);
		
	}
		
	@Test
	@Transactional
	public void testFindByName() {
		List<ObjectType> objectTypes = service.getObjectTypeByName("cLUsTeR");
		assertEquals(objectTypes.size(), 2);
		
		List<ConnectionType> connectionTypes = service.getConnectionTypeByName("bELonGS");
		assertEquals(connectionTypes.size(), 3);
		
		List<ConnectionType> connectionTypes2 = service.getConnectionTypeByName("buSineSS");
		assertEquals(connectionTypes2.size(), 1);
	}
}
