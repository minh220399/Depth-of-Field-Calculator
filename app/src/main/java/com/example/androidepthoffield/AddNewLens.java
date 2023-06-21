package com.example.androidepthoffield;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddNewLens extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_lens);

        setupSaveButton();
        setupCancelButton();
    }

    private void setupSaveButton() {
        Button saveButton = (Button) findViewById(R.id.saveButton);
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Extract data from the UI;
                EditText lensName = (EditText) findViewById(R.id.lensNameInput);
                String   lensNameToString = lensName.getText().toString();

                EditText lensFocalLength = (EditText) findViewById(R.id.lensFocalLengthInput);
                Integer lensFocalLengthToInt = Integer.parseInt(lensFocalLength.getText().toString());

                EditText lensAperture = (EditText) findViewById(R.id.lensApertureInput);
                Double lensApertureToDouble = Double.parseDouble(lensAperture.getText().toString());

                //Pass data back
                Intent intent = new Intent();
                intent.putExtra("lensName",lensNameToString);
                intent.putExtra("lensFocalLength",lensFocalLengthToInt);
                intent.putExtra("lensAperture",lensApertureToDouble);
                setResult(Activity.RESULT_OK, intent);
                finish();
            }
        });

    }

    private void setupCancelButton() {
        Button cancelButton = (Button) findViewById(R.id.cancelButton);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public static Intent makeIntent(Context context){
        return new Intent(context, AddNewLens.class);
    }
}
