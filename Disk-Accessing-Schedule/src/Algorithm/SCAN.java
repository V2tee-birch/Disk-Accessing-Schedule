package Algorithm;

import java.util.*;

public class SCAN {
	//size of waiting track
	static int size = 8;
	
	//size of disk
	static int disk_size = 200;
	
	public static void SCAN(int arr[], int head, String direction) {
		int seek_count = 0;
		int distance, cur_track;
		
		Vector<Integer> left = new Vector<Integer>();
		Vector<Integer> right = new Vector<Integer>();
		Vector<Integer> seek_sequence = new Vector<Integer>();
		
		//append end values which has to be visited before reverse
		if (direction == "left") 
			{left.add(0);}
		else if (direction == "right") 
			{right.add(disk_size -1);}
		
		for (int i = 0; i < size; i++) 
		{
			if (arr[i] < head) 
			{
				left.add(arr[i]);
			}
			else if (arr[i] > head) 
			{
				right.add(arr[i]);
			}
		}
		
		//sort left & right vectors
		Collections.sort(left);
		Collections.sort(right);
		
		//run while loop 2 times for scanning right & left of the head
		int run = 2;
		while(run -- > 0) 
		{
			if (direction == "left") 
			{
				for (int i = left.size()- 1; i >= 0; i--) 
				{
					cur_track = left.get(i);
					
					seek_sequence.add(cur_track);
					
					distance = Math.abs(cur_track - head);
					
					seek_count += distance;
					System.out.println("Current seek count: " + seek_count);
					
					head = cur_track;
				}
				direction = "right";
			}
			else if (direction == "right")
			{
				for (int i = 0; i < right.size(); i++) 
				{
					cur_track = right.get(i);
					
					seek_sequence.add(cur_track);
					
					distance = Math.abs(cur_track - head);
					
					seek_count += distance;
					System.out.println("Current seek count: " + seek_count);
					
					head = cur_track;
				}
				direction = "left";
			}
		}
		System.out.println("Total seek count is: " + seek_count);
		
		//print out schedule
		System.out.println("Disk accessing schedule: ");
		for (int i = 0; i < seek_sequence.size(); i++) {
			System.out.print(seek_sequence.get(i) + " ");
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
		System.out.println("Head: ");
		int head = scanner.nextInt();
		
		SCAN(arr, head, "left");

	}

}
