package com.fitzafful.gliveportal.ui.authentication;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fitzafful.gliveportal.R;
import com.fitzafful.gliveportal.ui.StudentMenuActivity;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_login);

        final EditText email = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        final Button login = findViewById(R.id.login);

            login.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    String Email = email.getText().toString();
                    String Password = password.getText().toString();
                    if (Email.isEmpty()) {
                        email.setError("Username required");
                    }else if (Password.isEmpty()) {
                        password.setError("Password required");
                    } else {
                        if((Email.equalsIgnoreCase("jdoe@gmail.com"))&&(Password.equalsIgnoreCase("password"))){
                            Snackbar.make(login, "Successfully logged in.", Snackbar.LENGTH_SHORT).show();
                            Intent intent1 = new Intent(getApplicationContext(), StudentMenuActivity.class);

                            SharedPreferences sharedPreferences = Login.this.getSharedPreferences("data", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putBoolean("hasLoggedIn", true);
                            editor.putString("username", email.getText().toString());
                            editor.apply();

                            startActivity(intent1);
                            finish();
                        }else{

                            Snackbar.make(login, "Kindly check user credentials and try again.", Snackbar.LENGTH_SHORT).show();
                        }


                    }

                }

            });
    }
}
