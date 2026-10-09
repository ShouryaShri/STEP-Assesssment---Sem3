import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract class Question {
    protected String questionText;
    protected int points;

    public Question(String questionText, int points) {
        this.questionText = questionText;
        this.points = points;
    }

    public abstract boolean evaluate(String answer);

    public int getPoints() {
        return points;
    }
}

class MultipleChoiceQuestion extends Question {
    private String correctOption;

    public MultipleChoiceQuestion(
        String questionText,
        int points,
        String correctOption
    ) {
        super(questionText, points);
        this.correctOption = correctOption;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctOption.equalsIgnoreCase(answer);
    }
}

class TrueFalseQuestion extends Question {
    private boolean correctAnswer;

    public TrueFalseQuestion(
        String questionText,
        int points,
        boolean correctAnswer
    ) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return Boolean.parseBoolean(answer) == correctAnswer;
    }
}

class ShortAnswerQuestion extends Question {
    private String correctAnswer;

    public ShortAnswerQuestion(
        String questionText,
        int points,
        String correctAnswer
    ) {
        super(questionText, points);
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean evaluate(String answer) {
        return correctAnswer.equalsIgnoreCase(answer);
    }
}

class Student {
    private String name;

    public Student(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

class Examination {
    private String name;
    private List<Question> questions;

    public Examination(String name) {
        this.name = name;
        this.questions = new ArrayList<>();
    }

    public void addQuestion(Question question) {
        questions.add(question);
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public String getName() {
        return name;
    }
}

class Attempt {
    private Student student;
    private Examination examination;
    private Map<Integer, String> answers;
    private boolean submitted;

    public Attempt(Student student, Examination examination) {
        this.student = student;
        this.examination = examination;
        this.answers = new HashMap<>();
        this.submitted = false;
    }

    public boolean recordAnswer(int questionNumber, String answer) {
        if (submitted) {
            return false;
        }

        answers.put(questionNumber, answer);
        return true;
    }

    public void submit() {
        submitted = true;
    }

    public boolean isSubmitted() {
        return submitted;
    }

    public void showResult() {
        int total = 0;
        int score = 0;

        List<Question> questions = examination.getQuestions();

        for (int i = 0; i < questions.size(); i++) {
            Question question = questions.get(i);
            total += question.getPoints();

            String answer = answers.get(i);

            if (answer != null && question.evaluate(answer)) {
                score += question.getPoints();

                System.out.println(
                    "Question " + (i + 1) +
                    ": Correct (" +
                    question.getPoints() +
                    " points)"
                );
            } else {
                System.out.println(
                    "Question " + (i + 1) +
                    ": Incorrect (0 points)"
                );
            }
        }

        System.out.println(
            "Total score: " + score + "/" + total
        );
    }
}

public class M3 {
    public static void main(String[] args) {
        Student student = new Student("Student 1");

        Examination exam = new Examination("Exam A");

        exam.addQuestion(
            new MultipleChoiceQuestion(
                "Which is a programming language?",
                5,
                "C"
            )
        );

        exam.addQuestion(
            new TrueFalseQuestion(
                "Java is a programming language.",
                5,
                false
            )
        );

        System.out.println(
            exam.getName() +
            " started by " +
            student.getName() + "."
        );

        Attempt attempt = new Attempt(student, exam);

        attempt.recordAnswer(0, "C");
        System.out.println("Answer recorded for Question 1.");

        attempt.recordAnswer(1, "true");
        System.out.println("Answer recorded for Question 2.");

        attempt.submit();

        System.out.println(
            exam.getName() +
            " submitted by " +
            student.getName() + "."
        );

        System.out.println("Result:");
        attempt.showResult();

        if (!attempt.recordAnswer(0, "A")) {
            System.out.println(
                "Cannot change answers for a submitted examination."
            );
        }
    }
}