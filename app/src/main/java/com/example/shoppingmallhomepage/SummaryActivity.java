package com.example.shoppingmallhomepage;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.loader.app.LoaderManager;
import androidx.loader.content.CursorLoader;
import androidx.loader.content.Loader;

public class SummaryActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor>{
    public CartAdapter mAdapter;
    public static final int LOADER = 0;
    public  DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sumary);

        TextView totalprice = findViewById(R.id.totalprice);
        Button clearthedata = findViewById(R.id.clearthedatabase);
        db = new DBHelper(this);

        clearthedata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContentResolver().delete(OrderContract.OrderEntry.CONTENT_URI, null, null);
            }
        });

        //getLoaderManager().initLoader(LOADER, null, (android.app.LoaderManager.LoaderCallbacks<Cursor>) this);
        LoaderManager.getInstance(this).initLoader(0, null, this);

        ListView listView = findViewById(R.id.list);
        mAdapter = new CartAdapter(this, null);
        listView.setAdapter(mAdapter);



        //totalprice.setText(""+db.getTotalPrice());
        int total =db.getTotalPrice();
        totalprice.setText(Integer.toString(total));
    }

    @NonNull
    @Override
    public Loader<Cursor> onCreateLoader(int id, @Nullable Bundle args) {
        String[] projection = {OrderContract.OrderEntry._ID,
                OrderContract.OrderEntry.COLUMN_NAME,
                OrderContract.OrderEntry.COLUMN_PRICE,
                OrderContract.OrderEntry.COLUMN_QUANTITY,
                OrderContract.OrderEntry.COLUMN_CREAM,
                OrderContract.OrderEntry.COLUMN_HASTOPPING
        };

        return new CursorLoader(this, OrderContract.OrderEntry.CONTENT_URI,
                projection,
                null,
                null,
                null);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<Cursor> loader, Cursor data) {
        mAdapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(@NonNull Loader<Cursor> loader) {

        mAdapter.swapCursor(null);
    }
}
