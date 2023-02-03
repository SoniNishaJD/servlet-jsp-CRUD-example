import java.util.Scanner;

public class SwipTwoNumberUsingBitwise {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		System.out.println("Enter first number: ");
		int a = sc.nextInt();

		System.out.println("Enter second number: ");
		int b = sc.nextInt();

		System.out.println("Number before swapping: " + a + " " + b);
		// a = a^b; //bitwise operator
		// b = a^b;
		// a = a^b;
		a = a * b; // multiplication and divison operation
		b = a / b;
		a = a / b;
		System.out.println("Number after swapping: " + a + " " + b);
	}
}
