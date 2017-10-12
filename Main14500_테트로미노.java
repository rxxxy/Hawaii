package algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main14500_테트로미노 {
	static int num1;
	static int num2;
	static int[][] paper;
	static int[][][] tetromino1 = new int[][][]{  { {0,0}, {0,1}, {0,2}, {0,3} } , { {0,0}, {1,0}, {2,0}, {3,0} } };
	static int[][] tetromino2 = new int[][]{ {0,0}, {0,1}, {1,0}, {1,1} };
	static int[][][] tetromino3 = new int[][][]{  { {0,0}, {1,0}, {2,0}, {2,1} } , { {0,0}, {0,1}, {1,1}, {2,1} }, { {0,0} , {0,-1}, {0,-2}, {1,-2} }, { {0,0}, {1,0}, {1,-1}, {1,-2} }  };
	static int[][][] tetromino4 = new int[][][]{  { {0,0}, {1,0}, {1,1}, {2,1} } , { {0,0}, {0,1}, {-1,1}, {-1,2} } };
	static int[][][] tetromino5 = new int[][][]{  { {0,0}, {0,-1}, {0,1}, {1,0} }, { {0,0}, {1,0}, {1,-1}, {1,1} }, { {0,0}, {0,1}, {-1,1}, {1,1} }, { {0,0}, {-1,0}, {1,0}, {0,1} } };
	static int[][][] tetromino6 = new int[][][]{  { {0,0}, {0,1}, {-1,1}, {-2,1} }, { {0,0}, {-1,0}, {-2,0}, {-2,1} }, { {0,0}, {1,0}, {1,1}, {1,2} }, { {0,0}, {0,1}, {0,2}, {1,2} } };
	static int[][][] tetromino7 = new int[][][]{  { {0,0}, {1,0}, {1,-1}, {2,-1} }, { {0,0}, {0,1}, {1,1}, {1,2} } };
	
	static int max;
	
	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		num1 = Integer.parseInt(st.nextToken());
		num2 = Integer.parseInt(st.nextToken());
		paper = new int[num1][num2];
		
		//1. 종이 채워주기
		for(int i=0; i<num1; i++){
			st = new StringTokenizer(in.readLine());
			for(int j=0; j<num2; j++){
				paper[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		//2. 검사하기
		for(int i=0; i<num1; i++){
			for(int j=0; j<num2; j++){
				tetromino1(i,j);
				tetromino2(i,j);
				tetromino3(i,j);
				tetromino4(i,j);
				tetromino5(i,j);
				tetromino6(i,j);
				tetromino7(i,j);
			}
		}
		
		//3. 출력하기
		System.out.println(max);
	}
	
	
	public static void tetromino1(int x, int y){
		int xx = 0;
		int yy = 0;
		int sum = 0;
		if(y+3<num2){
			for(int i=0; i<4; i++){
				xx = x+tetromino1[0][i][0];
				yy = y+tetromino1[0][i][1];
				sum += paper[xx][yy];
			}
			if(sum>max) max = sum;
		}
		
		if(x+3<num1){
			sum = 0;
			for(int i=0; i<4; i++){
				xx = x+tetromino1[1][i][0];
				yy = y+tetromino1[1][i][1];
				sum += paper[xx][yy];
			}
			if(sum>max) max = sum;
		}
	}
	
	
	public static void tetromino2(int x, int y){
		int xx = 0;
		int yy = 0;
		int sum = 0;
		if(x+1<num1&&y+1<num2){
			for(int i=0; i<4; i++){
				xx = x+tetromino2[i][0];
				yy = y+tetromino2[i][1];
				sum += paper[xx][yy];
			}
			if(sum>max) max = sum;
		}
	}
	
	
	public static void tetromino3(int x, int y){
		int xx = 0;
		int yy = 0;
		int xxx = 0;
		int yyy = 0;
		int sum1 = 0;
		int sum2 = 0;
		if(y+1<num2&&x+2<num1){
			for(int i=0; i<4; i++){
				xx = x+tetromino3[0][i][0];
				yy = y+tetromino3[0][i][1];
				xxx = x+tetromino3[1][i][0];
				yyy = y+tetromino3[1][i][1];
				sum1 += paper[xx][yy];
				sum2 += paper[xxx][yyy];
			}
			if(sum1>max) max = sum1;
			if(sum2>max) max = sum2;
		}
		
		if(y-2>=0&&x+1<num1){
			sum1 = 0;
			sum2 = 0;
			for(int i=0; i<4; i++){
				xx = x+tetromino3[2][i][0];
				yy = y+tetromino3[2][i][1];
				xxx = x+tetromino3[3][i][0];
				yyy = y+tetromino3[3][i][1];
				sum1 += paper[xx][yy];
				sum2 += paper[xxx][yyy];
			}
			if(sum1>max) max = sum1;
			if(sum2>max) max = sum2;
		}
		
	}
	
	
	public static void tetromino4(int x, int y){
		int xx = 0;
		int yy = 0;
		int sum = 0;
		if(y+1<num2&&x+2<num1){
			for(int i=0; i<4; i++){
				xx = x+tetromino4[0][i][0];
				yy = y+tetromino4[0][i][1];
				sum += paper[xx][yy];
			}
			if(sum>max) max = sum;
		}
		
		if(y+2<num2&&x-1>=0){
			sum = 0;
			for(int i=0; i<4; i++){
				xx = x+tetromino4[1][i][0];
				yy = y+tetromino4[1][i][1];
				sum += paper[xx][yy];
			}
			if(sum>max) max = sum;
		}
	}
	
	
	public static void tetromino5(int x, int y){
		int xx = 0;
		int yy = 0;
		int xxx = 0;
		int yyy = 0;
		int sum1 = 0;
		int sum2 = 0;
		if(y-1>=0&&y+1<num2&&x+1<num1){
			for(int i=0; i<4; i++){
				xx = x+tetromino5[0][i][0];
				yy = y+tetromino5[0][i][1];
				xxx = x+tetromino5[1][i][0];
				yyy = y+tetromino5[1][i][1];
				sum1 += paper[xx][yy];
				sum2 += paper[xxx][yyy];
			}
			if(sum1>max) max = sum1;
			if(sum2>max) max = sum2;
		}
		
		
		if(y+1<num2&&x-1>=0&&x+1<num1){
			sum1=0;
			sum2=0;
			for(int i=0; i<4; i++){
				xx = x+tetromino5[2][i][0];
				yy = y+tetromino5[2][i][1];
				xxx = x+tetromino5[3][i][0];
				yyy = y+tetromino5[3][i][1];
				sum1 += paper[xx][yy];
				sum2 += paper[xxx][yyy];
			}
			if(sum1>max) max = sum1;
			if(sum2>max) max = sum2;
		}
	}
	
	
	public static void tetromino6(int x, int y){
		int xx = 0;
		int yy = 0;
		int xxx = 0;
		int yyy = 0;
		int sum1 = 0;
		int sum2 = 0;
		if(x-2>=0&&y+1<num2){
			for(int i=0; i<4; i++){
				xx = x+tetromino6[0][i][0];
				yy = y+tetromino6[0][i][1];
				xxx = x+tetromino6[1][i][0];
				yyy = y+tetromino6[1][i][1];
				sum1 += paper[xx][yy];
				sum2 += paper[xxx][yyy];
			}
			if(sum1>max) max = sum1;
			if(sum2>max) max = sum2;
		}
		
		if(y+2<num2&&x+1<num1){
			sum1 = 0;
			sum2 = 0;
			for(int i=0; i<4; i++){
				xx = x+tetromino6[2][i][0];
				yy = y+tetromino6[2][i][1];
				xxx = x+tetromino6[3][i][0];
				yyy = y+tetromino6[3][i][1];
				sum1 += paper[xx][yy];
				sum2 += paper[xxx][yyy];
			}
			if(sum1>max) max = sum1;
			if(sum2>max) max = sum2;
		}
		
	}
	
	
	public static void tetromino7(int x, int y){
		int xx = 0;
		int yy = 0;
		int sum = 0;
		if(x+2<num1&&y-1>=0){
			for(int i=0; i<4; i++){
				xx = x+tetromino7[0][i][0];
				yy = y+tetromino7[0][i][1];
				sum += paper[xx][yy];
			}
			if(sum>max) max = sum;
		}
		
		if(x+1<num1&&y+2<num2){
			sum = 0;
			for(int i=0; i<4; i++){
				xx = x+tetromino7[1][i][0];
				yy = y+tetromino7[1][i][1];
				sum += paper[xx][yy];
			}
			if(sum>max) max = sum;
		}
	}
	
}
