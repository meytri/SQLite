package com.example.recyclerview;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<MhsModel> mhsList;
    MhsModel mm ;
    DbHelper db ;
    boolean isEdit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText edName = (EditText) findViewById(R.id.edName);
        EditText edNim = (EditText) findViewById(R.id.edNim);
        EditText edPhone = (EditText) findViewById(R.id.edPhone);

        Button btnSave = (Button) findViewById(R.id.btnSave);

        mhsList = new ArrayList<>();

        isEdit = false;

        Intent intent_main = getIntent();
        if(intent_main.hasExtra("mhsData")){
            mm = intent_main.getExtras().getParcelable("mhsData");
            edName.setText(mm.getName());
            edNim.setText(mm.getNim());
            edPhone.setText(mm.getPhone());

            isEdit = true;

            btnSave.setBackgroundColor(Color.BLUE);
            btnSave.setText("Edit");
        }



        db = new DbHelper(getApplicationContext());

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input_name = edName.getText().toString();
                String input_nim = edNim.getText().toString();
                String input_phone = edPhone.getText().toString();

                if(input_name.isEmpty() || input_nim.isEmpty() || input_phone.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Tidak ada data",Toast.LENGTH_SHORT).show();
                }else{
                    //mhsList.add(new MhsModel(-1, input_name, input_nim, input_phone));



                    boolean stts;

                    if(!isEdit){
                        mm = new MhsModel(-1, input_name, input_nim, input_phone);
                        stts = db.simpan(mm);

                        edName.setText("");
                        edNim.setText("");
                        edPhone.setText("");

                    }else{
                        mm = new MhsModel(mm.getId(), input_name, input_nim, input_phone);
                        stts = db.ubah(mm);
                    }


                    if(stts){

                        Toast.makeText(getApplicationContext(),"Data Berhasil Disimpan",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getApplicationContext(),"Data Gagal diimpan",Toast.LENGTH_SHORT).show();
                    }



                   // int_list.putParcelableArrayListExtra("mhsList", mhsList);
                   // startActivity(int_list);
                }
            }
        });

        Button btnView = (Button) findViewById(R.id.btnView);
        btnView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mhsList = db.list();

                if(mhsList.isEmpty()){
                    Toast.makeText(getApplicationContext(),"Tidak Ada Data?",Toast.LENGTH_SHORT).show();
                }else{
                    Intent int_list = new Intent(MainActivity.this, ListMhsActivity.class);
                    int_list.putParcelableArrayListExtra("mhsList", mhsList);
                    startActivity(int_list);
                }
            }
        });
    }
}