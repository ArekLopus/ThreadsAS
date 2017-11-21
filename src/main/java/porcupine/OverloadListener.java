package porcupine;

import javax.enterprise.event.Observes;

import com.airhacks.porcupine.execution.entity.Rejection;

public class OverloadListener {

	public void onOverload(@Observes Rejection rejection) {
		System.out.println("Rejection: "+rejection);
	}

}
