package com.example.androidepthoffield;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.androidepthoffield.model.Len;
import com.example.androidepthoffield.model.LensManager;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_AddNewLens = 1911;
    private LensManager manager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Create list of Lens
        manager = LensManager.getInstance();
        manager.add(new Len("Canon", 1.8, 50));
        manager.add(new Len("Tamron", 2.8, 90));
        manager.add(new Len("Sigma", 2.8, 200));
        manager.add(new Len("Nikon", 4, 200));

        populateListView();
        registerClickCallBack();
        setupAddMoreLensButton();

    }

    private void populateListView() {

        List<String> lensInfor= new ArrayList<>();
        for (Len len: manager){
            lensInfor.add(len.lenInfor());
        }

        //Build Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                R.layout.list_of_lens,
                lensInfor);
        // Configure the List View.
        ListView list = (ListView) findViewById(R.id.listViewMain);
        list.setAdapter(adapter);

    }

    private void registerClickCallBack() {
        ListView list =(ListView)findViewById(R.id.listViewMain);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String lenDetails = manager.getLens((int)id).lenInfor();
                Intent intent = new Intent(MainActivity.this, DepthOfFieldActivity.class);
                intent.putExtra("len details",lenDetails);
                intent.putExtra("id",(int)id);
                startActivity(intent);
            }
        });
    }

    private void setupAddMoreLensButton() {
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = makeIntentForNewLens(MainActivity.this);
                startActivityForResult(intent, REQUEST_CODE_AddNewLens);
            }
        });

    }

    public static Intent makeIntentForNewLens(Context context) {
        return AddNewLens.makeIntent(context);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @SuppressLint("MissingSuperCall")
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        switch (requestCode){
            case REQUEST_CODE_AddNewLens:
                if(resultCode == Activity.RESULT_OK){
                    //Get the Lens Infor
                    String lensName = data.getStringExtra("lensName");
                    Integer lensFocalLength = data.getIntExtra("lensFocalLength",0);
                    Double lensAperture = data.getDoubleExtra("lensAperture",0);
                    //Add new Lens
                    manager.add(new Len(lensName,lensAperture,lensFocalLength));
                    populateListView();
                }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
