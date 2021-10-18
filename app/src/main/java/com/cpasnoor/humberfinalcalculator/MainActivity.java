package com.cpasnoor.humberfinalcalculator;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import helpers.ViewHelpers;

public class MainActivity extends AppCompatActivity {
    public TextView tvOperation = null;
    public Resources res;
    private Calculator cal;
    private Boolean refreshScreen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();
        cal = new Calculator();
        tvOperation = findViewById(R.id.tvOperation);
    }

    public void onMemoryOprListener(View v) {
        String curValue = ViewHelpers.getTxtViewString(tvOperation);
        if (curValue.isEmpty()) {
            curValue = "0";
        }
        /**/
        String tag = (String) v.getTag();
        int stringResID = res.getIdentifier(tag, "string", getPackageName());
        /**/
        String result = cal.performMemoryOperations(stringResID, curValue);
        if (stringResID == R.string.btnMR) {
            updateOperationView(result, false, false);
        }
    }

    public void onArithmeticOprListener(View v) {
        String arithmetic_opr = ViewHelpers.getBtnViewString((Button) v); // Only Operation Buttons
        String currValue = ViewHelpers.getTxtViewString(tvOperation);
        cal.setOperationStatus(arithmetic_opr);
        cal.updateOperandsByStatus(currValue, refreshScreen);
        String result = cal.performArithOperations();
        refreshScreen = true;
        if (result == null) {
            ViewHelpers.animateView(tvOperation, true, getApplicationContext());
        } else {
            updateOperationView(result, true, result.length() > 15);
        }
    }

    public void onNumericsListener(View v) {
        String currValue = ViewHelpers.getBtnViewString((Button) v); // Only Numeric Buttons
        String updateText;
        if (refreshScreen) {
            updateText = currValue; // New Inputs On Screen
            refreshScreen = false;
        } else {
            String prevTxtValue = ViewHelpers.getTxtViewString(tvOperation);
            updateText = ViewHelpers.concat(prevTxtValue, currValue);
        }
        updateOperationView(updateText, false, false);
    }

    public void calculateOperation(View v) {
        String currValue = ViewHelpers.getTxtViewString(tvOperation);
        cal.updateOperandsByStatus(currValue, false);
        String result = cal.performArithOperations();
        refreshScreen = true;
        updateOperationView(result, true, result.length() > 15);
    }

    public void onExtraOprListener(View v) {
        int tagResourceID = res.getIdentifier((String) v.getTag(), "string", getPackageName());
        String currValue = ViewHelpers.getTxtViewString(tvOperation);
        String result = cal.performUnaryOperations(tagResourceID, currValue);
        refreshScreen = true;
        updateOperationView(result, true, result.length() > 15);
    }

    private void updateOperationView(String txt, Boolean animate, @NonNull Boolean shouldTrim) {
        if (shouldTrim) {
            txt = txt.substring(0, 15);
            Toast.makeText(getApplicationContext(), "Result trimmed due to limit exceed", Toast.LENGTH_SHORT).show();
        }
        if (txt.length() > 15) {
            Toast.makeText(getApplicationContext(), "Operation Limit Exceeded", Toast.LENGTH_SHORT).show();
            return;
        }
        ViewHelpers.animateView(tvOperation, animate, getApplicationContext());
        tvOperation.setText(txt);
    }
}