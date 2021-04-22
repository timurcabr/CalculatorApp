package com.example.calculatorapp.model;

import android.widget.Toast;

public class CalculatorModel implements CalculatorOperations {

    @Override
    public double binaryOperation(double a, double b, String operation) {
        double result = 0.0;
        switch (operation) {
            case "+":
                result = a + b;
                break;
            case "-":
                result = a - b;
                break;
            case "x":
                result = a * b;
                break;
            case "/":
                result = a / b;
                break;
        }
        return result;
    }
}
