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

import com.plainvanilla.organix.engine.model.ConnectionType;
import com.plainvanilla.organix.engine.model.ObjectType;

public class HibernateMappingTest {

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
		
		session.save(person);
		session.save(pdTeam);
		session.save(operationCluster);
		session.save(productCluster);
				
		ConnectionType itProduct_ProductCluster = ConnectionType.createInstance(220, "Belongs to Product Cluster", itSystem.getTypeNumber(), false, false, "Contains IT-Product", productCluster.getTypeNumber(), true, false);
		ConnectionType itProduct_OperationCluster = ConnectionType.createInstance(221, "Belongs to Operation Cluster", itSystem.getTypeNumber(), false, false, "Contains IT-Product", operationCluster.getTypeNumber(), true, false);
		ConnectionType itProduct_PDTeam = ConnectionType.createInstance(222, "Belongs to PD Team", itSystem.getTypeNumber(), false, false, "Contains IT-Product", pdTeam.getTypeNumber(), true, false);
		ConnectionType pcManager_ProductCluster = ConnectionType.createInstance(223, "Manages Product Cluster", person.getTypeNumber(), true, false, "Is Managed By", productCluster.getTypeNumber(), false, true);		
		ConnectionType pcManagerDeputy_ProductCluster = ConnectionType.createInstance(224, "Is PCMD", person.getTypeNumber(), false, false, "Has PCDM", productCluster.getTypeNumber(), false, false);		
		ConnectionType ocManager_OperationCluster = ConnectionType.createInstance(225, "Manages Operation Cluster", person.getTypeNumber(), true, false, "Is Managed By", operationCluster.getTypeNumber(), false, true);		
		ConnectionType ocManagerDeputy_OperationCluster = ConnectionType.createInstance(226, "Is OCMD", person.getTypeNumber(), false, false, "Has OCDM", operationCluster.getTypeNumber(), false, false);		
		ConnectionType pdtManager_PDTeam = ConnectionType.createInstance(227, "Manages PD Team", person.getTypeNumber(), true, false, "Is Managed By", pdTeam.getTypeNumber(), false, true);		
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
		
		
	
		
	}
	
	
	
}
