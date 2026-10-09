public class Ques2 {

    enum SubmissionStatus {
        SUBMITTED,
        GRADED
    }

    abstract static class Assignment {
        protected String title;
        protected int maxMarks;
        protected int dueDay;

        Assignment(
            String title,
            int maxMarks,
            int dueDay
        ) {
            this.title = title;
            this.maxMarks = maxMarks;
            this.dueDay = dueDay;
        }

        abstract double applyLatePenalty(
            double marks,
            int lateDays
        );

        String getTitle() {
            return title;
        }

        int getMaxMarks() {
            return maxMarks;
        }

        int getDueDay() {
            return dueDay;
        }
    }

    static class CodingAssignment extends Assignment {
        CodingAssignment(
            String title,
            int maxMarks,
            int dueDay
        ) {
            super(title, maxMarks, dueDay);
        }

        @Override
        double applyLatePenalty(
            double marks,
            int lateDays
        ) {
            return marks * Math.pow(0.90, lateDays);
        }
    }

    static class WrittenAssignment extends Assignment {
        WrittenAssignment(
            String title,
            int maxMarks,
            int dueDay
        ) {
            super(title, maxMarks, dueDay);
        }

        @Override
        double applyLatePenalty(
            double marks,
            int lateDays
        ) {
            return marks * Math.pow(0.80, lateDays);
        }
    }

    static class Student {
        private String name;

        Student(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Submission {
        private Student student;
        private Assignment assignment;
        private int submissionDay;
        private SubmissionStatus status;
        private double finalMarks;

        Submission(
            Student student,
            Assignment assignment,
            int submissionDay
        ) {
            this.student = student;
            this.assignment = assignment;
            this.submissionDay = submissionDay;
            this.status = SubmissionStatus.SUBMITTED;
        }

        int getLateDays() {
            return Math.max(
                0,
                submissionDay - assignment.getDueDay()
            );
        }

        boolean grade(double awardedMarks) {
            if (status != SubmissionStatus.SUBMITTED) {
                return false;
            }

            finalMarks = assignment.applyLatePenalty(
                awardedMarks,
                getLateDays()
            );

            status = SubmissionStatus.GRADED;
            return true;
        }

        boolean resubmit(int newDay) {
            if (status == SubmissionStatus.GRADED) {
                return false;
            }

            submissionDay = newDay;
            return true;
        }

        double getFinalMarks() {
            return finalMarks;
        }

        SubmissionStatus getStatus() {
            return status;
        }

        Student getStudent() {
            return student;
        }

        Assignment getAssignment() {
            return assignment;
        }
    }

    public static void main(String[] args) {
        Assignment coding = new CodingAssignment(
            "Linked List Lab",
            50,
            10
        );

        Assignment written = new WrittenAssignment(
            "Design Essay",
            50,
            12
        );

        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");

        Submission ashaSubmission =
            new Submission(asha, coding, 10);

        System.out.println(
            "Asha's submission for '" +
            coding.getTitle() +
            "' received (on time). Status: " +
            ashaSubmission.getStatus() + "."
        );

        Submission raviSubmission =
            new Submission(ravi, written, 14);

        System.out.println(
            "Ravi's submission for '" +
            written.getTitle() +
            "' received (" +
            raviSubmission.getLateDays() +
            " days late). Status: " +
            raviSubmission.getStatus() + "."
        );

        ashaSubmission.grade(45);

        System.out.println(
            "Asha graded: " +
            String.format("%.0f", ashaSubmission.getFinalMarks()) +
            "/" +
            coding.getMaxMarks() +
            ". Status: " +
            ashaSubmission.getStatus() + "."
        );

        raviSubmission.grade(40);

        System.out.println(
            "Ravi graded: " +
            String.format("%.0f", raviSubmission.getFinalMarks()) +
            "/" +
            written.getMaxMarks() +
            " after " +
            (raviSubmission.getLateDays() * 20) +
            "% late penalty. Status: " +
            raviSubmission.getStatus() + "."
        );

        if (!ashaSubmission.resubmit(11)) {
            System.out.println(
                "Cannot resubmit: '" +
                coding.getTitle() +
                "' has already been graded."
            );
        }
    }
}