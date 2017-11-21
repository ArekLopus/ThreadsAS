package transactionSynchronizationRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.transaction.TransactionSynchronizationRegistry;

@Stateless
public class TransSyncRegistryTest {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public void tsrTest() {
		tsr.putResource("1", "One");
	}

}
