package Vacc_Queue;

import java.util.StringTokenizer;

public class Patient {

    private static final String[] SpecialOcc = {"Teacher", "Nurse", "Care Giver"}; //priority occupations
    private static final String[] SpecialHealth = {"Pregnant","Cancer","Diabetes","Asthma",
            "Primary Immune Deficiency","Cardiovascular Disease"}; //priority health conditions
    private final String name;
    private String occ, health, time;
    private int age;
    private int point = 0; //base priority point

    /**
     * This constructor creates a patient with information from the data file
     * @param in information from data file (String)
     */
    public Patient (String in) {
        StringTokenizer st = new StringTokenizer(in, "\t"); //split the information
        name = st.nextToken();
        if (st.hasMoreTokens()) { //attach the corresponding information to the right attributes
            st.nextToken();
            age = Integer.parseInt(st.nextToken());
            occ = st.nextToken();
            health = st.nextToken();
            time = st.nextToken();
        }
        //Calculate the priority points
        if (age>=60) {point++;}
        for (String s : SpecialOcc) {
            if (occ.equals(s)) {point++;}
        }
        for (String z : SpecialHealth) {
            if (health.equals(z)) {point++;}
        }
    } //constructor

    /**
     * This method returns patient's name
     * @return Patient's name
     */
    public String getName( ) {
        return name;
    }

    /**
     * This method return patient's time of arrival
     * @return time of arrival
     */
    public String getTime( ) {
        return time;
    }

    /**
     * This method return priority point of a patient
     * @return priority point
     */
    public int getPoint ( ) {
        return point;
    }
}
