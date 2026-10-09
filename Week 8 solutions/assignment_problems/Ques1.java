public class Ques1 {

    interface WashType {
        int getDuration();
        double getCharge();
        String getName();
    }

    static class QuickWash implements WashType {
        public int getDuration() {
            return 30;
        }

        public double getCharge() {
            return 20;
        }

        public String getName() {
            return "Quick";
        }
    }

    static class NormalWash implements WashType {
        public int getDuration() {
            return 45;
        }

        public double getCharge() {
            return 30;
        }

        public String getName() {
            return "Normal";
        }
    }

    static class HeavyWash implements WashType {
        public int getDuration() {
            return 60;
        }

        public double getCharge() {
            return 45;
        }

        public String getName() {
            return "Heavy";
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

    static class WashingMachine {
        private String machineId;
        private boolean busy;

        WashingMachine(String machineId) {
            this.machineId = machineId;
            this.busy = false;
        }

        WashCycle startWash(Student student, WashType washType) {
            if (busy) {
                System.out.println("Machine " + machineId + " is currently busy.");
                return null;
            }

            busy = true;

            WashCycle cycle = new WashCycle(
                student,
                this,
                washType
            );

            System.out.printf(
                "%s wash started on %s for %s (%d min).%n",
                washType.getName(),
                machineId,
                student.getName(),
                washType.getDuration()
            );

            System.out.printf(
                "Charge: ₹%.2f%n",
                washType.getCharge()
            );

            return cycle;
        }

        void completeCycle() {
            if (!busy) {
                return;
            }

            busy = false;

            System.out.println(machineId + " cycle completed.");
            System.out.println(machineId + " is now free.");
        }
    }

    static class WashCycle {
        private Student student;
        private WashingMachine machine;
        private WashType washType;

        WashCycle(
            Student student,
            WashingMachine machine,
            WashType washType
        ) {
            this.student = student;
            this.machine = machine;
            this.washType = washType;
        }

        double getCharge() {
            return washType.getCharge();
        }

        int getDuration() {
            return washType.getDuration();
        }
    }

    public static void main(String[] args) {
        Student asha = new Student("Asha");
        Student ravi = new Student("Ravi");
        Student neha = new Student("Neha");

        WashingMachine m1 = new WashingMachine("M1");
        WashingMachine m2 = new WashingMachine("M2");

        m1.startWash(asha, new QuickWash());
        m1.startWash(ravi, new HeavyWash());
        m2.startWash(ravi, new HeavyWash());

        m1.completeCycle();

        m1.startWash(neha, new NormalWash());
    }
}