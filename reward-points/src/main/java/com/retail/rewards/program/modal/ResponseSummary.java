package com.retail.rewards.program.modal;

import java.util.List;

public class ResponseSummary {

	private String name;
	private String month;
	private Integer noOfTransactions;
	private Integer rewardPoints;
	private List<RewardPointsResponse> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getNoOfTransactions() {
		return noOfTransactions;
	}

	public void setNoOfTransactions(Integer noOfTransactions) {
		this.noOfTransactions = noOfTransactions;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	public List<RewardPointsResponse> getData() {
		return data;
	}

	public void setData(List<RewardPointsResponse> data) {
		this.data = data;
	}

}
