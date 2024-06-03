import java.util.*;
import java.text.*;


public class Gradebook {
   private final double[] SCORE_LIMITS = new double [] {60, 70, 80, 90, 100};
   private final String[] LETTER_GRADE = new String [] {"F", "D", "C", "B", "A"};
   private ChoiceFormat gradeFormat = new ChoiceFormat(SCORE_LIMITS, LETTER_GRADE);
   private HashMap<Student, Double> studentGrades;

   public Gradebook() {
      studentGrades = new HashMap<Student, Double>();
   }

   // getters
   public int getSize() {
      return this.studentGrades.size();
   }
   public Set<Student> getStudents() {
      if (studentGrades.size() > 0) {
         return studentGrades.keySet();
      }
      else {
         return null;
      }
   }
   // setters

   // logic
   //public double calcGrade(Student student) {
      //iterate through student hash map
      // retrieve scores for each task
      // and apply formula
      // Total_Grade = (0.1 x part.) + (0.35 x proj.) + (0.3 x Midt.) + (.25 x Final.)
   //}

   public void showNames() {
      for (Student student : getStudents()) {
         System.out.printf("%-25s%6f%5S",
          student.getName() /*student grade */, gradeFormat.format(90));
      }
   }

   // setters
}

/**
 * Student class, when instantiated, contains a HashMap that
 * stores the scores of tasks assigned to students. Also contains
 * the student's name as a String. Default constructor initiliazes
 * all variables.
 */
class Student {
    // Vars 
    private HashMap<String, Double> studentScores;
    private String studentName;

    // Constructor
    Student() {
        studentName = "";
        studentScores = new HashMap<String, Double>();
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
    public double getScore(String task) {
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
    public void setScore(String task, Double score) {
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

class CourseWeight {
   //public static void 
   //Total_Grade = (0.1 x part.) + (0.35 x proj.) + (0.3 x Midt.) + (.25 x Final.)
}
