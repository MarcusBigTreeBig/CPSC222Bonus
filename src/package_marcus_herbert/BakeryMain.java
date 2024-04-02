package package_marcus_herbert;

import java.util.ArrayList;

/**
 * Creates threads that test the Bakery algorithm for mutual exclusion
 * Runs the threads
 */

public class BakeryMain {
    public static void main(String[] args) {
        int numOfProcesses = 20;
        ArrayList<BakeryProcess> processes = new ArrayList<BakeryProcess>();
        for (int i = 0; i < numOfProcesses; i++) {
            processes.add(new BakeryProcess());
        }
        BakeryProcess.initializeSharedInfo();
        for (BakeryProcess p: processes) {
            p.start();
        }
    }
}

/*
Sample output:

ID: 0 Number: 1
ID: 2 Number: 1
ID: 5 Number: 1
ID: 1 Number: 2
ID: 4 Number: 2
ID: 3 Number: 3
ID: 19 Number: 4
ID: 6 Number: 5
ID: 12 Number: 5
ID: 10 Number: 6
ID: 7 Number: 7
ID: 8 Number: 7
ID: 11 Number: 8
ID: 9 Number: 9
ID: 13 Number: 10
ID: 14 Number: 11
ID: 15 Number: 11
ID: 17 Number: 12
ID: 18 Number: 12
ID: 16 Number: 13
 */
