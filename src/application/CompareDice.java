package application;

public class CompareDice {
	
	//주사위 패가 무엇인지 반환하는 메소드
	public DiceRuleVO getRule(int[] arrDice){
		//주사위 번호가 몇번 중복되는지 알기위한 배열
		int[] diceCnt = new int[6];
		
		//주사위번호대로 해당번호의 갯수를 증가시킨다.
		for(int i=0;i<arrDice.length;i++){
			diceCnt[arrDice[i]]++;
		}
		
		int pairNum1 = 0;
		int pairSize1 = 0;
		int pairNum2 = 0;
		int pairSize2 = 0;
		//주사위가 다섯개이기 때문에 최대로 중복되는 수는 두개이다
		//투페어의 경우 주사위가 하나가 남기 때문에 중복될 일이 없음
		for(int i=0;i<diceCnt.length;i++){
			//주사위 갯수가 1 이상이라는 것은 원페어 이상이라는 뜻
			if(diceCnt[i]>1){
				//패가 되는 숫자와 갯수를 1번과 2번에 담는다.
				if(pairNum1==0){
					pairNum1 = i+1;
					pairSize1 = diceCnt[i];
				}else if(pairNum2==0){
					pairNum2 = i+1;
					pairSize2 = diceCnt[i];
				}
			}
		}
		
		//2번패가 1번패보다 높으면 자리를 바꾸어 더 큰 패가 1번에 오도록 한다.
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
		
		//어떤 패에 해당되는지 찾아내기
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
	
	//누가 이겼는지 판단하는 메소드 vo1이 이겼으면 1 vo2가 이겼으면 -1 무승부이면 0을 리턴
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
