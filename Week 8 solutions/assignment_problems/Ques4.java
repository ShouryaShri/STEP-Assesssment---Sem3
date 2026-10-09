public class Ques4 {

    interface MembershipPlan {
        double calculateFee();
        String getPlanName();
    }

    static class MonthlyPlan implements MembershipPlan {
        public double calculateFee() {
            return 1000;
        }

        public String getPlanName() {
            return "Monthly";
        }
    }

    static class QuarterlyPlan implements MembershipPlan {
        public double calculateFee() {
            return 1000 * 3 * 0.90;
        }

        public String getPlanName() {
            return "Quarterly";
        }
    }

    static class AnnualPlan implements MembershipPlan {
        public double calculateFee() {
            return 1000 * 12 * 0.75;
        }

        public String getPlanName() {
            return "Annual";
        }
    }

    enum Status {
        ACTIVE,
        FROZEN,
        EXPIRED
    }

    static class Member {
        private String name;

        Member(String name) {
            this.name = name;
        }

        String getName() {
            return name;
        }
    }

    static class Membership {
        private Member member;
        private MembershipPlan plan;
        private Status status;

        Membership(
            Member member,
            MembershipPlan plan
        ) {
            this.member = member;
            this.plan = plan;
            this.status = Status.ACTIVE;
        }

        boolean checkIn() {
            return status == Status.ACTIVE;
        }

        boolean freeze() {
            if (status != Status.ACTIVE) {
                return false;
            }

            status = Status.FROZEN;
            return true;
        }

        boolean unfreeze() {
            if (status != Status.FROZEN) {
                return false;
            }

            status = Status.ACTIVE;
            return true;
        }

        void expire() {
            status = Status.EXPIRED;
        }

        Status getStatus() {
            return status;
        }

        double getFee() {
            return plan.calculateFee();
        }

        String getPlanName() {
            return plan.getPlanName();
        }

        Member getMember() {
            return member;
        }
    }

    static Membership buyMembership(
        Member member,
        MembershipPlan plan
    ) {
        Membership membership =
            new Membership(member, plan);

        System.out.println(
            plan.getPlanName() +
            " membership created for " +
            member.getName() + "."
        );

        System.out.printf(
            "Fee: ₹%.2f. Status: %s.%n",
            membership.getFee(),
            membership.getStatus()
        );

        return membership;
    }

    static void checkIn(Membership membership) {
        if (membership.checkIn()) {
            System.out.println(
                membership.getMember().getName() +
                " checked in successfully."
            );
        } else {
            System.out.println(
                "Check-in denied: " +
                membership.getMember().getName() +
                "'s membership is " +
                membership.getStatus() +
                "."
            );
        }
    }

    static void freeze(Membership membership) {
        if (membership.freeze()) {
            System.out.println(
                membership.getMember().getName() +
                "'s membership frozen. Status: " +
                membership.getStatus() +
                "."
            );
        } else {
            System.out.println(
                "Cannot freeze an " +
                membership.getStatus() +
                " membership."
            );
        }
    }

    public static void main(String[] args) {
        Member asha = new Member("Asha");
        Member ravi = new Member("Ravi");

        Membership ashaMembership =
            buyMembership(
                asha,
                new QuarterlyPlan()
            );

        Membership raviMembership =
            buyMembership(
                ravi,
                new MonthlyPlan()
            );

        checkIn(ashaMembership);

        freeze(ashaMembership);

        checkIn(ashaMembership);

        raviMembership.expire();

        System.out.println(
            ravi.getName() +
            "'s membership expired. Status: " +
            raviMembership.getStatus() +
            "."
        );

        freeze(raviMembership);
    }
}