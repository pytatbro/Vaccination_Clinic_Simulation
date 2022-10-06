package Vacc_Queue;

import java.util.*;
import java.io.*;
import java.nio.file.*;

/**
 * This class is the main class to runs the clinic program
 * @author Tuan Tran
 * @version 1.0
 */
public class Clinic {

    private String[] patients; // array for holding extracted information from the input file
    private WaitQueue wq;// main queue for the patients

    /**
     * This constructor runs the clinic, provide vaccine to patients
     * when it's their turn. Also, this print out the name of a patient
     * and the corresponding time when called for vaccine.
     */
    public Clinic ( ) {
        readData();
        creatOut();
        monitor();
    }

    /**
     * This method open the input file of patients and store them to
     * the patients array.
     */
    private void readData ( ) {
        patients = new String[15];
        try {
            File myFile = new File("patients.txt");
            Scanner sc = new Scanner(myFile);
            String data = sc.nextLine(); //skip first line of the input file
            for (int i=0; i<patients.length; i++) { // attach the information to the array pos
                data = sc.nextLine();
                patients[i] = data;
                if (!sc.hasNextLine()) {
                    break; // in case is end of file
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("error file not found");
        } // file not found
    }

    /**
     * This method print out patients' name and their correspond time
     * when called for vaccine.
     * @param st Patients' information
     */
    private void printData (String st) {
        File res = new File("output.txt");
        try {
            PrintWriter out = new PrintWriter(new FileWriter(res, true));
            out.append(st+"\n"); // write but not overwriting the prev
            out.close(); // close the file
        } catch (IOException e) {
            System.out.println("error occurred"); // for any error in I/O
        }
    }

    /**
     * This method creates the output file (if not existed)
     */
    private void creatOut ( ) {
        Path p = Paths.get("output.txt");
        try {
            Path f = Files.createFile(p); //create file
            System.out.println("file created at "+p);
        } catch (IOException e) {
            System.out.println("error, file already created!"); //if already existed
        }
    }

    /**
     * This is the main method of clinic class. This will start the clinic at
     * 9 am and handle the patients (enter the loop). After all the patients
     * are done, close the clinic (break the loop).
     */
    private void monitor ( ) {
        /*
        The idea here is to set two local variables to represent patients' array pos
        and number of patient. This is to maintain that it's the right patients (object)
        for the algorithm to work with.
         */
        int p=0; //pos of Patient in the patients array
        int v=0; //number of patients that have received vaccination
        wq = new WaitQueue(); //create an empty queue
        Timer t = new Timer();
        /*
        This loop "runs" the clinic and go through every patient
        until all patients have been vaccinated (v==15)
         */
        do {
            Patient pa = new Patient(patients[p]); //create new Patients object with array's information
            if (t.compare(pa.getTime()) == 0) { //if real time = patient time of arrival
                if (p <= 13) { //ensure the patients array pos not > patients.length
                    p++;
                }
                //vaccinate the patients immediately if the queue & vaccine room is empty
                if ((wq.isEmpty()) && ((t.getVxtime() == 15) || (t.getVxtime() == 1))) {
                    printData(pa.getName() + "\t" + t.timetoString());
                    v++; //increase number of patients done
                } else {
                    wq.insert(pa); //add patient to the queue
                }
            }
            if (t.getVxtime() == 15) { //finish vaccinate a patient
                if (!wq.isEmpty()) {
                    Patient re = wq.removeMax();
                    printData(re.getName() + "\t" + t.timetoString()); //print out patient's information
                    v++; //increase number of patients done
                }
            }
            t.increase();
        } while (v != 15); //done all the patients, break the loop
    }

    //main function
    public static void main(String[] args) {
        Clinic c = new Clinic();
    }
}
