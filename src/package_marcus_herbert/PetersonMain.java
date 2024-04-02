package package_marcus_herbert;

import java.util.ArrayList;

/**
 * Creates threads used for testing the Peterson algorithm for mutual exclusion
 * Test the Peterson algorithm
 */

public class PetersonMain {
    public static void main(String[] args) {
        int numOfProcesses = 20;
        ArrayList<PetersonProcess> processes = new ArrayList<PetersonProcess>();
        for (int i = 0; i < numOfProcesses; i++) {
            processes.add(new PetersonProcess());
        }
        PetersonProcess.initializeSharedInfo();
        for (PetersonProcess p: processes) {
            p.start();
        }
    }
}

/*
Sample output:

Finished 4
Finished 1
Finished 6
Finished 5
Finished 3
Finished 2
Finished 10
Finished 8
Finished 11
Finished 9
Finished 16
Finished 0
Finished 7
Finished 12
Finished 17
Finished 13
Finished 14
Finished 15
Finished 18
Finished 19
 */