package com.plainvanilla.organix.engine.test;

import static com.plainvanilla.organix.engine.test.TestUtils.getConfiguration;

import org.hibernate.FlushMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plainvanilla.organix.engine.model.Connection;
import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectInstance;
import com.plainvanilla.organix.engine.model.ObjectType;

public class HibernateMappingTest {

	final Logger logger = LoggerFactory.getLogger(HibernateMappingTest.class);
	
	private static Configuration cfg;
	private static SessionFactory sf;
	private static Session session;
	private static Transaction tx;
	
	@BeforeClass
	public static void init() {
		cfg = getConfiguration();
		sf = cfg.buildSessionFactory();
	}
	
	
	@Before
	public void beginTransaction() {
		session = sf.openSession();
		session.setFlushMode(FlushMode.AUTO);
		tx = session.beginTransaction();
	}
	
	@After
	public void endTransaction() {
		tx.commit();
		session.close();
	}
	
	
	@Test
	public void initTables() {
		
		logger.error("TRATALAAL");
		ObjectType itSystem = new ObjectType();
		itSystem.setName("IT-System");
		itSystem.setTypeNumber(6);

		ObjectType pdTeam = new ObjectType();
		pdTeam.setName("Product Delivery Team");
		pdTeam.setTypeNumber(7);

		ObjectType operationCluster = new ObjectType();
		operationCluster.setName("Operation Cluster");
		operationCluster.setTypeNumber(8);

		
		ObjectType productCluster = new ObjectType();
		productCluster.setName("Product Cluster");
		productCluster.setTypeNumber(9);

		
		ObjectType person = new ObjectType();
		person.setName("Person");
		person.setTypeNumber(1);		
		
		session.save(itSystem);
		session.save(person);
		session.save(pdTeam);
		session.save(operationCluster);
		session.save(productCluster);
				
		ConnectionType itProduct_ProductCluster = ConnectionType.createInstance(220, "Belongs to Product Cluster", itSystem.getTypeNumber(), true, false, "Contains IT-Product", productCluster.getTypeNumber(), false, false);
		ConnectionType itProduct_OperationCluster = ConnectionType.createInstance(221, "Belongs to Operation Cluster", itSystem.getTypeNumber(), true, false, "Contains IT-Product", operationCluster.getTypeNumber(), false, false);
		ConnectionType itProduct_PDTeam = ConnectionType.createInstance(222, "Belongs to PD Team", itSystem.getTypeNumber(), true, false, "Contains IT-Product", pdTeam.getTypeNumber(), false, false);
		ConnectionType pcManager_ProductCluster = ConnectionType.createInstance(223, "Manages Product Cluster", person.getTypeNumber(), false, false, "Is Managed By", productCluster.getTypeNumber(), true, true);		
		ConnectionType pcManagerDeputy_ProductCluster = ConnectionType.createInstance(224, "Is PCMD", person.getTypeNumber(), false, false, "Has PCDM", productCluster.getTypeNumber(), false, false);		
		ConnectionType ocManager_OperationCluster = ConnectionType.createInstance(225, "Manages Operation Cluster", person.getTypeNumber(), false, false, "Is Managed By", operationCluster.getTypeNumber(), true, true);		
		ConnectionType ocManagerDeputy_OperationCluster = ConnectionType.createInstance(226, "Is OCMD", person.getTypeNumber(), false, false, "Has OCDM", operationCluster.getTypeNumber(), false, false);		
		ConnectionType pdtManager_PDTeam = ConnectionType.createInstance(227, "Manages PD Team", person.getTypeNumber(), false, false, "Is Managed By", pdTeam.getTypeNumber(), true, true);		
		ConnectionType pdtManagerDeputy_PDTeam = ConnectionType.createInstance(228, "Is PDMD", person.getTypeNumber(), false, false, "Has PDMD", pdTeam.getTypeNumber(), false, false);		
		ConnectionType businessCoordinator_itProduct = ConnectionType.createInstance(229, "Has Business Coordinator", itSystem.getTypeNumber(), true, true, "Is Business Coordinator of", person.getTypeNumber(), false, false);
		
		session.save(itProduct_ProductCluster);
		session.save(itProduct_OperationCluster);
		session.save(itProduct_PDTeam);
		session.save(pcManager_ProductCluster);
		session.save(pcManagerDeputy_ProductCluster);
		session.save(ocManager_OperationCluster);
		session.save(ocManagerDeputy_OperationCluster);
		session.save(pdtManager_PDTeam);
		session.save(pdtManagerDeputy_PDTeam);
		session.save(businessCoordinator_itProduct);
		
		session.flush();
		tx.commit();
		tx = session.beginTransaction();
		
		ObjectInstance person1 = new ObjectInstance("Jan Novak", person);
		ObjectInstance person2 = new ObjectInstance("Karel Polak", person);
		ObjectInstance person3 = new ObjectInstance("Ivan Hrozny", person);
		ObjectInstance person4 = new ObjectInstance("Pater Noster", person);
		ObjectInstance person5 = new ObjectInstance("Michal David", person);
		
		ObjectInstance itSystem1 = new ObjectInstance("01-01-01", itSystem);
		ObjectInstance itSystem2 = new ObjectInstance("01-02-01", itSystem);
		ObjectInstance itSystem3 = new ObjectInstance("01-03-01", itSystem);
		ObjectInstance itSystem4 = new ObjectInstance("01-04-01", itSystem);
		ObjectInstance itSystem5 = new ObjectInstance("01-05-01", itSystem);
		
		ObjectInstance pdTeam1 = new ObjectInstance("PD Team 1", pdTeam);
		ObjectInstance pdTeam2 = new ObjectInstance("PD Team 2", pdTeam);
		ObjectInstance pdTeam3 = new ObjectInstance("PD Team 3", pdTeam);
		
		ObjectInstance productCluster1 = new ObjectInstance("Product Cluster 1", productCluster);
		ObjectInstance productCluster2 = new ObjectInstance("Product Cluster 2", productCluster);
		ObjectInstance productCluster3 = new ObjectInstance("Product Cluster 3", productCluster);
			
		ObjectInstance operationCluster1 = new ObjectInstance("Operation Cluster 1", operationCluster);
		ObjectInstance operationCluster2 = new ObjectInstance("Operation Cluster 2", operationCluster);
		ObjectInstance operationCluster3 = new ObjectInstance("Operation Cluster 3", operationCluster);
	
		session.save(person1);
		session.save(person2);
		session.save(person3);
		session.save(person4);
		session.save(person5);
		
		session.save(itSystem1);
		session.save(itSystem2);
		session.save(itSystem3);
		session.save(itSystem4);
		session.save(itSystem5);
				
		session.save(pdTeam1);
		session.save(pdTeam2);
		session.save(pdTeam3);
		
		session.save(productCluster1);
		session.save(productCluster2);
		session.save(productCluster3);
		
		session.save(operationCluster1);
		session.save(operationCluster2);
		session.save(operationCluster3);
		
		Connection connection1 = itProduct_ProductCluster.createConnection(itSystem1, productCluster1);
		Connection connection2 = itProduct_ProductCluster.createConnection(itSystem2, productCluster1);
		Connection connection3 = itProduct_ProductCluster.createConnection(itSystem3, productCluster1);
		Connection connection4 = itProduct_ProductCluster.createConnection(itSystem4, productCluster2);
		Connection connection5 = itProduct_ProductCluster.createConnection(itSystem5, productCluster3);

		Connection connection6 = itProduct_OperationCluster.createConnection(itSystem1, operationCluster3);
		Connection connection7 = itProduct_OperationCluster.createConnection(itSystem2, operationCluster2);
		Connection connection8 = itProduct_OperationCluster.createConnection(itSystem3, operationCluster1);
		Connection connection9 = itProduct_OperationCluster.createConnection(itSystem4, operationCluster3);
		Connection connection10 = itProduct_OperationCluster.createConnection(itSystem5, operationCluster1);

		Connection connection11 = itProduct_PDTeam.createConnection(itSystem1, pdTeam1);
		Connection connection12 = itProduct_PDTeam.createConnection(itSystem2, pdTeam3);
		Connection connection13 = itProduct_PDTeam.createConnection(itSystem3, pdTeam2);
		Connection connection14 = itProduct_PDTeam.createConnection(itSystem4, pdTeam3);
		Connection connection15 = itProduct_PDTeam.createConnection(itSystem5, pdTeam1);
		
		Connection connection16 = pdtManager_PDTeam.createConnection(person1, pdTeam1);
		Connection connection17 = pdtManager_PDTeam.createConnection(person2, pdTeam2);
		Connection connection18 = pdtManager_PDTeam.createConnection(person3, pdTeam3);
		
		Connection connection19 = pcManager_ProductCluster.createConnection(person2, productCluster1);
		Connection connection20 = pcManager_ProductCluster.createConnection(person1, productCluster2);
		Connection connection21 = pcManager_ProductCluster.createConnection(person1, productCluster3);

		Connection connection22 = ocManager_OperationCluster.createConnection(person2, operationCluster1);
		Connection connection23 = ocManager_OperationCluster.createConnection(person3, operationCluster2);
		Connection connection24 = ocManager_OperationCluster.createConnection(person4, operationCluster3);
		
		Connection connection25 = businessCoordinator_itProduct.createConnection(itSystem1, person4);
		Connection connection26 = businessCoordinator_itProduct.createConnection(itSystem2, person2);
		Connection connection27 = businessCoordinator_itProduct.createConnection(itSystem3, person2);
		Connection connection28 = businessCoordinator_itProduct.createConnection(itSystem4, person1);
		Connection connection29 = businessCoordinator_itProduct.createConnection(itSystem5, person4);
		
		Connection connection30 = pdtManagerDeputy_PDTeam.createConnection(person5, pdTeam1);
		Connection connection31 = pdtManagerDeputy_PDTeam.createConnection(person4, pdTeam1);
		Connection connection32 = pcManagerDeputy_ProductCluster.createConnection(person1, productCluster1);
		Connection connection33 = pcManagerDeputy_ProductCluster.createConnection(person2, productCluster1);		
		Connection connection34 = ocManagerDeputy_OperationCluster.createConnection(person3, operationCluster3);
		Connection connection35 = ocManagerDeputy_OperationCluster.createConnection(person4, operationCluster3);
		
		session.save(connection1);
		session.save(connection2);
		session.save(connection3);
		session.save(connection4);
		session.save(connection5);
		
		session.save(connection6);
		session.save(connection7);
		session.save(connection8);
		session.save(connection9);
		session.save(connection10);

		session.save(connection11);
		session.save(connection12);
		session.save(connection13);
		session.save(connection14);
		session.save(connection15);

		session.save(connection16);
		session.save(connection17);
		session.save(connection18);
		
		session.save(connection19);
		session.save(connection20);
		session.save(connection21);	
		
		session.save(connection22);
		session.save(connection23);
		session.save(connection24);	
	
		session.save(connection25);
		session.save(connection26);
		session.save(connection27);
		session.save(connection28);
		session.save(connection29);

		session.save(connection30);
		session.save(connection31);
		session.save(connection32);
		session.save(connection33);
		session.save(connection34);
		session.save(connection35);	
		
	}
	
}
