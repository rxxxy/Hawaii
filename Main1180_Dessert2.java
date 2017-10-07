package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1180_Dessert2 {
	static int nNum; //숫자 개수
	static int gNum; //기호 개수
	
	static char[] gihos = new char[]{'+', '-', '.'}; //쓸 수 있는 기호
	
	static int[] numArray; //숫자 담을 배열
	static char[] gihoArray; //기호 담을 배열
	
	static StringBuilder sb; //.으로 이어지는 숫자를 담을 스트링빌더
	static StringBuilder psb; //프린트할 스트링빌더
	static char pre = '0'; //바로 이전 연산자 담기
	static char prepre = '0';
	static int sum; //총 합 계산할 정수
	static int count; //옳은 답 개수
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		nNum = Integer.parseInt(in.readLine().trim());
		gNum = nNum-1;
		numArray = new int[nNum];
		gihoArray = new char[gNum];
		
		//숫자 배열 채워주기
		for(int i=0; i<nNum; i++){
			numArray[i] = i+1;
		}
		
		//기호 배열 채우는 DFS로 출발
		dessert(0);
		
		//총 개수 출력하기
		System.out.println(count);
		
	}
	
	public static void dessert(int index){
		
		
		if(index==gNum){
			//식 계산해서 봐서 답이 맞는지 해보기
			//sum과 sb와 이전 연산자 초기화
			sum = 0;
			sb = null;
			pre = '0';
			prepre = '0';
			int numPos = 1;
			int gihoPos = 0;
			
			//기호를 다 쓸 때까지 while문 돌리기
			while(gihoPos<gNum){
				while(gihoArray[gihoPos]=='.'){
					if(pre!='.'){
						sb = new StringBuilder();
						sb.append(numPos);
						prepre = pre;
						pre='.';
						gihoPos++;
						numPos++;
					}else{
						sb.append(numPos);
						pre='.';
						gihoPos++;
						numPos++;
					}
				}
				
				switch(gihoArray[gihoPos]){
					case '+' :
						calculate(gihoPos, numPos);
						pre = '+';
						gihoPos++;
						numPos++;
						break;
						
					case '-' :
						calculate(gihoPos, numPos);
						pre = '-';
						gihoPos++;
						numPos++;
						break;
				}
				
			}
			
			//마지막으로 남은 숫자 처리하기
			if(sb!=null){
				switch(prepre){
				case '0' :
					sum += Integer.parseInt(sb.toString());
					break;
					
				case '+' :
					sum += Integer.parseInt(sb.toString());
					break;
					
				case '-' :
					sum -= Integer.parseInt(sb.toString());
					break;
				}
			}else{
				switch(pre){
				case '0' :
					sum += numPos;
					break;
					
				case '+' :
					sum += numPos;
					break;
					
				case '-' :
					sum -= numPos;
					break;
				}
			}
			
			
			if(sum==0){
				psb = new StringBuilder();
				for(int i=0; i<gNum; i++){
					psb.append(i+1);
					psb.append(" ");
					psb.append(gihoArray[i]);
					psb.append(" ");
				}
				System.out.println(psb.toString());
				count++;
			}
			return;
		}
		
		
		for(int i=0; i<3; i++){
			gihoArray[index] = gihos[i];
			dessert(index+1);
		}
		
	}
	
	
	static void calculate(int gihoIndex, int numIndex){
		if(pre=='0'){
			sum += numIndex;
		}else if(pre=='.'&&prepre=='0'){
			sum += Integer.parseInt(sb.toString());
			sb = null;
		}else if(pre=='.'&&prepre!='0'){
			if(prepre=='+'){
				sum += Integer.parseInt(sb.toString());
				sb = null;
			}else if(prepre=='-'){
				sum -= Integer.parseInt(sb.toString());
				sb = null;
			}
		}else if(pre=='+'){
			sum += numIndex;
		}else if(pre=='-'){
			sum -= numIndex;
		}
	}
	

}
