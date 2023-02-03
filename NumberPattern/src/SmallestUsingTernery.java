
public class SmallestUsingTernery {

	public static void main(String[] args) {

		int a = 10;
		int b = 5;
		int c = 2;
		int n, smallest;
		n = a < b ? a : b;
		smallest = c < n ? c : n;
		System.out.println("smallest number is: " + smallest);
	}

}
