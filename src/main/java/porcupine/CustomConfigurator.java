package porcupine;

import javax.enterprise.inject.Specializes;

import com.airhacks.porcupine.configuration.control.ExecutorConfigurator;
import com.airhacks.porcupine.execution.control.ExecutorConfiguration;

@Specializes
public class CustomConfigurator extends ExecutorConfigurator {

	@Override
	public ExecutorConfiguration forPipeline(String name) {
		if(name.equals("justMyCustomName")) {
			return new ExecutorConfiguration.Builder()
			//.maxPoolSize(44).corePoolSize(22).build();
			.queueCapacity(1).maxPoolSize(1).corePoolSize(1).build();
		} else {
			return ExecutorConfiguration.defaultConfiguration();
		}
	}

	

}
