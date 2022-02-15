package com.retail.rewards.program.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.retail.rewards.program.modal.ResponseSummary;
import com.retail.rewards.program.modal.RewardPointsRequest;
import com.retail.rewards.program.modal.RewardPointsResponse;
import com.retail.rewards.program.modal.RewardPointsSumByCustomerResponse;

@Service
public class RewardPointsService {

	String[] months = { "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec" };
	Integer total;

	public List<ResponseSummary> calculateRewardsTotalByCustomerMonths(List<RewardPointsRequest> request) {
		List<RewardPointsResponse> resList = calculateRewards(request);
		return getRewardSystemTotalByCustomerMonths(resList);

	}

	public List<RewardPointsSumByCustomerResponse> getRewardsTotalByCustomer(List<RewardPointsRequest> request) {
		List<RewardPointsResponse> resList = calculateRewards(request);
		return buildRewardsTotalByCustomer(resList);

	}

	private List<ResponseSummary> getRewardSystemTotalByCustomerMonths(List<RewardPointsResponse> response) {
		Map<String, Map<String, List<RewardPointsResponse>>> multipleFieldsMapList = response.stream()
				.collect(Collectors.groupingBy(RewardPointsResponse::getCustomerName,
						Collectors.groupingBy(RewardPointsResponse::getMonth)));

		List<ResponseSummary> list = new ArrayList<>();
		multipleFieldsMapList.entrySet().forEach(entry1 -> {

			entry1.getValue().entrySet().forEach(entry2 -> {
				ResponseSummary responseSummary = new ResponseSummary();
				responseSummary.setName(entry1.getKey());
				responseSummary.setMonth(entry2.getKey());
				responseSummary.setNoOfTransactions(entry2.getValue().size());
				responseSummary.setRewardPoints(entry2.getValue().stream().mapToInt(i -> i.getRewardPoints()).sum());
				responseSummary.setData(entry2.getValue());
				list.add(responseSummary);
			});
		});

		return list;
	}

	private List<RewardPointsSumByCustomerResponse> buildRewardsTotalByCustomer(List<RewardPointsResponse> response) {
		Map<String, Map<String, List<RewardPointsResponse>>> multipleFieldsMapList = response.stream()
				.collect(Collectors.groupingBy(RewardPointsResponse::getCustomerName,
						Collectors.groupingBy(RewardPointsResponse::getMonth)));

		List<RewardPointsSumByCustomerResponse> resList = new ArrayList<>();
		multipleFieldsMapList.entrySet().forEach(entry1 -> {
			RewardPointsSumByCustomerResponse res = new RewardPointsSumByCustomerResponse();
			total = 0;
			res.setCustomer(entry1.getKey());
			entry1.getValue().entrySet().forEach(entry2 -> {
				total = total + entry2.getValue().stream().mapToInt(i -> i.getRewardPoints()).sum();
			});
			res.setPoints(total);
			resList.add(res);
		});

		return resList;
	}

	private List<RewardPointsResponse> calculateRewards(List<RewardPointsRequest> request) {
		List<RewardPointsResponse> resList = new ArrayList<>();
		request.forEach(data -> {
			RewardPointsResponse response = new RewardPointsResponse();
			Integer points = 0;

			Integer over100 = data.getAmount() - 100;

			if (over100 > 0) {
				// A customer receives 2 points for every dollar spent over $100 in each
				// transaction
				points += (over100 * 2);
			}

			if (data.getAmount() > 50) {
				// plus 1 point for every dollar spent over $50 in each transaction
				points += 50;
			}
			String month = null;
			try {
				month = getMonth(data.getTransactionDate());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("Invalid Date format , unable to parse input transactionDate");
				e.printStackTrace();
			}

			response.setRewardPoints(points);
			response.setMonth(month);
			response.setCustomerName(data.getName());
			response.setTransactionDate(data.getTransactionDate());
			response.setAmount(data.getAmount());
			resList.add(response);
		});
		return resList;

	}

	private String getMonth(String dateString) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("MM-dd-yyyy");
		Date parse = sdf.parse(dateString);
		Calendar c = Calendar.getInstance();
		c.setTime(parse);
		return months[c.get(Calendar.MONTH)];

	}

}