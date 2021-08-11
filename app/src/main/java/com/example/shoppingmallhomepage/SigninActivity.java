package com.example.shoppingmallhomepage;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SigninActivity extends AppCompatActivity {
    Button btnSignUp,btnSignIn;
    Intent intent;
    Intent intent1;
    TextView textViewResetPass;
    EditText editTextUser,editTextPass;
    DBHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);
        Log.d("lifecycle","onCreate invoked");

        db = new DBHelper(this);
        btnSignUp = (Button)findViewById(R.id.btn_signUp);
        btnSignIn = (Button)findViewById(R.id.btn_signLogin) ;
        textViewResetPass=(TextView)findViewById(R.id.textview_forgotpass);
        editTextUser = (EditText)findViewById(R.id.editText_user);
        editTextPass = (EditText)findViewById(R.id.editText_password);
        btnSignUp.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                intent = new Intent(getApplicationContext(),SignupActivity.class);
                startActivity(intent);

            }
        });
        textViewResetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent1 = new Intent(getApplicationContext(),ForgotPassActivity.class);

                startActivity(intent1);
            }
        });
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUser.getText().toString().trim();
                String pass = editTextPass.getText().toString().trim();
                Boolean res= db.checkUser(user,pass);
                if(res==true)
                {
                    Toast.makeText(SigninActivity.this,"Successfully loggin",Toast.LENGTH_SHORT).show();
                    Intent MainPage = new Intent(SigninActivity.this,MainActivity.class);
                    MainPage.putExtra("user",user);
                    startActivity(MainPage);
                }
                else
                {
                    Toast.makeText(SigninActivity.this,"Username or Password be incorrected",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
