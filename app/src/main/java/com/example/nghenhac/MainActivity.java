package com.example.nghenhac;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView txtTilte,txtTimestart,getTxtTimeend;
    SeekBar seekBar;
    ImageView btnPre, btnPlay, btnStop, btnNext, btnPause;
    ArrayList<Song> arrSong;
    MediaPlayer mediaPlayer;
    Animation animation;
    ImageView diaCD;
    int vitri=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AnhXa();
        AddSong();
        animation= AnimationUtils.loadAnimation(this,R.anim.rotale);

        ClickDS();

        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mediaPlayer.stop();
                mediaPlayer.release();
                btnPlay.setImageResource(R.drawable.play);
                KhoiTao();
            }
        });
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaPlayer.isPlaying()){
                    mediaPlayer.pause();
                    btnPlay.setImageResource(R.drawable.play);
                }else{
                    mediaPlayer.start();
                    btnPlay.setImageResource(R.drawable.pause);
                }
                SetTimeEnd();
                UpdateTime();

                diaCD.startAnimation(animation);
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitri++;
                if(vitri>arrSong.size()-1) vitri=0;
                if(mediaPlayer.isPlaying())mediaPlayer.stop();
                KhoiTao();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                SetTimeEnd();
                UpdateTime();
                diaCD.startAnimation(animation);
            }
        });
        btnPre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitri--;
                if(vitri < 0) vitri=arrSong.size()-1;
                if(mediaPlayer.isPlaying())mediaPlayer.stop();
                KhoiTao();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                SetTimeEnd();
                UpdateTime();
                diaCD.startAnimation(animation);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mediaPlayer.seekTo(seekBar.getProgress());
            }
        });
    }

    private void AddSong() {
        arrSong=new ArrayList<>();
        arrSong.add(new Song("Cảm giác lúc ấy sẽ ra sao",R.raw.camgiaclucayserasao));
        arrSong.add(new Song("Buồn của anh",R.raw.buon_cua_anh));
        arrSong.add(new Song("Biết không em",R.raw.biet_khong_em));
        arrSong.add(new Song("Cánh hồng phai",R.raw.canhhongphai));
        arrSong.add(new Song("Càng níu giữ càng dễ mất",R.raw.cangniugiucangdemat));
    }

    public void AnhXa(){
        txtTilte=(TextView)findViewById(R.id.title);
        txtTimestart=(TextView)findViewById(R.id.timestart);
        getTxtTimeend=(TextView)findViewById(R.id.timeend);
        seekBar=(SeekBar)findViewById(R.id.seekbar);
        btnPre=(ImageView)findViewById(R.id.btnpre);
        btnPlay=(ImageView)findViewById(R.id.btnplay);
        btnStop=(ImageView)findViewById(R.id.btnstop);
        btnNext=(ImageView)findViewById(R.id.btnnext);
        diaCD=(ImageView)findViewById(R.id.cd);

    }
    private void KhoiTao(){

        mediaPlayer=MediaPlayer.create(MainActivity.this, arrSong.get(vitri).getFile());
        txtTilte.setText(arrSong.get(vitri).getTilte());
    }
    private void SetTimeEnd(){
        SimpleDateFormat dinhdang=new SimpleDateFormat("mm:ss");
        getTxtTimeend.setText(dinhdang.format(mediaPlayer.getDuration()));
        seekBar.setMax(mediaPlayer.getDuration());
    }
    private void UpdateTime(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat dinhdangGio=new SimpleDateFormat("mm:ss");
                txtTimestart.setText(dinhdangGio.format(mediaPlayer.getCurrentPosition()));
                seekBar.setProgress(mediaPlayer.getCurrentPosition());//update time cho thanh sêkbar
                //kiểm tra nếu kết thúc thì sang bài hát tiếp theo
                mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        vitri++;
                        if(vitri>arrSong.size()-1) vitri=0;
                        if(mediaPlayer.isPlaying())mediaPlayer.stop();
                        KhoiTao();
                        mediaPlayer.start();
                        btnPlay.setImageResource(R.drawable.pause);
                        SetTimeEnd();
                        UpdateTime();
                    }
                });
                handler.postDelayed(this, 500);
            }
        },100);
    }
    private void ClickDS(){

                Bundle bundle = getIntent().getExtras();
                vitri = bundle.getInt("vitri");
                //if(mediaPlayer.isPlaying())mediaPlayer.stop();
                KhoiTao();
                mediaPlayer.start();
                btnPlay.setImageResource(R.drawable.pause);
                SetTimeEnd();
                UpdateTime();
        }

}