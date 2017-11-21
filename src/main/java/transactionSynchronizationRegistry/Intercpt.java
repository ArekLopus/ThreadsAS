package transactionSynchronizationRegistry;

import javax.annotation.Resource;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.transaction.TransactionSynchronizationRegistry;

public class Intercpt {
	
	@Resource
	TransactionSynchronizationRegistry tsr;
	
    @AroundInvoke
    public Object injectMap(InvocationContext ic) throws Exception{
        tsr.putResource("3", "Three");
        return ic.proceed();
    }


}
