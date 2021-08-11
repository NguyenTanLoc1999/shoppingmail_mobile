package com.example.shoppingmallhomepage;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;

public class MainActivityDiCho extends AppCompatActivity {

    List<ModelDiCho> modelList;
    RecyclerView recyclerView;
    OrderAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dicho);

        // creating an arraylist

        modelList = new ArrayList<>();
        modelList.add(new ModelDiCho("Green Tea", getString(R.string.greentea), R.drawable.greentea ));
        modelList.add(new ModelDiCho("Latte", getString(R.string.latte), R.drawable.late_460));
        modelList.add(new ModelDiCho("Orange Smoothie", getString(R.string.orangesmoothie), R.drawable.orange));
        modelList.add(new ModelDiCho("Bubble Tea", getString(R.string.bubbletea), R.drawable.milk));
        modelList.add(new ModelDiCho("Orange Vanilla", getString(R.string.orangevanilla), R.drawable.orangevanilla));
        modelList.add(new ModelDiCho("Cappucino", getString(R.string.cappcuni), R.drawable.cappcunio));
        modelList.add(new ModelDiCho("Thai Tea", getString(R.string.thaitea), R.drawable.thaitea));
        modelList.add(new ModelDiCho("Tea", getString(R.string.tea), R.drawable.tea));
        modelList.add(new ModelDiCho("Matcha", getString(R.string.match), R.drawable.match));

        // recyclerview
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(null));
        // adapter
        mAdapter = new OrderAdapter(this, modelList);
        recyclerView.setAdapter(mAdapter);




    }
}
