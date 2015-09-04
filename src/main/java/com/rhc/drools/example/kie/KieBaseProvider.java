package com.rhc.drools.example.kie;

import org.kie.api.KieBase;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;

public class KieBaseProvider {
	private final KieContainer KIE_CONTAINER;
	
	public KieBaseProvider() {
		KIE_CONTAINER = KieServices.Factory.get().newKieClasspathContainer();
	}
	
	public KieBase getKieBaseForDecisionTable() {
		return KIE_CONTAINER.getKieBase();
	}
}
