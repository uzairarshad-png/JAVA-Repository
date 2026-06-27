/**
 *
 * @author DANISH LAPTOP
 */
import java.util.ArrayList;
import java.util.List;

abstract class User {
 protected String name;
 public User(String name) { this.name = name; }
}
class Question {
 private String text;
 private String answer;
 public Question(String text, String answer) {
 this.text = text;
 this.answer = answer;
 }
 public boolean isCorrect(String input) {
 return answer.equalsIgnoreCase(input);
 }

 public String getText() { return text; }
}

class Quiz {
private String title;
private List<Question> questions = new ArrayList<>();
public Quiz(String title) { this.title = title; }
public void addQuestion(Question q) { questions.add(q); }
public List<Question> getQuestions() { return questions; }

 public int getTotalQuestions() { return questions.size(); }
}
// Instructor Class (Aggregation: Manages Quizzes)
class Instructor extends User {
 public Instructor(String name) { super(name); }
 public Quiz createQuiz(String title) {
 return new Quiz(title);
 }
}
class Student extends User {
 public Student(String name) { super(name); }
 public void takeQuiz(Quiz quiz, String[] studentAnswers) {
 int score = 0;
 List<Question> questions = quiz.getQuestions();
 for (int i = 0; i < questions.size(); i++) {
 if (questions.get(i).isCorrect(studentAnswers[i])) {
 score++;
 }
 }
  System.out.println(name + "'s Grade: " + score + "/" + quiz.getTotalQuestions());
 }
}
public class OnlineExamSystem {
 public static void main(String[] args) {
 // 1. Instructor creates a quiz
 Instructor prof = new Instructor("Dr. Smith");
 Quiz oopQuiz = prof.createQuiz("OOP Fundamentals");
 // 2. Add questions (Composition)
 oopQuiz.addQuestion(new Question("What does OOP stand for?", "Object Oriented Programming"));
 oopQuiz.addQuestion(new Question("Is Java a functional language?", "No"));
 // 3. Student takes the quiz
 Student uzair = new Student("Uzair");
 String[] myAnswers = {"Object Oriented Programming", "No"};

 uzair.takeQuiz(oopQuiz, myAnswers);
 }
}
