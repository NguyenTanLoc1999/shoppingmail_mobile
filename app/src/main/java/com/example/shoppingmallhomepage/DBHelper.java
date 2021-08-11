package com.example.shoppingmallhomepage;

import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME ="ministop.db";
    //public static final String TABLE_NAME ="user";
    public static final String COL_1 ="ID";
    public static final String COL_2 ="username";
    public static final String COL_3 ="password";
    public static final String COL_4 ="email";

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE user (ID INTEGER PRIMARY  KEY AUTOINCREMENT, username TEXT, password TEXT, email TEXT)");
        String SQL_TABLE = "CREATE TABLE " + "orderdrink" + " ( "
                + OrderContract.OrderEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                +  OrderContract.OrderEntry.COLUMN_NAME + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_QUANTITY + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_PRICE + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_HASTOPPING + " TEXT NOT NULL, "
                +  OrderContract.OrderEntry.COLUMN_CREAM + " TEXT NOT NULL);";

        sqLiteDatabase.execSQL(SQL_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL(" DROP TABLE IF EXISTS " + "user");
        onCreate(sqLiteDatabase);
    }

    public long addUser(String user, String password,String email){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",user);
        contentValues.put("password",password);
        contentValues.put("email",email);
        long res = db.insert("user",null,contentValues);
        db.close();
        return  res;
    }

    public boolean checkUser(String username, String password){
        String[] columns = { COL_1 };
        SQLiteDatabase db = getReadableDatabase();
        String selection = COL_2 + "=?" + " and " + COL_3 + "=?";
        String[] selectionArgs = { username, password };
        Cursor cursor = db.query("user",columns,selection,selectionArgs,null,null,null);
        int count = cursor.getCount();
        cursor.close();
        db.close();

        if(count>0)
            return  true;
        else
            return  false;
    }
    public int getTotalPrice(){
//        int sum =0;
//        SQLiteDatabase db = this.getWritableDatabase();
//        Cursor res = db.rawQuery( "SELECT SUM(" + (OrderContract.OrderEntry.COLUMN_PRICE) + ") as Total FROM "+"orderdrink", null );
//        if(res.moveToFirst())
//        {
//            sum = res.getInt(res.getColumnIndex("Total"));
//        }
//        else{
//            sum =-1;
//        }
//        res.close();
//        return sum;
        SQLiteDatabase db = this.getWritableDatabase();
        String sql = "SELECT SUM(" + (OrderContract.OrderEntry.COLUMN_PRICE) + ") as Total FROM "+"orderdrink";
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor.moveToFirst()) {
            return cursor.getInt(0);
        }
        return 0;
    }
}
