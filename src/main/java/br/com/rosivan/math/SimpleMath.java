package br.com.rosivan.math;

public class SimpleMath {
    public Double sum( Double numberOne, Double numberTwo ) {
        final double resultSum = numberOne + numberTwo;
        return resultSum;
    }

    public Double subtration(Double numberOne, Double numberTwo) {
        final double result = numberOne - numberTwo;
        return result;
    }

    public Double multiplication(Double numberOne, Double numberTwo ){
        final double result = numberOne * numberTwo;
        return result;
    }


    public Double division(Double numberOne, Double numberTwo) {
        final double result = numberOne / numberTwo;
        return result;
    }

    public Double mean(Double numberOne, Double numberTwo) {
        final double result = (numberOne + numberTwo) / 2;
        return result;
    }

    public Double squareRoot(Double number) {
        final double result = Math.sqrt(number);
        return result;
    }
}
