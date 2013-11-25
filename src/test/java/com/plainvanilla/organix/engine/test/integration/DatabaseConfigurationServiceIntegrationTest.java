package com.plainvanilla.organix.engine.test.integration;

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

import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectType;
import com.plainvanilla.organix.engine.model.exception.OrganixIllegalConfigurationException;
import com.plainvanilla.organix.engine.services.DatabaseConfigurationService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-config-test.xml")
@TransactionConfiguration(defaultRollback=false)
public class DatabaseConfigurationServiceIntegrationTest {
	
	private static final int PERSON_TYPE_ID = 1;
	private static final int PC_TYPE_ID = 9;
	private static final int OC_TYPE_ID = 8;
	private static final int PD_TEAM_TYPE_ID = 7;
	private static final int IT_SYSTEM_TYPE_ID = 6;
	
	private static final String PERSON = "Person";
	private static final String PRODUCT_CLUSTER = "Product Cluster";
	private static final String OPERATION_CLUSTER = "Operation Cluster";
	private static final String PRODUCT_DELIVERY_TEAM = "Product Delivery Team";
	private static final String IT_SYSTEM = "IT-System";
	
	
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
		
		service.addConnectionType(220, "Belongs to Product Cluster", itSystem.getTypeNumber(), true, false, "Contains IT-Product", productCluster.getTypeNumber(), false, false);
		service.addConnectionType(221, "Belongs to Operation Cluster", itSystem.getTypeNumber(), true, false, "Contains IT-Product", operationCluster.getTypeNumber(), false, false);
		service.addConnectionType(222, "Belongs to PD Team", itSystem.getTypeNumber(), true, false, "Contains IT-Product", pdTeam.getTypeNumber(), false, false);
		service.addConnectionType(223, "Manages Product Cluster", person.getTypeNumber(), false, false, "Is Managed By", productCluster.getTypeNumber(), true, true);		
		service.addConnectionType(224, "Is PCMD", person.getTypeNumber(), false, false, "Has PCDM", productCluster.getTypeNumber(), false, false);		
		service.addConnectionType(225, "Manages Operation Cluster", person.getTypeNumber(), false, false, "Is Managed By", operationCluster.getTypeNumber(), true, true);		
		service.addConnectionType(226, "Is OCMD", person.getTypeNumber(), false, false, "Has OCDM", operationCluster.getTypeNumber(), false, false);		
		service.addConnectionType(227, "Manages PD Team", person.getTypeNumber(), false, false, "Is Managed By", pdTeam.getTypeNumber(), true, true);		
		service.addConnectionType(228, "Is PDMD", person.getTypeNumber(), false, false, "Has PDMD", pdTeam.getTypeNumber(), false, false);		
		service.addConnectionType(229, "Has Business Coordinator", itSystem.getTypeNumber(), true, true, "Is Business Coordinator of", person.getTypeNumber(), false, false);
	
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
