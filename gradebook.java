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
    }

    // getters
    public String getName() {
        return this.studentName;
    }

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
        }
        else {
            return -1;
        }
    }// end getScore()

    /**
     * getNumScores() returns the number of task-scores associated
     * with the student instance
     */
    public int getNumScores() {
        return this.studentScores.size();
    }

    // setters
    public void setScore(String task, Integer score) {
        if (this.studentScores.put(task,score) != null) {
            System.out.printf("%s score has been updated to %f\n", task, score);
        }
        else {
            System.out.printf("%s score added", task);
        }
    }
}