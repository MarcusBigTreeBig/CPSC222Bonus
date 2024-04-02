package package_marcus_herbert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

/**
 * Thread used for testing the Peterson algorithm for mutual exclusion
 */

public class PetersonProcess extends Thread{

    private static ArrayList<PetersonProcess> processes;
    private static int nextID;
    private static int[] turn;
    private static int[] flag;

    private int id;

    /**
     * sets the shared information to what it needs to be for the Peterson algorithm
     */
    public static void initializeSharedInfo () {
        //comparator used to sort threads by id
        Comparator<PetersonProcess> idComp = new Comparator<PetersonProcess>() {
            @Override
            public int compare(PetersonProcess o1, PetersonProcess o2) {
                return o1.id-o2.id;
            }
        };
        Collections.sort(processes, idComp);
        int n = processes.size();
        turn = new int[n];
        flag = new int[n];
        for (int i = 0; i < n; i++) {//setting the information to the initial state
            turn[i] = 0;
            flag[i] = 0;
        }
    }

    /**
     * Creates the process with the next id to be used
     */
    public PetersonProcess () {
        this.id = nextID++;
        if (processes == null) {
            processes = new ArrayList<PetersonProcess>();
        }
        processes.add(this);
    }

    /**
     * The process waits for its turn to enter the critical section based on the peterson algorithm
     * Prints out its id
     * Stops
     */
    @Override
    public void run() {
        Random rand = new Random();
        //randomly wait up to a millisecond so threads don't necessarily arrive in order
        try {
            sleep(0, rand.nextInt(100));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        int n = processes.size();
        boolean flagCondition;
        for (int k = 1; k < n; k++) {//needs to attain level of n-1 to enter critical section
            //moves up a level
            flag[id] = k;
            turn[k] = id;

            do {
              flagCondition = true;//flag condition is true if this thread has the highest flag value (it's on top)
              for (PetersonProcess p: processes) {
                  if (flag[p.id] > k) {
                      flagCondition = false;
                  }
              }
            } while (turn[k] == id  && !flagCondition); //wait until it should move up a level again
        }
        //start of critical section
        System.out.println("Finished " + id);
        //end of critical section
        flag[id] = 0;//resets the thread so others can move up
    }

}
