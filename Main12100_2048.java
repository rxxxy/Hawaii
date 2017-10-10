package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main12100_2048 {

	static int num;
	static int[][] board;
	static int[][] tempBoard;
	static int[] direction = new int[5];
	static int max = 0;
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(in.readLine().trim());
		board = new int[num][num];
		tempBoard = new int[num][num];
		StringTokenizer st = null;
		
		for(int i=0; i<num; i++){
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<num; j++){
				board[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//DFS실행하기
		getDirection(0);
		
		//Max값 출력하기
		System.out.println(max);
		
	}
	
	
	public static void getDirection(int index){
		
		
		//리턴 조건은 방향배열(사이즈5)를 채웠을 때
		if(index==5){
			
			for(int i=0; i<num; i++){
				for(int j=0; j<num; j++){
					tempBoard[i][j] = board[i][j];
				}
			}
			
			for(int i=0; i<5; i++){
				
				switch(direction[i]){
				
				case 1 :
					east();
					break;
					
				case 2 :
					west();
					break;
					
				case 3 : 
					south();
					break;
					
				case 4 :
					north();
					break;
				
				}
				
			}
			
			
//			for(int i=0; i<num;i++){
//				for(int j=0; j<num; j++){
//					System.out.print(tempBoard[i][j]+" ");
//				}
//				System.out.println();
//			}
			
			//최대값 구하기
			for(int i=0; i<num; i++){
				for(int j=0; j<num; j++){
					if(tempBoard[i][j]>max){
						max = tempBoard[i][j];
					}
				}
			}
			
			return;
		}
		
		
		//5번의 움직임동안 나오는 방향 정하기
		for(int i=1; i<=4; i++){
			direction[index] = i;
			getDirection(index+1);
		}
	}
	
	
	static void east(){
		
		int x = num; //x는 채워 넣을 자리
		int xx = num; //xx는 움직이면서 데려올 숫자를 찾는 인덱스
		boolean[] check = new boolean[num]; //이미 합쳐진 값이면 true 처리
		
		for(int i=0; i<num; i++){
			
			x = num;
			xx = num;
			check = new boolean[num];
			
			while(xx>=0){
				
				while(xx>=0){
					xx--;
					if(xx>=0 && tempBoard[i][xx]!=0) break;
				}
				
				if(xx>=0){
					if(x==num){
						x=num-1;
						if(xx==x) continue;
						tempBoard[i][x] = tempBoard[i][xx];
						tempBoard[i][xx] = 0;
					}else if(tempBoard[i][xx]==tempBoard[i][x] && !check[x]){
						tempBoard[i][x] *= 2;
						tempBoard[i][xx] = 0;
						check[x] = true;
					}else if(tempBoard[i][xx]==tempBoard[i][x] && check[x]){
						x--;
						if(xx==x) continue;
						tempBoard[i][x] = tempBoard[i][xx];
						tempBoard[i][xx] = 0;
					}else if(tempBoard[i][xx]!=tempBoard[i][x]){
						x--;
						if(xx==x) continue;
						tempBoard[i][x] = tempBoard[i][xx];
						tempBoard[i][xx] = 0;
					}
				}
				
			}
			
		}
				
	}
	
	static void west(){
		
		int x = -1; //x는 채워 넣을 자리
		int xx = -1; //xx는 움직이면서 데려올 숫자를 찾는 인덱스
		boolean[] check = new boolean[num]; //이미 합쳐진 값이면 true 처리
		
		for(int i=0; i<num; i++){
			
			x = -1;
			xx = -1;
			check = new boolean[num];
			
			while(xx<num){
				
				while(xx<num){
					xx++;
					if(xx<num && tempBoard[i][xx]!=0) break;
				}
				
				if(xx<num){
					if(x==-1){
						x=0;
						if(xx==x) continue;
						tempBoard[i][x] = tempBoard[i][xx];
						tempBoard[i][xx] = 0;
					}else if(tempBoard[i][xx]==tempBoard[i][x] && !check[x]){
						tempBoard[i][x] *= 2;
						tempBoard[i][xx] = 0;
						check[x] = true;
					}else if(tempBoard[i][xx]==tempBoard[i][x] && check[x]){
						x++;
						if(xx==x) continue;
						tempBoard[i][x] = tempBoard[i][xx];
						tempBoard[i][xx] = 0;
					}else if(tempBoard[i][xx]!=tempBoard[i][x]){
						x++;
						if(xx==x) continue;
						tempBoard[i][x] = tempBoard[i][xx];
						tempBoard[i][xx] = 0;
					}
				}
				
			}
			
		}
		
	}
	
	static void south(){
		
		int y = num; //y는 채워 넣을 자리
		int yy = num; //yy는 움직이면서 데려올 숫자를 찾는 인덱스
		boolean[] check = new boolean[num]; //이미 합쳐진 값이면 true 처리
		
		for(int i=0; i<num; i++){
			
			y = num;
			yy = num;
			check = new boolean[num];
			
			while(yy>=0){
				
				while(yy>=0){
					yy--;
					if(yy>=0 && tempBoard[yy][i]!=0) break;
				}
				
				if(yy>=0){
					if(y==num){
						y=num-1;
						if(yy==y) continue;
						tempBoard[y][i] = tempBoard[yy][i];
						tempBoard[yy][i] = 0;
					}else if(tempBoard[yy][i]==tempBoard[y][i] && !check[y]){
						tempBoard[y][i] *= 2;
						tempBoard[yy][i] = 0;
						check[y] = true;
					}else if(tempBoard[yy][i]==tempBoard[y][i] && check[y]){
						y--;
						if(yy==y) continue;
						tempBoard[y][i] = tempBoard[yy][i];
						tempBoard[yy][i] = 0;
					}else if(tempBoard[yy][i]!=tempBoard[y][i]){
						y--;
						if(yy==y) continue;
						tempBoard[y][i] = tempBoard[yy][i];
						tempBoard[yy][i] = 0;
					}
				}
				
			}
			
		}
		
	}
	
	static void north(){
		
		int y = -1; //y는 채워 넣을 자리
		int yy = -1; //yy는 움직이면서 데려올 숫자를 찾는 인덱스
		boolean[] check = new boolean[num]; //이미 합쳐진 값이면 true 처리
		
		for(int i=0; i<num; i++){
			
			y = -1;
			yy = -1;
			check = new boolean[num];
			
			while(yy<num){
				
				while(yy<num){
					yy++;
					if(yy<num && tempBoard[yy][i]!=0) break;
				}
				
				if(yy<num){
					if(y==-1){
						y=0;
						if(yy==y) continue;
						tempBoard[y][i] = tempBoard[yy][i];
						tempBoard[yy][i] = 0;
					}else if(tempBoard[yy][i]==tempBoard[y][i] && !check[y]){
						tempBoard[y][i] *= 2;
						tempBoard[yy][i] = 0;
						check[y] = true;
					}else if(tempBoard[yy][i]==tempBoard[y][i] && check[y]){
						y++;
						if(yy==y) continue;
						tempBoard[y][i] = tempBoard[yy][i];
						tempBoard[yy][i] = 0;
					}else if(tempBoard[yy][i]!=tempBoard[y][i]){
						y++;
						if(yy==y) continue;
						tempBoard[y][i] = tempBoard[yy][i];
						tempBoard[yy][i] = 0;
					}
				}
				
			}
			
		}
		
	}
	

}
