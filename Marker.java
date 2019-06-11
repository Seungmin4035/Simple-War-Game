package AllMarker;

import java.util.Random;

public class Marker {
	int x, y, check;   //행, 열
	int tmpcheck;
	int moveRange;  //이동거리
	int attackRange;  //공격 범위
	int attackPower;  //공격력
	int strength;  //체력
	int tmp_strength;

	public Marker(int check) {
		this.check = check;
		tmpcheck = check + 1;
	}

	public boolean compare_rc(Marker ma) {
		if(this.x == ma.x && this.y == ma.y) return true;
		return false;
	}

	public void init() {
		Random ran = new Random();
		if(check == 0) x = ran.nextInt(4);  //0~3*/
		else x = ran.nextInt(4) + 7;
		y = ran.nextInt(10);  //0~10
	}

	public void printInfo() {}

	public boolean checkSelectXY(int x, int y) {
		if(this.x == x && this.y == y) return true;
		return false;
	}

	public boolean checkRange(int x, int y, int check2) {
		if((x > moveRange || y > moveRange) && check2==1 ) {
			System.out.println("Exceed Unit move range\n"); //이동범위 넘어감
			return false;
		}

		if((Math.abs(this.x-x) > attackRange || Math.abs(this.y-y) > attackRange) && check2==2 ) {
			System.out.println("Exceed Unit attack range\n"); //공격범위 넘어감
			return false;
		}
		return true;
	}

	public boolean checkDirection(int x, int y) {
		return true;
	}

	public void moveXY(int x, int y) { }

	public void loseStrength(int attack) {
		tmp_strength = this.strength;
		this.strength-=attack;
	}

	public void printStrength() {}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}
	
	public String toString() {
		return null;
	}

	public int getAttackPower() {
		return attackPower;
	}
	
	public int getStrength() {
		return strength;
	}
	public void plusStrength() { // 힐링존에 도달하면 생명력이 +3 되어야 하기 때문에 만든 메소드. 모든 말이 동일해서 슈퍼 클래스에 하나 만들어둠
		this.strength +=3;
	}
}
