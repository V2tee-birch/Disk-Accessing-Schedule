package algorithm;

import java.util.Collections;
import java.util.Scanner;
import java.util.Vector;

public class C_LOOK {

	// size of waiting track
	static int size = 8;

	// size of disk
	final static int disk_size = 200;

	public static void C_LOOK(int arr[], int head) {
		int seek_count = 0;
		int distance, cur_track;

		Vector<Integer> left = new Vector<Integer>();
		Vector<Integer> right = new Vector<Integer>();
		Vector<Integer> seek_sequence = new Vector<Integer>();

		for (int i = 0; i < size; i++) {
			if (arr[i] < head) {
				left.add(arr[i]);
			} else if (arr[i] > head) {
				right.add(arr[i]);
			}
		}

		// sort left & right vectors
		Collections.sort(left);
		Collections.sort(right);

		for (int i = 0; i < right.size(); i++) {
			cur_track = right.get(i);

			seek_sequence.add(cur_track);

			distance = Math.abs(cur_track - head);

			seek_count += distance;
			System.out.println("Current seek count: " + seek_count);

			head = cur_track;
		}

		// when reach the right end, jump back to first track in left direction
		seek_count += Math.abs(head - left.get(0));
		head = left.get(0);

		for (int i = 0; i < left.size(); i++) {
			cur_track = left.get(i);

			seek_sequence.add(cur_track);

			distance = Math.abs(cur_track - head);

			seek_count += distance;
			System.out.println("Current seek count: " + seek_count);

			head = cur_track;
		}

		System.out.println("Total seek count is: " + seek_count);

		// print out schedule
		System.out.println("Disk accessing schedule: ");
		for (int i = 0; i < seek_sequence.size(); i++) {
			System.out.print(seek_sequence.get(i) + " ");
		}
	}

	public static void main(String[] args) throws Exception {
		try (Scanner scanner = new Scanner(System.in)) {
			// input waiting track
			System.out.println("Waiting track: ");
			int arr[] = new int[size];

			for (int i = 0; i < size; i++) {
				arr[i] = scanner.nextInt();
			}

			// input head value
			System.out.println("Head: ");
			int head = scanner.nextInt();

			C_LOOK(arr, head);
		}

	}

}
