import java.util.*;
import java.text.*;


public class Gradebook {

   private static final double PARTICIPATION_WEIGHT = 0.10;
   private static final double PROJECT_WEIGHT = 0.35;
   private static final double MIDTERM_WEIGHT = 0.30;
   private static final double FINAL_EXAM_WEIGHT = 0.25;
   //private static final String COURSE_NAME = "COMP 163";
   

   private static final String[] courseProjects = new String [] {"Project 1", "Project 2"};
   private static final String[] courseMidterms = new String[] {"Midterm 1", "Midterm 2", "Midterm 3"};


   private String studentName, letterGrade;
   private boolean midtermSwapFlag;
   private double participationScore, projectAverage, midtermAverage;
   private double finalExamScore, totalScore;
   private HashMap<String, Double> projectScores, midtermScores;
   

   private double[] gradeThresholds = new double[] {0, 60, 70, 80, 90};
   private String[] letterGrades = new String[] {"F", "D", "C", "B", "A"};
   ChoiceFormat gradeFormat = new ChoiceFormat(gradeThresholds, letterGrades);
   DecimalFormat decimalFormat = new DecimalFormat("#.##");


   public static void main(String[] args) {
      Gradebook newDemo = new Gradebook();
      newDemo.demoMode();
      
   }

   public Gradebook() {
      studentName = "";
      letterGrade = "";
      midtermSwapFlag = false;
      participationScore = projectAverage = midtermAverage = finalExamScore =
       totalScore = 0;
      projectScores = new HashMap<String, Double>();
      midtermScores = new HashMap<String, Double>();
   }


   // getters
   public boolean getMidtermSwapFlag() {
      return this.midtermSwapFlag;
   }
   public String getLetterGrade() {
      return this.letterGrade;
   }

   public String getName() {
      return this.studentName;
   }

   public double getParticipationScore() {
      return this.participationScore;
   }

   public double getProjectAverage() {
      return this.projectAverage;
   }

   public HashMap<String, Double> getProjectScores() {
      return this.projectScores;
   }

   public double getProjectScore(String projectName) {
      return projectScores.get(projectName);
   }

   public double getMidtermAverage() {
      return this.midtermAverage;
   }

   public HashMap<String, Double> getMidtermScores() {
      return this.midtermScores;
   }

   public double getMidtermScore(String midtermName) {
      return midtermScores.get(midtermName);
   }

   public double getFinalExamScore() {
      return this.finalExamScore;
   }

   public double getTotalScore() {
      return this.totalScore;
   }


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
      double lowestMidtermScore = Collections.min(getMidtermScores().values());
      if (getFinalExamScore() > lowestMidtermScore) {
         double newMidtermAverage = 
            midtermSwapAverage(new ArrayList<Double>(getMidtermScores().values()));
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
      studentScores.set(studentScores.indexOf(lowestMidtermScore), getFinalExamScore());
      double newMidtermAverage = calculateAverage(studentScores);
      return newMidtermAverage;
   }

   public void calculateLetterGrade() {
      String letterGrade;
      letterGrade = gradeFormat.format(this.getTotalScore());
      setLetterGrade(letterGrade);
   }

   // Data Collectors
   public void collectName(Scanner scanner) {        // Sanitize Input
      promptName();
      this.setName(scanner.nextLine());
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
      System.out.println("Did student receive participation credit? (yes/no)");
   }

   public void promptTaskScore(String taskName) {
      System.out.printf("Enter grade for %s.\n", taskName);
   }

   public void promptFinalExam() {
      String finalExam = "Final Exam";
      promptTaskScore(finalExam);
   }
   public void doubleErrorMessage(String assignmentName) {
      System.out.printf("Error - Expected number score for %s.\n", assignmentName);
   }
   public void displayResults() {
      System.out.println("* * * * *");
      System.out.println("Here is the grade summary for " + getName() + ".");
      System.out.println("Participation score: " + decimalFormat.format(getParticipationScore()));
      System.out.println("Project score: " + decimalFormat.format(getProjectAverage()));
      System.out.println("Midterm score: " + decimalFormat.format(getMidtermAverage()));
      if (this.midtermSwapFlag == true) {
         System.out.println("A midterm grade was replaced by the final exam grade.");
      }
      System.out.println("Final Exam score: " + decimalFormat.format(getFinalExamScore()));
      System.out.println("Total score: " + decimalFormat.format(getTotalScore()));
      System.out.println("Final grade: " + getLetterGrade());
      System.out.println("* * * * *");
   }
}