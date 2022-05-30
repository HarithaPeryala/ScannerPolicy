package com.wf.cto.application.controller;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wf.cto.application.PolicyEnforcer;

@RestController
public class PolicyController {

		@Autowired
		KieContainer kieContainer;
		
		@Autowired
		RestTemplate restTemplate;
		
		@GetMapping(value="scan")
		public String getScan(@RequestParam String projectID) {
			PolicyEnforcer policyEnforcer = new PolicyEnforcer();
			policyEnforcer.setProjectProfile(projectID, restTemplate);

			KieSession kieSession = kieContainer.newKieSession("rulesSession");
			kieSession.insert(policyEnforcer);
			kieSession.fireAllRules();
			kieSession.dispose();
			
			return policyEnforcer.getEnforcePolicy();
		}


}
