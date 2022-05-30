package com.example.demo;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {

	@Autowired
	KieContainer kieContainer;
	@GetMapping(value="getsla")
	public Test getSla(@RequestParam String type) {
		Test test = new Test();
		test.setType(type);

		KieSession kieSession = kieContainer.newKieSession("rulesSession");
		kieSession.insert(test);
		kieSession.fireAllRules();
		kieSession.dispose();
		
		return test;
	}
}
