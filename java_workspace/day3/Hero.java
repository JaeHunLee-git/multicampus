public class Hero {
	int hp=10;
	boolean fly=false;
	String name="�ް���";
	Bullet bullet;
	
	public void setHp(int hp) { 
		this.hp=hp;
	}

	public void setFly(boolean fly) {
		this.fly=fly;
	}

	public void setName(String name) {
		this.name=name;	
	}

	public void fire(Bullet bullet){
               (��)������� bullet �� �Ѿ��� �����ϰ� �ʹ�
	}

	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.setHp(��);
		hero.setFly(��);
		hero.setName(��);
		hero.fire(��);		
	}	
}
