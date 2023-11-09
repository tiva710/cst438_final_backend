package com.cst438.controller;


import java.util.Optional;
import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.cst438.dto.MultiplyProblem;
import com.cst438.dto.MultiplyResult;
import com.cst438.service.MultiplyChecker;
import com.cst438.service.MultiplyHistory;


@RestController
@CrossOrigin
public class ResultController {
	
	@Autowired
	private MultiplyChecker checker;
	
	@Autowired
	private MultiplyHistory history;
	
	
	@PostMapping("/result")
	public MultiplyResult check(
                  Principal principal, 
                  @RequestBody MultiplyProblem mp) {
		System.out.println(mp);
		mp = new MultiplyProblem(principal.getName(), mp.factorA(), 
                                     mp.factorB(), mp.attempt());
		MultiplyResult mr = checker.check(mp);
		history.saveHistory(mp, mr);
		//level.postMessageToLevel(mr);
		System.out.println(mr);
		return mr;
	}	
	@GetMapping("/result")
	public MultiplyResult[] getLastNresults(
			Principal principal,
              @RequestParam("lastN") Optional<Integer> lastN) {
		int n = lastN.orElse(5);
		return history.getHistory(principal.getName(), n);
	}

}

