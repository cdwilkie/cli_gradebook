CLI Interactive Gradebook

Prompts user for information than displays the grade.

Default Grade consists of:
    - Participation 10%
    - Projects      35%
    - Midterms      30%
    - Final Exam    25%

Total_Grade = (0.1 x part.) + (0.35 x proj.) + (0.3 x Midt.) + (.25 x Final.)
If (finalExam > midterm) && (TotalGrade[with Midterm replaced] > TotalGrade) = Replace Midterm with Final.

Grade Scale:
    A   90% - 100%
    B   80% - 90%
    C   70% - 80%
    D   60% - 70%
    F   60% > 0%

    * Award higher grade if on boundary. Round to 2 decimal places using DecimalFormat

Sample Interaction (* = Prompt, @ = Response)
    * Welcome to the interactive grade book. What is the student name?
    @ String response
    * Did student receive participation credit? (yes/no)
    @ String response
    * Enter grade for Project 1.
    @ Integer response
    * Enter grade for Project 2.
    @ Integer response
    * Enter grade for Midterm 1.
    @ Integer response
    * Enter grade for Midterm 2.
    @ Integer Response
    * Enter grade for Midterm 3.
    @ Integer Response
    * Enter grade for Final Exam.
    @ Integer Response
    * * * * * * ( 5 asterisks)
    * Here is the grade summary for <Student Name>.
    * Participation score: Double (Full or none)
    * Project score: Double
    * Midterm score: Double
    * A midterm grade was replaced by the final exam grade. (Conditional Prompt)
    * Final Exam score: Double
    * Total score: Double
    * Final grade: Double
    * * * * * * (5 Asterisks)
