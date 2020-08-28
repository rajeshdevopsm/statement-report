package com.assignment.model;

public class Statement {

    private long id;
    private long accountId;
    private String date;
    private String amount;
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "Statement [id=" + id + ", accountId=" + accountId + ", date=" + date + ", amount=" + amount + "]";
	}
	
}
