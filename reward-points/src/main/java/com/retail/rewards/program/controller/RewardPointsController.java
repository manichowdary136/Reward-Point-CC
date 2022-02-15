package com.retail.rewards.program.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retail.rewards.program.modal.ResponseSummary;
import com.retail.rewards.program.modal.RewardPointsRequest;
import com.retail.rewards.program.modal.RewardPointsSumByCustomerResponse;
import com.retail.rewards.program.service.RewardPointsService;

@RestController
@RequestMapping("/retail/points")
public class RewardPointsController {

	@Autowired
	RewardPointsService service;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@PostMapping(path = "/getRewardsTotalByCustomerMonths")
	public List<ResponseSummary> getRewardsTotalByCustomerMonths(@RequestBody List<RewardPointsRequest> request) {
		return service.calculateRewardsTotalByCustomerMonths(request);

	}

	@PostMapping(path = "/getRewardsTotalByCustomer")
	public List<RewardPointsSumByCustomerResponse> getRewardsTotalByCustomer(
			@RequestBody List<RewardPointsRequest> request) {
		return service.getRewardsTotalByCustomer(request);

	}

}
