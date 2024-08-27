import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.*;

public class Statistics {
    public void printShortStatistic(DataSorter dataSorter) {
        System.out.println("Количество записанных элементов: ");

        System.out.println("Целые числа: " + dataSorter.getIntegers().size());
        System.out.println("Числа с плавающей точкой : " + dataSorter.getFloats().size());
        System.out.println("Строки: " + dataSorter.getStrings().size());
    }

    public void printFullStatistic(DataSorter dataSorter) {
        if (!dataSorter.getIntegers().isEmpty()) {
            List<BigInteger> integers = new ArrayList<>();

            BigInteger sum = new BigInteger("0");

            for (String integer : dataSorter.getIntegers()) {
                BigInteger current = new BigInteger(integer);

                integers.add(current);

                sum = sum.add(current);
            }

            BigInteger max = integers.stream()
                    .max(BigInteger::compareTo)
                    .orElseThrow(NoSuchElementException::new);

            BigInteger min = integers.stream()
                    .min(BigInteger::compareTo)
                    .orElseThrow(NoSuchElementException::new);

            System.out.println("Сумма всех целых чисел: " + sum);
            System.out.println("Среднее значение целых чисел: " + sum.divide(BigInteger.valueOf(integers.size())));
            System.out.println("Максимальное значение: " + max);
            System.out.println("Минимальное значение: " + min);
        }

        if (!dataSorter.getFloats().isEmpty()) {
            List<BigDecimal> floats = new ArrayList<>();

            BigDecimal sum = new BigDecimal("0");

            for (String myFloat : dataSorter.getIntegers()) {
                BigDecimal current = new BigDecimal(myFloat);

                floats.add(current);
                sum = sum.add(current);
            }

            BigDecimal max = floats.stream()
                    .max(BigDecimal::compareTo)
                    .orElseThrow(NoSuchElementException::new);

            BigDecimal min = floats.stream()
                    .min(BigDecimal::compareTo)
                    .orElseThrow(NoSuchElementException::new);

            System.out.println("Сумма всех целых чисел: " + sum);
            System.out.println("Среднее значение целых чисел: " + sum.divide(BigDecimal.valueOf(floats.size()), RoundingMode.CEILING));
            System.out.println("Максимальное значение: " + max);
            System.out.println("Минимальное значение: " + min);
        }

        if (!dataSorter.getStrings().isEmpty()) {
            String maxLine = Collections.max(dataSorter.getStrings(), Comparator.comparing(String::length));
            String minLine = Collections.min(dataSorter.getStrings(), Comparator.comparing(String::length));

            System.out.println("Самая длинная строка: " + maxLine);
            System.out.println("Самая короткая строка: " + minLine);
        }
    }
}
