package com.example.recyclerview;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListMhsActivity extends AppCompatActivity {

    MhsAdapter mhsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listmhs);

        //ListView lvname = (ListView) findViewById(R.id.lvname);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        ArrayList<MhsModel> mhsList = getIntent().getExtras().getParcelableArrayList("mhsList");

        mhsAdapter = new MhsAdapter(mhsList, new MhsAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(ArrayList<MhsModel>mhsList, int position) {
                AlertDialog.Builder dialog = new AlertDialog.Builder(ListMhsActivity.this);
                dialog.setTitle("Pilihan");
                dialog.setItems(new CharSequence[]{"Hapus", "Edit"}, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int item) {

                        DbHelper db = new DbHelper(getApplicationContext());
                        MhsModel mm = mhsList.get(position);

                        switch (item){
                            case 0:
                                boolean stts = db.hapus(mm.getId());
                                if(stts){
                                    mhsAdapter.removeItem(position);
                                    Toast.makeText(getApplicationContext(),"Data Berhasil Di Hapus",Toast.LENGTH_SHORT).show();
                                }
                                break;
                            case 1:

                                Intent intent_main = new Intent(ListMhsActivity.this, MainActivity.class);
                                intent_main.putExtra("mhsData", mm );

                                startActivity(intent_main); Toast.makeText(getApplicationContext(),"Data Berhasil Di Edit",Toast.LENGTH_SHORT).show();
                                break;
                        }
                    }
                });
                dialog.create().show();
            }
        });

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(ListMhsActivity.this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mhsAdapter);

        FloatingActionButton fabTambah = findViewById(R.id.fabTambah);
        fabTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListMhsActivity.this, MainActivity.class));
            }
        });

//        if(mhsList.isEmpty()){
//            mhsList.add("Datamu ndi?");
//        }

//        ArrayAdapter<String> ad_name = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, list_name);
//
//        lvname.setAdapter(ad_name);

    }
}