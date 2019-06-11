package AllMarker;

public class I extends Marker {
	public I(int check) {
		super(check);
	}

	{  //초기화(생성자 실행 전 초기화)
		moveRange = 1;
		attackRange = 1;
		attackPower = 4;
		strength = 24;
	}
	
	public void printInfo() {
		System.out.printf("I%d_(%d, %d)", check+1, this.x+1, this.y+1);
	}
	
	public void moveXY(int x, int y) {
		System.out.printf("I%d move (%d, %d) to ", check+1, this.x+1, this.y+1);
		this.x += x;
		this.y += y;
		System.out.printf("(%d, %d)\n", this.x+1, this.y+1);
	}
	
	public String toString() {
		return "I" + tmpcheck;
	}
	
	public void printStrength() {
		System.out.printf("I%d's HitPoint decrease %d to %d\n", check+1, tmp_strength, strength);
		if(strength <= 0) 
			System.out.printf("I%d is dead\n", tmpcheck);
	}
}
