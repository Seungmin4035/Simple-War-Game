package Game;

import java.util.ArrayList;
import java.util.Scanner;
import AllMarker.*;
import Civilization.*;

public class GameManager {
	Civilization[] player = new Civilization[2];  //�÷��̾�
	Scanner s = new Scanner(System.in);
	static int Plusable = 1;  // ȸ�� ����� ���� ���� �� 1���� �����ϱ� ������ Ƚ���� ǥ���ϱ� ���� ���� ������ �������.

	int select_x, select_y, move_x, move_y, target_x, target_y;;
	boolean rangeCheck, moveCheck, checkDirection;
	Marker selectMarker, targetMarker, tmpMarker;
	ArrayList<Marker> allPlayerMarker = new ArrayList<>();
	ArrayList<Marker> player1_Marker = new ArrayList<>();
	String[][] board = new String[11][11];

	GameManager(Civilization[] player){
		this.player = player;
	}

	void addMarkerList() {
		allPlayerMarker.addAll(player[0].getAllMarker());  //player1 Marker
		allPlayerMarker.addAll(player[1].getAllMarker());  //player2 Marker
	}

	void playGame() {
		addMarkerList();
		
		while(true) {
			
			for(int i = 0; i<player.length; i++) {
				drawGameBoard();
				System.out.printf("[Player %d]'s turn\n1. Move\n2. Attack\n3. ChangeTurn\n4. Give up\n", i+1);
				int num = s.nextInt();

				switch(num) {
				case 1:{
					System.out.println("\nChoose Move - Select Unit(Coordinate)");
					while(true) {
						select_x = s.nextInt(); 
						select_y = s.nextInt();
						selectMarker = player[i].getSelectMarker(select_x-1, select_y-1);   /////////////////////// 
						if(selectMarker==null)  {//���翩��
							System.out.printf("It's not player%d's Unit\n", i+1);
							continue;  //�ٽ� ��ǥ �Է�
						}
						break;  //�����ϸ� while�� ������
					}
					System.out.print("You choose ");
					selectMarker.printInfo();  //������ ���� ��ǥ���� ���
					System.out.println();
					while(true) {
						System.out.println("Input Move Direction [(x, y) => (current x + x, current y + y)]");
						move_x = s.nextInt();
						move_y = s.nextInt();

						checkDirection = selectMarker.checkDirection(move_x, move_y);
						if(checkDirection==false)  //true�� ��ȯ�Ǹ� �� �ű� �� ���� (A�� false�� ��ȯ�� �� ����)
							continue;
						
						rangeCheck = selectMarker.checkRange(move_x, move_y, num);  //�̵����� 
						if(rangeCheck==false)
							continue;

						if(checkMove()==false)  //�ߺ��Ǵ� ���� ������ �ߺ����� �˸��� ���ڿ� ���
							continue;
						
						
						selectMarker.moveXY(move_x, move_y); //�����̰� �� �� ���� ��ǥ���� ���
						if(((selectMarker.getX())== 5 && (selectMarker.getY())==5) && Plusable ==1) {
							System.out.println("Congratulations! Now you are in healing zone.");
							System.out.println("Your soldier will be given *three point* of strength.");
							selectMarker.plusStrength();
							selectMarker.printInfo();
							System.out.print(" now the soldier's strength is "+ selectMarker.getStrength() + "!");
							System.out.println();
							Plusable--;
						}
						
						break;  //�÷��̾��� turn�� �Ѿ
					}
				}
				break;

				case 2:{
					System.out.println("Choose Attack - Select Unit for Attack(Coordinate)");
					while(true) {
						select_x = s.nextInt(); 
						select_y = s.nextInt();
						selectMarker = player[i].getSelectMarker(select_x-1, select_y-1);
						if(selectMarker==null) { //���翩��
							System.out.printf("It's not player%d's Unit\n\n", i+1);
							continue;
						}
						if(selectMarker.getAttackPower() == 0) {  //���� ���� �������� ��(���� ���ݷ��� 0)
							System.out.printf("This unit can't Attack");
							continue;
						}
						break;
					}

					System.out.print("You choose ");
					selectMarker.printInfo();
					System.out.println("for Attack");
					while(true) {
						System.out.println("Select Target(Coordinate)");
						target_x = s.nextInt();
						target_y = s.nextInt();
						if(i==0) targetMarker = player[1].getSelectMarker(target_x-1, target_y-1);
						else targetMarker = player[0].getSelectMarker(target_x-1, target_y-1);
						if(targetMarker == null) {   //���ݴ��� ���� �������� ������
							if(i==0) System.out.printf("It's not player2's Unit\n");
							if(i==1) System.out.printf("It's not player1's Unit\n");
							continue;  //��ǥ �ٽ� �Է�
						}

						rangeCheck = selectMarker.checkRange(target_x-1, target_y-1, num);  //���ݹ��� üũ
						if(rangeCheck==false)
							continue;

						checkDirection = selectMarker.checkDirection(move_x, move_y);  //�̵������ ���ݹ��� ���� ����
						if(checkDirection==false)  //true�� ��ȯ�Ǹ� moveRC����
							continue;

						System.out.print("You choose ");
						targetMarker.printInfo();
						System.out.println("for Target");
						
						System.out.println(selectMarker + " Attack " + targetMarker);
						
						targetMarker.loseStrength(selectMarker.getAttackPower());  //ü�� ����
						targetMarker.printStrength();   //�� �� ���� ü���� 0���� ������ �����ϴ� ��ɹ��� ����ȴ�.

						if(targetMarker.getStrength() <= 0)  //ü���� 0���� �۰ų� ���ԵǸ�
							allPlayerMarker.remove(targetMarker); //�ش� �� ����

						break;//����, ���ⱸ�� �߰�
					}
				}
				break;

				case 3:
					continue;   
					
				case 4:
					System.out.println("Are you sure you want to give the game up? (1.YES 2.NO)");
					int g = s.nextInt();
					if(g == 2) {
						System.out.println("You chose No.");
						System.out.println("The game will be continued.");
						i--;
						continue;
					}
					else if(g ==1 && i ==0) { // player1 �� �����ϰڴٰ� ���� ���
						System.out.println("You chose Yes.");
						System.out.println("***************************************");
						System.out.printf("The game is over. [Player %d] Win!", i+2);
						return;
					}
					else if(g==1 && i==1) {  // player2�� ���� �ϰڴٰ� ���� ���
						System.out.println("You chose Yes.");
						System.out.println("***************************************");
						System.out.printf("The game is over. [Player %d] Win!", i);
						return;
					}
					break;
				} //switch-case��
			}  //for�� (player����)	
			
		} //while�� 
		
	} 

	boolean checkMove() {
		for(Marker m : allPlayerMarker) {
			if(selectMarker.getX() + move_x == m.getX() && selectMarker.getY() + move_y == m.getY()) {
				System.out.println("Other Unit already here.\n");
				return false;
			}
		}
		return true;
	}

	void drawGameBoard() {
		for(int i = 0; i < board.length; i++) {            //x��ǥ�� j y��ǥ�� i 
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = null;   //�ʱ�ȭ
			}
		}
		
		for(int i = 0; i < board.length; i++) {            //x��ǥ�� j y��ǥ�� i 
			for(int j = 0; j < board[i].length; j++) {
				for(Marker m : allPlayerMarker) {
					if(m.getX() == j && m.getY() == i) {
						board[i][j] = m.toString();
					}
				}
			}
		}

		System.out.println("   1   2   3   4   5   6   7   8   9   10   11");
		System.out.println("  ������������������������������������������������������������������������������������������");
		for(int i = 0; i < board.length; i++) {
			if(i<9) {
				System.out.print( i+1 +" |");
			}
			else
				System.out.print( i+1 +"|");

			
			for(int j= 0; j < board[i].length; j++) {
				if(board[i][j] != null) 
						System.out.print(board[i][j]+" |");
				else
					System.out.print("   |");
			} 
			
			System.out.println("");
			if(i != 10)
				System.out.println("  |��������������������������������������������������������������������������������������|");
			else
			System.out.println("  ������������������������������������������������������������������������������������������");
		}
	}
}
