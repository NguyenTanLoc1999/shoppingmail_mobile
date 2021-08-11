package com.example.shoppingmallhomepage;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Spinner spinner;
    ImageView imageViewDiCho;
    Intent intent;
    TextView txtuser,txtlogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        spinner = findViewById(R.id.spinner);
        imageViewDiCho = findViewById(R.id.ivDiCho);
        txtuser=(TextView)findViewById(R.id.txtusername);
        txtlogout=(TextView)findViewById(R.id.txtlogout);

        Intent getusername = getIntent();
        String message = getusername.getStringExtra("user");
        txtuser.setText(message);

        final List<String> categories = new ArrayList<String>();

        categories.add("TP.HCM");
        categories.add("Hà Nội");
        categories.add("Đà Nẵng");
        categories.add("Cà Mau");
        categories.add("Đồng Nai");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, R.layout.custom_textview_to_spinner, categories);
        spinner.setAdapter(dataAdapter);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "Selected : "+ categories.get(position), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });



        imageViewDiCho.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),MainActivityDiCho.class);

                startActivity(intent);
            }
        });
        txtlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent signin = new Intent(getApplicationContext(),SigninActivity.class);
                startActivity(signin);
            }
        });

    }
}