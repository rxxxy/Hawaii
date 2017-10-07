package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main1006_�κ� {
	static int num1;
	static int num2;
	
	static int[][] map;
	static int[] start = new int[4];
	static int[] end = new int[3];
	
	static Queue<int[]> que = new LinkedList<>();
	static int[][][] moving = { {{0,1}, {0,2}, {0,3}},
								{{0,-1}, {0,-2}, {0,-3}},	
								{{1,0}, {2,0}, {3,0}},
								{{-1,0}, {-2,0}, {-3,0}}
								};
	

	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		
		//1. �κ� �� �����
		map = new int[num1][num2];
		for(int i=0; i<num1; i++){
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<num2; j++){
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//2. �κ� ������, ������ ó���ϱ�
		st = new StringTokenizer(in.readLine());
		start[0] = Integer.parseInt(st.nextToken())-1;
		start[1] = Integer.parseInt(st.nextToken())-1;
		start[2] = Integer.parseInt(st.nextToken());
		start[3] = 1;
		
		st = new StringTokenizer(in.readLine());
		end[0] = Integer.parseInt(st.nextToken())-1;
		end[1] = Integer.parseInt(st.nextToken())-1;
		end[2] = Integer.parseInt(st.nextToken());
		
		if(start[0]==end[0]&&start[1]==end[1]){
			if(((start[2]==1||start[2]==2) && (end[2]==3||end[2]==4))||
					((start[2]==3||start[2]==4) && (end[2]==1||end[2]==2))){
				System.out.println(1);
				return;
			}else if((start[2]==1&&end[2]==2)||(start[2]==2&&end[2]==1)||
					(start[2]==3&&end[2]==4)||(start[2]==4&&end[2]==3)){
				System.out.println(2);
				return;
			}else{
				System.out.println(0);
				return;
			}
		}
		
		
		//3. �κ� ���������� BFS ����
		map[start[0]][start[1]] = 1;
		que.offer(start);
		goRobot();
		
		//4. ��� Ƚ�� ����ϱ�
		System.out.println(map[end[0]][end[1]]-1);
		
	}
	
	
	public static void goRobot(){
		int[] temp = null;
		int[][] move = null;
		int xx = 0;
		int yy = 0;
		int count = 0;
		int direction = 0;
		
		while(!que.isEmpty()){
			
			temp = que.poll();
			
			//4�������� ��� �� ����
			//4���⿡���� 1,2,3���� ��� �� ����
			
			for(int i=1; i<=4; i++){
				move = moving[i-1];
				for(int j=0; j<3; j++){
					xx = temp[0]+move[j][0];
					yy = temp[1]+move[j][1];
					direction = temp[2];
					count = temp[3];
					
					if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&map[xx][yy]!=1){
						count = count+1;
						if(i!=direction){
							if(((i==1||i==2) && (direction==3||direction==4))||
									((i==3||i==4) && (direction==1||direction==2))){
								count += 1;
							}else if((i==1&&direction==2)||(i==2&&direction==1)||
									(i==3&&direction==4)||(i==4&&direction==3)){
								count += 2;
							}
						}
						
						if(xx==end[0]&&yy==end[1]){
							if(((i==1||i==2) && (end[2]==3||end[2]==4))||
									((i==3||i==4) && (end[2]==1||end[2]==2))){
								count += 1;
							}else if((i==1&&end[2]==2)||(i==2&&end[2]==1)||
									(i==3&&end[2]==4)||(i==4&&end[2]==3)){
								count += 2;
							}
						}
						
						if(j>=1){
							if(map[xx-move[0][0]][yy-move[0][1]] == 1){
								continue;
							}
							if(j==2){
								if(map[xx-move[1][0]][yy-move[1][1]]==1){
									continue;
								}
							}
						}
						
						if(map[xx][yy]==0||map[xx][yy]>count){
							map[xx][yy] = count;
							que.offer(new int[]{xx,yy,i,count});
						}
					}
				}
			}
		}
	}

}
