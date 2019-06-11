package AllMarker;

import java.util.Random;

public class Marker {
	int x, y, check;   //��, ��
	int tmpcheck;
	int moveRange;  //�̵��Ÿ�
	int attackRange;  //���� ����
	int attackPower;  //���ݷ�
	int strength;  //ü��
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
			System.out.println("Exceed Unit move range\n"); //�̵����� �Ѿ
			return false;
		}

		if((Math.abs(this.x-x) > attackRange || Math.abs(this.y-y) > attackRange) && check2==2 ) {
			System.out.println("Exceed Unit attack range\n"); //���ݹ��� �Ѿ
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
	public void plusStrength() { // �������� �����ϸ� ������� +3 �Ǿ�� �ϱ� ������ ���� �޼ҵ�. ��� ���� �����ؼ� ���� Ŭ������ �ϳ� ������
		this.strength +=3;
	}
}
