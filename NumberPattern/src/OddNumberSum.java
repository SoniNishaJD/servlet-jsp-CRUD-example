
public class OddNumberSum {

	public static void main(String[] args) {
		int i;
		int sum = 0;

		for (i = 0; i <= 100; i++) {
			if (i % 2 == 1) {
				sum = sum + i;
			}
		}
		System.out.println("Sum of between 1 to 100 odd number is: " + sum);

	}

}
