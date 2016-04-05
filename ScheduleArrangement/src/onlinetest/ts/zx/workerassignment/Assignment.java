package onlinetest.ts.zx.workerassignment;

public class Assignment {
    int workday;
    WorkerPattern worker;

    public Assignment(int day, WorkerPattern wk) {
        this.workday = day;
        this.worker = wk;
    }

    public void setWorkday(int workday) {
        this.workday = workday;
    }

    public int getWorkday() {
        return workday;
    }


    public void setWorker(WorkerPattern worker) {
        this.worker = worker;
    }

    public WorkerPattern getWorker() {
        return worker;
    }
}
