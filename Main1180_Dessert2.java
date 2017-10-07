package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1180_Dessert2 {
	static int nNum; //���� ����
	static int gNum; //��ȣ ����
	
	static char[] gihos = new char[]{'+', '-', '.'}; //�� �� �ִ� ��ȣ
	
	static int[] numArray; //���� ���� �迭
	static char[] gihoArray; //��ȣ ���� �迭
	
	static StringBuilder sb; //.���� �̾����� ���ڸ� ���� ��Ʈ������
	static StringBuilder psb; //����Ʈ�� ��Ʈ������
	static char pre = '0'; //�ٷ� ���� ������ ���
	static char prepre = '0';
	static int sum; //�� �� ����� ����
	static int count; //���� �� ����
	
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		nNum = Integer.parseInt(in.readLine().trim());
		gNum = nNum-1;
		numArray = new int[nNum];
		gihoArray = new char[gNum];
		
		//���� �迭 ä���ֱ�
		for(int i=0; i<nNum; i++){
			numArray[i] = i+1;
		}
		
		//��ȣ �迭 ä��� DFS�� ���
		dessert(0);
		
		//�� ���� ����ϱ�
		System.out.println(count);
		
	}
	
	public static void dessert(int index){
		
		
		if(index==gNum){
			//�� ����ؼ� ���� ���� �´��� �غ���
			//sum�� sb�� ���� ������ �ʱ�ȭ
			sum = 0;
			sb = null;
			pre = '0';
			prepre = '0';
			int numPos = 1;
			int gihoPos = 0;
			
			//��ȣ�� �� �� ������ while�� ������
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
			
			//���������� ���� ���� ó���ϱ�
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
