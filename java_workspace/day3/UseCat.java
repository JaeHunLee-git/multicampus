class UseCat {
	public static void main(String[] args) {

		//����� �Ѹ��� �ν��Ͻ� �ø��� 
		int   x  =  5;  //�⺻ �ڷ���

		//Cat  c1  = new Cat("�����",5,"���");			
		//Cat  c2  = new Cat("����",2, "����");	
		//System.out.println(c1.name);
		//System.out.println(c2.name);

		Cat c3 = new Cat();
		c3.setName("�����");
		c3.setAge(5);
		c3.setColor("����");

		System.out.println(c3.name);
		System.out.println(c3.age);
		System.out.println(c3.color);
	}
}
