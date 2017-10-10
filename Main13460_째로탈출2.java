package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main13460_째로탈출2 {
	static int num1;
	static int num2;
	static int[][] zzero;
	static int[][] moving = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
	static int[] goal = new int[2];
	static Deque<int[]> que = new LinkedList<>();
	static boolean resultFlag;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		num2 = Integer.parseInt(st.nextToken());
		num1 = Integer.parseInt(st.nextToken());
		
		zzero = new int[num1][num2];
		String line = "";
		for(int i=0; i<num1; i++){
			line = in.readLine().trim();
			for(int j=0; j<num2; j++){
				if(line.charAt(j)=='#'){
					zzero[i][j] = 1;
				}else if(line.charAt(j)=='.'){
					zzero[i][j] = 0;
				}else if(line.charAt(j)=='R'){
					zzero[i][j] = -1;
					que.add(new int[]{i,j,-1,0});
				}else if(line.charAt(j)=='B'){
					zzero[i][j] = -2;
					que.add(new int[]{i,j,-2,0});
				}else if(line.charAt(j)=='O'){
					zzero[i][j] = 99999;
					goal[0] = i;
					goal[1] = j;
				}
			}
		}
		
		
	}
	
	
	public static void escape(){
		
		int[] temp1 = null;
		int[] temp2 = null;
		int x = 0;
		int y = 0;
		int xx = 0;
		int yy = 0;
		int count = 0;
		int whom = 0;
		boolean flag = false;
		int flagDir = 0;
		
		while(!que.isEmpty()){
			temp1 = que.poll();
			temp2 = que.poll();
			
		outer:	for(int i=0; i<4; i++){
				x = temp1[0]+moving[i][0];
				y = temp1[1]+moving[i][1];
				whom = temp1[2];
				count = temp1[3];
				
				//빨간 구슬 작업 먼저하기
				if(x>=0&&x<num1&&y>=0&&y<num2&&(zzero[x][y]!=1||zzero[x][y]>count+1)){
					while(x>=0&&x<num1&&y>=0&&y<num2&&(zzero[x][y]==0||zzero[x][y]<=count+1)){
						if(zzero[x][y]>=2 && zzero[x][y]<=count+1){
							que.offer(new int[]{temp1[0], temp1[1], -1, count});
							que.offer(new int[]{temp2[0], temp2[1], -2, count});
							continue outer;
						}
						x += moving[i][0];
						y += moving[i][1];
					}
					
					if(x>=0&&x<num1&&y>=0&&y<num2){
						if(zzero[x][y]==1){
							zzero[x-moving[i][0]][y-moving[i][1]] = count+1;
							que.offer(new int[]{x-moving[i][0],y-moving[i][1], whom, count+1});
						}else if(whom==-1 && zzero[x][y]==99999){
							zzero[goal[0]][goal[1]] = count+1;
							flag = true;
						//앞에 파란 구슬이 있는 경우에
						}else if(zzero[x][y]==-2){
							//빨간 구슬 다시 넣어주기
							//파란 구슬 작업 먼저 해주고
							//그리고 빨간 구슬 다시 돌리기
							//그리고 continue;
							xx = temp2[0]+moving[i][0];
							yy = temp2[1]+moving[i][1];
								
							if(zzero[xx][yy]!=1){
								while(xx>=0&&xx<num1&&yy>=0&&yy<num2&&zzero[xx][yy]==0){
									xx += moving[i][0];
									yy += moving[i][1];
								}
								
								if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&zzero[xx][yy]==1){
									zzero[xx-moving[i][0]*2][yy-moving[i][1]*2] = count+1;
									que.offer(new int[]{xx-moving[i][0]*2, yy-moving[i][1]*2, -1, count+1});
									que.offer(new int[]{xx-moving[i][0], yy-moving[i][1], -2, count+1});
								}else if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&zzero[xx][yy]==99999){
									System.out.println(-1);
									return;
								}
							}else{
								que.offer(new int[]{xx-moving[i][0]*2, yy-moving[i][1]*2, -1, count+1});
								que.offer(new int[]{xx-moving[i][0], yy-moving[i][1], -2, count+1});
							}
							continue;
						}
					}
						
				}else{
					//갈 수 있는 방향이 아닌 방향은 과감하게 continue
					que.offer(new int[]{temp1[0], temp1[1], -1, count});
					que.offer(new int[]{temp2[0], temp2[1], -2, count});
					continue;
				}
				
				xx = temp2[0]+moving[i][0];
				yy = temp2[1]+moving[i][1];
				whom = temp2[2];
				count = temp2[3];
				
				//파란 구슬 작업하기
				if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&zzero[xx][yy]!=1){
					while(x>=0&&x<num1&&y>=0&&y<num2&&zzero[x][y]==0){
						x += moving[i][0];
						y += moving[i][1];
					}
					
					if(x>=0&&x<num1&&y>=0&&y<num2){
						if(zzero[x][y]==1){
							zzero[x-moving[i][0]][y-moving[i][1]] = count+1;
							que.offer(new int[]{x-moving[i][0],y-moving[i][1], whom, count+1});
						}else if(whom==-1 && zzero[x][y]==99999){
							zzero[goal[0]][goal[1]] = count+1;
							flag = true;
						//앞에 파란 구슬이 있는 경우에
						}else if(zzero[x][y]==-2){
							//빨간 구슬 다시 넣어주기
							//파란 구슬 작업 먼저 해주고
							//그리고 빨간 구슬 다시 돌리기
							//그리고 continue;
							xx = temp2[0]+moving[i][0];
							yy = temp2[1]+moving[i][1];
								
							if(zzero[xx][yy]!=1){
								while(xx>=0&&xx<num1&&yy>=0&&yy<num2&&zzero[xx][yy]==0){
									xx += moving[i][0];
									yy += moving[i][1];
								}
								
								if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&zzero[xx][yy]==1){
									zzero[xx-moving[i][0]*2][yy-moving[i][1]*2] = count+1;
									que.offer(new int[]{xx-moving[i][0]*2, yy-moving[i][1]*2, -1, count+1});
									que.offer(new int[]{xx-moving[i][0], yy-moving[i][1], -2, count+1});
								}else if(xx>=0&&xx<num1&&yy>=0&&yy<num2&&zzero[xx][yy]==99999){
									System.out.println(-1);
									return;
								}
							}else{
								que.offer(new int[]{xx-moving[i][0]*2, yy-moving[i][1]*2, -1, count+1});
								que.offer(new int[]{xx-moving[i][0], yy-moving[i][1], -2, count+1});
							}
							continue;
						}
					}
						
				}
				
				
				
				
				
				
				
				
				
				
				
				
			}
			
		}// Queue END
		
	}

	
	
	
}
