package Algorithm;
import java.util.Scanner;

public class FCFS {
	//size of waiting track
		static int size = 8;
		
	public static void FCFS(int arr[], int head) {
		int seek_count = 0;
		int distance, cur_track;
		
		for (int i = 0; i < size; i++) {
			cur_track = arr[i];
			
			distance = Math.abs(cur_track - head);
			
			seek_count += distance;
			
			head = cur_track;
			
			System.out.println("Current seek count: " + seek_count);
		}
		System.out.println("Total seek count is: " + seek_count);
		
		System.out.println("Disk accessing schedule: ");
		
		for(int i = 0; i < size; i++) {
			System.out.print(arr[i] + " ");
		}

	}


	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		//input waiting track
		System.out.println("Waiting track: ");
		int arr[] = new int[size];
		
		for (int i = 0; i < size; i++) {
			arr[i] = scanner.nextInt();
		}
		
		//input head value
		System.out.println("Head:");
		int head = scanner.nextInt();
		
		FCFS(arr, head);


	}

}