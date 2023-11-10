import java.util.Scanner;
import java.util.regex.Pattern;

public class Query {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String name = "", section = "", studentNum = "", birthdayStr = "";

        System.out.println("What is the password?");
        String password = scanner.nextLine();

        if (password.equals("misskonasya")) {

            System.out.println("\nKindly Fill out your information :)");

            name = Print.printScan("What is your full name?", scanner, "name");
            studentNum = Print.printScan("What is your student number?", scanner, "studentNum");
            section = Print.printScan("What is your strand and section \nExample: STEM102", scanner, "section");
            birthdayStr = Print.printScan("When were you born? \nExample:  07/24/2007 (MM/DD/YYYY)", scanner,
                    "date");
            Date birthday = new Date(birthdayStr);

            while (true) {
                String toPrint = Print.printScan("\nWould you like to print a list of your information? (y/n)", scanner,
                        "");
                if (toPrint.equals("y")) {
                    Print.printInformation(name, studentNum, section, birthday);
                    break;
                } else if (toPrint.equals("n")) {
                    System.out.println("Thank you for filling out this form!");
                    break;
                } else {
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
    String[] months = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October",
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

    public static String printScan(String print, Scanner scanner, String type) {
        boolean continueLoop = true;

        while (true) {
            System.out.println("\n" + print);
            String input = scanner.nextLine();
            switch (type) {
                case "name":
                    continueLoop = Verifier.name(input);
                    break;
                case "studentNum":
                    continueLoop = Verifier.studentNum(input);
                    break;
                case "section":
                    continueLoop = Verifier.section(input);
                    break;
                case "date":
                    continueLoop = Verifier.date(input);
                    break;
                default:
                    return input;
            }

            if (continueLoop) {
                return input;
            } else {
                System.out.println("\nWrong input, please try again");
            }

        }

    }
}

class Verifier {
    public static boolean name(String name) {
        boolean matchFound = Pattern.matches("[a-zA-Z\s]+", name);
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

        try {
            String[] sections = { "STEM", "MAWD", "CCTECH", "HUMMS", "ABM", "CUARTS",
                    "TOPPERS", "GAS" };
            String sectionInput = section.substring(0, section.length() - 3);
            for (int i = 0; i < sections.length; i++) {
                if (sections[i].equals(sectionInput)) {
                    return true;
                }
            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }

    public static boolean date(String date) {
        try {
            if (date.length() == 10) {
                String[] dateArr = date.split("/");
                int month = Integer.parseInt(dateArr[0]);
                int day = Integer.parseInt(dateArr[1]);
                int year = Integer.parseInt(dateArr[2]);

                if (month <= 12 && month > 0 && day <= 31 && day > 0 && year < Date.currentYear) {
                    return true;
                }

            }
            return false;
        } catch (Exception e) {
            return false;
        }
    }
}
