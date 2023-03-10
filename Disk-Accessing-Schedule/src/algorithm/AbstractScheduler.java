package algorithm;

import java.util.ArrayList;

public class AbstractScheduler {
    
    // Common internal members

    String name;
    int[] requests;
    int head;
    Boolean direction; // True if direction is right, False if direction is left.
    ArrayList<Integer> schedule = new ArrayList<Integer>();
    ArrayList<Integer> seekCount = new ArrayList<Integer>();
	final int diskSize = 200; // Size of disk

    // Common method

    public void compute() {

    };

    public void printResult() {
        System.out.println(this.name);

        System.out.println("\tRequest    Seek count");
        for (int i = 0; i < this.schedule.size(); i++) {
            System.out.printf("\t%7d", schedule.get(i));
            System.out.printf("    ");
            System.out.printf("%10d \n", seekCount.get(i));
        }
    };

    public String getName() {
        return this.name;
    }

    public ArrayList<Integer> getSchedule() {
        return this.schedule;
    }

    public ArrayList<Integer> getSeekCount() {
        return this.seekCount;
    }

    public int getTotalSeekCount() {
        return this.seekCount.get(this.seekCount.size() -1);
    }

    // Common constructors

    public AbstractScheduler(String name, int[] requests, int head) {
        this.name = name;
        this.requests = requests;
        this.head = head;
        compute();
    }

    public AbstractScheduler(String name, int[] requests, int head, Boolean direction) {
        this.name = name;
        this.requests = requests;
        this.head = head;
        this.direction = direction;
        compute();
    }

}
