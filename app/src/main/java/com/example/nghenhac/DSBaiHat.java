package com.example.nghenhac;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

public class DSBaiHat extends AppCompatActivity {
    ListView listView;
    ArrayList<Song> arrSong;
    Adapter adapter;
    int vitri=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_d_s_bai_hat);
        listView=(ListView)findViewById(R.id.listbaihat);
        arrSong=new ArrayList<Song>();
        AddSong();
        adapter=new Adapter(this,arrSong);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent=new Intent(DSBaiHat.this, MainActivity.class);
                vitri=position;
                intent.putExtra("vitri",vitri);
                startActivity(intent);
            }
        });
    }
    private void AddSong() {
        arrSong.add(new Song("Cảm giác lúc ấy sẽ ra sao",R.raw.camgiaclucayserasao));
        arrSong.add(new Song("Buồn của anh",R.raw.buon_cua_anh));
        arrSong.add(new Song("Biết không em",R.raw.biet_khong_em));
        arrSong.add(new Song("Cánh hồng phai",R.raw.canhhongphai));
        arrSong.add(new Song("Càng níu giữ càng dễ mất",R.raw.cangniugiucangdemat));
    }
}