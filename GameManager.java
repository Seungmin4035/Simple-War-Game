package Game;

import java.util.ArrayList;
import java.util.Scanner;
import AllMarker.*;
import Civilization.*;

public class GameManager {
	Civilization[] player = new Civilization[2];  //플레이어
	Scanner s = new Scanner(System.in);
	static int Plusable = 1;  // 회복 기능은 게임 진행 중 1번만 가능하기 때문에 횟수를 표시하기 위해 따로 변수를 만들었다.

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
						if(selectMarker==null)  {//존재여부
							System.out.printf("It's not player%d's Unit\n", i+1);
							continue;  //다시 좌표 입력
						}
						break;  //존재하면 while문 나가기
					}
					System.out.print("You choose ");
					selectMarker.printInfo();  //선택한 말의 좌표정보 출력
					System.out.println();
					while(true) {
						System.out.println("Input Move Direction [(x, y) => (current x + x, current y + y)]");
						move_x = s.nextInt();
						move_y = s.nextInt();

						checkDirection = selectMarker.checkDirection(move_x, move_y);
						if(checkDirection==false)  //true가 반환되면 말 옮길 수 있음 (A는 false가 반환될 수 있음)
							continue;
						
						rangeCheck = selectMarker.checkRange(move_x, move_y, num);  //이동범위 
						if(rangeCheck==false)
							continue;

						if(checkMove()==false)  //중복되는 말이 있으면 중복됨을 알리는 문자열 출력
							continue;
						
						
						selectMarker.moveXY(move_x, move_y); //움직이고 난 뒤 말의 좌표정보 출력
						if(((selectMarker.getX())== 5 && (selectMarker.getY())==5) && Plusable ==1) {
							System.out.println("Congratulations! Now you are in healing zone.");
							System.out.println("Your soldier will be given *three point* of strength.");
							selectMarker.plusStrength();
							selectMarker.printInfo();
							System.out.print(" now the soldier's strength is "+ selectMarker.getStrength() + "!");
							System.out.println();
							Plusable--;
						}
						
						break;  //플레이어의 turn이 넘어감
					}
				}
				break;

				case 2:{
					System.out.println("Choose Attack - Select Unit for Attack(Coordinate)");
					while(true) {
						select_x = s.nextInt(); 
						select_y = s.nextInt();
						selectMarker = player[i].getSelectMarker(select_x-1, select_y-1);
						if(selectMarker==null) { //존재여부
							System.out.printf("It's not player%d's Unit\n\n", i+1);
							continue;
						}
						if(selectMarker.getAttackPower() == 0) {  //만약 왕을 선택했을 시(왕은 공격력이 0)
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
						if(targetMarker == null) {   //공격당할 말이 존재하지 않으면
							if(i==0) System.out.printf("It's not player2's Unit\n");
							if(i==1) System.out.printf("It's not player1's Unit\n");
							continue;  //좌표 다시 입력
						}

						rangeCheck = selectMarker.checkRange(target_x-1, target_y-1, num);  //공격범위 체크
						if(rangeCheck==false)
							continue;

						checkDirection = selectMarker.checkDirection(move_x, move_y);  //이동방향과 공격방향 조건 같음
						if(checkDirection==false)  //true가 반환되면 moveRC실행
							continue;

						System.out.print("You choose ");
						targetMarker.printInfo();
						System.out.println("for Target");
						
						System.out.println(selectMarker + " Attack " + targetMarker);
						
						targetMarker.loseStrength(selectMarker.getAttackPower());  //체력 잃음
						targetMarker.printStrength();   //이 때 왕의 체력이 0보다 작으면 종료하는 명령문이 실행된다.

						if(targetMarker.getStrength() <= 0)  //체력이 0보다 작거나 같게되면
							allPlayerMarker.remove(targetMarker); //해당 말 삭제

						break;//힐링, 포기구간 추가
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
					else if(g ==1 && i ==0) { // player1 가 포기하겠다고 했을 경우
						System.out.println("You chose Yes.");
						System.out.println("***************************************");
						System.out.printf("The game is over. [Player %d] Win!", i+2);
						return;
					}
					else if(g==1 && i==1) {  // player2가 포기 하겠다고 했을 경우
						System.out.println("You chose Yes.");
						System.out.println("***************************************");
						System.out.printf("The game is over. [Player %d] Win!", i);
						return;
					}
					break;
				} //switch-case문
			}  //for문 (player순서)	
			
		} //while문 
		
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
		for(int i = 0; i < board.length; i++) {            //x좌표가 j y좌표가 i 
			for(int j = 0; j < board[i].length; j++) {
				board[i][j] = null;   //초기화
			}
		}
		
		for(int i = 0; i < board.length; i++) {            //x좌표가 j y좌표가 i 
			for(int j = 0; j < board[i].length; j++) {
				for(Marker m : allPlayerMarker) {
					if(m.getX() == j && m.getY() == i) {
						board[i][j] = m.toString();
					}
				}
			}
		}

		System.out.println("   1   2   3   4   5   6   7   8   9   10   11");
		System.out.println("  ┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
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
				System.out.println("  |━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━|");
			else
			System.out.println("  ┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
		}
	}
}
