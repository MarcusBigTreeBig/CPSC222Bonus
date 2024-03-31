package package_marcus_herbert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class PetersonProcess extends Thread{

    private static ArrayList<PetersonProcess> processes;
    private static int nextID;
    private static int[] turn;
    private static int[] flag;

    private int id;

    public static void initializeSharedInfo () {
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
        for (int i = 0; i < n; i++) {
            turn[i] = 0;
            flag[i] = 0;
        }
    }

    public PetersonProcess () {
        this.id = nextID++;
        if (processes == null) {
            processes = new ArrayList<PetersonProcess>();
        }
        processes.add(this);
    }

    @Override
    public void run() {
//        Random rand = new Random();
//        try {
//            wait(0, rand.nextInt(100));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        int n = processes.size();
        boolean flagCondition;
        for (int k = 1; k < n; k++) {
            flag[id] = k;
            turn[k] = id;
            do {
              flagCondition = true;
              for (PetersonProcess p: processes) {
                  if (flag[p.id] > k) {
                      flagCondition = false;
                  }
              }
            } while (turn[k] == id && !flagCondition);
        }
        //start of critical section
        System.out.println(id);
        //end of critical section
    }

}
