public class Hero {
	int hp=10;
	boolean fly=false;
	String name="메가맨";
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
               (라)멤버변수 bullet 에 총알을 대입하고 싶다
	}

	public static void main(String[] args) {
		Hero hero = new Hero();
		hero.setHp(마);
		hero.setFly(바);
		hero.setName(사);
		hero.fire(아);		
	}	
}
