import java.util.*;
import java.text.*;

/**
 * Gradebook class models the behavior of a
 * classroom gradebook. Tasks (Projects, Midterms,etc.)
 * are assigned a weight so that a final grade may be
 * tallied. Methods are provided to output results to console.<p>
 * Contains a main() driver to begin a demo of the class
 * by constructing a record and collecting data. Instantiated
 * with default constructor. Demonstration activated by
 * demoMode() method.
 */
public class Gradebook {
   /**
    * main() is the driver for Gradebook class demo.
    * A gradebook object is instantiated, wherein
    * the demoMode() method is called. Console
    * interaction begins.
    * @param args Command-Line arguments not utilized.
    */
   public static void main(String[] args) {
      Gradebook newDemo = new Gradebook();
      newDemo.demoMode();
   }// end main()

   // Course Weight for Tasks
   /** Participation is 10% of Total Grade */
   private static final double PARTICIPATION_WEIGHT = 0.10;
   /** Projects are 35% of Total Grade */
   private static final double PROJECT_WEIGHT = 0.35;
   /** Midterms are 30% of Total Grade */
   private static final double MIDTERM_WEIGHT = 0.30;
   /** Final Exam is 25% of Total Grade */
   private static final double FINAL_EXAM_WEIGHT = 0.25;
   //private static final String COURSE_NAME = "COMP 163";

   // Grade Thresholds
   /** Holds threshold values for each letter grade */
   private static final double[] gradeThresholds = new double[] {0, 60, 70, 80, 90};
   /** Holds all possible letter grades */
   private static final String[] letterGrades = new String[] {"F", "D", "C", "B", "A"};
   
   // List of Scored Course Tasks
   /** Holds name of all scored course projects */
   private static final String[] courseProjects = 
      new String[] {"Project 1", "Project 2"};
   /** Holds name of all scored course midterms */
   private static final String[] courseMidterms = 
      new String[] {"Midterm 1", "Midterm 2", "Midterm 3"};

   // Instance Variables
   /** Holds the first and last name of a student */
   private String studentName;
   /** Holds the assigned letter grade earned */
   private String letterGrade;
   /** Flags whether the lowest midterm grade has been swapped */
   private boolean midtermSwapFlag;
   /** Holds the earned participation score */             
   private double participationScore;
   /** Holds the calculated project score average */
   private double projectAverage;
   /** Holds the calculated midterm score average */
   private double midtermAverage;
   /** Holds the final exam score earned */
   private double finalExamScore;
   /** Holds the calculated total score earned */
   private double totalScore;
   /** Holds the pairing of projects assigned and score earned */
   private HashMap<String, Double> projectScores;
   /** Holds the pairing of midterms assigned and score earned */
   private HashMap<String, Double> midtermScores;
   
   // Formats numerical score to letter grade
   /** Formats decimal grades into letter grades, via thresholds */
   ChoiceFormat gradeFormat;
   /** Formats decimal numbers to two decimal places using pattern "#.##" */
   DecimalFormat decimalFormat;

   // Constructors
   /**
    * Gradebook() constructor initializes all instance variables
    * to default/empty/null/false values. ChoiceFormat allows
    * decimal grades to be parsed into letter grades.
    * DecimalFormat keeps double values to two percision places.
    */
   public Gradebook() {
      studentName = "";
      letterGrade = "";
      midtermSwapFlag = false;
      participationScore = projectAverage = midtermAverage =
       finalExamScore = totalScore = 0;
      projectScores = new HashMap<String, Double>();
      midtermScores = new HashMap<String, Double>();
      gradeFormat = new ChoiceFormat(gradeThresholds, letterGrades);
      decimalFormat = new DecimalFormat("#.##");
   }//end Gradebook() constructor


   // getters
   /**
    * getMidtermSwapFlag() returns the current value
    * stored in the boolean midtermSwapFlag.
    * @return Boolean flag that identifies a midterm swap occured
    */
   public boolean getMidtermSwapFlag() {
      return this.midtermSwapFlag;
   }//end getMidtermSwapFlag()

   /**
    * getLetterGrade() returns the current value
    * stored in the String letterGrade.
    * @return String Letter Grade value
    */
   public String getLetterGrade() {
      return this.letterGrade;
   }//end getLetterGrade()

   /**
    * getName() returns the current value
    * stored in the String studentName.
    * @return String value of student name.
    */
   public String getName() {
      return this.studentName;
   }//end getName()

   /**
    * getParticipationScore() returns the current value
    * stored in the double participationScore.
    * @return Double value score awarded from participation
    */
   public double getParticipationScore() {
      return this.participationScore;
   }//end getParticipationScore()

   /**
    * getProjectAverage() returns the value stored
    * in the double projectAverage.
    * @return Double average of all project scores
    */
   public double getProjectAverage() {
      return this.projectAverage;
   }//end getProjectAverage()

   /**
    * getProjectScores() returns HashMap<String,Double>
    * reference to projectScores.
    * @return Reference to HashMap that stores Projects and scores.
    */
   public HashMap<String, Double> getProjectScores() {
      return this.projectScores;
   }//end getProjectScores()

   /**
    * getProjectScore() returns a single score value
    * associated with the passed parameter.
    * @param projectName String value representing a project name.
    * @return Double value representing a score on specified project.
    */
   public double getProjectScore(String projectName) {
      return projectScores.get(projectName);
   }//end getProjectScore()

   /**
    * getMidtermAverage() returns the double value representing
    * the calculated midterm average.
    * @return Double calculated midterm average
    */
   public double getMidtermAverage() {
      return this.midtermAverage;
   }//end getMidtermAverage()

   /**
    * getMidtermScores() returns a reference to the
    * midtermScores HashMap<String, Double> instance variable.
    * @return Reference to HashMap of student midterm scores.
    */
   public HashMap<String, Double> getMidtermScores() {
      return this.midtermScores;
   }//end getMidtermScores()

   /**
    * getMidtermScore() returns the double value associated
    * with the passed string parameter.
    * @param midtermName String representing an assigned Midterm.
    * @return Double value representing score on specified Midterm.
    */
   public double getMidtermScore(String midtermName) {
      return midtermScores.get(midtermName);
   }//end getMidtermScore()

   /**
    * getFinalExamScore() returns the double value
    * representing the score for Final Exam.
    * @return Double value representing final exam score.
    */
   public double getFinalExamScore() {
      return this.finalExamScore;
   }//end getFinalExamScore()

   /**
    * getTotalScore() returns the double value
    * representing the calculated Total Score earned.
    * @return Double value calculated Total Score.
    */
   public double getTotalScore() {
      return this.totalScore;
   }//end getTotalScore()


   // setters
   /**
    * setMidtermSwapFlag() accepts a boolean parameter
    * and uses it to update the instance midtermSwapFlag
    * @param midtermSwapFlag Boolean indicating status of swap
    */
   public void setMidtermSwapFlag(boolean midtermSwapFlag) {
      this.midtermSwapFlag = midtermSwapFlag;
   }//end setMidtermSwapFlag()

   /**
    * setLetterGrade() accepts a String parameter that
    * represents a letter grade. Parameter is used to update
    * instance variable letterGrade.
    * @param letterGrade String holding a letter grade
    */
   public void setLetterGrade(String letterGrade) {
      this.letterGrade = letterGrade;
   }//end setLetterGrade()

   /**
    * setName() accepts a string parameter that
    * represents a student name. Parameter is used to
    * update instance variable studentName.
    * @param studentName String holding student name
    */
   public void setName(String studentName) {
      this.studentName = studentName;
   }//end setName()

   /**
    * setParticipationScore accepts a double parameter that
    * represents a participation score. Parameter is used to
    * update instance variable participationScore.
    * @param participationScore Double holding participation score.
    */
   public void setParticipationScore(double participationScore) {
      this.participationScore = participationScore;
   }//end setParticipationScore()

   /**
    * setProjectAverage() accepts a double parameter that
    * represents a calculated project average. Parameter is used
    * to update instance variable projectAverage.
    * @param projectAverage Double holding calculated project average
    */
   public void setProjectAverage(double projectAverage) {
      this.projectAverage = projectAverage;
   }//end setProjectAverage()

   /**
    * setProjectScores() accepts a HashMap<String,Double> reference
    * parameter that represents the assigned project names and associated
    * scores earned. Parameter is used to update instance variable
    * projectScores.
    * @param projectScores HashMap<String,Double> reference that holds
    * project name and associated scores earned.
    */
   public void setProjectScores(HashMap<String, Double> projectScores) {
      this.projectScores = projectScores;
   }//end setProjectScores()

   /**
    * setProjectScore() accepts a String holding a project name and a
    * double representing an associated score. This key,value pair is used
    * to update the projectScores HashMap entry for that key.
    * @param projectName String representing an assigned project name
    * @param projectScore Double representing an associated project score
    */
   public void setProjectScore(String projectName, double projectScore) {
      projectScores.put(projectName, projectScore);
   }//end setProjectScore()

   /**
    * setMidtermAvverage accepts a double holding a calculated midterm
    * average. Parameter is used to update the instance variable midtemAverage
    * @param midtermAverage Double holding calculated midterm average
    */
   public void setMidtermAverage(double midtermAverage) {
      this.midtermAverage = midtermAverage;
   }//end setMidtermAverage

   /**
    * setMidtermScores() accepts a HashMap<String, Double> reference
    * as a parameter. Reference used to update instance variable midtermScores.
    * @param midtermScores HashMap<String, Double> reference holding
    * assigned midterms and their associated scores.
    */
   public void setMidtermScores(HashMap<String, Double> midtermScores) {
      this.midtermScores = midtermScores;
   }//end setMidtermScores()

   /**
    * setMidtermScores() accepts a String and Double parameter to update
    * the instance variable HashMap<String, Double> entry.
    * @param midtermName String representing an assigned course midterm
    * @param midtermScore Double representing an associated score earned on
    * an assigned midterm.
    */
   public void setMidtermScore(String midtermName, double midtermScore) {
      midtermScores.put(midtermName, midtermScore);
   }//end setMidtermScore()

   /**
    * setFinalExamScore() accepts a double parameter to update
    * the instance variable finalExamScore.
    * @param finalExamScore Double representing the final exam score.
    */
   public void setFinalExamScore(double finalExamScore) {
      this.finalExamScore = finalExamScore;
   }//end setFinalExamScore()

   /**
    * setTotalScore() accepts a double parameter to update
    * the instance variable totalScore
    * @param totalScore Double representing the calculated
    * total score for the course.
    */
   public void setTotalScore(double totalScore) {
      this.totalScore = totalScore;
   }//end setTotalScore()

   // Logic
   /**
    * demoMode() calls the necessary methods to demonstrate the
    * features of the Gradebook class. CLI begins collecting
    * data from the user via the console. A grade is calculated
    * from the scores and a letter grade is applied. Results
    * are displayed to the console.
    */
   public void demoMode() {

      Scanner scanner = new Scanner(System.in);
      collectName(scanner);
      collectParticipation(scanner);
      collectProjects(scanner);
      collectMidterms(scanner);
      collectFinalExam(scanner);
      double projectAverage = 
         calculateAverage(new ArrayList<Double>(projectScores.values()));
      setProjectAverage(projectAverage);
      double midtermAverage = 
         calculateAverage(new ArrayList<Double>(midtermScores.values()));
      setMidtermAverage(midtermAverage);
      double totalScore = calculateTotalScore();
      setTotalScore(totalScore);
      midtermSwapTest();
      calculateLetterGrade();
      displayResults();   
   }//end demoMode()

   /**
    * calculateTotalScore() retrieves the values stored in
    * participation, project average, midterm average, and
    * Final Exam score and applies the course weights to
    * calculate the course grade total score.
    * @return Double calculated total score for course
    */
   public double calculateTotalScore() {
      double totalScore = 0;
      totalScore += (getParticipationScore() * PARTICIPATION_WEIGHT);
      totalScore += (getProjectAverage() * PROJECT_WEIGHT);
      totalScore += (getMidtermAverage() * MIDTERM_WEIGHT);
      totalScore += (getFinalExamScore() * FINAL_EXAM_WEIGHT);
      return totalScore;
   }//end calculateTotalScore()

   /**
    * calculateTotalScore() is an overloaded fuction that accepts
    * a double modifiedMidterm parameter that represents the 
    * new midterm average after replacing the lowest midterm
    * score with the higher Final Exam Score.
    * @param modifiedMidterm Double new midterm average with swap.
    * @return Double new calculated total score with swap
    */
   public double calculateTotalScore(double modifiedMidterm) {
      double totalScore = 0;
      totalScore += (getParticipationScore() * PARTICIPATION_WEIGHT);
      totalScore += (getProjectAverage() * PROJECT_WEIGHT);
      totalScore += (modifiedMidterm * MIDTERM_WEIGHT);
      totalScore += (getFinalExamScore() * FINAL_EXAM_WEIGHT);
      return totalScore;
   }//end calculateTotalScore()

   /**
    * calculateAverage() accepts an ArrayList<Double> reference
    * representing student scores. The average of the scores is
    * calculated and then returned as a double.
    * @param studentScores ArrayList<Double> reference of scores
    * @return Double calculated average of scores
    */
   public double calculateAverage(ArrayList<Double>studentScores) {
      double calculatedAverage = 0;
      for (double value : studentScores) {
         calculatedAverage += value;
      }//end for()
      calculatedAverage /= studentScores.size();
      return calculatedAverage;
   }//end calculatedAverage()

   /**
    * midtermSwapTest() checks to see if the student's
    * scores are eligible for the midterm swap. If the
    * final score is higher than the lowest midterm and
    * switching the scores would generate a higher letter
    * grade, the swap is performed.
    */
   public void midtermSwapTest() {
      double lowestMidtermScore =
       Collections.min(getMidtermScores().values());
      if (getFinalExamScore() > lowestMidtermScore) {
         double newMidtermAverage = 
            midtermSwapAverage(new ArrayList<Double>(
               getMidtermScores().values()));
         double newTotalScore = calculateTotalScore(newMidtermAverage);
         double totalPointsGained = newTotalScore - getTotalScore();
         double totalPointsNeeded = 10 - (getTotalScore() % 10);
         if (totalPointsGained >= totalPointsNeeded) {
            setMidtermSwapFlag(true);
            setMidtermAverage(newMidtermAverage);
            setTotalScore(newTotalScore);
         }//end if new score should swap
         else {
            setMidtermSwapFlag(false);
         }//end else no swap
      }//end if a midterm lower than final
      else {
         setMidtermSwapFlag(false);
      }//end else midterm not lower
   }//end midtermSwapTest()

   /**
    * midtermSwapAverage() accepts an ArrayList<Double> reference
    * of midterm scores. Locates the smallest value and swaps that
    * value for the Final Exam score. The new average for midterm
    * is calculated and returned.
    * @param studentScores ArrayList<Double> reference of midterm scores
    * @return double new calculated midterm average
    */
   public double midtermSwapAverage(ArrayList<Double> studentScores) {
      double lowestMidtermScore = Collections.min(studentScores);
      studentScores.set(studentScores.indexOf(lowestMidtermScore),
       getFinalExamScore());
      double newMidtermAverage = calculateAverage(studentScores);
      return newMidtermAverage;
   }//end midtermSwapAverage()

   /**
    * calculateLetterGrade() applies the gradeFormat choice formatter
    * to parse the double Total Score into the appropriate 
    * letter grade. Updates the instance variable to store the letter.
    */
   public void calculateLetterGrade() {
      String letterGrade = gradeFormat.format(this.getTotalScore());
      setLetterGrade(letterGrade);
   }//end calculateLetterGrade()

   // Data Collectors
   /**
    * collectName() accepts a Scanner object reference
    * to parse the System.in stream and collect the
    * student name.
    * @param scanner Reference to Scanner object (System.in)
    */
   public void collectName(Scanner scanner) {        // Sanitize Input
      promptName();
      String studentName = scanner.nextLine();
      this.setName(studentName);
   }//end collectName()

   public void collectParticipation(Scanner scanner) { 
      while (true) {
         promptParticipation();
         String userResponse = scanner.nextLine();
         if (userResponse.toLowerCase().trim().equals("yes")) {
            this.setParticipationScore(100);
            break;
         }//end if input yes
         else if (userResponse.toLowerCase().trim().equals("no")) {
            this.setParticipationScore(0);
            break;
         }//end else if input no
         else {
            System.out.println("Expected 'yes' or 'no' as answer");
            continue;
         }//end else wrong input
      }//end while input not collected
   }

   public void collectProjects(Scanner scanner) {
      for (String project : courseProjects) {
         promptTaskScore(project);
         while (!scanner.hasNextDouble()) {
            doubleErrorMessage(project);
            scanner.nextLine();
            promptTaskScore(project);
         }//end while input not double
         double projectScore = scanner.nextDouble();
         this.setProjectScore(project, projectScore);
      }//end for every project
   }

   public void collectMidterms(Scanner scanner) {
      for (String midterm : courseMidterms) {
         promptTaskScore(midterm);
         while (!scanner.hasNextDouble()) {
            doubleErrorMessage(midterm);
            scanner.nextLine();
            promptTaskScore(midterm);
         }//end while input not double
         double midtermScore = scanner.nextDouble();
         this.setMidtermScore(midterm, midtermScore);
      }//end for every midterm
   }

   public void collectFinalExam(Scanner scanner) {
      String finalExam = "Final Exam";
      promptTaskScore(finalExam);
      while (!scanner.hasNextDouble()) {
         doubleErrorMessage(finalExam);
         scanner.nextLine();
         promptTaskScore(finalExam);
      }//end while input not double
      double finalExamScore = scanner.nextDouble();
      this.setFinalExamScore(finalExamScore);
   }

   // Prompts
   /**
    * promptName() utilizes println commands to output a
    * welcome message to the user and the prompt for the 
    * name of a student.
    */
   public void promptName() {
      System.out.println("Welcome to the interactive grade book. " +
       "What is the student name?");
   }//end promptName()

   /**
    * promptParticipation() prints a prompt to console asking the 
    * user to enter a 'yes' or 'no' response for receiving 
    * participation credi
    */
   public void promptParticipation() {
      System.out.println("Did student receive participation credit?" +
       " (yes/no)");
   }//end promptParticipation()

   /**
    * promptTaskScore() accepts a string name of an assigned task
    * for the course and then prompts the user to enter the score
    * for that task.
    * @param taskName String representing the name of a course task
    */
   public void promptTaskScore(String taskName) {
      System.out.printf("Enter grade for %s.\n", taskName);
   }//end promptTaskScore()

   /**
    * promptFinalExam() prints a prompt to console asking the user
    * to enter the score earned by the student on the course 
    * final exam
    */
   public void promptFinalExam() {
      String finalExam = "Final Exam";
      promptTaskScore(finalExam);
   }//end promptFinalExam()

   /**
    * doubleErrorMessage() accepts a string holding the name of a
    * course assignment/task and outputs an error message to the console
    * utilizing that name.<p>This message is called by any method that 
    * uses a scanner object and parses incorrect input when looking for
    * a double
    * @param assignmentName String holding name of an assignment
    */
   public void doubleErrorMessage(String assignmentName) {
      System.out.printf("Error - Expected number score for %s.\n",
       assignmentName);
   }//end doubleErrorMessage()

   /**
    * displayResults() queries the instance variables and outputs
    * the data to the console. Information is formatted to neatly
    * display:<ul> 
    *    <li>Student Name</li>
    *    <li>Participation Score</li>
    *    <li>Project Score Average</li>
    *    <li>Midterm Score Average</li>
    *    <li>Status of Midterm Swap</li>
    *    <li>Final Exam Score</li>
    *    <li>Total Course Core</li>
    *    <li>Final Letter Grade</li></ul>
    */
   public void displayResults() {
      System.out.println("* * * * *");
      System.out.println("Here is the grade summary for " + getName() + ".");
      System.out.println("Participation score: " +
       decimalFormat.format(getParticipationScore()));
      System.out.println("Project score: " +
       decimalFormat.format(getProjectAverage()));
      System.out.println("Midterm score: " +
       decimalFormat.format(getMidtermAverage()));
      if (this.midtermSwapFlag == true) {
         System.out.println("A midterm grade was replaced by" +
          " the final exam grade.");
      }//end if midterm swapped
      System.out.println("Final Exam score: " +
       decimalFormat.format(getFinalExamScore()));
      System.out.println("Total score: " +
       decimalFormat.format(getTotalScore()));
      System.out.println("Final grade: " + getLetterGrade());
      System.out.println("* * * * *");
   }//end displayResults()
}//end Gradebook class