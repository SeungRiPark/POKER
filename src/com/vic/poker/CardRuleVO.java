package com.vic.poker;

public class CardRuleVO {
	private int pairNum1;
	private int pairSize1;
	private int pairNum2;
	private int pairSize2;
	private int straightMax;
	public int getStraightMax() {
		return straightMax;
	}
	public void setStraightMax(int straightMax) {
		this.straightMax = straightMax;
	}
	private CardRule cardRule;
	public int getPairNum1() {
		return pairNum1;
	}
	public void setPairNum1(int pairNum1) {
		this.pairNum1 = pairNum1;
	}
	public int getPairSize1() {
		return pairSize1;
	}
	public void setPairSize1(int pairSize1) {
		this.pairSize1 = pairSize1;
	}
	public int getPairNum2() {
		return pairNum2;
	}
	public void setPairNum2(int pairNum2) {
		this.pairNum2 = pairNum2;
	}
	public int getPairSize2() {
		return pairSize2;
	}
	public void setPairSize2(int pairSize2) {
		this.pairSize2 = pairSize2;
	}
	public CardRule getCardRule() {
		return cardRule;
	}
	public void setCardRule(CardRule cardRule) {
		this.cardRule = cardRule;
	}
	
	
}
