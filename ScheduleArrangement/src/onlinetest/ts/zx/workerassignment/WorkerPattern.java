package onlinetest.ts.zx.workerassignment;

public class WorkerPattern {
    private int id;
    private String name;
    private int[] dayoff;

    public WorkerPattern(int wkId, String wkName, int[] dayoffArr) {
        id = wkId;
        name = wkName;
        dayoff = dayoffArr;
    }

    public boolean isDayOff(int i) {
        boolean result = false;
        for (int j : dayoff) {
            result = i == j;
            if (result == true)
                break;
        }
        return result;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Worker ID: ");
        sb.append(this.id);
        sb.append(" , Name: ");
        sb.append(this.name);
        sb.append(" , dayoff: ");
        for (int i : this.dayoff) {
            sb.append(WorkDay.getStringById(i) + " ");
        }
        return sb.toString();
    }


    @Override
    public int hashCode() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (null != obj && obj instanceof WorkerPattern) {
            WorkerPattern wp = (WorkerPattern)obj;
            if (this.id == wp.id && this.name.equals(wp.name) &&
                this.dayoff.length == wp.dayoff.length) {
                for (int k = 0; k < this.dayoff.length; k++) {
                    if (this.dayoff[k] != wp.dayoff[k]) {
                        return false;
                    }
                }
                return true;
            }
            return false;

        } else
            return false;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setDayoff(int[] dayoff) {
        this.dayoff = dayoff;
    }

    public int[] getDayoff() {
        return dayoff;
    }
}
