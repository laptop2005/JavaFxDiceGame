package application;

public class CompareDice {
	
	//�ֻ��� �а� �������� ��ȯ�ϴ� �޼ҵ�
	public DiceRuleVO getRule(int[] arrDice){
		//�ֻ��� ��ȣ�� ��� �ߺ��Ǵ��� �˱����� �迭
		int[] diceCnt = new int[6];
		
		//�ֻ�����ȣ��� �ش��ȣ�� ������ ������Ų��.
		for(int i=0;i<arrDice.length;i++){
			diceCnt[arrDice[i]]++;
		}
		
		int pairNum1 = 0;
		int pairSize1 = 0;
		int pairNum2 = 0;
		int pairSize2 = 0;
		//�ֻ����� �ټ����̱� ������ �ִ�� �ߺ��Ǵ� ���� �ΰ��̴�
		//������� ��� �ֻ����� �ϳ��� ���� ������ �ߺ��� ���� ����
		for(int i=0;i<diceCnt.length;i++){
			//�ֻ��� ������ 1 �̻��̶�� ���� ����� �̻��̶�� ��
			if(diceCnt[i]>1){
				//�а� �Ǵ� ���ڿ� ������ 1���� 2���� ��´�.
				if(pairNum1==0){
					pairNum1 = i+1;
					pairSize1 = diceCnt[i];
				}else if(pairNum2==0){
					pairNum2 = i+1;
					pairSize2 = diceCnt[i];
				}
			}
		}
		
		//2���а� 1���к��� ������ �ڸ��� �ٲپ� �� ū �а� 1���� ������ �Ѵ�.
		if(pairSize2>pairSize1){
			int temp = 0;
			temp = pairNum1;
			pairNum1 = pairNum2;
			pairNum2 = temp;
			
			temp = pairSize1;
			pairSize1 = pairSize2;
			pairSize2 = temp;
		}
		
		DiceRuleVO vo = new DiceRuleVO();
		vo.setPairNum1(pairNum1);
		vo.setPairSize1(pairSize1);
		vo.setPairNum2(pairNum2);
		vo.setPairSize2(pairSize2);
		
		//� �п� �ش�Ǵ��� ã�Ƴ���
		DiceRule diceRule = DiceRule.NOPAIR;
		switch(pairSize1){
			case 2:
				if(pairSize2==2){
					diceRule = DiceRule.TWOPAIR;
				}else{
					diceRule = DiceRule.ONEPAIR;
				}
				break;
			case 3:
				if(pairSize2==2){
					diceRule = DiceRule.FULLHOUSE;
				}else{
					diceRule = DiceRule.TRIPLEDICE;
				}
				break;
			case 4:
				diceRule = DiceRule.FOURDICE;
				break;
			case 5:
				diceRule = DiceRule.FIVEDICE;
				break;
		}
		vo.setDiceRule(diceRule);
		
		return vo;
	}
	
	//���� �̰���� �Ǵ��ϴ� �޼ҵ� vo1�� �̰����� 1 vo2�� �̰����� -1 ���º��̸� 0�� ����
	public int compareRule(DiceRuleVO vo1, DiceRuleVO vo2){
		
		int whosWin = 0;
		
		if(vo1.getDiceRule().value>vo2.getDiceRule().value){
			whosWin = 1;
		}else if(vo1.getDiceRule().value<vo2.getDiceRule().value){
			whosWin = -1;
		}else{
			if(vo1.getPairNum1()>vo2.getPairNum1()){
				whosWin = 1;
			}else if(vo1.getPairNum1()<vo2.getPairNum1()){
				whosWin = -1;
			}else{
				if(vo1.getPairNum2()>vo2.getPairNum2()){
					whosWin = 1;
				}else if(vo1.getPairNum2()<vo2.getPairNum2()){
					whosWin = -1;
				}else{
					whosWin = 0;
				}
			}
		}
		
		return whosWin;
	}
	
	public static void main(String[] args) {
		CompareDice obj = new CompareDice();
		int[] arr1 = {1,2,2,3,6};
		int[] arr2 = {1,1,3,3,6};
		DiceRuleVO comVo = obj.getRule(arr1);
		DiceRuleVO userVo = obj.getRule(arr2);
		int i = obj.compareRule(comVo, userVo);
		System.out.println(comVo.getDiceRule());
		System.out.println(userVo.getDiceRule());
		System.out.println(i);
	}
}
