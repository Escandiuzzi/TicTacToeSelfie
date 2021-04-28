package com.escandiuzzi.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;

public class WinnerActivity extends AppCompatActivity {

    ImageView ivWinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        ivWinner = (ImageView) findViewById(R.id.ivWinner);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("winnerBundle");

        byte[] byteArray = bundle.getByteArray("image");
        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        ivWinner.setImageBitmap(image);

        try {
            wait(3000);
            Intent mainScreenIntent = new Intent(getBaseContext(), MainActivity.class);
            startActivity(intent);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}