/* 
Program: EvenOdd.java
Author: Keshav Dial
Program Description:The prompts user for 10 numbers that it will in turn describe as either Even or Odd. 
*/

import java.util.Scanner; //used for taking user input
public class EvenOdd {
	public static void main(String[] arguments){
		Scanner user_input = new Scanner(System.in); //creates user_input scanner
		System.out.println("Choose 10 odd or even numbers");
		for(int i=1; i<=10; i++){ //loops 10 times
			System.out.println("Choose #"+ i + ": ");
			int evenodd = Integer.parseInt(user_input.next());
			if ((evenodd%2)>0){
				System.out.println("Odd");
			}
			else{
				System.out.println("Even");
			}
		}
		if(user_input != null) {
		    user_input.close();
		} //closes resource leak
	}
}
