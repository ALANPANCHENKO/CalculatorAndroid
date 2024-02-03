package com.example.firstproject;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView editText, resultText;
    Button bu_C, bu_Open, bu_Close;
    Button buDel, buSum, buMult, buDiv, buequals;
    Button buone, butwo, buthree, bufour, bufive, busix, buseven, bueight, bunine, buzero;
    Button bu_Tochka, bu_AC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultText = findViewById(R.id.editText1);
        editText = findViewById(R.id.editText);

        assignId(bu_C, R.id.buC);
        assignId(bu_Open, R.id.buOpen);
        assignId(bu_Close, R.id.buClose);
        assignId(buDel, R.id.Del);
        assignId(buMult, R.id.Mult);
        assignId(buDiv, R.id.Div);
        assignId(buequals, R.id.equals);
        assignId(buSum, R.id.Sum);
        assignId(buone, R.id.one);
        assignId(butwo, R.id.two);
        assignId(buthree, R.id.three);
        assignId(bufour, R.id.four);
        assignId(bufive, R.id.five);
        assignId(busix, R.id.six);
        assignId(buseven, R.id.seven);
        assignId(bueight, R.id.eight);
        assignId(bunine, R.id.nine);
        assignId(buzero, R.id.zero);
        assignId(bu_Tochka, R.id.buTochka);
        assignId(bu_AC, R.id.buAC);
    }

    void assignId(Button btn, int id){
        btn = findViewById(id);
        btn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Button btn = (Button) v;
        String buttontext = btn.getText().toString();
        String dataToCalculate = resultText.getText().toString();


        if(buttontext.equals("AC")){
            resultText.setText("");
            editText.setText("0");
            return;
        }
        if(buttontext.equals("=")){
            try {
                double result = evaluateExpression(dataToCalculate);
                editText.setText(String.valueOf(result));
                resultText.setText("");
            } catch (Exception e) {
                editText.setText("Error");
            }
            return;
//            resultText.setText(editText.getText());
//            return;
        }
        if(buttontext.equals("C")){
            if(dataToCalculate.length() != 0){
            dataToCalculate = dataToCalculate.substring(0,dataToCalculate.length()-1);
            }else{
                editText.setText("Not found");
            }
        }else{
            if(dataToCalculate.equals("0")){
            dataToCalculate = buttontext;
            }else{
                dataToCalculate = dataToCalculate + buttontext;
            }
        }
        resultText.setText(dataToCalculate);
    }

    private double evaluateExpression(String expression) {
        char operator = ' ';
        double result = 0;
        String currentNumber = "";

        for (int i = 0; i < expression.length(); i++) {
            char currentChar = expression.charAt(i);

            if (Character.isDigit(currentChar) || currentChar == '.') {
                currentNumber += currentChar;
            } else if (isOperator(currentChar)) {
                if (!currentNumber.isEmpty()) {
                    double operand = Double.parseDouble(currentNumber);
                    result = performOperation(result, operand, operator);
                    currentNumber = "";
                }
                operator = currentChar;
            }
        }

        if (!currentNumber.isEmpty()) {
            double operand = Double.parseDouble(currentNumber);
            result = performOperation(result, operand, operator);
        }

        return result;
    }

    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private double performOperation(double operand1, double operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1 + operand2;
            case '-':
                return operand1 - operand2;
            case '*':
                return operand1 * operand2;
            case '/':
                if (operand2 != 0) {
                    return operand1 / operand2;
                } else {

                    throw new ArithmeticException("Division by zero");
                }
            default:
                return operand2;
        }
    }

}
