import java.util.*;

public class Gradebook {
     
}

class Student {
    // Vars 
    private HashMap<String, Integer> studentScores;
    private String studentName;

    // Constructor
    Student() {
        studentName = "";
        studentScores = new HashMap<String, Integer>();
    }//end Default Constructor

    // getters
    /**
     * getName() returns the String value representing a
     * Student's name, associated with the instance
     */
    public String getName() {
        return this.studentName;
    }//end getName()

    /**
     * getScore() returns an integer when called, either returning
     * the score (value) associated with the passed string (task) or
     * returns -1 to indicate no score is available.
     * 
     * @param task Stores the string representation of an assignment name.
     */
    public int getScore(String task) {
        if (this.studentScores.containsKey(task)) {
            return this.studentScores.get(task);
        }//end if
        else {
            return -1;
        }//end else
    }// end getScore()

    /**
     * getNumScores() returns the number of task-scores associated
     * with the student instance
     */
    public int getNumScores() {
        return this.studentScores.size();
    }//end getNumScores()

    // setters
    /**
     * setScore() updates the student's grade and associated
     * task. If the entry for task already exists, a message
     * notifies the user that an update has occured. If the
     * task did not exist, the user is notified of the addition.
     * 
     * @param task String representation of a task which has received a score
     * @param score Integer representing score given for a task
     */
    public void setScore(String task, Integer score) {
        if (this.studentScores.put(task,score) != null) {
            System.out.printf("%s score has been updated to %f\n", task, score);
        }//end if
        else {
            System.out.printf("%s score added", task);
        }//end else
    }//end setScore()

    /**
     * setName() accepts a string argument and associates
     * that String as the name for the student instance.
     * 
     * @param studentName String representing a student's name
     */
    public void setName(String studentName) {
        this.studentName = studentName;
    }//end setName()
}//end Student