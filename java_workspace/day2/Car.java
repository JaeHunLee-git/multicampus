/* �ڵ����� �����Ѵ�*/
class Car{
	/*����, �Լ�*/
	
	String color="yellow";//������ �����
	int price=200;			//������ 200 
	
	//�ڹٿ�����  �޼���  ���ǽ� ��ȯ�� ���� ���θ� �ݵ�� �˷���� �Ѵ�
	//return ���� ���� �޼���� ȣ���ڰ� �ƹ��͵� ������ ���� ���ٰ� �Ͽ� void
	public void setColor(){
		color="red";
	}

	public void setPrice(){
		price=500;
	}

	//return �� ���� �ڷ�����  void �ڸ��� �ۼ��ؾ� ��	
	public int  getRandom(){
		return 5;
	}

	public boolean  getFlag(){
		return true;
	}
}


