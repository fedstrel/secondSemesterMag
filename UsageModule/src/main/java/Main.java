/*
*   Необходимо аппроксимировать значение функции y = cos(x)
*   при помощи ряда Тейлора с заданной точностью на промежутке [-1; 1].
*   Результат необходимо вывести в виде таблицы, имеющей следующую структуру
*   | x | точное значение функции | апрокс. значение функции | модуль разницы между значениями |
*   |___|_________________________|__________________________|_________________________________|
* */

import approximator.FunctionApproximator;
import approximator.impl.CosApproximator;
import approximator.impl.SinApproximator;
import builder.TableBuilder;
import exception.HardShutDownException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    public static void main(String[] args) throws IOException {
        TableBuilder builder = new TableBuilder();
        TableBuilder.TableBuilderRowClass tableBuilder = builder
                .addColumn("x", 30)
                .addColumn("Точное значение фукнции", 0)
                .addColumn("Аппрокс. значение функции", 0)
                .addColumn("Модуль разницы между значениями", 0)
                .data();

        FunctionApproximator approximator = chooseFunction();

        System.out.println("Введите количество точек, на которые будет разбит отрезок.");
        int count = Integer.parseInt(reader.readLine());
        System.out.println("Введите точность с которой будет приближаться значение.");
        double accuracy = Double.parseDouble(reader.readLine());

        double upperBound = approximator.getUpperBound();
        double x = approximator.getLowerBound();
        double step = (upperBound - x) / count;
        double approx;
        double correct;
        while (x < upperBound) {
            correct = approximator.findFunctionAtPointExact(x);
            approx = approximator.findFunctionAtPointApprox(x, accuracy);
            tableBuilder.addRow(List.of(
                    String.valueOf(x),
                    String.valueOf(correct),
                    String.valueOf(approx),
                    String.valueOf(Math.abs(correct - approx))));
            x += step;
        }
        approx = approximator.findFunctionAtPointApprox(1, accuracy);
        correct = approximator.findFunctionAtPointExact(x);
        tableBuilder.addRow(List.of(
                "1",
                String.valueOf(correct),
                String.valueOf(approx),
                String.valueOf(Math.abs(correct - approx))));

        System.out.println(tableBuilder.getTable());
    }

    public static FunctionApproximator chooseFunction() {
        System.out.println("Введите 0, если будем вычислять косинус");
        System.out.println("Введите 1, если будем вычислять синус");
        System.out.println("Введите что угодно кроме, если хотите экстренно завершить работу приложения");
        try {
            int value = Integer.parseInt(reader.readLine());
            return getApproxByInput(value);
        } catch (Exception e) {
            throw new HardShutDownException("Экстренное завершение работы");
        }
    }

    public static FunctionApproximator getApproxByInput(int input) {
        switch (input) {
            case 0:
                return new CosApproximator();
            case 1:
                return new SinApproximator();
            default:
                throw new HardShutDownException("Эктренное завершение работы");
        }
    }
}