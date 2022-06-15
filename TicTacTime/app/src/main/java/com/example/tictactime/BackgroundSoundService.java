package com.example.tictactime;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.widget.Toast;
import androidx.annotation.Nullable;

// BackgroundSoundService - Using MediaPlayer feature from Android Studio to insert music
public class BackgroundSoundService extends Service {
    MediaPlayer mediaPlayer;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
    @Override
    public void onCreate() {
        super.onCreate();
        mediaPlayer = MediaPlayer.create(this, R.raw.laguapp);  // Calling Audio File
        mediaPlayer.setLooping(true); // Loops music
        mediaPlayer.setVolume(100, 100); // Balanced mono and stereo volume
    }
    public int onStartCommand(Intent intent, int flags, int startId) {
        mediaPlayer.start();    // Starting the music
        Toast.makeText(getApplicationContext(), "Upping up the Music!",    Toast.LENGTH_SHORT).show();  // Pop up text
        return startId;
    }
    public void onStart(Intent intent, int startId) {
    }
    @Override
    public void onDestroy() {
        mediaPlayer.stop();
        mediaPlayer.release();
    }
    @Override
    public void onLowMemory() {
    }
}