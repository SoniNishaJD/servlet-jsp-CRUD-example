import java.util.Scanner;

public class PositiveNegativeNumber {
	public static void main(String[] args) {

		int num;
		Scanner sc = new Scanner(System.in);

		System.out.print("Enter the number: ");
		num = sc.nextInt();

		if (num > 0) {
			System.out.println("Given number is positive");
		} else if (num < 0) {
			System.out.println("Given number is negative");
		} else {
			System.out.println("Given number is zero");
		}
	}

}
