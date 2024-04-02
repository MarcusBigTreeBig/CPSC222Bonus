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
