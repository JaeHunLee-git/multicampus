/*
[��Ű���� ����ϴ� ���� ]  

1) .java �� .class ������ �Բ� ���� �ʰ� �и����� ����
2) ���ü� �ִ� Ŭ�������� ���� ���丮���� ��� ���� 
3) Ŭ������ �ߺ� ������ ���ذ� �� �ִ�.
*/
package animal;

//��Ű���� �����, Ŭ������ �ܺο��� ����ϰ� �Ϸ��� �ݵ��
//�ش� Ŭ������ public ���� �����ؾ� �Ѵ�
public class Dog{
	private String name="�ǹ�"; // default ���������ڰ� ����
	private int age=9;
	
	//������  public ���� ���Ƶ� ������, �߿��� ������ ��찡 �����Ƿ� 
	//������ public ���� ���� ���߹���� ���� ã�ƺ��� ����..
	//���� ������ ������ private �ܺ��� ������ ����, ���� ���� �޼��带
	//���ؼ��� �������� ������ �� �ֵ��� �ϴ� Ŭ���� ���� ����� ������ 
	//ĸ��ȭ(����ȭ) -encapsulation
	
	public String getName(){  //getter  reading
		return name;
	}
	
	public void setName(String name){ //setter  writing
		this.name=name;
	}

}
