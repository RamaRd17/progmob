package com.example.mykomponenpc;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private RecyclerView rvCategory;
    private ArrayList<komponen_pc> list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvCategory = findViewById(R.id.rv_category);
        rvCategory.setHasFixedSize(true);

        list.addAll(komponen_pcData.getListData());
        showRecyclerGrid();
    }

    private void showRecyclerGrid(){
        rvCategory.setLayoutManager(new GridLayoutManager(this, 2));
        Gridkomponen_pcAdapter Gridkomponen_pcAdapter = new Gridkomponen_pcAdapter(this);
        Gridkomponen_pcAdapter.setListKomponen(list);
        rvCategory.setAdapter(Gridkomponen_pcAdapter);

        ItemClickSupport.addTo(rvCategory).setOnItemClickListener(new ItemClickSupport.OnItemClickListener() {
            @Override
            public void onItemClicked(RecyclerView recyclerView, int position, View v) {
                showSelectedResep(list.get(position));
            }
        });
    }
    private void showSelectedResep(komponen_pc komponen){
        Toast.makeText(this, "Kamu memilih "+komponen.getJudul(), Toast.LENGTH_LONG).show();

        Intent intent = new Intent(MainActivity.this, DetailActivity.class);
        intent.putExtra("judul", komponen.getJudul());
        intent.putExtra("isi", komponen.getIsi());
        intent.putExtra("photo", komponen.getPhoto());
        startActivity(intent);
    }
}
