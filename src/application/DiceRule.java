package application;

//�ֻ��� ���� �����̸� ������ enum
public enum DiceRule {
	NOPAIR(0),
	ONEPAIR(1),
	TWOPAIR(2),
	TRIPLEDICE(3),
	FULLHOUSE(4),
	FOURDICE(5),
	FIVEDICE(6);
	public final int value;
	private DiceRule(int value){
		this.value = value;
	}
}
