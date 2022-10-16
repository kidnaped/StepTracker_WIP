import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StepTracker tracker = new StepTracker();


        printMenu();
        int userInput = sc.nextInt();

        while (!(userInput == 0)) {
            if (userInput == 1) {
                tracker.stepInput();
            }
            else if (userInput == 2) {
                tracker.printUserStats();
            }
            else if (userInput == 3) {
                tracker.setStepGoals();
            }
            else System.out.println("Такой команды я еще не знаю :)");

            printMenu();
            userInput = sc.nextInt();
        }
    }

    public static void printMenu() {
        System.out.println("""
                Выберите действие:
                1. Ввести количество пройденных шагов.
                2. Вывести статистику пользователя за выбранный месяц.
                3. Изменить цели по количеству шагов в день.
                0. Выход из приложения.""");
    }
}
