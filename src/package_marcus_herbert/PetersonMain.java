package package_marcus_herbert;

import java.util.ArrayList;

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
