/*
���� ���丮�� �ִ� Account Ŭ������ ����� �ֹ�������
= ����� ���Ȼ� �ſ� ���
*/
class UseAccount{
	public static void main(String[] args) {
		//�ν��Ͻ� �ø��� 
		Account acc = new Account();
		acc.master="��";
		//acc.balance=500000000; //���� ���� ���� ������ ���� ������ ����
		
		//��� ��� 
		System.out.println(acc.master);

		//������ ���������� ���Ƴ������Ƿ�, �������� ������� ����ؾ� �Ѵ�.. 
		//�̶� �����͸� �������� getter ��, �����͸� ������ setter �� �̿����� 
		acc.setBalance(700000000); //setter�� �̿��� ������ ���� (����)
		System.out.println(acc.getBalance()); //getter�� ���� ���� �б�
	}
}
