package com.example.android.quizapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private CheckBox checkBoxA, checkBoxB, checkBoxC,checkBoxD,checkBoxE,checkBoxF;
    private boolean radio1Btn,radio2Btn;
    private boolean mySwitch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        checkBoxA = (CheckBox) findViewById(R.id.check1_1);
        checkBoxB = (CheckBox) findViewById(R.id.check1_2);
        checkBoxC = (CheckBox) findViewById(R.id.check1_3);
        checkBoxD = (CheckBox) findViewById(R.id.check2_1);
        checkBoxE = (CheckBox) findViewById(R.id.check2_2);
        checkBoxF = (CheckBox) findViewById(R.id.check2_3);


        /**
         * This method is checking the switch button if is on or off
         */
        Switch sw = (Switch) findViewById(R.id.simpleSwitch);
        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mySwitch=true;// The toggle is enabled
                } else {
                    mySwitch=false;
                    // The toggle is disabled
                }
            }
        });
    }


    /**
     * This method is checking the check Box selected from question no1 and 2
     */

    public void onCheckboxClicked(View view) {

        switch(view.getId()) {

            case R.id.check1_1:

                checkBoxB.setChecked(false);
                checkBoxC.setChecked(false);

                break;

            case R.id.check1_2:

                checkBoxC.setChecked(false);
                checkBoxA.setChecked(false);

                break;

            case R.id.check1_3:

                checkBoxA.setChecked(false);
                checkBoxB.setChecked(false);

                break;
            case R.id.check2_1:

                checkBoxE.setChecked(false);
                checkBoxF.setChecked(false);

                break;

            case R.id.check2_2:

                checkBoxF.setChecked(false);
                checkBoxD.setChecked(false);

                break;

            case R.id.check2_3:

                checkBoxD.setChecked(false);
                checkBoxE.setChecked(false);

                break;

        }
    }

    /**
     * This method is checking the radio button selected from radioO radiogroup
     */
    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio1:
                if (checked)
                    radio1Btn=true;
                    break;
            case R.id.radio2:
                if (checked)
                    radio1Btn=false;
                    break;
        }
    }

    /**
     * This method is checking the radio button selected from radioI radiogroup
     */
    public void onRadioButtonClicked1(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.radio3:
                if (checked)
                    radio2Btn=false;
                    break;
            case R.id.radio4:
                if (checked)
                    radio2Btn=true;
                    break;
        }
    }


    /**
     * This method is called when the Submit Score button is clicked.
     */
    public void submitScore(View view) {
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();
        boolean hasMySwitch=mySwitch;

       CheckBox check1_3_CheckBox = (CheckBox) findViewById(R.id.check1_3);
        boolean hasCheck1_3 = check1_3_CheckBox.isChecked();

       CheckBox check2_2_CheckBox = (CheckBox) findViewById(R.id.check2_2);
        boolean hasCheck2_2 = check2_2_CheckBox.isChecked();

        boolean hasRadio1=radio1Btn;
        boolean hasRadio2=radio2Btn;

        int score = calculateScore(hasCheck1_3, hasCheck2_2,hasRadio1,hasRadio2);
////        Log.v("MainActivity","The score is: "+score);
        String priceMessage = createScoreSummary(name, score,hasMySwitch);
        displayMessage(priceMessage);
    }

    /**
     * Calculates the score of the quiz.
     *
     * @param isCorrect13 the correct answer for the question no 1
     * @param isCorrect22 the correct answer for the question no 2
     * @param isRadio1 ,isRadio2 selections for questions no 3 and 4
     * @return the score
     */

    private int calculateScore(boolean isCorrect13, boolean isCorrect22,boolean isRadio1,boolean isRadio2) {
        int baseScore = 20;
        if (isCorrect13 == true) {
            baseScore = baseScore + 20;
        }

        if (isCorrect22 == true) {
            baseScore= baseScore + 20;
        }
        if (isRadio1==true) {
            baseScore=baseScore+20;
        }
        if (isRadio2==true){
            baseScore=baseScore+20;
        }

       return baseScore;
    }

    /**
     * display the result of the quiz
     * @param name is the name of the user
     * @param score   of the quiz
     * @param isMySwitch whether or not the user liked the quiz
     * @return  scoreMessage return the final message with the result of the quiz
     */
    private String createScoreSummary(String name, int score, boolean isMySwitch) {
        String scoreMessage = "Congratulation " + name;
        scoreMessage += "\nYour Score is: " + score;

        if(isMySwitch==true){
            scoreMessage+="\nI'm so glad that you loved my quiz. Thank you!";
        }
        else if(isMySwitch==false){
            scoreMessage+="\nNice to have you here!";
        }

        return scoreMessage;
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.quiz_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}
