package Game;

import java.util.Scanner;
import Civilization.*;

public class GameProgram {
	Scanner s = new Scanner(System.in);
	static Civilization[] player = new Civilization[2];  //플레이어
	
	void startScreen() {
		System.out.println("===== Simple War Game =====");
		for(int i = 0; i < player.length; i++) {
			System.out.println("Select Player" + (i+1) + "'s Civilization");
			select(i);
		}
	}
	
	void select(int i) {
		int n;
		System.out.print("1. Korean\n2. Japanese\n=>");
		n = s.nextInt();
		if(n == 1) player[i] = new Korea(i);
		else player[i] = new Japan(i);
		player[i].init();	//배열 초기화
	}
	
	public static void main(String[] args) {		
		GameProgram gp = new GameProgram();
		gp.startScreen();
		
		GameManager gb = new GameManager(player);
		gb.playGame();
	}
}
