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

	//Main.fxml�� ����� ���� �ֻ��� ImageView��
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
	
	//���� �̰���� ǥ���� Label
	@FXML private Label label_whosWin;
	@FXML private Label label_diceRule1;
	@FXML private Label label_diceRule2;
	
	//�ǵ��� �ܾ��� ǥ���� Label
	@FXML private Label label_comMoney;
	@FXML private Label label_userMoney;
	@FXML private Label label_userBet;
	
	//�ǵ��� �ø��� ���� ��ư
	@FXML private Button button_upBet;
	@FXML private Button button_downBet;
	
	//ImageView���� for������ ó���ϱ� ���� �ϱ� ���� �迭
	private ImageView[] arrImageView_1;
	private ImageView[] arrImageView_2;
	
	//�ֻ����� ���������� �ֻ��� �и� ������ �迭
	private int[] arrDice_1;
	private int[] arrDice_2;
	
	//�ֻ��� �̹����� 1~6���� ������ �迭
	private Image[] arrImage_dice;
	
	//���� ���� �ݾ��� ������ ����
	private int comMoney;
	private int userMoney;
	private int userBet;
	
	public MainController(){
		//�ֻ��� �̹����� �̸� �迭�� ��Ƶд�
		arrImage_dice = new Image[6];
		for(int i=0;i<6;i++){
			arrImage_dice[i] = new Image("resource/dice_"+(i+1)+".jpg");
		}
		
		
		//�ֻ��� �и� ���� �迭 �ʱ�ȭ
		arrDice_1 = new int[5];
		arrDice_2 = new int[5];
		
		//��ǻ�Ϳ� ���� �ѿ��� �ǵ� �ʱ�ȭ
		comMoney = 10000;
		userMoney = 5000;
		userBet = 100;
	}
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		//ȭ�鿡 ǥ�õ� �ֻ��� ImageView�� �����ϱ� ������ �迭�� �־�д�
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
		
		//�ǵ��� ǥ��
		label_comMoney.setText(comMoney+"");
		label_userMoney.setText(userMoney+"");
		label_userBet.setText(userBet+"");
	}
	
	//���� �ø� ��ư�� Ŭ���������� �̺�Ʈ
	@FXML
	public void clickUpBet(ActionEvent event){
		if(userBet<1000){
			userBet += 100;
			label_userBet.setText(userBet+"");
		}
	}
	
	//���� ���� ��ư�� Ŭ���������� �̺�Ʈ
	@FXML
	public void clickDownBet(ActionEvent event){
		if(userBet>100){
			userBet -= 100;
			label_userBet.setText(userBet+"");
		}
	}

	//�ֻ��� ������ ��ư�� Ŭ���������� �̺�Ʈ
	@FXML
	public void throwDice(ActionEvent event){
		
		label_whosWin.setText("");
		label_diceRule1.setText("");
		label_diceRule2.setText("");
		
		//�ֻ����� ������ �ϴ� �����ϰ� �ֻ����� ǥ���� �� ���� ������ �����Ͽ� ���ġ �Ѵ�
		//�̶� Timeline���� �ִϸ��̼� ȿ���� �ش�.
		Timeline timeline = new Timeline();
		
		//������ �ֻ������� �����ϰ� ������ �ֻ��� �и� ���ϱ� ���� �ֻ����� �迭�� ��´�.
		for(int i=0;i<arrImageView_1.length;i++){
			int imageNum = (int)(Math.random()*6);
			//�ֻ����� ���� ������ ������ ���� �״�� ������ ���̱� ������ Duration�� 0�ʷ� �Ͽ� KeyFrame�� timeline�� �߰��Ѵ�.
			KeyValue keyValue = new KeyValue(arrImageView_1[i].imageProperty(),arrImage_dice[imageNum]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(0), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			arrDice_1[i] = imageNum;
		}
		//�Ʒ����� �ֻ������� �����ϰ� ������ �ֻ��� �и� ���ϱ� ���� �ֻ����� �迭�� ��´�.
		for(int i=0;i<arrImageView_2.length;i++){
			int imageNum = (int)(Math.random()*6);
			//�ֻ����� ���� ������ ������ ���� �״�� ������ ���̱� ������ Duration�� 0�ʷ� �Ͽ� KeyFrame�� timeline�� �߰��Ѵ�.
			KeyValue keyValue = new KeyValue(arrImageView_2[i].imageProperty(),arrImage_dice[imageNum]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(0), keyValue);
			timeline.getKeyFrames().add(keyFrame);
			arrDice_2[i] = imageNum;
		}
		
		//�ֻ��� �迭�� �������� �ϱ� ���� �����Ѵ�.
		Arrays.sort(arrDice_1);
		Arrays.sort(arrDice_2);
		
		//������ �ֻ��� ������� �̹����� �ٽ� �ٲ��ش�.
		for(int i=0;i<arrImageView_1.length;i++){
			//�ֻ��� �迭�� ����������  ������ �Ŀ� �ֻ��� �̹����� ��迭 �ϱ� ���� Duration�� 1�ʷ� �ְ� �迭�� �ش��ϴ� �̹����� �����ϴ� KeyFrame�� timeline�� �߰��Ѵ�.
			KeyValue keyValue = new KeyValue(arrImageView_1[i].imageProperty(),arrImage_dice[arrDice_1[i]]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
			timeline.getKeyFrames().add(keyFrame);
		}
		for(int i=0;i<arrImageView_2.length;i++){
			//�ֻ��� �迭�� ����������  ������ �Ŀ� �ֻ��� �̹����� ��迭 �ϱ� ���� Duration�� 1�ʷ� �ְ� �迭�� �ش��ϴ� �̹����� �����ϴ� KeyFrame�� timeline�� �߰��Ѵ�.
			KeyValue keyValue = new KeyValue(arrImageView_2[i].imageProperty(),arrImage_dice[arrDice_2[i]]);
			KeyFrame keyFrame = new KeyFrame(Duration.seconds(1), keyValue);
			timeline.getKeyFrames().add(keyFrame);
		}
		
		//������ �и� ���Ͽ� ���� �̰���� �Ǻ�
		CompareDice compareDice = new CompareDice();
		DiceRuleVO vo1 = compareDice.getRule(arrDice_1);
		DiceRuleVO vo2 = compareDice.getRule(arrDice_2);
		int result = compareDice.compareRule(vo1, vo2);
		String whosWinText = "";
		if(result>0){
			whosWinText = "��ǻ�Ͱ� �¸��Ͽ����ϴ�.";
			comMoney += userBet;
			userMoney -= userBet;
		}else if(result<0){
			whosWinText = "����ڰ� �¸��Ͽ����ϴ�.";
			comMoney -= userBet;
			userMoney += userBet;
		}else{
			whosWinText = "���º��Դϴ�.";
		}
		
		//���� �̰���� ������ �а� �����̾����� �󺧿� ǥ���ϴ� Ű �������� Ÿ�Ӷ��ο� �߰�
		KeyValue keyValue_whosWin = new KeyValue(label_whosWin.textProperty(),whosWinText);
		KeyValue keyValue_diceRule1 = new KeyValue(label_diceRule1.textProperty(),vo1.getDiceRule().name());
		KeyValue keyValue_diceRule2 = new KeyValue(label_diceRule2.textProperty(),vo2.getDiceRule().name());
		KeyFrame keyFrame_whosWin = new KeyFrame(Duration.seconds(1.5), keyValue_whosWin);
		KeyFrame keyFrame_diceRule1 = new KeyFrame(Duration.seconds(1.5), keyValue_diceRule1);
		KeyFrame keyFrame_diceRule2 = new KeyFrame(Duration.seconds(1.5), keyValue_diceRule2);
		timeline.getKeyFrames().add(keyFrame_whosWin);
		timeline.getKeyFrames().add(keyFrame_diceRule1);
		timeline.getKeyFrames().add(keyFrame_diceRule2);
		
		//���ñݾ׿� ���� ���� ���� �Ҵ� �ݾ� ǥ��
		KeyValue keyValue_comMoney = new KeyValue(label_comMoney.textProperty(),comMoney+"");
		KeyValue keyValue_userMoney = new KeyValue(label_userMoney.textProperty(),userMoney+"");
		KeyFrame keyFrame_comMoney = new KeyFrame(Duration.seconds(1.5), keyValue_comMoney);
		KeyFrame keyFrame_userMoney = new KeyFrame(Duration.seconds(1.5), keyValue_userMoney);
		timeline.getKeyFrames().add(keyFrame_comMoney);
		timeline.getKeyFrames().add(keyFrame_userMoney);
		
		
		//timeline�� �÷��� �ϸ� ���ݱ��� ����� Ű�����ӵ��� �ð��� Duration�� ���� ����ȴ�.
		timeline.play();
	}
}
