package onlinetest.ts.zx.workerassignment;

import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws IOException {
        //        String file = "C:\Users\ZhangXu\Desktop\sample data.txt";
        System.out.println("Pleae input the worker list txt file path: ");
        Scanner sc = new Scanner(System.in);
        String file = sc.nextLine();
        AssignmentBuilder ab = new AssignmentBuilder(file);
        ab.buildAssignments();
        ab.printByDay();
        System.out.println("");
        ab.printByPerson();
//        ab.exportToTxt();
    }
}
