class DataConvert{
	
	public static void main(String[] args){
		short s1 = 3;
		short s2=4;
		
		/*
		���������� ��ǻ�� �ý����� 4����Ʈ �� 32��Ʈ�� ����ȭ�Ǿ� �־����Ƿ�
		���������� ���α׷��� ���� ���� ����� 32��Ʈ�� ���� ������.
		���� java ������ �������� �ƴ� int ���� ���� �������� ���ؼ���
		�� byte, short  ���ؼ���, ���� �������� �ڵ� int�������� ����ȯ�� �߻�
		byte  < short < int < long 
		*/

		//ù��° �ذ�å
		int result = s1 + s2;
		
		//�ս��� �����ؼ���, short���� ��ȯ�ϱ� ���ϸ� 
		//��������ȯ �������� cast �����ڸ� �������
		short result2 = (short)(s1 + s2);
	}
}