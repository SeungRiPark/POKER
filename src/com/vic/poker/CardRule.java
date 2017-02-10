package com.vic.poker;


public enum CardRule {
	NOPAIR(0),ONEPAIR(1),TWOPAIR(2),TRIPLE(3),
	FLASH(4),STRAIGHT(5),FULLHOUSE(6),FOURCARD(7);
	
	public final int value;
	private CardRule(int value) {
		this.value = value;
	}
}
