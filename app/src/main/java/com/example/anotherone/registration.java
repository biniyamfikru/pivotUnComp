package com.example.anotherone;

import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anotherone.model.Info;
import com.example.anotherone.model.data;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.util.Date;
import java.util.Objects;

public class registration extends AppCompatActivity {

    private Button bt;
    private DatabaseReference databaseReference;

    private RecyclerView recyclerView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        Toolbar toolbar = findViewById(R.id.hometool);
//        setSupportActionBar(toolbar);
//        Objects.requireNonNull(getSupportActionBar()).setTitle("bina");

        data data = new data();
//        String Uid = data.getUid().toString();
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users");
        databaseReference.keepSynced(true);
        bt =(Button) findViewById(R.id.button);

        recyclerView = (RecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        layoutManager.setStackFromEnd(true);
        layoutManager.setReverseLayout(true);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                onStart();
                cusdia();
            }
        });
    }

    private void cusdia(){
        AlertDialog.Builder dialog = new AlertDialog.Builder(registration.this);

        LayoutInflater inflater = LayoutInflater.from(registration.this);

        View view = inflater.inflate(R.layout.input,null);

        final AlertDialog mydialog = dialog.create();

        mydialog.setView(view);

        final EditText ID = view.findViewById(R.id.etid);
        final EditText etemail = view.findViewById(R.id.etemailinput);
        final EditText elpa_usage = view.findViewById(R.id.elpausage);
        final EditText water_usage = view.findViewById(R.id.watusage);
        final EditText elpalast = view.findViewById(R.id.elpalast);
        final EditText waterlast = view.findViewById(R.id.watlast);
        final EditText elpacurrent = view.findViewById(R.id.elpathis);
        final EditText watercurrent = view.findViewById(R.id.watthis);


        Button save = view.findViewById(R.id.buttonsave);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                String Uid = ID.getText().toString().trim();
                String elpausage = elpa_usage.getText().toString().trim();
                String watusage = water_usage.getText().toString().trim();
                String elpa_last = elpalast.getText().toString().trim();
                String wat_last = waterlast.getText().toString().trim();
                String elpathis = elpacurrent.getText().toString().trim();
                String waterthis = watercurrent.getText().toString().trim();
                final String Email = etemail.getText().toString().trim();


                String ELPAStatus = "false";
                String WaterStatus = "false";

                int elppausage = Integer.parseInt(elpausage);
                int waterusage = Integer.parseInt(watusage);
                int elpalastmonth = Integer.parseInt(elpa_last);
                int waterlastmonth = Integer.parseInt(wat_last);
                int elpacurrentmonth = Integer.parseInt(elpathis);
                int watercurrentmonth = Integer.parseInt(waterthis);




                data data = new data(Uid,elppausage,waterusage,elpalastmonth,elpacurrentmonth,waterlastmonth,watercurrentmonth);
                Info info = new Info(ELPAStatus,WaterStatus);


                databaseReference.child(Uid).child("Information").addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        Info info1 = dataSnapshot.getValue(Info.class);
                        String email = info1.getEmail();
                        if(email.equals(Email)){
                            Toast.makeText(getApplicationContext(),"please Enter Appropriate user Info",Toast.LENGTH_LONG).show();
                            return;
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

                databaseReference.child(Uid).setValue(data);

                Toast.makeText(getApplicationContext(),"added",Toast.LENGTH_SHORT).show();

                mydialog.dismiss();
            }
        });

        mydialog.show();

    }


    @Override
    protected void onStart() {
        super.onStart();


        FirebaseRecyclerAdapter<data, MyViewHolder> adapter = new FirebaseRecyclerAdapter<data, MyViewHolder>(
                data.class,
                R.layout.item_list,
                MyViewHolder.class,
                databaseReference

        ) {
            @Override
            protected void populateViewHolder(MyViewHolder viewHolder, data model, int position) {

                viewHolder.setUid(model.getUid());
                viewHolder.setELPAUsage(model.getElpa_usage());
                viewHolder.setWaterUsage(model.getWater_usage());
                viewHolder.setElpaLastMonth(model.getElpa_last_month());
                viewHolder.setElpaThistMonth(model.getElpa_current_month());
                viewHolder.setWaterLastMonth(model.getWater_last_month());
                viewHolder.setWaterThisMonth(model.getWater_current_month());
            }
        };

//        recyclerView.setAdapter(adapter);
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder{

        View myview;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            myview=itemView;
        }

        public void setUid(String Uid){
            TextView uid = myview.findViewById(R.id.tvuid);
            uid.setText(Uid);

        }

        public void setELPAUsage(int Usage){
            TextView elpausage = myview.findViewById(R.id.tvelpausage);
//            String string= String.valueOf(Usage);
            elpausage.setText(Usage);
        }
        public void setWaterUsage(int Usage){
            TextView waterusage = myview.findViewById(R.id.tvwatusage);
//            String string= String.valueOf(Usage);
            waterusage.setText(Usage);
        }
        public void setElpaLastMonth(int last){
            TextView elpalast = myview.findViewById(R.id.tvelpalast);
//            String string = String.valueOf(last);
            elpalast.setText(last);
        }
        public void setWaterLastMonth(int last){
            TextView waterlast = myview.findViewById(R.id.tvwatlast);
//            String string = String.valueOf(last);
            waterlast.setText(last);
        }
        public void setElpaThistMonth(int thismonth){
            TextView elpathis = myview.findViewById(R.id.elpatvthis);
//            String string = String.valueOf(thismonth);
            elpathis.setText(thismonth);
        }
        public void setWaterThisMonth(int thismonth){
            TextView waterlast = myview.findViewById(R.id.watvthis);
//            String string = String.valueOf(thismonth);
            waterlast.setText(thismonth);
        }
    }

}
