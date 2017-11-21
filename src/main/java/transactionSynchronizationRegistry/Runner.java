package transactionSynchronizationRegistry;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
@Interceptors(Intercpt.class)
public class Runner {
	
	@Inject
	TransSyncRegistryTest tsrt;
	
	@Inject
	TransSyncRegistryTest2 tsrt2;
	
//	@Inject
//	TransSyncRegistryCDI tsrCDI;		//Doesnt work
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	@PersistenceContext
	EntityManager em;
	
	public String runIt() {
		tsrt.tsrTest();
		tsrt2.tsrTest2();
		em.persist(new Entityk("Arek","Garek"));
		//tsrCDI.tsrTest();
		return "Put key 1: "+tsr.getResource("1")+", Put key 2: "+tsr.getResource("2")+
				", Put key 3 (Interceptor): "+tsr.getResource("3")+", Put key 4 (Entity): "+tsr.getResource("4")
				//+", Put key 5 (CDI): "+tsr.getResource("5")
				+"<br/>transaction status: "+tsr.getTransactionStatus()+" ,transaction key: "+tsr.getTransactionKey();
	}
	
}
