class Test{
	public static void main(String[] args){
		/*�ڹٽ�ũ��Ʈ ó��, �ڹٵ� �迭�� ���̸� �������� �ø��� �����
			������? ���� ó��...�� ũ�⸦ ���������� ó���� �� ����?
			��) �ڹٿ��� �迭�� ũ�� ������ �Ұ�
			�ذ�å) �÷��� �����ӿ� ���̺귯������ js  ���� �ξ� �پ���
			           �ڷ� ������ ����
			�ڹٿ��� �÷��� �����ӿ��̳� ��Ÿ ���̺귯���� �̿��Ϸ���? 
			api document�� ���� ������� �˾ƾ� �� 
		*/			

		//���� �츮 ���丮���� String.class  ������ �������� �ʴ���, 
		//java.lang �̶�� ���丮�� �����ϴ� String.class�� 
		//�ڵ����� ��ġ�� ���� �������� Ŭ������ �νĵǾ��� �ִ�..
		//���: String.class �� ������ �ִ� ��Ȳ��
		String str="korea , fighting";
		char c = str.charAt(6);
		System.out.println(c);

		String[] result = str.split(","); //��ǥ�� �������� ���ڿ� �迭�� ��ȯ����
		System.out.println("�и���Ų ��� �迭�� ���̴� "+result.length);
		System.out.println("0��° ���� "+result[0].trim());
		System.out.println("1��° ���� "+result[1].trim());

		
		//���� ���丮��  Dog ���� ������ �����Ƿ�, ���� �ȳ�
		Dog d = new Dog("�Ʒ���",6);

		//Cat c = new Cat(); ���� ���丮�� Cat �� �������� ����

	}
}
