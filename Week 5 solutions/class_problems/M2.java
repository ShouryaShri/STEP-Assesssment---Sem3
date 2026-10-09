public class M2 {

    static String classifyAccess(String fieldModifier, String accessorContext) {
        if (accessorContext.equals("SAME_CLASS")) {
            if (fieldModifier.equals("private") ||
                fieldModifier.equals("default") ||
                fieldModifier.equals("protected") ||
                fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        if (accessorContext.equals("SAME_PACKAGE")) {
            if (fieldModifier.equals("default") ||
                fieldModifier.equals("protected") ||
                fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        if (accessorContext.equals("DIFFERENT_PACKAGE")) {
            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")) {
            if (fieldModifier.equals("protected") ||
                fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        if (accessorContext.equals("SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")) {
            if (fieldModifier.equals("public")) {
                return "ALLOWED";
            }
        }

        return "DENIED";
    }

    public static void main(String[] args) {
        System.out.println(
            classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_OWN_TYPE")
        );

        System.out.println(
            classifyAccess("protected", "SUBCLASS_DIFFERENT_PACKAGE_PARENT_TYPE")
        );
    }
}