package com.example.calculatorapp.presenter;

import android.content.Intent;
import android.util.Log;

import com.example.calculatorapp.R;
import com.example.calculatorapp.model.CalculatorOperations;
import com.example.calculatorapp.view.CalculatorView;

public class CalculatorPresenter {
    private CalculatorOperations calculatorOperations;
    private CalculatorView calculatorView;

    public CalculatorPresenter(CalculatorView calculatorView, CalculatorOperations calculatorOperations) {
        this.calculatorOperations = calculatorOperations;
        this.calculatorView = calculatorView;
    }

    public void onButtonClear() {
        calculatorView.clear();
    }

    public void onButtonEqual(double a, String operand, double b) {
        double result = calculatorOperations.binaryOperation(a, b, operand);
        if (b == 0){
            calculatorView.showResult(null);
        }
        else {
            calculatorView.showResult(String.valueOf(result));
        }
    }

}