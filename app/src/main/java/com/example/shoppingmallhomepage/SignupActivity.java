package com.example.shoppingmallhomepage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupActivity extends AppCompatActivity {
    DBHelper db;
    Button BackLogin,btnSignUp;
    EditText editTextUser,editTextPass,editTestRepass,editTextEmail;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        db = new DBHelper(this);
        BackLogin = (Button)findViewById(R.id.btn_logIn);
        editTextUser = (EditText)findViewById(R.id.editText_user) ;
        editTextPass = (EditText)findViewById(R.id.editText_password) ;
        editTestRepass = (EditText)findViewById(R.id.editText_repassword) ;
        editTextEmail = (EditText)findViewById(R.id.editText_email) ;
        btnSignUp = (Button)findViewById(R.id.btn_signUp);
        Intent intent = getIntent();

        BackLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent BackIntent = new Intent();
                setResult(RESULT_OK,BackIntent);
                finish();
            }
        });
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = editTextUser.getText().toString().trim();
                String pwd = editTextPass.getText().toString().trim();
                String re_pwd = editTestRepass.getText().toString().trim();
                String email = editTextEmail.getText().toString().trim();
                if(pwd.equals(re_pwd)){
                    long val = db.addUser(user,pwd,email);
                    if(val > 0){
                        Toast.makeText(SignupActivity.this,"You have registered",Toast.LENGTH_SHORT).show();
                        Intent moveToLogin = new Intent(SignupActivity.this,SigninActivity.class);
                        startActivity(moveToLogin);
                    }
                    else{
                        Toast.makeText(SignupActivity.this,"Registeration Error",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(SignupActivity.this,"Password is not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
