package package_marcus_herbert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BakeryProcess extends Thread{

    private static ArrayList<BakeryProcess> processes;
    private static int nextID;
    private static boolean[] choosing;
    private static long[] number;

    private int id;
    private Comparator<BakeryProcess> comp;

    public static void initializeSharedInfo () {
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
        for (int i = 0; i < n; i++) {
            choosing[i] = false;
            number[i] = 0;
        }
    }

    public static long max (long[] nums) {
        long highest = Long.MIN_VALUE;
        for (long i: nums) {
            if (i > highest) {
                highest = i;
            }
        }
        return highest;
    }

    public BakeryProcess () {
        this.id = nextID++;
        if (processes == null) {
            processes = new ArrayList<BakeryProcess>();
        }
        processes.add(this);
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

    @Override
    public void run() {
//        Random rand = new Random();
//        try {
//            wait(0, rand.nextInt(100));
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        choosing[id] = true;
        number[id] = Long.MIN_VALUE;
        number[id] = 1+max(number);
        choosing[id] = false;
        int n = number.length;
        for (int i = 0; i < n; i++) {
            while (choosing[i] != false) {

            }
            while (number[i] != 0 && comp.compare(this, processes.get(i)) > 0) {

            }
        }
        //start of critical section
        System.out.println("ID: " + id + " Number: " + number[id]);
        //end of critical section
        number[id] = 0;
    }
}
