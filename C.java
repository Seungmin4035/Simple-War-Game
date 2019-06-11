package AllMarker;

public class C extends Marker {
	public C(int check) {
		super(check);
	}

	{  //초기화(생성자 실행 전 초기화)
		moveRange = 3;
		attackRange = 1;
		attackPower = 6;
		strength = 15;
	}
	
	public void printInfo() {
		System.out.printf("C%d_(%d, %d)", check+1, this.x+1, this.y+1);
	}
	
	public void moveXY(int x, int y) {
		System.out.printf("C%d move (%d, %d) to ", check+1, this.x+1, this.y+1);
		this.x += x;
		this.y += y;
		System.out.printf("(%d, %d)\n", this.x+1, this.y+1);
	}
	
	public String toString() {
		return "C" + tmpcheck;
	}
	
	public void printStrength() {
		System.out.printf("C%d's HitPoint decrease %d to %d\n", check+1, tmp_strength, strength);
		if(strength <= 0) 
			System.out.printf("C%d is dead\n", tmpcheck);
	}
}
