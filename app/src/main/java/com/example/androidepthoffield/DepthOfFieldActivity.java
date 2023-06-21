package com.example.androidepthoffield;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.androidepthoffield.model.DepthOfFieldCalculator;
import com.example.androidepthoffield.model.LensManager;

import java.text.DecimalFormat;

public class DepthOfFieldActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_depth_of_field);
        Intent intent = getIntent();
        String lenDetails = intent.getStringExtra("len details");
        TextView displayLen = (TextView) findViewById(R.id.lenDetails);
        displayLen.setText(lenDetails);
        EditText confusionVal = (EditText) findViewById(R.id.confusionVal);
        String circleOfConfusiion = "0.029";
        confusionVal.setText(circleOfConfusiion);

        setUpCalculateButton();

    }
    private String formatM(double distanceInM) {
        DecimalFormat df = new DecimalFormat("0.00");
        return df.format(distanceInM);
    }

    private void setUpCalculateButton() {
        Intent intent = getIntent();
        int id = intent.getIntExtra("id",0);
        Button calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LensManager manager = LensManager.getInstance();

                Intent intent = getIntent();
                int id = intent.getIntExtra("id",0);

                EditText circleOfConfusion = (EditText) findViewById(R.id.confusionVal);
                Double confusionToDouble = Double.parseDouble(circleOfConfusion.getText().toString());


                EditText distance = (EditText) findViewById(R.id.distanceVal);
                Double distanceToDouble = Double.parseDouble(distance.getText().toString());

                EditText aperture = (EditText) findViewById(R.id.apertureVal);
                Double apertureToDouble = Double.parseDouble(aperture.getText().toString());
                if( distanceToDouble < manager.getLens(id).getMaximumAperture())
                {
                    TextView hyperFocal = (TextView) findViewById(R.id.HyperfocalVal);
                    hyperFocal.setText("Invalid aperture");

                    TextView nearFocal = (TextView) findViewById(R.id.nearFocalVal);
                    nearFocal.setText("Invalid aperture");

                    TextView farFocal = (TextView) findViewById(R.id.farFocalVal);
                    farFocal.setText("Invalid aperture");

                    TextView depth = (TextView) findViewById(R.id.DepthOfFieldVal);
                    depth.setText("Invalid aperture");
                }

                else {

                    DepthOfFieldCalculator cal = new DepthOfFieldCalculator(manager.getLens(id), distanceToDouble, apertureToDouble);


                    TextView hyperFocal = (TextView) findViewById(R.id.HyperfocalVal);
                    hyperFocal.setText(formatM(cal.hyperfocalDistance() / 1000));

                    TextView nearFocal = (TextView) findViewById(R.id.nearFocalVal);
                    nearFocal.setText(formatM(cal.nearFocalPoint() / 1000));

                    TextView farFocal = (TextView) findViewById(R.id.farFocalVal);
                    farFocal.setText(formatM(cal.farFocalPoint() / 1000));

                    TextView depth = (TextView) findViewById(R.id.DepthOfFieldVal);
                    depth.setText(formatM(cal.depthOfField() / 1000));
                }



            }
        });
    }
}
