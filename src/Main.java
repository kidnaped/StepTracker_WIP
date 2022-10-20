import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StepTracker tracker = new StepTracker();

        printMenu();
        int userInput = sc.nextInt();

        while (userInput != 0) {
            if (userInput == 1) {
                stepInput(sc, tracker);
            } else if (userInput == 2) {
                tracker.printUserStats(showMonthMenu(sc, tracker));
            } else if (userInput == 3) {
                tracker.setStepGoals(showStepGoalMenu(sc, tracker));
            } else System.out.println("Такой команды я еще не знаю :)");

            printMenu();
            userInput = sc.nextInt();
        }
    }

    public static void printMenu() {
        System.out.println("Выберите действие:\n" +
                "1. Ввести количество пройденных шагов.\n" +
                "2. Вывести статистику пользователя за выбранный месяц.\n" +
                "3. Изменить цели по количеству шагов в день.\n" +
                "0. Выход из приложения.");
    }

    public static int showMonthMenu(Scanner sc, StepTracker tracker) {
        System.out.println("Введите месяц от 1 (январь) до 12 (декабрь).");
        int month = sc.nextInt();
        while (month < 1 || month > tracker.monthDate.length) {
            System.out.println("Попробуйте снова!");
            month = sc.nextInt();
        }
        return month;
    }

    public static int showDaysMenu(Scanner sc, StepTracker tracker) {
        System.out.println("Введите день от 1 до 30.");
        int day = sc.nextInt();
        while (day < 1 || day > tracker.monthDate[0].data.length) {
            System.out.println("Попробуйте снова.");
            day = sc.nextInt();
        }
        return day;
    }

    public static int showStepsMenu(Scanner sc) {
        System.out.println("Введите количество шагов.");
        int steps = sc.nextInt();
        if (steps < 0) {
            System.out.println("Количество шагов должно быть положительным.");
            steps = sc.nextInt();
        }
        return steps;
    }
    public static int showStepGoalMenu(Scanner sc, StepTracker tracker) {
        System.out.println("Текущая цель по количеству шагов: " + tracker.defaultStepGoals);
        System.out.println("Введите новую цель по количеству шагов.");
        int stepGoals = sc.nextInt();
        while (stepGoals < 0) {
            System.out.println("Введите положительное число!");
            stepGoals = sc.nextInt();
        }
        System.out.println("Новая цель по количеству шагов: " + stepGoals);
        return stepGoals;
    }

    public static void stepInput(Scanner sc, StepTracker tracker) {
        int month = showMonthMenu(sc, tracker);
        int day = showDaysMenu(sc, tracker);
        int steps = showStepsMenu(sc);
        tracker.monthDate[month-1].data[day-1] = steps;
    }

}
