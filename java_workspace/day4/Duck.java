/*
 ���� �ڽ� ��, ������ �����Ѵ�
*/
class Duck extends Bird{
	//������ Ư¡�� �θ�Ŭ������ �̹� ��������
	//�Ƿ�, ���⼭�� �������� ���� Ư������
	//ǥ���ϴ� �� ȿ�����̴�..
	/*
	boolean hasWing=true;
	String color;
	*/
	public Duck(){
		//��Ӱ��迡�� super() ����Ʈ �����ڸ� �ڵ� ȣ��
		//�θ� �Ű����� �ִ� �����ڸ� ������ ��������
		//�����Ϸ��� ���� �ڵ�ȣ�⿡ �������� ����,
		//�����ڰ� ���� ������ �Ѵ�. 
		super("yellow");
		System.out.println("�� ����");
	}

	public void quack(){
		System.out.println("���");
	}

}
