package com.example.anotherone;

import android.app.ProgressDialog;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.anotherone.model.Info;
import com.example.anotherone.model.data;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class create_account extends AppCompatActivity {

    private DatabaseReference databaseReference;

    private FirebaseAuth mAuth;

    private ProgressDialog dialog;

    private Button sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        databaseReference = FirebaseDatabase.getInstance().getReference("Users");
        mAuth = FirebaseAuth.getInstance();

        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading...");
        dialog.setMessage("Please Wait ...");


        sign = (Button) findViewById(R.id.btsignup);
        sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.show();
                create();
                dialog.dismiss();
            }
        });

    }

    public void create(){
        EditText Email = findViewById(R.id.etemail);
        EditText Password1 = findViewById(R.id.etpass1);
        EditText Password2 = findViewById(R.id.etpass2);

        String email = Email.getText().toString().trim();
        String password = Password1.getText().toString().trim();
        String password2 = Password2.getText().toString().trim();

        if(TextUtils.isEmpty(email)){

            Toast.makeText(this,"please enter your email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password)){

            Toast.makeText(this,"please enter your password",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(password2)){

            Toast.makeText(this,"please confirm your password",Toast.LENGTH_LONG).show();
            return;
        }
        if(!(password.equals(password2))) {
            Toast.makeText(this,"please insert same password",Toast.LENGTH_LONG).show();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(getApplicationContext(),"Registered",Toast.LENGTH_SHORT).show();
                        Fillinfo();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getApplicationContext(),"Not Registered "+e.getMessage(),Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void Fillinfo(){
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");

        EditText Email = findViewById(R.id.etemail);
        EditText Password1 = findViewById(R.id.etpass1);
        EditText Password2 = findViewById(R.id.etpass2);
        EditText Name = findViewById(R.id.etfullname);
        EditText ELPAID = findViewById(R.id.etelpaid);
        EditText WaterID = findViewById(R.id.etwatid);
        EditText CBENumber = findViewById(R.id.etcbeno);
        EditText CBEPassword = findViewById(R.id.etcbepass);


        String email = Email.getText().toString().trim();
        String password = Password1.getText().toString().trim();
        String password2 = Password2.getText().toString().trim();
        String name = Name.getText().toString().trim();
        String elpaId = ELPAID.getText().toString().trim();
        String waterId = WaterID.getText().toString().trim();
        String CBEnumber = CBENumber.getText().toString().trim();
        String CBEpassword = CBEPassword.getText().toString().trim();
        String Uid = elpaId +waterId;
        String ELPAStatus = "false";
        String WaterStatus = "false";

        int elppausage = 0;
        int waterusage = 0;
        int elpalastmonth = 0;
        int waterlastmonth = 0;
        int elpacurrentmonth = 0;
        int watercurrentmonth = 0;


        if(TextUtils.isEmpty(name)){

            Toast.makeText(this,"please enter your email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(elpaId)){

            Toast.makeText(this,"please enter your password",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(waterId)){

            Toast.makeText(this,"please confirm your password",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(CBEnumber)){

            Toast.makeText(this,"please enter your email",Toast.LENGTH_LONG).show();
            return;
        }
        if(TextUtils.isEmpty(CBEpassword)){

            Toast.makeText(this,"please enter your password",Toast.LENGTH_LONG).show();
            return;
        }

        data data = new data(Uid,elppausage,waterusage,elpalastmonth,elpacurrentmonth,waterlastmonth,watercurrentmonth);
        Info info = new Info(name,email,password,elpaId,waterId,ELPAStatus,WaterStatus,CBEnumber,CBEpassword);

        databaseReference.child(Uid).child("Data").setValue(data);
        databaseReference.child(Uid).child("Information").setValue(info);

    }
}
