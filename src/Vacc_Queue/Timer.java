package Vacc_Queue;

public class Timer {

    private int hour = 9; // start at 9 am
    private int minute = 0; //reset at 60
    private int vxtime = 0; //reset at 15

    public Timer ( ) {
    } //constructor

    /**
     * This method increase the real time by one minutes
     * and also the vaccine time
     */
    public void increase ( ) {
        minute++;
        if (minute == 60) { //minute reach 60 -> increase hour
            minute = 0;
            hour++;
        }
        if (vxtime == 15) { //done 1 rotation of vaccine time
            vxtime = 1;
        }
        if (vxtime<15) {
            vxtime++;
        }
    }

    /**
     * This method compare patients' time of arrival and real time
     * @param s time of arrival
     * @return -1 (less than), 0 (equals), 1 (greater than)
     */
    public int compare(String s) {
        return timetoString().compareTo(s);
    }

    /**
     * This method convert real time (hour&minute) to String form
     * @return String form of real time
     */
    public String timetoString ( ) {
        String h, h1, h2;
        h1 = String.valueOf(hour);
        h2 = String.valueOf(minute);
        if (hour<10) {
            h1 = "0"+h1;
        }
        if (minute<10) {
            h2 = "0"+h2;
        }
        h = h1+":"+h2;
        return h;
    }

    /**
     * This method returns the current vaccine time
     * @return vaccine time
     */
    public int getVxtime ( ) {
        return vxtime;
    }
}
