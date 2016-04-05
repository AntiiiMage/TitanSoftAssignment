package onlinetest.ts.zx.workerassignment;

public class WorkDay {
    private final static int MON = 1;
    private final static int TUE = 2;
    private final static int WED = 3;
    private final static int THU = 4;
    private final static int FRI = 5;
    private final static int SAT = 6;
    private final static int SUN = 7;


    public static int getWorkersNeeded(int workday) {
        switch (workday) {
        case MON:
        case TUE:
        case WED:
        case THU:
        case FRI:
            return 2;
        case SAT:
        case SUN:
            return 3;
        default:
            return 2;
        }
    }

    public static int[] getAllWorkday() {
        return new int[] { MON, TUE, WED, THU, FRI, SAT, SUN };
    }

    public static String getStringById(int workday) {
        switch (workday) {
        case MON:
            return "Monday";
        case TUE:
            return "Tuesday";
        case WED:
            return "Wednesday";
        case THU:
            return "Thursday";
        case FRI:
            return "Friday";
        case SAT:
            return "Saturday";
        case SUN:
            return "Sunday";
        default:
            return null;
        }
    }

    private static int getIdByString(String str) {
        switch (str) {
        case "Monday":
            return MON;
        case "Tuesday":
            return TUE;
        case "Wednesday":
            return WED;
        case "Thursday":
            return THU;
        case "Friday":
            return FRI;
        case "Saturday":
            return SAT;
        case "Sunday":
            return SUN;
        default:
            return 0;
        }
    }

    public static int totalManPower() {
        int total = 0;
        for (int i : getAllWorkday()) {
            total += getWorkersNeeded(i);
        }
        return total;
    }

    static int[] getDaysFromStrs(String[] strings) {
        int[] days = new int[strings.length];
        for (int i = 0; i < strings.length; i++) {
            days[i] = getIdByString(strings[i]);
        }
        return days;
    }
}
