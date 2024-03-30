package package_marcus_herbert;

import java.util.ArrayList;

public class BakeryMain {
    public static void main(String[] args) {
        int numOfProcesses = 20;
        ArrayList<BakeryProcess> proceses = new ArrayList<BakeryProcess>();
        for (int i = 0; i < numOfProcesses; i++) {
            proceses.add(new BakeryProcess());
        }
        BakeryProcess.initializeSharedInfo();
        for (BakeryProcess p: proceses) {
            p.start();
        }
    }
}
