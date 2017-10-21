package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class Main3190_뱀 {
	
	static int num;
	static int[][] board;
	static int appleNum;
	static int dirNum;
	static List<int[]> list = new ArrayList<>();
	
	static int[] head = new int[3];
	static Deque<int[]> que = new LinkedList<>();
	static int[][] moving = {{0,1}, {1,0}, {0,-1}, {-1,0}};
	static boolean flag;
	static int count;
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		num = Integer.parseInt(in.readLine().trim());
		board = new int[num][num];
		
		
		//1. 사과 넣어주기 작업
		appleNum = Integer.parseInt(in.readLine().trim());
		StringTokenizer st = null;
		int x = 0;
		int y = 0;
		for(int i=0; i<appleNum; i++){
			st = new StringTokenizer(in.readLine());
			x = Integer.parseInt(st.nextToken())-1;
			y = Integer.parseInt(st.nextToken())-1;
			board[x][y] = -1;			
		}
		
		
		//2. 방향 넣어주기 작업
		dirNum = Integer.parseInt(in.readLine().trim());
		String xx = "";
		for(int i=0; i<dirNum; i++){
			st = new StringTokenizer(in.readLine());
			x = Integer.parseInt(st.nextToken());
			xx = st.nextToken();
			if(xx.equals("D")){
				list.add(new int[]{x, 0});
			}else{
				list.add(new int[]{x, 2});
			}
		}
		
		list.sort(new Comparator<int[]>() {
			public int compare(int[] o1, int[] o2) {
				return o1[0]-o2[0];
			}
		});
		
		
		
		//3. DFS돌리기
		head[0] = 0;
		head[1] = 0;
		head[2] = 0;
		que.offer(new int[]{0,0,0});
		board[0][0] = 1;
		goSnake(1);
		
		
		//4. 카운트출력하기
		System.out.println(count);
		
	}
	
	
	
	public static void goSnake(int index){
		
		if(flag) return;
		
		int[] temp = que.peekFirst();
		head[0] = temp[0];
		head[1] = temp[1];
		head[2] = temp[2];
		
		int xx = head[0]+moving[head[2]][0];
		int yy = head[1]+moving[head[2]][1];
		
		//1. 움직인 자리가 범위를 벗어나지 않은 경우
		if(xx>=0&&xx<num&&yy>=0&&yy<num){
			head[0] = xx;
			head[1] = yy;
			
			//1-1) 사과를 만난 경우
			if(board[xx][yy]==-1){
				board[xx][yy] = 1;
				que.offerFirst(new int[]{head[0],head[1],head[2]});	
			//1-2) 사과를 안 만난 경우
			}else{
				temp = que.pollLast();
				xx = temp[0];
				yy = temp[1];
				
				//1-2-1) 뱀꼬리를 만나는 경우
				if(board[head[0]][head[1]]==1){
					count = index;
					flag = true;
					return;
				//1-2-2) 뱀꼬리도 안만나는 경우
				}else{
					board[xx][yy] = 0;
					board[head[0]][head[1]]=1;
					que.offerFirst(new int[]{head[0], head[1], head[2]});
				}
			}
			
		//2. 움직인 자리가 범위를 벗어 난 경우
		}else{
			flag = true;
			count = index;
			return;
		}
		
		int turn = 0;
		temp = null;
		int newX = 0;
		int newY = 0;
		int newD = 0;
		
		if(list.size()!=0){
			if(index==list.get(0)[0]){
				turn = list.get(0)[1];
				if(turn==0){
					temp = que.pollFirst();
					newX = temp[0];
					newY = temp[1];
					newD = temp[2]+1;
					if(newD==4){
						newD = 0;
					}
				}else if (turn==2){
					temp = que.pollFirst();
					newX = temp[0];
					newY = temp[1];
					newD = temp[2]-1;
					if(newD==-1){
						newD = 3;
					}
				}
				que.offerFirst(new int[]{newX, newY, newD});
				list.remove(0);
			}
		}
		
		mapPrint();
		goSnake(index+1);
		
	}
	
	public static void mapPrint(){
		
		for(int i=0; i<num; i++){
			for(int j=0; j<num; j++){
				System.out.print(board[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
	}
}





