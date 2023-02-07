package Algorithm;

import java.util.Scanner;

class node {
	//distance: difference between head position and current track
	int distance = 0;
	
	//true if track has been accessed
	boolean accessed = false;
}

public class SSTF {
	//size of waiting track
	static int size = 8;
	
	//calculate difference between head and each track
	public static void caldiff(int arr[], int head, node diff[]) {
		for(int i = 0; i < size; i++) 
		{
			diff[i].distance = Math.abs(head - arr[i]);
		}
	}
	
	//find unaccessed track that has minimum distance from head
	public static int findMin(node diff[]) {
		int index = -1, minimum = Integer.MAX_VALUE;
		
		for(int i = 0; i < size; i++) 
		{
			if (!diff[i].accessed && minimum > diff[i].distance) 
			{
				minimum = diff[i].distance;
				index = i;
			}
		}
		return index;
	}
	
	public static void SSTF(int arr[], int head) {
		int seek_count = 0;
		
		//create object of class node
		node diff[] = new node[size];
		
		//initialize array
		for (int i = 0; i < size; i++) 
		{
			diff[i] = new node();
		}
		
		//seek_sequence: schedule of tracks
		int[] seek_sequence = new int[size + 1];
		
		for (int i = 0; i < size; i++) 
		{
			seek_sequence[i] = head;
			caldiff(arr, head, diff);
			
			int index = findMin(diff);
			
			diff[index].accessed = true;
			
			seek_count += diff[index].distance;
			System.out.println("Current seek count: " + seek_count);
			
			//accessed track becomes new head
			head = arr[index];
		}
		
		//for last accessed track
		seek_sequence[size] = head;
		
		System.out.println("Total seek count is: " + seek_count);
		
		//print out schedule
		System.out.println("Disk accessing schedule: ");
		for (int i = 1; i < size + 1; i++) {
			System.out.print(seek_sequence[i] + " ");
		}
		
	}
	
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		//input waiting track
		System.out.println("Waiting track: ");
		int arr[] = new int[size];
		
		for (int i = 0; i < size; i++) 
		{
			arr[i] = scanner.nextInt();
		}
		
		//input head value
		System.out.println("Head:");
		int head = scanner.nextInt();
		
		SSTF(arr, head);

	}

}
