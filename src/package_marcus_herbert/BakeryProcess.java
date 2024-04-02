package package_marcus_herbert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Threads used to test the Bakery algorithm for mutual exclusion
 */

public class BakeryProcess extends Thread{

    private static ArrayList<BakeryProcess> processes;
    private static int nextID;
    private static boolean[] choosing;
    private static long[] number;

    private int id;
    private Comparator<BakeryProcess> comp;

    /**
     * Sets all the shared information to it's required initial state for the Bakery algorithm
     */
    public static void initializeSharedInfo () {
        //comparator used to sort by id
        Comparator<BakeryProcess> idComp = new Comparator<BakeryProcess>() {
            @Override
            public int compare(BakeryProcess o1, BakeryProcess o2) {
                return o1.id-o2.id;
            }
        };
        Collections.sort(processes, idComp);
        int n = processes.size();
        choosing = new boolean[n];
        number = new long[n];
        for (int i = 0; i < n; i++) {//setting all the information to its initial state
            choosing[i] = false;
            number[i] = 0;
        }
    }

    /**
     * Finds the largest long in an array of long
     *
     * @param nums
     * @return
     */
    public static long max (long[] nums) {
        long highest = Long.MIN_VALUE;
        for (long i: nums) {
            if (i > highest) {
                highest = i;
            }
        }
        return highest;
    }

    /**
     * Creates a process with the next id that has not been used
     */
    public BakeryProcess () {
        this.id = nextID++;
        if (processes == null) {//if this is the first to be created, create a list for the processes
            processes = new ArrayList<BakeryProcess>();
        }
        processes.add(this);
        //comparator used to determine order of entry for threads entering the critical section
        //first takes the smallest number obtained from the bakery algorithm, takes smallest id if there is a tie for the number
        comp = new Comparator<BakeryProcess>() {
            @Override
            public int compare(BakeryProcess o1, BakeryProcess o2) {
                if (number[o1.id] == number[o2.id]) {
                    return o1.id-o2.id;
                }else{
                    return (int)(number[o1.id]-number[o2.id]);
                }
            }
        };
    }

    /**
     * Enters the critical section once allowed to by the Bakery algorithm
     * Prints it's id and it's number obtained from the Bakery algorithm
     * Stops
     */
    @Override
    public void run() {
        Random rand = new Random();
        //randomly wait for up to a millisecond, so threads don't necessarily arrive in order of id
        try {
            sleep(0, rand.nextInt(1000));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        //start of acquiring a number
        choosing[id] = true;
        number[id] = Long.MIN_VALUE;
        number[id] = 1+max(number);
        choosing[id] = false;
        //end of acquiring a number

        int n = number.length;
        //it is this threads turn to enter the critical section when:
        //    no other process is choosing a number
        //    it has the smallest number that has not gone through
        //    if there is a tie for the number, if it has the smallest id of those that are tied
        for (int i = 0; i < n; i++) {
            while (choosing[i] != false) {

            }
            while (number[i] != 0 && comp.compare(this, processes.get(i)) > 0) {
                try {
                    sleep(0, 1);//sleep fixes code
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        //start of critical section
        System.out.println("ID: " + id + " Number: " + number[id]);
        //end of critical section
        number[id] = 0;//resets this thread
    }
}
