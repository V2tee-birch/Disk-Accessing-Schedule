import java.util.Scanner;
	
public class Main {

    static int size = 8;
	public static void main(String[] args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			// input waiting track
			System.out.println("Waiting track: ");
			int arr[] = new int[size];

			for (int i = 0; i < size; i++) {
				arr[i] = scanner.nextInt();
			}

			// input head value
			System.out.println("Head:");
			int head = scanner.nextInt();

		}

	}
    
}
