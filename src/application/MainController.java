package application;

import java.net.URL;
import java.util.Arrays;
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
import javafx.util.Duration;

public class MainController implements Initializable{

	//Main.fxml에 만들어 놓은 주사위 ImageView들
	@FXML private ImageView imageView_dice_1_1;
	@FXML private ImageView imageView_dice_1_2;
	@FXML private ImageView imageView_dice_1_3;
	@FXML private ImageView imageView_dice_1_4;
	@FXML private ImageView imageView_dice_1_5;
	@FXML private ImageView imageView_dice_2_1;
	@FXML private ImageView imageView_dice_2_2;
	@FXML private ImageView imageView_dice_2_3;
	@FXML private ImageView imageView_dice_2_4;
	@FXML private ImageView imageView_dice_2_5;
	
	//누가 이겼는지 표시할 Label
	@FXML private Label label_whosWin;
	@FXML private Label label_diceRule1;
	@FXML private Label label_diceRule2;
	
	//판돈과 잔액을 표시할 Label
	@FXML private Label label_comMoney;
	@FXML private Label label_userMoney;
	@FXML private Label label_userBet;
	
	//판돈을 올리고 내릴 버튼
	@FXML private Button button_upBet;
	@FXML private Button button_downBet;
	
	//ImageView들을 for문으로 처리하기 쉽게 하기 위한 배열
	private ImageView[] arrImageView_1;
	private ImageView[] arrImageView_2;
	
	//주사위가 던져졌을때 주사위 패를 저장할 배열
	private int[] arrDice_1;
	private int[] arrDice_2;
	
	//주사위 이미지를 1~6까지 저장할 배열
	private Image[] arrImage_dice;
	
	//각자 남은 금액을 저장할 변수
	private int comMoney;
	private int userMoney;
	private int userBet;
	
	public MainController(){
		//주사위 이미지를 미리 배열에 담아둔다
		arrImage_dice = new Image[6];
		for(int i=0;i<6;i++){
			arrImage_dice[i] = new Image("resource/dice_"+(i+1)+".jpg");
		}
		
		
		//주사위 패를 담을 배열 초기화
		arrDice_1 = new int[5];
		arrDice_2 = new int[5];
		
		//컴퓨터와 유저 둘에게 판돈 초기화
		comMoney = 10000;
		userMoney = 5000;
		userBet = 100;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//화면에 표시된 주사위 ImageView를 관리하기 쉽도록 배열에 넣어둔다
		arrImageView_1 = new ImageView[5];
		arrImageView_1[0] = imageView_dice_1_1;
		arrImageView_1[1] = imageView_dice_1_2;
		arrImageView_1[2] = imageView_dice_1_3;
		arrImageView_1[3] = imageView_dice_1_4;
		arrImageView_1[4] = imageView_dice_1_5;
		
		arrImageView_2 = new ImageView[5];
		arrImageView_2[0] = imageView_dice_2_1;
		arrImageView_2[1] = imageView_dice_2_2;
		arrImageView_2[2] = imageView_dice_2_3;
		arrImageView_2[3] = imageView_dice_2_4;
		arrImageView_2[4] = imageView_dice_2_5;
		
		//판돈을 표시
		label_comMoney.setText(comMoney+"");
		label_userMoney.setText(userMoney+"");
		label_userBet.setText(userBet+"");
	}
	
	//배팅 올림 버튼을 클릭했을때의 이벤트
	@FXML
	public void clickUpBet(ActionEvent event){
		if(userBet<1000){
			userBet += 100;
			label_userBet.setText(userBet+"");
		}
	}
	
	//배팅 내림 버튼을 클릭했을때의 이벤트
	@FXML
	public void clickDownBet(ActionEvent event){
		if(userBet>100){
			userBet -= 100;
			label_userBet.setText(userBet+"");
		}
	}

	//주사위 던지기 버튼을 클릭했을때의 이벤트
	@FXML
	public void throwDice(ActionEvent event){
		
		label_whosWin.setText("");
		label_diceRule1.setText("");
		label_diceRule2.setText("");
		
		//주사위를 던지면 일단 랜덤하게 주사위를 표시한 후 보기 좋도록 정렬하여 재배치 한다
		//이때 Timeline으로 애니메이션 효과를 준다.
		Timeline timeline = new Timeline();
		
		//위쪽의 주사위들을 랜덤하게 굴리고 주사위 패를 비교하기 위해 주사위패 배열에 담는다.
		for(int i=0;i<arrImageView_1.length;i++){
			int imageNum = (int)(Math.random()*6);
			//주사위를 던진 순간은 랜덤한 상태 그대로 보여줄 것이기 때문에 Duration을 0초로 하여 KeyFrame을 timeline에 추가한다.
			KeyValue keyValue = new KeyValue(arrImageView_1[i].imageProperty(),arrImage_dice[imageNum]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(0), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			arrDice_1[i] = imageNum;
		}
		//아래쪽의 주사위들을 랜덤하게 굴리고 주사위 패를 비교하기 위해 주사위패 배열에 담는다.
		for(int i=0;i<arrImageView_2.length;i++){
			int imageNum = (int)(Math.random()*6);
			//주사위를 던진 순간은 랜덤한 상태 그대로 보여줄 것이기 때문에 Duration을 0초로 하여 KeyFrame을 timeline에 추가한다.
			KeyValue keyValue = new KeyValue(arrImageView_2[i].imageProperty(),arrImage_dice[imageNum]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(0), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			arrDice_2[i] = imageNum;
		}
		
		//주사위 배열을 보기좋기 하기 위해 정렬한다.
		Arrays.sort(arrDice_1);
		Arrays.sort(arrDice_2);
		
		//정렬한 주사위 모양으로 이미지를 다시 바꿔준다.
		for(int i=0;i<arrImageView_1.length;i++){
			//주사위 배열을 보기좋도록  정렬한 후에 주사위 이미지를 재배열 하기 위해 Duration을 1초로 넣고 배열에 해당하는 이미지를 설정하는 KeyFrame을 timeline에 추가한다.
			KeyValue keyValue = new KeyValue(arrImageView_1[i].imageProperty(),arrImage_dice[arrDice_1[i]]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
			timeline.getKeyFrames().add(keyFrame);
		}
		for(int i=0;i<arrImageView_2.length;i++){
			//주사위 배열을 보기좋도록  정렬한 후에 주사위 이미지를 재배열 하기 위해 Duration을 1초로 넣고 배열에 해당하는 이미지를 설정하는 KeyFrame을 timeline에 추가한다.
			KeyValue keyValue = new KeyValue(arrImageView_2[i].imageProperty(),arrImage_dice[arrDice_2[i]]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
			timeline.getKeyFrames().add(keyFrame);
		}
		
		//서로의 패를 비교하여 누가 이겼는지 판별
		CompareDice compareDice = new CompareDice();
		DiceRuleVO vo1 = compareDice.getRule(arrDice_1);
		DiceRuleVO vo2 = compareDice.getRule(arrDice_2);
		int result = compareDice.compareRule(vo1, vo2);
		String whosWinText = "";
		if(result>0){
			whosWinText = "컴퓨터가 승리하였습니다.";
			comMoney += userBet;
			userMoney -= userBet;
		}else if(result<0){
			whosWinText = "사용자가 승리하였습니다.";
			comMoney -= userBet;
			userMoney += userBet;
		}else{
			whosWinText = "무승부입니다.";
		}
		
		//누가 이겼는지 서로의 패가 무엇이었는지 라벨에 표시하는 키 프레임을 타임라인에 추가
		KeyValue keyValue_whosWin = new KeyValue(label_whosWin.textProperty(),whosWinText);
		KeyValue keyValue_diceRule1 = new KeyValue(label_diceRule1.textProperty(),vo1.getDiceRule().name());
		KeyValue keyValue_diceRule2 = new KeyValue(label_diceRule2.textProperty(),vo2.getDiceRule().name());
		KeyFrame keyFrame_whosWin = new KeyFrame(Duration.seconds(1.5), keyValue_whosWin);
		KeyFrame keyFrame_diceRule1 = new KeyFrame(Duration.seconds(1.5), keyValue_diceRule1);
		KeyFrame keyFrame_diceRule2 = new KeyFrame(Duration.seconds(1.5), keyValue_diceRule2);
		timeline.getKeyFrames().add(keyFrame_whosWin);
		timeline.getKeyFrames().add(keyFrame_diceRule1);
		timeline.getKeyFrames().add(keyFrame_diceRule2);
		
		//베팅금액에 따라 돈을 따고 잃는 금액 표시
		KeyValue keyValue_comMoney = new KeyValue(label_comMoney.textProperty(),comMoney+"");
		KeyValue keyValue_userMoney = new KeyValue(label_userMoney.textProperty(),userMoney+"");
		KeyFrame keyFrame_comMoney = new KeyFrame(Duration.seconds(1.5), keyValue_comMoney);
		KeyFrame keyFrame_userMoney = new KeyFrame(Duration.seconds(1.5), keyValue_userMoney);
		timeline.getKeyFrames().add(keyFrame_comMoney);
		timeline.getKeyFrames().add(keyFrame_userMoney);
		
		
		//timeline을 플레이 하면 지금까지 저장된 키프레임들이 시간별 Duration에 따라 재생된다.
		timeline.play();
	}
}
