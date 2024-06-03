import java.util.*;
import java.text.*;


public class Gradebook {
  
   private static double PARTICIPATION_WEIGHT = 0.10;
   private static double PROJECT_WEIGHT = 0.35;
   private static double MIDTERM_WEIGHT = 0.30;
   private static double FINAL_EXAM_WEIGHT = 0.25;

   private double[] gradeLimits = new double [] {0, 60, 70, 80, 90};
   private String[] gradeLetters = new String [] {"F", "D", "C", "B", "A"};
   ChoiceFormat gradeFormat = new ChoiceFormat(gradeLimits, gradeLetters);


   private Record recordEntry;

   
   //Total Grade Letter


   public static void main(String[] args) {
      Gradebook gb = new Gradebook();
      
   }

   public Gradebook () {
      recordEntry = new Record();
      recordEntry.collectData();
      totalScore(recordEntry);
   }

   public double participationScore(Record record) {
      return (record.getParticipationScore() * PARTICIPATION_WEIGHT);
   }

   public double projectScore(Record record) {
      double projectTotal = 0;
      for (Double score : record.getProjScores().values()) {
         projectTotal += score;
      }
      projectTotal /= record.getProjScores().values().size();
      return (projectTotal * PROJECT_WEIGHT);
   }

   public double midtermScore(Record record) {
      double midtermTotal = 0;
      for (Double score : record.getMidtermScores().values()) {
         midtermTotal += score;
      }
      midtermTotal /= record.getMidtermScores().values().size();
      return (midtermTotal * MIDTERM_WEIGHT);

   }

   public double finalExamScore(Record record) {
      return (record.getFinalExamScore() * FINAL_EXAM_WEIGHT);
   }

   public void totalScore(Record record) {
      double partScore = participationScore(record);
      double projScore = projectScore(record);
      double midScore = midtermScore(record);
      double feScore = finalExamScore(record);
      double totalScore = (partScore + projScore + midScore + feScore);
      record.setTotalScore(totalScore);
      record.setLetterGrade(gradeFormat.format(totalScore));
      System.out.printf("\n%5.2f %5s\n\n",totalScore, record.getLetterGrade());


   }


   public void midtermSwap(Record record) {
      double midtermTotal = 0;
      for (Double score : record.getMidtermScores().values()) {
         midtermTotal += score;
      }
      if (record.getFinalExamScore() > Collections.min(record.getMidtermScores().values())) {
         midtermTotal -= Collections.min(record.getMidtermScores().values());
         midtermTotal += record.getFinalExamScore();
      }
      midtermTotal /= record.getMidtermScores().values().size();
      midtermTotal *= MIDTERM_WEIGHT;
   }

}


class Record {
   private static String [] courseProjects = new String[] {"Project 1", "Project 2"};
   private static String [] courseMidterms = new String[] {"Midterm 1", "Midterm 2"};
   private String studentName;
   private String letterGrade;
   //private boolean participationScore;
   private HashMap<String, Double> projectScores, midtermScores;
   //private HashMap<String, Double> midtermScores;
   private double participationScore, finalExamScore, totalScore;

   public Record() {
      studentName = "";
      projectScores = new HashMap<String, Double>();
      midtermScores = new HashMap<String, Double>();
      participationScore = finalExamScore = totalScore = 0;
   }

   // getters

   public double getParticipationScore() {
      return this.participationScore;
   }
      
   
   public HashMap<String, Double> getProjScores() {
      return this.projectScores;
   }

   public HashMap<String, Double> getMidtermScores() {
      return this.midtermScores;
   }

   public double getFinalExamScore() {
      return this.finalExamScore;
   }

   public String getLetterGrade() {
      return this.letterGrade;
   }


   // setters
   public void setLetterGrade(String grade) {
      this.letterGrade = grade;
   }

   public void setTotalScore(double score) {
      this.totalScore = score;
   }

   
   
   
   
   // Prompts
   public void collectData() {
      try (Scanner scanner = new Scanner(System.in)) {
         namePrompt(scanner);
         participationPrompt(scanner);
         projectPrompt(scanner);
         midtermPrompt(scanner);
         finalExamPrompt(scanner);
      }
      catch (Exception e) {
         System.out.println(e.getMessage());
      }
   }

   public void namePrompt(Scanner scanner) throws Exception {
      System.out.println("Welcome to the interactive gradebook. What is the student name?");
      this.studentName = scanner.nextLine();
   }
   
   public void participationPrompt(Scanner scanner) throws Exception {
      System.out.println("Did student receive participation credit? (yes/no)");
      String userResponse = scanner.nextLine().toLowerCase().trim();
      if (userResponse.equals("yes")) {
         this.participationScore = 100;
      }
      else if (userResponse.equals("no")) {
         this.participationScore = 0;
      }
      else {
         throw new IllegalArgumentException("Only answers of 'yes' or 'no' accpeted for participation");
      }
   }

   public void projectPrompt(Scanner scanner) throws Exception {
      for (String project : courseProjects) {
         System.out.printf("Enter grade for %s.\n", project);
         this.projectScores.put(project,scanner.nextDouble());
      }
   }

   public void midtermPrompt(Scanner scanner) throws Exception {
      for (String midterm : courseMidterms) {
         System.out.printf("Enter grade for %s.\n", midterm);
         this.midtermScores.put(midterm, scanner.nextDouble());
      }
   }

   public void finalExamPrompt(Scanner scanner) throws Exception {
      System.out.println("Enter grade for Final Exam.");
      this.finalExamScore = scanner.nextDouble();
   }

}


