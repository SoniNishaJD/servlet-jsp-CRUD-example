
public class SumOfDigitNumber {

	public static void main(String[] args) {
		int num = 12345;
		int x = 0;
		int sum = 0;

		while (num != 0) {
			x = num % 10;
			sum = sum + x;
			num = num / 10;
		}
		System.out.println("Sum of digit is: " + sum);
	}

}
