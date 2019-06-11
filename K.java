package AllMarker;

public class K extends Marker{
	public K(int check) {
		super(check);
		if (check==0)  x = 0;  //ù��° ������
		else x = 10;
		y = 5;
	}

	{  //�ʱ�ȭ(������ ���� �� �ʱ�ȭ)
		moveRange = 1;
		attackRange = 0;  //���ݺҰ�
		attackPower = 0; //���ݷ�x
		strength = 15;
	}

	public void printInfo() {
		System.out.printf("K%d_(%d, %d)", check+1, this.x+1, this.y+1);
	}
	
	public void moveXY(int x, int y) {
		System.out.printf("K%d move (%d, %d) to ", check+1, this.x+1, this.y+1);
		this.x += x;
		this.y += y;
		System.out.printf("(%d, %d)\n", this.x+1, this.y+1);
	}
	
	public String toString() {
		return "K" + tmpcheck;
	}
	
	public void printStrength() {
		int winner;
		
		System.out.printf("K%d's HitPoint decrease %d to %d\n", check+1, tmp_strength, strength);
		if(strength <= 0) {
			System.out.printf("K%d is dead\n", tmpcheck);
			if(check==0) winner = 2;
			else winner = 1;
			System.out.printf("[Player %d] Win!", winner);
			System.exit(0);  //�ٷ� ����
		}
	}
}
