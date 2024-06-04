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
   }

   public double calculateTotalScore() {
      double totalScore = 0;
      totalScore += (getParticipationScore() * PARTICIPATION_WEIGHT);
      totalScore += (getProjectAverage() * PROJECT_WEIGHT);
      totalScore += (getMidtermAverage() * MIDTERM_WEIGHT);
      totalScore += (getFinalExamScore() * FINAL_EXAM_WEIGHT);
      return totalScore;
   }

   public double calculateTotalScore(double modifiedMidterm) {
      double totalScore = 0;
      totalScore += (getParticipationScore() * PARTICIPATION_WEIGHT);
      totalScore += (getProjectAverage() * PROJECT_WEIGHT);
      totalScore += (modifiedMidterm * MIDTERM_WEIGHT);
      totalScore += (getFinalExamScore() * FINAL_EXAM_WEIGHT);
      return totalScore;
   }

   public double calculateAverage(ArrayList<Double>studentScores) {
      double calculatedAverage = 0;
      for (double value : studentScores) {
         calculatedAverage += value;
      }
      calculatedAverage /= studentScores.size();
      return calculatedAverage;
   }

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
         }
         else {
            setMidtermSwapFlag(false);
         }

      }
      else {
         setMidtermSwapFlag(false);
      }
   }

   public double midtermSwapAverage(ArrayList<Double> studentScores) {
      double lowestMidtermScore = Collections.min(studentScores);
      studentScores.set(studentScores.indexOf(lowestMidtermScore),
       getFinalExamScore());
      double newMidtermAverage = calculateAverage(studentScores);
      return newMidtermAverage;
   }

   public void calculateLetterGrade() {
      String letterGrade = gradeFormat.format(this.getTotalScore());
      setLetterGrade(letterGrade);
   }

   // Data Collectors
   public void collectName(Scanner scanner) {        // Sanitize Input
      promptName();
      String studentName = scanner.nextLine();
      this.setName(studentName);
   }

   public void collectParticipation(Scanner scanner) { 
      while (true) {
         promptParticipation();
         String userResponse = scanner.nextLine();
         if (userResponse.toLowerCase().trim().equals("yes")) {
            this.setParticipationScore(100);
            break;
         }
         else if (userResponse.toLowerCase().trim().equals("no")) {
            this.setParticipationScore(0);
            break;
         }
         else {
            System.out.println("Expected 'yes' or 'no' as answer");
            continue;
         }
      }
   }

   public void collectProjects(Scanner scanner) {
      for (String project : courseProjects) {
         promptTaskScore(project);
         while (!scanner.hasNextDouble()) {
            doubleErrorMessage(project);
            scanner.nextLine();
            promptTaskScore(project);
         }
         double projectScore = scanner.nextDouble();
         this.setProjectScore(project, projectScore);
      }
   }

   public void collectMidterms(Scanner scanner) {
      for (String midterm : courseMidterms) {
         promptTaskScore(midterm);
         while (!scanner.hasNextDouble()) {
            doubleErrorMessage(midterm);
            scanner.nextLine();
            promptTaskScore(midterm);
         }
         double midtermScore = scanner.nextDouble();
         this.setMidtermScore(midterm, midtermScore);
      }
   }

   public void collectFinalExam(Scanner scanner) {
      String finalExam = "Final Exam";
      promptTaskScore(finalExam);
      while (!scanner.hasNextDouble()) {
         doubleErrorMessage(finalExam);
         scanner.nextLine();
         promptTaskScore(finalExam);
      }
      double finalExamScore = scanner.nextDouble();
      this.setFinalExamScore(finalExamScore);
   }

   // Prompts
   public void promptName() {
      System.out.println("Welcome to the interactive grade book. " +
       "What is the student name?");
   }

   public void promptParticipation() {
      System.out.println("Did student receive participation credit?" +
       " (yes/no)");
   }

   public void promptTaskScore(String taskName) {
      System.out.printf("Enter grade for %s.\n", taskName);
   }

   public void promptFinalExam() {
      String finalExam = "Final Exam";
      promptTaskScore(finalExam);
   }

   public void doubleErrorMessage(String assignmentName) {
      System.out.printf("Error - Expected number score for %s.\n",
       assignmentName);
   }

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
      }
      System.out.println("Final Exam score: " +
       decimalFormat.format(getFinalExamScore()));
      System.out.println("Total score: " +
       decimalFormat.format(getTotalScore()));
      System.out.println("Final grade: " + getLetterGrade());
      System.out.println("* * * * *");
   }
}//end Gradebook class