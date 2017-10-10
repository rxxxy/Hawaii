package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main13460_°��Ż��2 {
	static int num1;
	static int num2;
	static int[][] zzero;
	static int[][] moving = new int[][]{{0,1}, {0,-1}, {1,0}, {-1,0}};
	
	static int[] goal = new int[2];
	static Deque<int[]> que = new LinkedList<>();
	static boolean resultFlag;
	static int resultCount;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		num2 = Integer.parseInt(st.nextToken());
		num1 = Integer.parseInt(st.nextToken());
		zzero = new int[num1][num2];
		int[] redStart = new int[2];
		int[] blueStart = new int[2];
		
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
					redStart[0] = i;
					redStart[1] = j;
				}else if(line.charAt(j)=='B'){
					zzero[i][j] = -2;
					blueStart[0] = i;
					blueStart[1] = j;
				}else if(line.charAt(j)=='O'){
					zzero[i][j] = 99999;
					goal[0] = i;
					goal[1] = j;
				}
			}
		}
		
		zzero[redStart[0]][redStart[1]] = 2;
		zzero[blueStart[0]][blueStart[1]] = 0;
		que.offer(new int[]{redStart[0], redStart[1], -1, 2});
		que.offer(new int[]{blueStart[0], blueStart[1], -2, 2});
		escape();
		
		if(resultFlag) System.out.println(resultCount-2);
		
		
	}
	
	
	public static void escape(){
		
		int[] temp1 = null;
		int[] temp2 = null;
		
		int redX = 0;
		int redY = 0;
		
		int blueX = 0;
		int blueY = 0;
		
		int count = 0;
		int whom = 0;
		
		int tempX = 0;
		int tempY = 0;
		
		
		while(!que.isEmpty()){
			tempX = -1;
			tempY = -1;
			temp1 = que.poll();
			temp2 = que.poll();
			
			
			//�� ���� ��� ������
outer:			for(int i=0; i<4; i++){
				if(resultFlag){ return; }
				
				//���� ���� ���� ������
				whom = temp1[2];
				count = temp1[3];
				redX = temp1[0]+moving[i][0];
				redY = temp1[1]+moving[i][1];
				
				//�� �� �ִ� ������������ Ȯ���ϱ�
				if(redX>=0&&redX<num1&&redY>=0&&redY<num2&&zzero[redX][redY]!=1){
					//�� �� �ִ� ������ ���� Ȯ�� ������ �� ĭ�� �ִ� ���� �� ����
					//������ �濡 ��簡 ������ ��縦 ���� ������ ����
					//������ ���尡 �׻� ���� ���� �ɷ� ����
					
					
					while(redX>=0&&redX<num1&&redY>=0&&redY<num2){
						
						if(zzero[redX][redY]==99999||zzero[redX][redY]==1||(redX==temp2[0]&&redY==temp2[1])){
							break;
						}else if(zzero[redX][redY]!=0 && zzero[redX][redY]<=count+1){
							//�̹� ���� ���ε� ������ �� ���� �� ���̸� �� �ʿ䰡 ����
							continue;
						}else if(zzero[redX][redY]==0){
							redX = redX+moving[i][0];
							redY = redY+moving[i][1];
							
						}else if(zzero[redX][redY]>count+1){
							redX = redX+moving[i][0];
							redY = redY+moving[i][1];
						}
					}
					
					//���ͼ� ����ģ ���� �������� Ȯ���ϱ�
					//���� ���, �ⱸ�� ���, �Ķ��� ������ ���
					if(zzero[redX][redY]==1){
						tempX = redX-moving[i][0];
						tempY = redY-moving[i][1];
						zzero[tempX][tempY] = count+1;
						que.offer(new int[]{tempX, tempY, -1, count+1});
					}else if(zzero[redX][redY]==99999){						
						resultFlag = true;
						resultCount = count+1;
					}else if(redX==temp2[0]&&redY==temp2[1]){
					//�Ķ��� ������ ����ģ ���, �Ķ��� ������ ���� ������ ���� ��
						whom = temp2[2];
						blueX = temp2[0]+moving[i][0];
						blueY = temp2[1]+moving[i][1];
						
						if(blueX>=0&&blueX<num1&&blueY>=0&&blueY<num2&&zzero[blueX][blueY]!=1){
							
							while(blueX>=0&&blueX<num1&&blueY>=0&&blueY<num2){
								if(zzero[blueX-moving[i][0]*2][blueY-moving[i][1]*2]<=count+1){
									continue outer;
								}else if(zzero[blueX][blueY]!=1){
									blueX = blueX+moving[i][0];
									blueY = blueY+moving[i][1];
									//�ٸ� ���𰡸� ����ħ
								}else if(zzero[blueX][blueY]==1){
									zzero[blueX-moving[i][0]*2][blueY-moving[i][1]*2] = count+1;
									que.offer(new int[]{blueX-moving[i][0]*2, blueY-moving[i][1]*2, -1, count+1});
									que.offer(new int[]{blueX-moving[i][0], blueY-moving[i][1], -2, count+1});
								}
							}
							
						}
						continue;
						
					}
				
				//�� �� ���� �����̸� continue�ϱ� (blue�� �� �� ������)
				}else{
					continue;
				}
				
				
				//�Ķ� ���� ������
				whom = temp2[2];
				blueX = temp2[0]+moving[i][0];
				blueY = temp2[1]+moving[i][1];
				
				if(blueX>=0&&blueX<num1&&blueY>=0&&blueY<num2&&zzero[blueX][blueY]!=1){
						
					while(blueX>=0&&blueX<num1&&blueY>=0&&blueY<num2){
						if(zzero[blueX][blueY]==99999){
							System.out.println(-1);
							resultFlag = false;
							return;
						}else if(blueX==tempX&&blueY==tempY){
							que.offer(new int[]{blueX-moving[i][0], blueY-moving[i][1], -2, count+1});
							break;
						}else if(zzero[blueX][blueY]!=1){
							blueX = blueX+moving[i][0];
							blueY = blueY+moving[i][1];
							//�ٸ� ���𰡸� ����ħ
						}else if(zzero[blueX][blueY]==1){
							que.offer(new int[]{blueX-moving[i][0], blueY-moving[i][1], -2, count+1});
							break;
						}
					}
				}
			}
			
			
			
		}
		
			
		
		for(int i=0; i<num1; i++){
			for(int j=0; j<num2; j++){
				System.out.print(zzero[i][j]+" ");
			}
			System.out.println();
		}
		System.out.println();
		System.out.println();
	}
	
	
}