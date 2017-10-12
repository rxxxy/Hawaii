package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main14503_로봇청소기 {
	static int num1;
	static int num2;
	static int[][] square;
	static int[][] moving = new int[][]{{-1,0}, {0,1}, {1,0}, {0,-1}};
	
	static Queue<int[]> que = new LinkedList<>();
	static int count = 1;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		square = new int[num1][num2];
		
		//1. 출발점 설정하기
		st = new StringTokenizer(in.readLine());
		int[] start = new int[3];
		start[0] = Integer.parseInt(st.nextToken());
		start[1] = Integer.parseInt(st.nextToken());
		start[2] = Integer.parseInt(st.nextToken());
		
		//2. 배열 담아주기
		for(int i=0; i<num1; i++){
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<num2; j++){
				square[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//3. 출발점 que에 담고 bfs 출발하기
		square[start[0]][start[1]] = 2;
		que.offer(new int[]{start[0], start[1], start[2]});
		clean();
		
		//4. 카운트 출력하기
		System.out.println(count);
	}
	
	
	public static void clean(){
		int[] temp = null;
		int xx = 0;
		int yy = 0;
		int dir = 0;
		
outer:		while(!que.isEmpty()){
			temp = que.poll();
			dir = temp[2];
			for(int i=0; i<4; i++){
				dir--;
				if(dir==-1){ dir = 3; }
				xx = temp[0]+moving[dir][0];
				yy = temp[1]+moving[dir][1];
				
				if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&square[xx][yy]==0){
					count++;
					square[xx][yy] = 2;
					que.offer(new int[]{xx, yy, dir});
					continue outer;
				}
			}
			xx = temp[0]-moving[dir][0];
			yy = temp[1]-moving[dir][1];
			if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&square[xx][yy]!=1){
				que.offer(new int[]{xx, yy, dir});
			}
		}
	}

}
