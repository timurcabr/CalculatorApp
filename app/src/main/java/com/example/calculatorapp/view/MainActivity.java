package com.example.calculatorapp.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.calculatorapp.R;
import com.example.calculatorapp.model.CalculatorModel;
import com.example.calculatorapp.model.CalculatorOperations;
import com.example.calculatorapp.presenter.CalculatorPresenter;

public class MainActivity extends AppCompatActivity implements CalculatorView {

    private static final String KEY = "numbers";
    StringBuilder builder = new StringBuilder();
    TextView numbers;

    CalculatorPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new CalculatorPresenter(this, new CalculatorModel());
        numbers = findViewById(R.id.numbers);

        if (savedInstanceState != null) {
            String value = String.valueOf(savedInstanceState.getString(KEY));
            builder.append(value);
            numbers.setText(builder.toString());
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        if (numbers.getText().toString().length() > 0) {
            outState.putString(KEY, numbers.getText().toString());
        }
        super.onSaveInstanceState(outState);
    }

    public void calcClick(View view) {
        Button button = (Button) view;
        String[] result;
        if (button.getId() == R.id.clear) {
            //TODO: Presenter onButtonClear call
            presenter.onButtonClear();

        } else {
            String selectedButton = (String) button.getTag();
            if (selectedButton.equals("=") && numbers.getText().length() > 0) {
                result = formatNumbers(numbers.getText().toString());
                Log.i("result", result[0] + " " + result[1] + " " + result[2]);

                //TODO: Presenter onButtonEqual call
                presenter.onButtonEqual(Double.parseDouble(result[0]), result[1], Double.parseDouble(result[2]));
            }
            if (!selectedButton.equals("=")) {
                builder.append(selectedButton);
                numbers.setText(builder.toString());
            }
        }
    }

    private String[] formatNumbers(String numbers) {
        String[] operation = new String[3];
        char[] chars = numbers.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if (chars[i] == '+' || chars[i] == '-' || chars[i] == 'x' || chars[i] == '/') {
                operation[0] = numbers.substring(0, i);
                operation[1] = String.valueOf(chars[i]);
                operation[2] = numbers.substring(i + 1);
            }
        }
        return operation;
    }


    @Override
    public void showResult(String result) {
        numbers.setText("");
        builder.setLength(0);
        if(result == null){
            numbers.setText(getResources().getText(R.string.divisionMessage));
        }else {
            numbers.setText(result);
        }
    }

    @Override
    public void clear() {
        numbers.setText("");
        builder.setLength(0);
    }

}