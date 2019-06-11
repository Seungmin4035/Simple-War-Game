package AllMarker;

public class A extends Marker {
	public A(int check) {
		super(check);
	}
	
	{  //초기화(생성자 실행 전 초기화)
		moveRange = 2;
		attackRange = 4;
		attackPower = 3;
		strength = 12;
	}
	
	public void printInfo() {
		System.out.printf("A%d_(%d, %d)", check+1, this.x+1, this.y+1);
	}
	
	public void moveXY(int x, int y) {
		System.out.printf("A%d move (%d, %d) to ", check+1, this.x+1, this.y+1);
		this.x += x;
		this.y += y;
		System.out.printf("(%d, %d)\n", this.x+1, this.y+1);
	}
	
	public String toString() {
		return "A" + tmpcheck;
	}
	
	public void printStrength() {
		System.out.printf("A%d's HitPoint decrease %d to %d\n", check+1, tmp_strength, strength);
		if(strength <= 0) 
			System.out.printf("A%d is dead\n", tmpcheck);
	}
	
	public boolean checkDirection(int x, int y) {
		if(x == 0 || y == 0)
			return true;
		else {
			System.out.println("This unit can't move diagonal direction\n");
			return false;
		}
	}
	

}
