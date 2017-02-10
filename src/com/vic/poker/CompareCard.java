package com.vic.poker;




public class CompareCard {




	// 
	public CardRuleVO getRule(int[] arrCard){
		int count = 0;
		int[] cntCard = new int[13];
		int[] arrShape = new int[4];


		for(int i =0; i < arrCard.length ; i++){
			cntCard[arrCard[i]%13]++;
			arrShape[arrCard[i]/13]++;
		}

		int pairNum1 = 0;
		int pairSize1 = 0;
		int pairNum2 = 0;
		int pairSize2 = 0;

		for(int i = cntCard.length-1 ;  i >= 0;i--) {
			if(cntCard[i] > 1) {
				if(pairNum1 == 0){
					pairNum1 = i+1;
					pairSize1 = cntCard[i];
				}else if(pairNum2 == 0){
					pairNum2 = i+1;
					pairSize2 = cntCard[i];
				}
			}
			if(count < 5 ) {
				if(cntCard[i] != 0) {
					count++;
				}else {
					count = 0;
				}
			}
		}



		if(pairSize2 > pairSize1){
			int tmp = pairNum1;
			pairNum1 = pairNum2;
			pairNum2 = tmp;

			tmp = pairSize1;
			pairSize1 = pairSize2;
			pairSize2 = tmp;

		}

		CardRuleVO vo = new CardRuleVO();

		vo.setPairNum1(pairNum1);
		vo.setPairNum2(pairNum2);
		vo.setPairSize1(pairSize1);
		vo.setPairSize2(pairSize2);

		CardRule cardRule = CardRule.NOPAIR;

		switch(pairSize1) {
		case 2:
			if(pairSize2 == 2){
				cardRule = CardRule.TWOPAIR;
			}
			else{
				cardRule = CardRule.ONEPAIR;
			}
			break;
		case 3:
			if(pairSize2 == 2 || pairSize2 == 3){
				cardRule = CardRule.FULLHOUSE;
			}else {
				cardRule = CardRule.TRIPLE;
			}
			break;
		case 4:
			cardRule = CardRule.FOURCARD;
			break;
		}
		if(cardRule == CardRule.NOPAIR || cardRule == CardRule.ONEPAIR || cardRule == CardRule.TWOPAIR || cardRule == CardRule.TRIPLE){
			for(int i =0; i < arrShape.length; i++) {
				if(arrShape[i] > 4) {
					cardRule = CardRule.FLASH;

				}
			}
			if(count == 5) {
				cardRule = CardRule.STRAIGHT;

			}

		}

		if(count == 5) {
			int[] arrCard13 = new int[7];
			int max =0;
			for(int i =0; i< arrCard.length;i++){
				arrCard13[i] = arrCard[i]%13;
			}

			int min =0;
			for(int i =0; i< arrCard13.length;i++){
				if(arrCard13[i] <= min){
					min = arrCard13[i];
				}
			}
			for(int i =0; i< arrCard13.length;i++){
				arrCard13[i] -= min;
			}

			for(int i =0; i< 3;i++){
				// 0234568  
				// 01234
				if(arrCard13[i] == 0 && arrCard13[i+1] == 1 && arrCard13[i+2] == 2 && arrCard13[i+3] == 3 
						&& arrCard13[i+4] == 4){
					if((i+5) < arrCard13.length && arrCard13[i+5] == 5){
						if((i+6) < arrCard13.length && arrCard13[i+5] == 6){
							max = arrCard13[i+6];
							break;
						}else {
							max = arrCard13[i+5];
							break;
						}
					}else{
						max = arrCard13[i+4];
					}
				}
				if(i == 2){
					for(int j = 0; j < arrCard13.length; j++) {
						arrCard13[j]--;

					}
				}
			}
			vo.setStraightMax(max);

		}
		vo.setCardRule(cardRule);
		return vo;
	}

	public int compareRule(CardRuleVO vo1, CardRuleVO vo2) {
		int whosWin = 0;

		if(vo1.getCardRule().value > vo2.getCardRule().value) {
			whosWin = 1;
		}else if(vo1.getCardRule().value < vo2.getCardRule().value) {
			whosWin = -1;
		} else{
			if(vo1.getPairNum1() > vo2.getPairNum1()) {
				if(vo2.getPairNum1() == 1) {
					whosWin = -1;
				}else {
					whosWin = 1;
				}
			} else if(vo1.getPairNum1() < vo2.getPairNum1()){
				if(vo1.getPairNum1() == 1) {
					whosWin = 1;
				}else{
					whosWin = -1;
				}
			}else{
				if(vo1.getPairNum2() > vo2.getPairNum2()) {
					whosWin = 1;
				}else if(vo1.getPairNum2() < vo2.getPairNum2()) {
					whosWin = -1;
				}else {
					whosWin = 0;
				}

			}

		}
		if(vo1.getStraightMax() !=0 && vo2.getStraightMax() !=0) {
			if(vo1.getStraightMax() > vo2.getStraightMax()) {
				whosWin = 1;
			}else if(vo1.getStraightMax() < vo2.getStraightMax()) {
				whosWin = -1;
			}else {
				whosWin = 0;
			}
		}
		return whosWin;
	}
}
