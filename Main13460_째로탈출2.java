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
	static int[][] board;
	static int[] success = new int[2];
	static int[][] moving = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	static Queue<int[]> que = new LinkedList<>();
	static boolean redFlag;
	static boolean blueFlag;
	static int result = -1;
	
	static int redXsmall;
	static int redXbig;
	static int redYsmall;
	static int redYbig;
	static int blueXsmall;
	static int blueXbig;
	static int blueYsmall;
	static int blueYbig;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		int[] redStart = new int[2];
		int[] blueStart = new int[2];
		
		//1. 보드판 배열 채우기
		char[] array = null;
		board = new int[num1][num2];
		for(int i=0; i<num1; i++){
			array = in.readLine().trim().toCharArray();
			for(int j=0; j<num2; j++){
				if(array[j]=='.'){
					board[i][j] = 0;
				}else if(array[j]=='#'){
					board[i][j] = 1;
				}else if(array[j]=='R'){
					board[i][j] = 0;
					redStart[0] = i;
					redStart[1] = j;
				}else if(array[j]=='B'){
					board[i][j] = 0;
					blueStart[0] = i;
					blueStart[1] = j;
				}else if(array[j]=='O'){
					board[i][j] = 0;
					success[0] = i;
					success[1] = j;
				}
			}
		}
		
		
		//2. 큐에 시작점 채워주기
		que.offer(new int[]{redStart[0], redStart[1], 0});
		que.offer(new int[]{blueStart[0], blueStart[1], 0});
		
		
		//3. BFS 돌리기
		zzero();
		
		
		//4. 카운트 출력하기
		System.out.println(result);
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
			count = red[2];
			if(count==10) return;
			
			for(int i=0; i<4; i++){
				redFlag = false;
				blueFlag = false;
				redX = red[0]+moving[i][0];
				redY = red[1]+moving[i][1];
				blueX = blue[0]+moving[i][0];
				blueY = blue[1]+moving[i][1];
				
				
				//RED 이동하기
				while(true){
					if(board[redX][redY]==1){
						break;
					}else if(redX==success[0]&&redY==success[1]){
						redFlag = true;
					}
					redX += moving[i][0];
					redY += moving[i][1];
				}
				
				redX = redX - moving[i][0];
				redY = redY - moving[i][1];
				
				
				//BLUE 이동하기
				while(true){
					if(board[blueX][blueY]==1){
						break;
					}else if(blueX==success[0]&&blueY==success[1]){
						blueFlag = true;
					}
					blueX += moving[i][0];
					blueY += moving[i][1];
				}
				
				blueX = blueX - moving[i][0];
				blueY = blueY - moving[i][1];
				
				//RED만 들어갔다면 return, 둘다 들어갔으면 continue, 둘다 안들어갔으면 que에 넣기
				if(blueFlag) {
					continue;
				}else if(redFlag) {
					result = count+1;
					return;
				}else{
					//이동 후 체크하기
					//두 개가 만났다면 그 전 자리와 비교해서 뭐가 앞에 있어야 하는지 잡기
					if(redX==blueX && redY==blueY){
						if(i==0){
							if(blue[1]>red[1]){
								redX -= moving[i][0];
								redY -= moving[i][1];
							}else{
								blueX -= moving[i][0];
								blueY -= moving[i][1];
							}
						}else if(i==1){
							if(blue[1]<red[1]){
								redX -= moving[i][0];
								redY -= moving[i][1];
							}else{
								blueX -= moving[i][0];
								blueY -= moving[i][1];
							}
						}else if(i==2){
							if(blue[0]>red[0]){
								redX -= moving[i][0];
								redY -= moving[i][1];
							}else{
								blueX -= moving[i][0];
								blueY -= moving[i][1];								
							}
						}else if(i==3){
							if(blue[0]<red[0]){
								redX -= moving[i][0];
								redY -= moving[i][1];
							}else{
								blueX -= moving[i][0];
								blueY -= moving[i][1];								
							}
						}
					}
					
					
					if(redX==red[0]&&redY==red[1]&&blueX==blue[0]&&blueY==blue[1]){
						continue;
					}

					que.offer(new int[]{redX, redY, count+1});
					que.offer(new int[]{blueX, blueY, count+1});
					
				}
			}
		}
	}

}
