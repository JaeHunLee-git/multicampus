package com.sds.app0229.use;

import com.sds.app0229.bird.Bird;
import com.sds.app0229.bird.Duck;

class UseDuck {
	public static void main(String[] args) {
		//�⺻�ڷ��� �� ����ȯ 
		//�ڹٿ����� ����� �ڷ����̶��, ���� ����ȯ�� �����ϴ� 
		//����(byte,short, int ,long, float, double), ����(char)
		
		byte b=4;
		int x=7;
		x = b;
		

		//�ڹٿ��� ��ü���� �ڷ����̶��, ��ü�� ����ȯ�� �����ұ�?  yes
		//�ڹٿ��� ��Ӱ��迡 �ִ� �ڷ����� ���� ����� �ڷ������� �����ȴ�.
		//���� ��ü�� ����ȯ�� ���� 

		//���� �Ѹ��� �ν��Ͻ� �ø��� 
		Duck d = new Duck();
		//�θ���� ������ , age�� ����غ���
		System.out.println(d.name);
		d.eat();


		Bird bird = new Duck();
		System.out.println(bird.age);

	    byte      =    int
		Duck dc = (Duck)bird;
		
	}
}
