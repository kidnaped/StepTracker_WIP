import java.util.Scanner;
public class StepTracker {
    Scanner sc;
    Converter converter;
    int[][] monthsAndDays;
    int steps;
    int stepGoals;
    int defaultStepGoals;
    int month;
    int day;

    StepTracker() {
        sc = new Scanner(System.in);
        converter = new Converter();
        monthsAndDays = new int[12][30];
        steps = 0;
        defaultStepGoals = 10000;
        stepGoals = defaultStepGoals;

        for (int m = 0; m < monthsAndDays.length; m++) {
            for (int d = 0; d < monthsAndDays[m].length; d++) {
                monthsAndDays[m][d] = 0;
            }
        }
    }
    void stepInput() {
        showMonthMenu();

        if (month != 0) showDaysMenu();
        if (day != 0) showStepsMenu();

        if ((month != 0) && (day != 0) && (steps !=0))
            monthsAndDays[month-1][day-1] = steps;
    }

    void printUserStats() {
        int stepsTotal = 0;
        int maxSteps = 0;

        showMonthMenu();
        if (month == 0) return;

        System.out.println("Статистика пользователя за " + month + " месяц:");
        System.out.println("Пройденные шаги:");
        for (int d = 0; d < monthsAndDays[month - 1].length; d++) {
            System.out.println((d + 1) + "день: " + monthsAndDays[month-1][d] + " шагов");
            stepsTotal = stepsTotal + monthsAndDays[month - 1][d];
            if (monthsAndDays[month - 1][d] > maxSteps)
                maxSteps = monthsAndDays[month - 1][d];
        }

        System.out.println("Общее количество шагов за месяц: " + stepsTotal);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxSteps);
        System.out.println("Среднее количество шагов: " + stepsTotal/monthsAndDays[month - 1].length);
        System.out.println("Пройденная дистанция: " + converter.distance(stepsTotal) + " километров.");
        System.out.println("Количество сожженных килокалорий: " + converter.calories(stepsTotal));
        System.out.println("Лучшая серия: " + bestSeries(monthsAndDays, month, stepGoals) + " дней.");

    }

    void setStepGoals() {
        System.out.println("Введите новую цель по количеству шагов.\n" +
                "Или нажмите 0 для возврата в главное меню.");
        stepGoals = sc.nextInt();
        if (stepGoals == 0) {
            stepGoals = defaultStepGoals;
            return;
        } else if (stepGoals < 1) {
            stepGoals = defaultStepGoals;
            System.out.println("Введите положительное число!\nИли нажмите 0 для возврата в главное меню.");
            stepGoals = sc.nextInt();
        } else System.out.println("Новая цель по количеству шагов: " + stepGoals);
    }

    void showMonthMenu() {
        System.out.println("Введите месяц от 1 (январь) до 12 (декабрь).\n" +
                "Или нажмите 0 для возврата в главное меню.");
        month = sc.nextInt();
        if (month == 0) return;
        while (month < 1 || month > 12) {
            System.out.println("Попробуйте снова!\nИли нажмите 0 для возврата в главное меню.");
            month = sc.nextInt();
            if (month == 0) return;
        }
    }

    void showDaysMenu() {
        System.out.println("Введите день от 1 до 30.\n" +
                "Или нажмите 0 для возврата в главное меню.");
        day = sc.nextInt();
        if (day == 0) return;
        while (day < 1 || day > 30) {
            System.out.println("Попробуйте снова.\nИли нажмите 0 для возврата в главное меню.");
            day = sc.nextInt();
            if (day == 0) return;
        }
    }

    void showStepsMenu() {
        System.out.println("Введите количество шагов.\n" +
                "Или нажмите 0 для возврата в главное меню.");
        steps = sc.nextInt();
        if (steps == 0) return;
        else if (steps < 0) {
            System.out.println("Количество шагов должно быть положительным.");
            steps = sc.nextInt();
        }
    }
    int bestSeries(int[][] monthsAndDays, int month, int stepGoals) {
        int seriesBuffer = 1;
        int series = 1;
        for (int d = 0; d < monthsAndDays[month - 1].length; d++) {
            if ((monthsAndDays[month - 1][d] >= stepGoals)
                && (monthsAndDays[month - 1][d + 1] >= stepGoals)) {
                series++;
                if (seriesBuffer < series) seriesBuffer = series;
            } else {
                series = 1;
            }
        }
        return seriesBuffer;
    }
}
