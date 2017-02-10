package com.vic.poker;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class MainController implements Initializable {
	@FXML private ImageView img_dic_up_1;
	@FXML private ImageView img_dic_up_2;
	@FXML private ImageView img_dic_up_3;
	@FXML private ImageView img_dic_up_4;
	@FXML private ImageView img_dic_up_5;
	@FXML private ImageView img_dic_up_6;
	@FXML private ImageView img_dic_up_7;
	@FXML private ImageView img_dic_down_1;
	@FXML private ImageView img_dic_down_2;
	@FXML private ImageView img_dic_down_3;
	@FXML private ImageView img_dic_down_4;
	@FXML private ImageView img_dic_down_5;
	@FXML private ImageView img_dic_down_6;
	@FXML private ImageView img_dic_down_7;
	@FXML private Button btn_play;
	@FXML private Label label_whosWin;
	@FXML private Label label_pc;
	@FXML private Label label_user;
	@FXML private Label usr_money;
	@FXML private Label bat_money;
	@FXML private Label pc_money;
	@FXML private Button btn_batup;
	@FXML private Button btn_batdown;




	private ImageView[] arrImageView_1;
	private ImageView[] arrImageView_2;

	private Image[] arrImage_dice;

	private int[] arrCard_1 = new int[7];
	private int[] arrCard_2 = new int[7];
	private boolean[] isCard = new boolean[52]; 
	private boolean isStart; 


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println(">> main ");

		arrImage_dice = new Image[52];


		for(int i = 0 ; i < arrImage_dice.length ; i++){

			arrImage_dice[i] = new Image("resource/poker_"+(i+1)+".JPG");

		}

		arrImageView_1 = new ImageView[7];
		arrImageView_1[0] = img_dic_up_1;
		arrImageView_1[1] = img_dic_up_2;
		arrImageView_1[2] = img_dic_up_3;
		arrImageView_1[3] = img_dic_up_4;
		arrImageView_1[4] = img_dic_up_5;
		arrImageView_1[5] = img_dic_up_6;
		arrImageView_1[6] = img_dic_up_7;
		arrImageView_2 = new ImageView[7];	
		arrImageView_2[0] = img_dic_down_1;
		arrImageView_2[1] = img_dic_down_2;
		arrImageView_2[2] = img_dic_down_3;
		arrImageView_2[3] = img_dic_down_4;
		arrImageView_2[4] = img_dic_down_5;
		arrImageView_2[5] = img_dic_down_6;
		arrImageView_2[6] = img_dic_down_7;

		pc_money.setText("10000");
		usr_money.setText("50000");
		bat_money.setText("1000");
	}

	@FXML
	public void onClickBatCard(ActionEvent event) {
		isStart = true;
//				boolean tok = true;
//				while(tok) {
		
		Timeline timeline = new Timeline();
		
		for(int i = 0 ; i < arrImageView_1.length; i++) {
			
			KeyValue keyvalue1 = new KeyValue(arrImageView_1[i].imageProperty(), null);
			KeyFrame keyframe1 = new KeyFrame(Duration.seconds(0), keyvalue1);
			KeyValue keyvalue2 = new KeyValue(arrImageView_2[i].imageProperty(), null);
			KeyFrame keyframe2 = new KeyFrame(Duration.seconds(0), keyvalue2);
			
			timeline.getKeyFrames().addAll(keyframe1,keyframe2);
		
		}
		
		for(int i = 0 ; i < arrImageView_1.length; i++) {
			int imageShape1 = 0;
			int imageNumber1 = 0;
			int imageShape2 = 0;
			int imageNumber2 = 0;

			while(true){
				imageShape1 = (int)(Math.random()*4);
				imageNumber1 = (int)(Math.random()*13);
				if(!isCard[imageShape1*13+imageNumber1]) {
					break;
				}
			}
			while(true){
				imageShape2 = (int)(Math.random()*4);
				imageNumber2 = (int)(Math.random()*13);
				if(!isCard[imageShape2*13+imageNumber2]) {
					break;
				}
			}
			
			KeyValue keyvalue = new KeyValue(arrImageView_1[i].imageProperty(), arrImage_dice[(imageShape1*13+imageNumber1)]);
			KeyFrame keyframe = new KeyFrame(Duration.seconds((i+0.1)*0.3), keyvalue);
			timeline.getKeyFrames().add(keyframe);

			isCard[imageShape1*13+imageNumber1] = true;
			arrCard_1[i] = (imageShape1*13+imageNumber1);

			isCard[imageShape2*13+imageNumber2] = true;
			arrCard_2[i] = (imageShape2*13+imageNumber2);
		}


		cardSort();

		for(int i = 0 ; i < arrImageView_1.length; i++) {

			arrImageView_1[i].setImage(arrImage_dice[arrCard_1[i]]);
			arrImageView_1[i].setId(""+(arrCard_1[i]));
			arrImageView_2[i].setImage(arrImage_dice[arrCard_2[i]]);
			arrImageView_2[i].setId(""+(arrCard_2[i]));
		}

		getResult();

//					if(vo1.getCardRule().name().equals("STRAIGHT")&&vo2.getCardRule().name().equals("STRAIGHT")){
//						tok = false;
//					}
		resetCard();
//				}

	}

	private void cardSort() {
		for(int i =0; i < arrCard_1.length; i++) {
			for(int j =0 ; j < arrCard_1.length ; j++) {
				if(arrCard_1[i]%13 != arrCard_1[j]%13 && arrCard_1[i]%13 < arrCard_1[j]%13){
					arrCard_1[i] ^= arrCard_1[j];
					arrCard_1[j] ^= arrCard_1[i];
					arrCard_1[i] ^= arrCard_1[j];
				}
				if(arrCard_2[i]%13 != arrCard_2[j]%13 && arrCard_2[i]%13 < arrCard_2[j]%13){
					arrCard_2[i] ^= arrCard_2[j];
					arrCard_2[j] ^= arrCard_2[i];
					arrCard_2[i] ^= arrCard_2[j];
				}	
			}
		}

		for(int i =0; i < arrCard_1.length; i++){
			for(int j =0 ; j < arrCard_1.length ; j++) {
				if(arrCard_1[i]%13 == arrCard_1[j]%13 && arrCard_1[i]/13 < arrCard_1[j]/13){
					int tmp = arrCard_1[i];
					arrCard_1[i] = arrCard_1[j];
					arrCard_1[j] = tmp;
				}
				if(arrCard_2[i]%13 == arrCard_2[j]%13 && arrCard_2[i]/13 < arrCard_2[j]/13){
					int tmp = arrCard_2[i];
					arrCard_2[i] = arrCard_2[j];
					arrCard_2[j] = tmp;
				}	
			}
		}
	}

	private void getResult() {
		CompareCard compareCard = new CompareCard();
		CardRuleVO vo1 = compareCard.getRule(arrCard_1);
		CardRuleVO vo2 = compareCard.getRule(arrCard_2);

		int result = compareCard.compareRule(vo1, vo2);

		String whosWinText ="";
		if(result > 0){
			whosWinText ="컴퓨터가 승리하였습니다.";

			pc_money.setText(Integer.parseInt(pc_money.getText())+Integer.parseInt(bat_money.getText())+"");
			usr_money.setText(Integer.parseInt(usr_money.getText())-Integer.parseInt(bat_money.getText())+"");


		} else if(result < 0){
			whosWinText = "사용자가 승리하였습니다.";

			pc_money.setText(Integer.parseInt(pc_money.getText())-Integer.parseInt(bat_money.getText())+"");
			usr_money.setText(Integer.parseInt(usr_money.getText())+Integer.parseInt(bat_money.getText())+"");
		} else {
			whosWinText = "무승부 입니다.";
		}

		label_pc.setText(vo1.getCardRule().name());
		label_user.setText(vo2.getCardRule().name());
		label_whosWin.setText(whosWinText);
	}

	private void resetCard() {
		for(int i = 0; i < isCard.length; i++) {
			isCard[i] = false;
		}
	}

	@FXML
	public void setOnClickBatUp(ActionEvent event) {
		int betMoney = Integer.parseInt( bat_money.getText());

		if(betMoney < 10000 && betMoney <=  Integer.parseInt(usr_money.getText())) {
			betMoney+= 1000;
			bat_money.setText(betMoney+"");
		}

	}

	@FXML
	public void setOnClickBatDown(ActionEvent event) {
		int betMoney = Integer.parseInt( bat_money.getText());

		if(betMoney > 1000 ) {
			betMoney-= 1000;
			bat_money.setText(betMoney+"");
		}
	}

	@FXML
	public void setOnclickChangeCard(MouseEvent event) {

		if(isStart) {
			System.out.println(((ImageView)event.getTarget()).getId());
			((ImageView)event.getTarget()).setImage(new Image("resource/poker_0.JPG"));
			int cardNum = Integer.parseInt(((ImageView)event.getTarget()).getId());

			pc_money.setText(Integer.parseInt(pc_money.getText())+100+"");
			usr_money.setText(Integer.parseInt(usr_money.getText())-100+"");

			for(int i = 0 ; i < arrCard_2.length; i++) {
				if(arrCard_2[i] == cardNum){
					int imageShape1 = 0;
					int imageNumber1 = 0;

					while(true){
						imageShape1 = (int)(Math.random()*4);
						imageNumber1 = (int)(Math.random()*13);
						if(!isCard[imageShape1*13+imageNumber1]) {
							break;
						}
					}
					isCard[cardNum] = false;
					isCard[imageShape1*13+imageNumber1] = true;
					arrCard_2[i] = (imageShape1*13+imageNumber1);
				}



			}

		}
	}

	@FXML
	public void setOnClickCahngeButton() {
		if(isStart){
			cardSort();

			for(int i = 0 ; i < arrImageView_1.length; i++) {

				arrImageView_1[i].setImage(arrImage_dice[arrCard_1[i]]);
				arrImageView_1[i].setId(""+(arrCard_1[i]));
				arrImageView_2[i].setImage(arrImage_dice[arrCard_2[i]]);
				arrImageView_2[i].setId(""+(arrCard_2[i]));
			}
			getResult();
		}
	}



}
