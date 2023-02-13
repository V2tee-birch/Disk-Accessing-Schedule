import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Scanner;
import java.util.stream.Collectors;

import algorithm.AbstractScheduler;
import algorithm.CLOOKScheduler;
import algorithm.CSCANScheduler;
import algorithm.FCFSScheduler;
import algorithm.LIFOScheduler;
import algorithm.LOOKScheduler;
import algorithm.SCANScheduler;
import algorithm.SSTFScheduler;

public class Main {

	static int size = 8;
	private static Scanner scanner = new Scanner(System.in);

	private FCFSScheduler fcfsScheduler;
	private LIFOScheduler lifoScheduler;
	private SSTFScheduler sstfScheduler;
	private SCANScheduler scanSchedulerLeft;
	private SCANScheduler scanSchedulerRight;
	private LOOKScheduler lookSchedulerLeft;
	private LOOKScheduler lookSchedulerRight;
	private CSCANScheduler cscanSchedulerLeft;
	private CSCANScheduler cscanSchedulerRight;
	private CLOOKScheduler clookSchedulerLeft;
	private CLOOKScheduler clookSchedulerRight;

	public static void main(String[] args) throws Exception {

		System.out.println("Disk Accessing Scheduler: Console");

		// Input number of requests
		System.out.println("Input waiting track:");
		int[] arr = getRequests();

		// Input head value

		System.out.println("Input head:");
		int head = scanner.nextInt();

		System.out.println("Waiting track: " + Arrays.toString(arr));
		System.out.println("Head: " + head);

		Main main = new Main(arr, head);

		mainMenu(main);

	}

	private static void mainMenu(Main main) {
		System.out.println("Choose one of the following options:\n"
				+ "\t1. FCFS \n"
				+ "\t2. LIFO \n"
				+ "\t3. SSTF \n"
				+ "\t4. SCAN \n"
				+ "\t5. CSCAN\n"
				+ "\t6. LOOK \n"
				+ "\t7. CLOOK\n"
				+ "\t0. Compare algorithms.");
		int choice = getChoice(7);

		switch (choice) {
			case 1:
				main.fcfsScheduler.printResult();
				break;
			case 2:
				main.lifoScheduler.printResult();
				break;
			case 3:
				main.sstfScheduler.printResult();
				break;
			case 4:
				main.scanSchedulerLeft.printResult();
				main.scanSchedulerRight.printResult();
				break;
			case 5:
				main.cscanSchedulerLeft.printResult();
				main.cscanSchedulerRight.printResult();
				break;
			case 6:
				main.lookSchedulerLeft.printResult();
				main.lookSchedulerRight.printResult();
				break;
			case 7:
				main.clookSchedulerLeft.printResult();
				main.clookSchedulerRight.printResult();
				break;
			case 0:
				compareMenu(main);
				break;
		}
	};

	private static void compareMenu(Main main) {
		System.out.println("Choose one of the following options:\n"
		+ "\t 1. FCFS \n"
		+ "\t 2. LIFO \n"
		+ "\t 3. SSTF \n"
		+ "\t 4. SCAN (Left) \n"
		+ "\t 5. SCAN (Right) \n"
		+ "\t 6. CSCAN (Left)\n"
		+ "\t 7. CSCAN (Right)\n"
		+ "\t 8. LOOK (Left)\n"
		+ "\t 9. LOOK (Right)\n"
		+ "\t10. CLOOK (Left)\n"
		+ "\t11. CLOOK (Right)\n");
		
		System.out.println("Input algorithms that you want to compare:");
		AbstractScheduler[] algorithms = {main.fcfsScheduler, main.lifoScheduler, main.sstfScheduler, 
							   			  main.scanSchedulerLeft, main.scanSchedulerRight,
							   			  main.cscanSchedulerLeft, main.cscanSchedulerRight,
							   			  main.lookSchedulerLeft, main.lookSchedulerRight,
							   			  main.clookSchedulerLeft, main.clookSchedulerRight};
		int[] choices = getAlgorithms();

		System.out.println("Algorithms    | Seek count | Sequence");
		for (int i = 0; i < choices.length; i++) {
			System.out.printf("%13.13s | %10d | %s\n", 
							  algorithms[i].getName(), 
							  algorithms[i].getTotalSeekCount(),
							  algorithms[i].getSchedule()
							  );
		}
	};

	private static int[] getAlgorithms() {
		int[] numbers = scanInt();
		while (numbers.length < 2) {
			System.out.println("Please input a valid array of minimum two integers.");
			numbers = scanInt();

			Set<Integer> numbersList = new HashSet<Integer>();
			for (int i = 0; i < numbers.length; i++) {
				if (0 < numbers[i] && numbers[i] < 12) {
					numbersList.add(numbers[i]);
				}
			}
			numbers = numbersList.stream().mapToInt(Number::intValue).toArray();
		}
		return numbers;
	}

	private static int[] getRequests() {
		int[] numbers = scanInt();
		while (numbers.length < 1) {
			System.out.println("Please input a valid array.");
			numbers = scanInt();
		}
		return numbers;
	}

	private static int getChoice(int max) {
		Integer input = scanner.nextInt();
		scanner.nextLine();
		while (0 > input || input > max) {
			System.out.println("Please choose a valid number.");
			input = scanner.nextInt();
			scanner.nextLine();
		}
		return input;
	}

	private static int[] scanInt() {

		String line = scanner.nextLine();
		String[] numberStrs = line.split(" ");
		int[] numbers = new int[numberStrs.length];
		int index = 0;
		for (int i = 0; i < numberStrs.length; i++) {
			try {
				numbers[index] = Integer.parseInt(numberStrs[i]);
				index++;
			} catch (NumberFormatException nfe) {
				System.out.println(numberStrs[i] + " is not a valid number, will be discarded.");
			}
		}
		// Now there will be a number of 'invalid' elements at the end which will need to be trimmed
		numbers = Arrays.copyOf(numbers, index);
		Set<Integer> set = Arrays.stream(numbers).boxed().collect(Collectors.toSet());
		numbers = set.stream().mapToInt(Number::intValue).toArray();
		return numbers;
	}

	public Main(int[] arr, int head) {
		this.fcfsScheduler = new FCFSScheduler(arr, head);
		this.lifoScheduler = new LIFOScheduler(arr, head);
		this.sstfScheduler = new SSTFScheduler(arr, head);
		this.scanSchedulerLeft = new SCANScheduler(arr, head, false);
		this.scanSchedulerRight = new SCANScheduler(arr, head, true);
		this.lookSchedulerLeft = new LOOKScheduler(arr, head, false);
		this.lookSchedulerRight = new LOOKScheduler(arr, head, true);
		this.cscanSchedulerLeft = new CSCANScheduler(arr, head, false);
		this.cscanSchedulerRight = new CSCANScheduler(arr, head, true);
		this.clookSchedulerLeft = new CLOOKScheduler(arr, head, false);
		this.clookSchedulerRight = new CLOOKScheduler(arr, head, true);
	}

}
