import java.util.Scanner;
import java.util.function.Consumer;
import java.util.stream.IntStream;

interface MyValue {
    double getValue();
}

interface MyParamValue {
    double getValue(double v);
}

interface NumericTest{
    boolean test(int n, int m);
}

interface Operation{
    double result(double a, double b);
}

interface NumericFunc{
    int func(int n);
}

interface TestGeneric<T>{
    boolean test(T n, T m);
}

interface MyTest{
    boolean test(int n);
}

interface StringMan {
    String changeStr(String s);
}



public class Lambdas {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        MyValue myVal;
        MyParamValue myPval;

        myVal = () -> 100;
        System.out.println("\nA constant value: " + myVal.getValue());
        myPval = n -> 1 / n;
        System.out.println("Reciprocal of 4 is: " + myPval.getValue(4));
        System.out.println("Reciprocal of 8 is: " + myPval.getValue(8));

        NumericTest isFactor = (n, d) -> (n % d) == 0;
        if (isFactor.test(10, 2)) System.out.println("\n2 is a factor of 10");
        if (!isFactor.test(10, 3)) System.out.println("3 is not a factor of 10");
        System.out.println();

        NumericTest lessThan = (n, m) -> (n < m);
        if (lessThan.test(2, 10)) System.out.println("2 is less than 10");
        if (!lessThan.test(10, 2)) System.out.println("10 is not less than 2");
        System.out.println();

        NumericTest absEqual = (n, m) -> (n < 0 ? -n : n) == (m < 0 ? -m : m);
        if (absEqual.test(4, -4)) System.out.println("Absolute values of 4 and -4 are equal");
        if (!absEqual.test(4, -5)) System.out.println("Absolute values of 4 and -5 are not equal");
        System.out.println();

        Operation sum = (a, b) -> a + b;
        Operation sub = (a, b) -> a - b;
        Operation mul = (a, b) -> a * b;
        Operation div = (a, b) -> a / b;

        System.out.print("Please enter the first number: ");
        double a = input.nextDouble();
        System.out.print("Please enter the second number: ");
        double b = input.nextDouble();

        System.out.println("\nThe sum of " + a + " and " + b + " is: " + sum.result(a, b));
        System.out.println("The subtraction of " + a + " and " + b + " is: " + sub.result(a, b));
        System.out.println("The multiplication of " + a + " and " + b + " is: " + mul.result(a, b));
        System.out.println("The division of " + a + " and " + b + " is: " + div.result(a, b));

        NumericFunc smallestF = (n) -> {
            int result = 1;
            n = n <0 ? -n : n;
            for (int i = 2; i<= n / i; i++){
                if((n % i) == 0){
                    result = i;
                    break;
                }
            }
            return result;
        };

        System.out.println("\nThe smallest factor of 12 is: " + smallestF.func(12));

        TestGeneric<Integer> isFactorG = (n, d) -> (n % d) == 0;
        if (isFactorG.test(10, 2)) System.out.println("\n2 is a factor of 10");
        if (!isFactorG.test(10, 3)) System.out.println("3 is not a factor of 10");
        System.out.println();

        System.out.print("Please enter a number to know if its between 10 and 20: ");
        int n = input.nextInt();
        MyTest isBetween = (x) -> {
            if (x>=10 && (x <= 20)) return true;
            else return false;
        };
        if (isBetween.test(n)) System.out.println(n + " is between 10 and 20");
        else System.out.println(n + " is not between 10 and 20");
        System.out.println();

        NumericFunc factorial = (k) -> {
            int result = 1;
            for (int i = 1; i <= k; i++){
                result = i * result;
            }
            return result;
        };
        System.out.println("\nThe factorial of 3 is: " + factorial.func(3));
        System.out.println("The factorial of 5 is: " + factorial.func(5));
        System.out.println("The factorial of 9 is: " + factorial.func(9));

        NumericFunc fibonacci = (z) -> {
            int result = 0;
            int l = 0;
            int u = 1;
            for (int i = 1; i <= z; i++){
                result = l + u;
                l = u;
                u = result;
            }
            return result;
        };
        System.out.println("\nThe fibonacci of 3 is: " + fibonacci.func(3));
        System.out.println("The fibonacci of 5 is: " + fibonacci.func(5));
        System.out.println("The fibonacci of 9 is: " + fibonacci.func(9));


        StringMan changeShort = (s) -> s.replaceAll("\\s", "");

        StringMan changeLong = (s) -> {
            StringBuilder result = new StringBuilder();
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) != ' ') {
                    result.append(s.charAt(i));
                }
            }
            return result.toString();
        };

        System.out.print("\nPlease enter a string: ");
        String s = input.nextLine();
        System.out.println("The string without spaces is: " + changeShort.changeStr(s));
        System.out.println("The string without spaces is: " + changeLong.changeStr(s));
        System.out.println();

        Consumer <Integer> tablaMulti = (g) -> {
            for (int i = 1; i <= 10; i++) {
                int result = g * i;
                System.out.println(g + " x " + i + " = " + result);
            }
        };


        System.out.print("Enter a number: ");
        int g = input.nextInt();

        tablaMulti.accept(g);
        System.out.println();

        //Programacion tradicional
        System.out.println("The numbers in the list are:");
        int fuente[] = {4, 5, 6, 7, 8, 9, 34, 52, 14, 95};
        int suma = 0;
        for (int i = 0; i < fuente.length; i++){
            suma += fuente[i];
        }
        for (int i = 0; i < fuente.length; i++){
            System.out.print(fuente[i] + " ");
        }
        System.out.println("\nThe sum of all the numbers in the list is: " + suma);


        //Programacion funcional
        System.out.printf("The sum is: %d%n", IntStream.of(fuente).sum());

        System.out.printf("The average is: %.2f%n", IntStream.of(fuente).average().getAsDouble());


    }
}




