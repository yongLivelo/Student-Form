import java.util.Scanner;
import java.util.regex.Pattern;

public class Query {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the password?");
        String password = scanner.nextLine();
        String name = "", section = "", studentNum = "", birthdayStr = "";
        if (password.equals("pogisijulio")) {

            System.out.println("\nKindly Fill out your information :)");

            boolean isNameCorrect = true;
            while (isNameCorrect) {
                name = Print.printScan("What is your full name?", scanner);
                if (Verifier.name(name)) {
                    isNameCorrect = false;
                } else {
                    System.out.println("\nWrong input, please try again");
                }
            }

            boolean isStudentNumberCorrect = true;
            while (isStudentNumberCorrect) {
                studentNum = Print.printScan("What is your student number?", scanner);
                if (Verifier.studentNum(studentNum)) {
                    isStudentNumberCorrect = false;
                } else {
                    System.out.println("\nWrong input, please try again");
                }
            }

            boolean isSectionCorrect = true;
            while (isSectionCorrect) {
                section = Print.printScan("What is your strand and section \nExample: STEM102", scanner);
                if (Verifier.section(section)) {
                    isSectionCorrect = false;
                } else {
                    System.out.println("\nWrong input, please try again");
                }
            }

            boolean isBirthdayCorrect = true;
            while (isBirthdayCorrect) {
                birthdayStr = Print.printScan("When were you born? \nExample:  07/24/2007 (MM/DD/YYYY)", scanner);
                if (Verifier.date(birthdayStr)) {
                    isBirthdayCorrect = false;
                } else {
                    System.out.println("\nWrong input, please try again");
                }
            }

            Date birthday = new Date(birthdayStr);

            boolean isCorrect = true;
            while (isCorrect) {
                String toPrint = Print.printScan("Would you like to print a list of your information? (y/n)", scanner);
                if (toPrint.equals("y")) {
                    Print.printInformation(name, studentNum, section, birthday);
                    isCorrect = false;
                } else if (toPrint.equals("n")) {
                    System.out.println("Thank you for filling out this form!");
                    isCorrect = false;
                } else {
                    System.out.println("Wrong input, please try again");
                    System.out.println("\nInvalid input, please try again");
                }

            }
        } else {
            System.out.println("Wrong Password, Try again :)");
        }
        scanner.close();
    }

}

class Date {
    public static final int currentYear = 2023;

    int day, month, year;

    String[] months = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
            "October",
            "November", "December" };

    public Date(String date) {
        String[] dateArr = date.split("/");
        month = Integer.parseInt(dateArr[0]);
        day = Integer.parseInt(dateArr[1]);
        year = Integer.parseInt(dateArr[2]);
    }

    public int getAge() {
        return currentYear - this.year;
    }

    public String getBdayText() {
        return months[this.month - 1] + " " + this.day + ", " + this.year;
    }

}

class Print {
    public static void printInformation(String name, String studentNum, String Section, Date birthday) {
        String line = ("--------------------");
        System.out.println("\n\n\n\n\n\nUser Information");
        System.out.println(line);
        System.out.println("Name: \t\t" + name);
        System.out.println("Student Number: " + studentNum);
        System.out.println("Section: \t" + Section);
        System.out.println("Birthday: \t" + birthday.getBdayText());
        System.out.println("age: \t\t" + birthday.getAge());
        System.out.println(line);
    }

    public static String printScan(String print, Scanner scanner) {

        System.out.println("\n" + print);
        String scan = scanner.nextLine();
        return scan;

    }

}

class Verifier {
    public static boolean name(String name) {
        boolean matchFound = Pattern.matches("[a-zA-Z]+", name);
        if (matchFound) {
            return true;
        } else {
            return false;
        }
    }

    public static boolean studentNum(String studentNum) {
        boolean matchFound = Pattern.matches("[0-9]+", studentNum);
        if ((studentNum.length() == 11) && matchFound) {
            return true;
        }
        return false;
    }

    public static boolean section(String section) {
        String[] sections = { "STEM", "MAWD", "CCTECH", "HUMMS", "ABM", "CUARTS",
                "TOPPERS" };
        String sectionInput = section.substring(0, section.length() - 3);
        for (int i = 0; i < sections.length; i++) {
            if (sections[i].equals(sectionInput)) {
                return true;
            }
        }
        return false;
    }

    public static boolean date(String date) {
        try {
            if (date.length() == 10) {
                String[] dateArr = date.split("/");
                int month = Integer.parseInt(dateArr[0]);
                int day = Integer.parseInt(dateArr[1]);
                int year = Integer.parseInt(dateArr[2]);
                if (month <= 12 && month > 0 && day <= 31 && day > 0 && year < Date.currentYear)
                    return true;
            }
        } catch (Exception e) {
            return false;
        }
        return false;

    }
}
