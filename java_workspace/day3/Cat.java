/*
�����ڰ� Ŭ������ �����Ѵٴ� ���� ����
�� ������ ���ο� �ڷ����� â���س���
�Ͱ� ����. ���� �Ʒ��� CatŬ������
Cat �� �ڷ����̴�
*/
class Cat{
	String name;
	int age;
	String color;	
	
	/*
	�ý��� �� �����Ϸ��� ���� ���ǵ�
	�����ڸ� ������ ����Ʈ �����ڶ� ��
	����Ʈ �������� ������ �ּ����� ����
	�� ���� ������ �ȳ����� ����..
	public Cat(){
	}
	*/

	//�����ڰ� �����ڸ� ����ϸ�, ���̻�
	//�����Ϸ��� ���� �� �����������̾���
	//����Ʈ �����ڴ� �������� �ʴ´�
	//�����ڿ� �Ű������� �ξ, �پ��� �ν��Ͻ��� �����غ���..
	//�����ڵ� �޼����̹Ƿ�, �Ű������� ����� �� �ִ�..
	//������ �޼���� ��ȯ���� �ξ�� �ȵȴ�
	//void ���� ��ȯ���� �δ� ����, �Ϲݸ޼��尡 �Ǿ������ 
	//new ������ �ڿ��� ȣ��Ǵ� �����ڷμ� ������ ����X(ȣ��X)
	/*
	public Cat(String name, int age, String color){
		
		//this�� �ν��Ͻ��� �ڱ� �ڽ��� ����Ű�� ���� ���۷��� ����

		this.name=name;
		this.age=age;
		this.color=color;
		System.out.println("���� ������ ������ ȣ��");
	}
	*/

	//�Ϲݸ޼��� ��...
	public void setName(String name){
		this.name=name;
	}
	public void setAge(int age){
		this.age=age;
	}
	public void setColor(String color){
		this.color=color;
	}
}
