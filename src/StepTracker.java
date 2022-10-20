public class StepTracker {
    Converter converter;
    MonthData[] monthDate;
    int defaultStepGoals;

    StepTracker() {
        converter = new Converter();
        monthDate = new MonthData[12];

        defaultStepGoals = 10000;

        for (int m = 0; m < monthDate.length; m++) {
            monthDate[m] = new MonthData();
        }
    }

    void printUserStats(int month) {
        int stepsTotal = 0;
        int maxSteps = 0;


        System.out.println("Статистика пользователя за " + month + " месяц:");
        System.out.println("Пройденные шаги:");
        for (int d = 0; d < monthDate[month - 1].data.length; d++) {
            if (d < ((monthDate[month -1].data.length) - 1))
            System.out.print((d + 1) + " день: " + monthDate[month-1].data[d] + " шагов, ");
            else System.out.println((d + 1) + " день: " + monthDate[month-1].data[d] + " шагов.");

            stepsTotal = stepsTotal + monthDate[month-1].data[d];
            if (monthDate[month-1].data[d] > maxSteps)
                maxSteps = monthDate[month-1].data[d];
        }

        System.out.println("Общее количество шагов за месяц: " + stepsTotal);
        System.out.println("Максимальное пройденное количество шагов в месяце: " + maxSteps);
        System.out.println("Среднее количество шагов: " + stepsTotal/monthDate[month -1].data.length);
        System.out.println("Пройденная дистанция: " + converter.distance(stepsTotal) + " километров.");
        System.out.println("Количество сожженных килокалорий: " + converter.calories(stepsTotal));
        System.out.println("Лучшая серия: " + getBestSeries(monthDate[month - 1], defaultStepGoals) + " дней.");

    }

    int getBestSeries(MonthData monthDate, int stepGoals) {
        int seriesMax = 0;
        int seriesCurrent = 0;
        for (int d = 0; d < monthDate.data.length; d++) {
            if (monthDate.data[d] >= stepGoals) {
                seriesCurrent++;
                if (seriesMax < seriesCurrent) {
                    seriesMax = seriesCurrent;
                }
            } else {
                seriesCurrent = 0;
            }
        }
        return seriesMax;
    }
    void setStepGoals(int stepGoals) {
        defaultStepGoals = stepGoals;
    }

    class MonthData {
        int[] data;
        MonthData() {
            data = new int[30];
        }
    }
}
