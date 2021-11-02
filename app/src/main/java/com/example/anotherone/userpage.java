package com.example.anotherone;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class userpage extends AppCompatActivity {

    FirebaseAuth mAuth;

    String ID ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userpage);

        Button login = findViewById(R.id.btloginuser);
        TextView create = findViewById(R.id.tvcreate);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Auth();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),create_account.class));
            }
        });
    }

    public  void Auth(){

        mAuth = FirebaseAuth.getInstance();

        EditText Email  = findViewById(R.id.etloginemail);
        EditText Password = findViewById(R.id.etloginpass);
        EditText id = findViewById(R.id.etuidloginuser);

        String email = Email.getText().toString().trim();
        String password = Password.getText().toString().trim();
        final String Uid = id.getText().toString().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"please enter your email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){

            Toast.makeText(this,"please enter your password",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(Uid)){

            Toast.makeText(this,"please enter your email",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(),"signed in",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            ID = Uid;
                        }
                        else {
                            Toast.makeText(getApplicationContext(),"try again",Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                });

    }
}
