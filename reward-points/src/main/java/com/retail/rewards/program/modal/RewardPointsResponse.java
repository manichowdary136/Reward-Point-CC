package com.retail.rewards.program.modal;

public class RewardPointsResponse {

	private String customerName;
	private String month;
	private String transactionDate;
	private Integer amount;
	private Integer rewardPoints;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}	

	public String getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(String transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getRewardPoints() {
		return rewardPoints;
	}

	public void setRewardPoints(Integer rewardPoints) {
		this.rewardPoints = rewardPoints;
	}

	@Override
	public String toString() {
		return "[customerName=" + customerName + ", month=" + month + ", transactionDate="
				+ transactionDate + ", amount=" + amount + ", rewardPoints=" + rewardPoints + "]";
	}

}
