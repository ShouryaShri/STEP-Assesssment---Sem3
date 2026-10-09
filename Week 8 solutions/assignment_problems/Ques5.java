import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Ques5 {

    interface NotificationChannel {
        void send(Student student, Notice notice);
    }

    static class EmailChannel implements NotificationChannel {
        public void send(
            Student student,
            Notice notice
        ) {
            System.out.println(
                "[Email → " +
                student.getName() +
                "] " +
                notice.getTitle()
            );
        }
    }

    static class SmsChannel implements NotificationChannel {
        public void send(
            Student student,
            Notice notice
        ) {
            System.out.println(
                "[SMS → " +
                student.getName() +
                "] " +
                notice.getTitle()
            );
        }
    }

    static class AppChannel implements NotificationChannel {
        public void send(
            Student student,
            Notice notice
        ) {
            System.out.println(
                "[App → " +
                student.getName() +
                "] " +
                notice.getTitle()
            );
        }
    }

    static class Student {
        private String name;
        private String department;
        private List<NotificationChannel> channels;

        Student(
            String name,
            String department
        ) {
            this.name = name;
            this.department = department;
            this.channels = new ArrayList<>();
        }

        void addChannel(NotificationChannel channel) {
            channels.add(channel);
        }

        String getName() {
            return name;
        }

        String getDepartment() {
            return department;
        }

        List<NotificationChannel> getChannels() {
            return channels;
        }
    }

    static class Notice {
        private String title;
        private Set<String> departments;

        Notice(
            String title,
            Set<String> departments
        ) {
            if (title == null ||
                title.trim().isEmpty()) {
                throw new IllegalArgumentException(
                    "A title is required"
                );
            }

            if (departments == null ||
                departments.isEmpty()) {
                throw new IllegalArgumentException(
                    "At least one target department is required"
                );
            }

            this.title = title;
            this.departments =
                new HashSet<>(departments);
        }

        String getTitle() {
            return title;
        }

        boolean targets(String department) {
            return departments.contains(department);
        }

        Set<String> getDepartments() {
            return departments;
        }
    }

    static class NoticeBoard {
        private List<Student> students;

        NoticeBoard() {
            students = new ArrayList<>();
        }

        void addStudent(Student student) {
            students.add(student);
        }

        void postNotice(
            String title,
            Set<String> departments
        ) {
            Notice notice;

            try {
                notice = new Notice(
                    title,
                    departments
                );
            } catch (IllegalArgumentException e) {
                System.out.println(
                    "Cannot post notice: " +
                    e.getMessage() +
                    "."
                );
                return;
            }

            System.out.print(
                "Notice '" +
                notice.getTitle() +
                "' posted to "
            );

            int count = 0;

            for (String department :
                 notice.getDepartments()) {

                System.out.print(department);

                if (count <
                    notice.getDepartments().size() - 1) {
                    System.out.print(", ");
                }

                count++;
            }

            System.out.println(".");

            for (Student student : students) {
                if (notice.targets(
                    student.getDepartment()
                )) {
                    for (NotificationChannel channel :
                         student.getChannels()) {

                        channel.send(
                            student,
                            notice
                        );
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        NoticeBoard board = new NoticeBoard();

        Student asha =
            new Student("Asha", "CSE");

        asha.addChannel(
            new EmailChannel()
        );

        asha.addChannel(
            new AppChannel()
        );

        Student ravi =
            new Student("Ravi", "ECE");

        ravi.addChannel(
            new SmsChannel()
        );

        board.addStudent(asha);
        board.addStudent(ravi);

        Set<String> cse =
            new HashSet<>();

        cse.add("CSE");

        board.postNotice(
            "Lab Closed Tomorrow",
            cse
        );

        Set<String> cseEce =
            new HashSet<>();

        cseEce.add("CSE");
        cseEce.add("ECE");

        board.postNotice(
            "Fee Deadline Extended",
            cseEce
        );

        Set<String> empty =
            new HashSet<>();

        board.postNotice(
            "Sports Day",
            empty
        );
    }
}