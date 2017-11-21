package transactionSynchronizationRegistry;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.inject.Model;
import javax.transaction.TransactionSynchronizationRegistry;

@Model
public class TransSyncRegistryCDI {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
	public void tsrTest() {
		tsr.putResource("5", "Five");
	}

}
