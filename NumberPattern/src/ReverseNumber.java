import java.util.Scanner;

public class ReverseNumber {

	public static void main(String[] args) {

		System.out.println("Enter any number for reversing: ");
		Scanner sc = new Scanner(System.in);
		int num = sc.nextInt();
		reverseNumber(num);

	}

	public static void reverseNumber(int n) {
		int x = 0;
		int a = 0;
		while (n > 0) {
			a = n % 10; // 5, 4
			n = n / 10; // 1234, 123
			x = x + a; // 5, 54,

			if (n > 0) {
				x = x * 10; // 50, 540
			}
		}
		System.out.print(x);
	}
}
