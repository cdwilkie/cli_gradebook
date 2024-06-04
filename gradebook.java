import java.util.*;
import java.text.*;

/**
 * Gradebook class models the behavior of a
 * course gradebook. Tasks (Projects, Midterms,etc.)
 * are assigned a weight so that a final grade may be
 * tallied. Methods provided to output results to console.<p>
 * Contains a main() driver to begin a demo of the class
 * by constructing a record and collecting data. Instantiated
 * with default constructor. Demonstration activated by
 * demoMode() method.
 */
public class Gradebook {

   // Course Weight for Tasks
   private static final double PARTICIPATION_WEIGHT = 0.10;
   private static final double PROJECT_WEIGHT = 0.35;
   private static final double MIDTERM_WEIGHT = 0.30;
   private static final double FINAL_EXAM_WEIGHT = 0.25;
   //private static final String COURSE_NAME = "COMP 163";
   
   // List of Scored Course Tasks
   private static final String[] courseProjects = 
      new String[] {"Project 1", "Project 2"};
   private static final String[] courseMidterms = 
      new String[] {"Midterm 1", "Midterm 2", "Midterm 3"};

   // Instance Variables
   private String studentName, letterGrade;
   private boolean midtermSwapFlag;             //Lowest Midterm Grade Swapped
   private double participationScore, projectAverage, midtermAverage;
   private double finalExamScore, totalScore;
   private HashMap<String, Double> projectScores, midtermScores;
   
   // Formats numerical score to letter grade
   private double[] gradeThresholds = new double[] {0, 60, 70, 80, 90};
   private String[] letterGrades = new String[] {"F", "D", "C", "B", "A"};
   ChoiceFormat gradeFormat = new ChoiceFormat(gradeThresholds, letterGrades);
   DecimalFormat decimalFormat = new DecimalFormat("#.##");

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

   // Constructors
   /**
    * Gradebook() constructor initializes all instance variables
    * to default/empty/null/false values.
    */
   public Gradebook() {
      studentName = "";
      letterGrade = "";
      midtermSwapFlag = false;
      participationScore = projectAverage = midtermAverage =
       finalExamScore = totalScore = 0;
      projectScores = new HashMap<String, Double>();
      midtermScores = new HashMap<String, Double>();
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
   public void setMidtermSwapFlag(boolean midtermSwapFlag) {
      this.midtermSwapFlag = midtermSwapFlag;
   }
   public void setLetterGrade(String letterGrade) {
      this.letterGrade = letterGrade;
   }

   public void setName(String studentName) {
      this.studentName = studentName;
   }

   public void setParticipationScore(double participationScore) {
      this.participationScore = participationScore;
   }

   public void setProjectAverage(double projectAverage) {
      this.projectAverage = projectAverage;
   }

   public void setProjectScores(HashMap<String, Double> projectScores) {
      this.projectScores = projectScores;
   }

   public void setProjectScore(String projectName, double projectScore) {
      projectScores.put(projectName, projectScore);
   }

   public void setMidtermAverage(double midtermAverage) {
      this.midtermAverage = midtermAverage;
   }

   public void setMidtermScores(HashMap<String, Double> midtermScores) {
      this.midtermScores = midtermScores;
   }

   public void setMidtermScore(String midtermName, double midtermScore) {
      midtermScores.put(midtermName, midtermScore);
   }

   public void setFinalExamScore(double finalExamScore) {
      this.finalExamScore = finalExamScore;
   }

   public void setTotalScore(double totalScore) {
      this.totalScore = totalScore;
   }

   // Logic
   // Consider methods that accept arrays/collections of doubles
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
}