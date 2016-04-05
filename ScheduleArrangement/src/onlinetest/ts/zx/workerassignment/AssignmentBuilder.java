package onlinetest.ts.zx.workerassignment;

import java.io.FileNotFoundException;
import java.io.IOException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class AssignmentBuilder {

    private ArrayList<Assignment> assignments;
    private Map<WorkerPattern, Integer> availableWorker;
    private WorkerPattern[] workerList;
    private LoadWorker lw;
    private String filelocation;

    public AssignmentBuilder(WorkerPattern[] wkList) {
        workerList = wkList;
    }

    public AssignmentBuilder(String str) throws IOException {
        filelocation = str.replaceAll("sample data.txt", "");
        lw = new LoadWorker(str);
        workerList = lw.retrieveWorkerList();
    }

    private boolean isAvailable(int workDay, WorkerPattern wp) {
        if (wp.isDayOff(workDay))
            return false;
        for (Assignment temp : assignments) {

            if (temp.getWorkday() == workDay && temp.getWorker().equals(wp)) {
                return false;
            }
        }
        return true;
    }


    public void buildAssignments() {
        initAvailableWorkerList();
        assignments = new ArrayList<Assignment>();
        for (int i = WorkDay.getAllWorkday().length; i > 0; i--) {

            for (int j = 0; j < WorkDay.getWorkersNeeded(i); j++) {
                if (this.availbaleWorkerArr(i).length > 0) {
                    WorkerPattern wp = pickFromAvailableList(i);
                    assignments.add(new Assignment(i, wp));
                    availableWorker.put(wp, availableWorker.get(wp) - 1);
                }

            }

        }
    }

    private void initAvailableWorkerList() {
        availableWorker = new HashMap<WorkerPattern, Integer>();
        for (WorkerPattern wp : workerList) {
            availableWorker.put(wp, averageWorkingDays() + 1);
        }
    }


    public ArrayList<Assignment> getAssignments() {
        return assignments;
    }


    private int averageWorkingDays() {
        int workerNo = workerList.length;
        int total = WorkDay.totalManPower();
        return total / workerNo;
    }


    private WorkerPattern pickFromAvailableList(int workday) {
        int index = pickRandomIndex(0, availbaleWorkerArr(workday).length);
        return (WorkerPattern)availbaleWorkerArr(workday)[index];
    }

    public Object[] availbaleWorkerArr(int workday) {
        ArrayList<WorkerPattern> wps = new ArrayList<WorkerPattern>();
        for (WorkerPattern wp : availableWorker.keySet()) {
            if (availableWorker.get(wp) > 0 && this.isAvailable(workday, wp)) {
                wps.add(wp);
            }
        }
        return wps.toArray();
    }

    private static int pickRandomIndex(int min, int max) {
        int randomNo = min + (int)(Math.random() * max);
        return randomNo;
    }
    
    public void printByDay(){
        PrintAssignment.printAssignmentByDay(getAssignments());
    }
    
    public void printByPerson(){
        PrintAssignment.printAssignmentByWorker(workerList, getAssignments());
    }
    
    
    public void exportToTxt() throws FileNotFoundException, IOException {
        PrintAssignment pa = new PrintAssignment(this.filelocation, this.getAssignments(), this.workerList);
        pa.expport();
    }
    
    
}
