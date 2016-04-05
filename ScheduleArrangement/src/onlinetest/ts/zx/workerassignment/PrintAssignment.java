package onlinetest.ts.zx.workerassignment;

import java.awt.Desktop;

import java.io.BufferedWriter;
import java.io.File;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import java.io.IOException;
import java.io.OutputStreamWriter;

import java.util.ArrayList;

public class PrintAssignment {
    
    ArrayList<Assignment> assignments;
    WorkerPattern[] wps;
    String fileLoc;
    
    public PrintAssignment(String file, ArrayList<Assignment> arg1, WorkerPattern[] arg2){
        assignments = arg1;
        wps = arg2;
        fileLoc = file;
    }
    
    
    public static void printAssignment(ArrayList<Assignment> ass){
        for (Assignment temp : ass){
            System.out.println("+++++++++++++++++++" + WorkDay.getStringById(temp.getWorkday()) + "+++++++++++++++++++");
            System.out.println(temp.getWorker().toString());
        }
    }
    
    public static void printAssignmentByDay(ArrayList<Assignment> ass){
        System.out.println(String.format("%-20s%s", "Working Day", "Workers"));
        for(int i : WorkDay.getAllWorkday()){
            
             StringBuilder sb = new StringBuilder();
            for(Assignment temp : ass){
                if(temp.getWorkday() == i){
                sb.append(temp.getWorker().getName());
                sb.append(", ");}
            }
            sb.delete(sb.length()-2, sb.length());
            System.out.println(String.format("%-20s%s", WorkDay.getStringById(i), sb.toString()));
        }
    }
    
    public static void printAssignmentByWorker(WorkerPattern[] wps,ArrayList<Assignment> asss){
        System.out.println(String.format("%-20s%s", "Worker", "Working Days"));
        for(WorkerPattern wp : wps){
            
            StringBuilder sb = new StringBuilder();
            for(Assignment ass : asss){
                if(ass.getWorker().equals(wp)){
                    sb.append(WorkDay.getStringById(ass.getWorkday()));
                    sb.append(", ");
                }
            }
            sb.delete(sb.length()-2, sb.length());
            System.out.println(String.format("%-20s%s", wp.getName(), sb.toString()));
        }
        
    }

    void expport() throws FileNotFoundException, IOException {
        File fout = new File(fileLoc + "arrangements.txt");
        FileOutputStream fos = new FileOutputStream(fout);
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        printDailyDetails(bw);
        bw.newLine();
        bw.newLine();
        printPersondetails(bw);
        bw.close();
        Desktop.getDesktop().open(fout);


        //
//        for(WorkerPattern wp : wps){
//            System.out.println("+++++++++++++++++" + wp.getName() + "++++++++++++++++++");
//            for(Assignment ass : asss){
//                if(ass.getWorker().equals(wp)){
//                    System.out.println(WorkDay.getStringById(ass.getWorkday()));
//                }
//            }
//        }
        
    }

    private void printDailyDetails(BufferedWriter bw) throws IOException {
        bw.write(String.format("%-20s%s", "Day", "Workers"));
        
        for (int i : WorkDay.getAllWorkday()) {
            bw.newLine();
            bw.write(String.format("%-20s", WorkDay.getStringById(i)));
            for (Assignment temp : this.assignments) {
                if (temp.getWorkday() == i) {
                    bw.write(temp.getWorker().getName());
                    bw.write(", ");
                }

            }
        }
    }

    private void printPersondetails(BufferedWriter bw) throws IOException {
        bw.write(String.format("%-20s%s", "Name","Work Days"));
        for(WorkerPattern wp : wps){
            bw.newLine();
            bw.write(String.format("%-20s",wp.getName()));
            for(Assignment ass : assignments){
                if(ass.getWorker().equals(wp)){
                    bw.write(WorkDay.getStringById(ass.getWorkday()));
                    bw.write(", ");
                }
            }
        }
    }
}
