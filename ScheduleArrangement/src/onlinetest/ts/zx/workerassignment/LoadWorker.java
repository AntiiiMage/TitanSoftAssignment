package onlinetest.ts.zx.workerassignment;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FilterInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Scanner;

public class LoadWorker {
    private String file;

    public LoadWorker(String filename) {
        file = filename;
    }


    public WorkerPattern[] retrieveWorkerList() throws IOException {
        ArrayList arr = new ArrayList();
        readTxt(arr);
        WorkerPattern[] wps = new WorkerPattern[arr.size()];
        for (int i = 0; i < wps.length; i++) {
            wps[i] = (WorkerPattern)arr.get(i);
        }
        return wps;
    }


    public void readTxt(ArrayList arr) throws IOException {
        Scanner in = new Scanner(new FileReader(file));
        int index = 0;
        try {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                if (++index > 2 && line != null) {
                    arr.add(retrieveWorkerFromStr(index - 2, line));
                }
            }
        } finally {
            in.close();
        }
    }

    private static WorkerPattern retrieveWorkerFromStr(int id, String str) {
        String[] strs = reshapeStr(str).split(",");
        int[] offdays =
            WorkDay.getDaysFromStrs(new String[] { strs[1], strs[2] });
        return new WorkerPattern(id, strs[0], offdays);
    }


    private static String reshapeStr(String str) {
        str = str.replaceAll(" ", "");
        str = str.replaceFirst("\t", "");
        str = str.replaceAll("\t", ",");
        return str;
    }
}
