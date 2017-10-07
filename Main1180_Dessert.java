package jungol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main1180_Dessert {
	static int nNum; //���� ����
	static int gNum; //��ȣ ����
	
	static char[] gihos = new char[]{'+', '-', '.'}; //�� �� �ִ� ��ȣ
	static char[] gihoArray; //��ȣ ���� �迭
	
	static StringBuilder sb = new StringBuilder(); //.���� �̾����� ���ڸ� ���� ��Ʈ������
	static StringBuilder psb; //����Ʈ�� ��Ʈ������
	
	static char pre = '0'; //�ٷ� ���� ������ ���
	static char prepre = '0';
	static double sum; //�� �� ����� ����
	static int count; //���� �� ����
	
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		nNum = Integer.parseInt(in.readLine().trim());
		gNum = nNum-1;
		gihoArray = new char[gNum];
		sb = new StringBuilder();
		for(int i=0; i<nNum/2; i++){
			sb.append(i+1);
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
			sb.delete(0, sb.length());
			pre = '0';
			prepre = '0';
			int numPos = 1;
			int gihoPos = 0;
			
			//��ȣ�� �� �� ������ while�� ������
			while(gihoPos<gNum){
				while(gihoPos<gNum&&gihoArray[gihoPos]=='.'){
					if(pre=='0'){
						sb.append(numPos);
						prepre = '0';
						pre = '.';
						gihoPos++;
						numPos++;
					}else if(pre!='.'){
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
				
				if(gihoPos==gNum) break;
				
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
			if(!sb.toString().equals("")){
				switch(prepre){
				case '0' :
					return;
					
				case '+' :
					sb.append(nNum);
					sum += Double.parseDouble(sb.toString());
					break;
					
				case '-' :
					sb.append(nNum);
					sum -= Double.parseDouble(sb.toString());
					break;
				}
			}else{
				switch(pre){
				case '0' :
					return;
					
				case '+' :
					sum += numPos;
					break;
					
				case '-' :
					sum -= numPos;
					break;
				}
			}
			
			
			if(sum==0){
				if(count<20){
					psb = new StringBuilder();
					for(int i=0; i<gNum; i++){
						psb.append(i+1);
						psb.append(" ");
						psb.append(gihoArray[i]);
						psb.append(" ");
					}
					psb.append(nNum);
					System.out.println(psb);
				}
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
			sb.append(numIndex);
			sum += Double.parseDouble(sb.toString());
			sb.delete(0, sb.length());
		}else if(pre=='.'&&prepre!='0'){
			if(prepre=='+'){
				sb.append(numIndex);
				sum += Double.parseDouble(sb.toString());
				sb.delete(0, sb.length());
			}else if(prepre=='-'){
				sb.append(numIndex);
				sum -= Double.parseDouble(sb.toString());
				sb.delete(0, sb.length());
			}
		}else if(pre=='+'){
			sum += numIndex;
		}else if(pre=='-'){
			sum -= numIndex;
		}
	}

}
