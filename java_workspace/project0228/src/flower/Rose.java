package flower; //�� Ŭ������ bin �ؿ� flower�� �����Ұ���
						// �� �����ϵǸ�, ~~bin/flower ��Ű���ȿ� Rose.class �ΰڴ�
public class Rose{
	String color="red"; //default  ���������ڰ� �����(������ ���� ��Ű������ �ִ�)
	//Ŭ������ ����Ѵ�(����- ��Ӱ����� �ڽ��� �ٸ� ��Ű���� ���� ��� ���� �Ұ�)
	private String name="���";	 //���� Ŭ�������� �޼��常 ���ٰ���
	
	public String getName(){
		return name;
	}
	public void setName(String name){
		this.name=name;
	}
}
