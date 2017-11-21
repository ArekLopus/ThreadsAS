package transactionSynchronizationRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TransSyncRegistryTest2 {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public void tsrTest2() {
		tsr.putResource("2", "Two");
	}

}
