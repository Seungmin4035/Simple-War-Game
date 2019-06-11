package Civilization;

import java.util.ArrayList;
import AllMarker.*;

public class Civilization {
	int check;
	ArrayList<Marker> AllMarker = new ArrayList<>();
	Marker[] arrK; Marker[] arrA; Marker[] arrI; Marker[] arrC;
	
	public void init() {
		arrK[0] = new K(check);
		AllMarker.add(arrK[0]);
		for(int i = 0; i < arrA.length; i++) {
			arrA[i] = new A(check);	
			addMarker(arrA[i]);
		}
		for(int i = 0; i < arrI.length; i++) {
			arrI[i] = new I(check);
			addMarker(arrI[i]);
		}
		for(int i = 0; i < arrC.length; i++) {
			arrC[i] = new C(check);
			addMarker(arrC[i]);
		}

		System.out.println();
	}	

	void addMarker(Marker arrM) {
		do {
			arrM.init();
		}while(compare(arrM));	//true�� �ٽ� while�� ����	
		AllMarker.add(arrM);
	}

	boolean compare(Marker ma) {
		for(Marker m : AllMarker) {
			if(m.compare_rc(ma))
				return true;
		}
		return false;
	}
	
	public Marker getSelectMarker(int x, int y){   //�ش� x,y��ǥ�� �� ��������
		if(arrK[0].checkSelectXY(x, y))  //�Է��� x,y��ǥ�� ���� ��ġ�� �ִ��� Ȯ��
			return arrK[0];
		
		for(int i = 0; i < arrA.length; i++) {
			if(arrA[i].checkSelectXY(x, y))
				return arrA[i];
		}
		for(int i = 0; i < arrI.length; i++) {
			if(arrI[i].checkSelectXY(x, y))
				return arrI[i];
		}
		for(int i = 0; i < arrC.length; i++) {
			if(arrC[i].checkSelectXY(x, y))
				return arrC[i];
		}
		return null;
	}
	
	public ArrayList<Marker> getAllMarker(){
		return AllMarker;
	}
}
