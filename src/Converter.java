public class Converter {

    double stepLength;
    double stepCalRate;

    Converter() {
        stepLength = 0.75;
        stepCalRate = 50;
    }

    double distance(int steps) {
        return (steps * stepLength) / 1000;
    }

    double calories(int steps) {
        return (steps * stepCalRate) / 1000;
    }
}
