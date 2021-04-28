package com.escandiuzzi.android.tictactoe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class WinnerActivity extends AppCompatActivity {

    TextView tvTitle;

    Button btnPlayAgain;

    ImageView ivWinner;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winner);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ivWinner = (ImageView) findViewById(R.id.ivWinner);
        btnPlayAgain = (Button) findViewById(R.id.btnPlayAgain);

        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("winnerBundle");

        byte[] byteArray = bundle.getByteArray("image");
        Bitmap image = BitmapFactory.decodeByteArray(byteArray, 0, byteArray.length);

        String winnerName = bundle.getString("winnerName");

        tvTitle.setText(winnerName + " Won!");

        ivWinner.setImageBitmap(image);

        btnPlayAgain.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                Intent mainScreenIntent = new Intent(getBaseContext(), MainActivity.class);
                startActivity(mainScreenIntent);
            }
        });

    }
}