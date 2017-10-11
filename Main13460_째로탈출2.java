package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main13460_째로탈출2 {
	static int num1;
	static int num2;
	static char[][] board;
	static int[] redStart = new int[2];
	static int[] blueStart = new int[2];
	static int[] success = new int[2];
	
	static Queue<int[]> que = new LinkedList<>();
	static int[][] moving = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
	static int resultCount;
	static boolean resultRedFlag;
	static boolean resultBlueFlag;
	
	static boolean redGoFlag = true;
	static boolean blueGoFlag = true;
	static boolean overTen;

	public static void main(String[] args) throws IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		
		
		//1. 보드판 채워주기
		char[] temp = null;
		board = new char[num1][num2];
		for(int i=0; i<num1; i++){
			temp = in.readLine().toCharArray();
			for(int j=0; j<num2; j++){
				board[i][j] = temp[j];
				if(board[i][j]=='R'){ 
					redStart[0] = i; 
					redStart[1] = j; 
					board[i][j] = '.';
				}else if(board[i][j]=='B'){
					blueStart[0] = i;
					blueStart[1] = j; 
					board[i][j] = '.';
				}else if(board[i][j]=='O'){ 
					success[0] = i; 
					success[1] = j; 
				}
			}
		}
		
		
		//2. 시작점 큐에 담아주기
		que.offer(new int[]{redStart[0], redStart[1], 'R', 0});
		que.offer(new int[]{blueStart[0], blueStart[1], 'B', 0});
		
		
		//3. BFS돌리기
		zzero();
		
		//4. count출력하기
		if(resultBlueFlag){
			System.out.println(-1);
		}else if(resultRedFlag){
			System.out.println(resultCount);
		}
		
		if(overTen){
			System.out.println(-1);
		}
	}
	
	
	
	public static void zzero(){
		
		int[] red = null;
		int[] blue = null;
		int redX = 0;
		int redY = 0;
		int blueX = 0;
		int blueY = 0;
		int count = 0;
		
		
		while(!que.isEmpty()){
			
			red = que.poll();
			blue = que.poll();
			
			for(int i=0; i<4; i++){
				redX = red[0];
				redY = red[1];
				count = red[3];
				blueX = blue[0];
				blueY = blue[1];
				
				while(true){
					
					if(!redGoFlag&&!blueGoFlag) break;
					
					if(redGoFlag){
						redX = redX + moving[i][0];
						redY = redY + moving[i][1];
					}
					if(blueGoFlag){
						blueX = blueX + moving[i][0];
						blueY = blueY + moving[i][1];
					}
					
					if(board[redX][redY]=='#'){
						redGoFlag = false;
						redX = redX - moving[i][0];
						redY = redY - moving[i][1];
					}else if(redX==blueX&&redY==blueY){
						redX = redX - moving[i][0];
						redY = redY - moving[i][1];
					}else if(redX==success[0]&&redY==success[1]){
						resultRedFlag = true;
						redX = redX + moving[i][0];
						redY = redY + moving[i][1];
					}else if(redX+moving[i][0]==blueX&&
							redY+moving[i][1]==blueY&&
							board[blueX][blueY]=='#'){
						redGoFlag = false;
						redX = redX - moving[i][0];
						redY = redY - moving[i][1];
					}
					
					if(board[blueX][blueY]=='#'){
						blueGoFlag = false;
						blueX = blueX - moving[i][0];
						blueY = blueY - moving[i][1];
					}else if(blueX==redX&&blueY==redY){
						blueX = blueX - moving[i][0];
						blueY = blueY - moving[i][1];
					}else if(blueX==success[0]&&blueY==success[1]){
						resultBlueFlag = true;
						blueX = blueX + moving[i][0];
						blueY = blueY + moving[i][1];
					}else if(blueX+moving[i][0]==redX&&
							blueY+moving[i][1]==redY&&
							board[redX][redY]=='#'){
						blueGoFlag = false;
						blueX = blueX - moving[i][0];
						blueY = blueY - moving[i][1];
					}
					
					
					if(resultRedFlag||resultBlueFlag){
						resultCount = count+1;
						return;
					}else if(count+1==10){
						overTen = true;
						return;
					}
				}
				
				redGoFlag = true;
				blueGoFlag = true;
				que.offer(new int[]{redX, redY, 'R',  count+1});
				que.offer(new int[]{blueX, blueY, 'R',  count+1});
				
			}
		}
	}
}
