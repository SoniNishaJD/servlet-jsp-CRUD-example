
public class SumFirst100Number {

	public static void main(String[] args) {
		int i = 1;
		int sum = 0;
		int x = 0;

		while (x != 100) { // odd number sum
			if (i % 2 == 1) {
				sum = sum + i;
				x++;
				System.out.println(x + "   " + i);
			}
			i++;
		}

		System.out.println("Sum of First 100 odd number is: " + sum);

	}

}
