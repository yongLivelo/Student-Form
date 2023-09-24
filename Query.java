import java.util.Scanner;

public class Query {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("What is the password?");
        String password = scanner.nextLine();
        if (password.equals("pogisijulio")) {
            Print.space(1);
            System.out.println("Kindly Fill out your information :)");
            String name = Print.printScan("What is your full name?", scanner);
            String studentNum = Print.printScan("What is your student number?", scanner);
            String section = Print.printScan("What is your strand and section \nExample: STEM102", scanner);
            String birthdayStr = Print.printScan("When were you born? \nExample: 07/24/2007 (MM/DD/YYYY)", scanner);
            Date birthday = new Date(Integer.parseInt(birthdayStr.substring(0, 2)),
                    Integer.parseInt(birthdayStr.substring(3, 5)), Integer.parseInt(birthdayStr.substring(6, 10)));

            String toPrint = Print.printScan("Would you like to print a list of your information? (y/n)", scanner);
            if (toPrint.equals("y")) {
                Print.printInformation(name, studentNum, section, birthday);
            } else if (toPrint.equals("n")) {
                System.out.println("Thank you for filling out this form!");
            } else {
                System.out.println("Invalid input, please try again");
            }
        } else {
            System.out.println("Wrong Password, Try again :)");
        }
        scanner.close();
    }

}

class Date {
    int day, month, year;
    String[] months = { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "October",
            "November", "December" };

    public Date(int month, int day, int year) {
        this.month = month;
        this.day = day;
        this.year = year;
    }

    public int getAge(int currentYear) {
        return currentYear - this.year;
    }

    public String getBdayText() {
        return months[this.month - 1] + " " + this.day + ", " + this.year;
    }

}

class Print {
    public static void printInformation(String name, String studentNum, String Section, Date birthday) {
        String line = ("--------------------");
        space(6);
        System.out.println("User Information");
        System.out.println(line);
        System.out.println("Name: \t\t" + name);
        System.out.println("Student Number: " + studentNum);
        System.out.println("Section: \t" + Section);
        System.out.println("Birthday: \t" + birthday.getBdayText());
        System.out.println("age: \t\t" + birthday.getAge(2023));
        System.out.println(line);
    }

    public static String printScan(String print, Scanner scanner) {
        space(1);
        System.out.println(print);
        String scan = scanner.nextLine();
        return scan;
    }

    public static void space(int spaces) {
        for (int i = 0; i < spaces; i++) {
            System.out.println("");
        }

    }
}

class Verifier {
    public static void verifier(String type, String value) {
        String[] valueArr = value.split("");
        if (type.equals("studentNumber")) {
            if (value.length() != 11) {
                for (int i = 0; i < 11; i++) {
                    Integer.parseInt(valueArr[i]);
                }

            }
        }

        String[] section = { "MAWD", "HUMMS", "STEM", "CCTECH", "ABM" };
        if (type.equals("section")) {

        }
    }
}
