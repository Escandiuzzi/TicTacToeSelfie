package com.escandiuzzi.android.tictactoe;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    private final int CAMERA_1 = 1;
    private final int CAMERA_2 = 2;
    private final int WINNER = 3;

    private final int DELAYTIME = 3000;

    int[][] board = new int[3][3];

    ImageButton tile00;
    ImageButton tile01;
    ImageButton tile02;

    ImageButton tile10;
    ImageButton tile11;
    ImageButton tile12;

    ImageButton tile20;
    ImageButton tile21;
    ImageButton tile22;

    ImageButton ibPlayer1;
    ImageButton ibPlayer2;
    Button clearBtb;

    boolean player1PhotoSelected;
    boolean player2PhotoSelected;
    boolean selectedFirstPlayer;
    boolean gameOver;

    File photoFile;

    Bitmap player1Photo;
    Bitmap player2Photo;

    int color;
    int turn;
    int selectedPlayer;
    int plays;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tile00 = (ImageButton) findViewById(R.id.ibTile00);
        tile01 = (ImageButton) findViewById(R.id.ibTile01);
        tile02 = (ImageButton) findViewById(R.id.ibTile02);

        tile10 = (ImageButton) findViewById(R.id.ibTile10);
        tile11 = (ImageButton) findViewById(R.id.ibTile11);
        tile12 = (ImageButton) findViewById(R.id.ibTile12);

        tile20 = (ImageButton) findViewById(R.id.ibTile20);
        tile21 = (ImageButton) findViewById(R.id.ibTile21);
        tile22 = (ImageButton) findViewById(R.id.ibTile22);

        ibPlayer1 = (ImageButton) findViewById(R.id.ibPlayer1);
        ibPlayer2 = (ImageButton) findViewById(R.id.ibPlayer2);
        clearBtb = (Button) findViewById(R.id.btnClear);

        color = Color.argb(255,225,132,158);

        resetGame(true);

        tile00.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[0][0] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile00.setImageBitmap(player1Photo);
                    else
                        tile00.setImageBitmap(player2Photo);

                    board[0][0] = selectedPlayer;
                    nextTurn();
                }
            }

        });

        tile01.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[0][1] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile01.setImageBitmap(player1Photo);
                    else
                        tile01.setImageBitmap(player2Photo);

                    board[0][1] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        tile02.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[0][2] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile02.setImageBitmap(player1Photo);
                    else
                        tile02.setImageBitmap(player2Photo);

                    board[0][2] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        tile10.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[1][0] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile10.setImageBitmap(player1Photo);
                    else
                        tile10.setImageBitmap(player2Photo);

                    board[1][0] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        tile11.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[1][1] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile11.setImageBitmap(player1Photo);
                    else
                        tile11.setImageBitmap(player2Photo);

                    board[1][1] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        tile12.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[1][2] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile12.setImageBitmap(player1Photo);
                    else
                        tile12.setImageBitmap(player2Photo);

                    board[1][2] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        tile20.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[2][0] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile20.setImageBitmap(player1Photo);
                    else
                        tile20.setImageBitmap(player2Photo);

                    board[2][0] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        tile21.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[2][1] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile21.setImageBitmap(player1Photo);
                    else
                        tile21.setImageBitmap(player2Photo);

                    board[2][1] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        tile22.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if (board[2][2] == -1 && gameStarted()) {

                    if (selectedPlayer == 1)
                        tile22.setImageBitmap(player1Photo);
                    else
                        tile22.setImageBitmap(player2Photo);

                    board[2][2] = selectedPlayer;
                    nextTurn();
                }
            }
        });

        ibPlayer1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {

                if(!player1PhotoSelected)
                    takePhoto(v, CAMERA_1);
                else if(!selectedFirstPlayer && player1PhotoSelected && player2PhotoSelected)
                {
                    ibPlayer1.setBackgroundColor(color);
                    selectedFirstPlayer = true;
                    selectedPlayer = 1;
                }
            }
        });

        ibPlayer2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                if(!player2PhotoSelected)
                    takePhoto(v, CAMERA_2);
                else if(!selectedFirstPlayer & player1PhotoSelected && player2PhotoSelected)
                {
                    ibPlayer2.setBackgroundColor(color);
                    selectedFirstPlayer = true;
                    selectedPlayer = 2;
                }
            }
        });

        clearBtb.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                resetGame(true);
            }
        });

    }

    private boolean gameStarted() {
        return !gameOver && player1PhotoSelected && player2PhotoSelected && selectedFirstPlayer;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && requestCode == CAMERA_1) {
            sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE),
                    Uri.fromFile(photoFile).toString());

            updatePlayerPhoto(1, data.getExtras().get("data"));
        }

        else if (resultCode == RESULT_OK && requestCode == CAMERA_2) {
            sendBroadcast(new Intent
                    (Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                            Uri.fromFile(photoFile)));

            updatePlayerPhoto(2,  data.getExtras().get("data"));
        }

        else if (resultCode == RESULT_OK && requestCode == WINNER) {
            resetGame(false);
        }
    }

    private void updatePlayerPhoto(int id, Object bitmap) {

        if (id == 1) {
            player1Photo = (Bitmap) bitmap;
            ibPlayer1.setImageBitmap(player1Photo);
            player1PhotoSelected = true;
        }
        else if (id == 2) {
            player2Photo = (Bitmap) bitmap;
            ibPlayer2.setImageBitmap(player2Photo);
            player2PhotoSelected = true;
        }
    }

    private void resetGame(boolean fullReset) {

        if(fullReset) {
            ibPlayer1.setImageResource(R.drawable.player1);
            ibPlayer2.setImageResource(R.drawable.player2);
            player1PhotoSelected = false;
            player2PhotoSelected = false;
        }

        gameOver = false;

        turn = 1;
        selectedPlayer = 0;
        plays = 0;

        selectedFirstPlayer = false;

        tile00.setImageBitmap(null);
        tile01.setImageBitmap(null);
        tile02.setImageBitmap(null);

        tile10.setImageBitmap(null);
        tile11.setImageBitmap(null);
        tile12.setImageBitmap(null);

        tile20.setImageBitmap(null);
        tile21.setImageBitmap(null);
        tile22.setImageBitmap(null);

        tile00.setBackgroundColor(Color.BLACK);
        tile01.setBackgroundColor(Color.BLACK);
        tile02.setBackgroundColor(Color.BLACK);

        tile10.setBackgroundColor(Color.BLACK);
        tile11.setBackgroundColor(Color.BLACK);
        tile12.setBackgroundColor(Color.BLACK);

        tile20.setBackgroundColor(Color.BLACK);
        tile21.setBackgroundColor(Color.BLACK);
        tile22.setBackgroundColor(Color.BLACK);

        ibPlayer1.setBackgroundColor(Color.BLACK);
        ibPlayer2.setBackgroundColor(Color.BLACK);

        for(int x = 0; x < 3; x++)
            for(int y = 0; y < 3; y++)
                board[x][y] = -1;
    }

    private void takePhoto(View view, int param) {

        Intent takePictureIntent = new
                Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if ( takePictureIntent.resolveActivity(getPackageManager()) != null ) {
            try {
                photoFile = createFile();
            } catch (IOException ex) {
                showAlert(getBaseContext().getResources().getString(R.string.error), ex.toString(), 0);
            }
            if (photoFile != null)
                startActivityForResult(takePictureIntent, param);
        }
    }

    private void showAlert(String title, String message, int id) {
        AlertDialog alertDialog = new
                AlertDialog.Builder(MainActivity.this).create();

        alertDialog.setTitle(title);
        alertDialog.setMessage(message);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL,
                getString(R.string.ok),
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        if (id == 0)
                            dialog.dismiss();
                        else if (id == 1)
                        {
                            dialog.dismiss();
                           resetGame(false);
                        }
                    }
                });
        alertDialog.show();
    }

    private File createFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_Hhmmss").format(new Date());

        File folder = Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES);

        File image = new File(folder.getPath() +
                File.separator + "JPG_" + timeStamp + ".jpg");

        return image;
    }

    private void nextTurn() {

        gameOver = verifyBoard(selectedPlayer);

        if(plays == 8 && gameOver == false)
            showAlert(getBaseContext().getResources().getString(R.string.draw), getBaseContext().getResources().getString(R.string.drawMessage), 1);

        if (selectedPlayer == 1) {

            if (gameOver) {
                ibPlayer1.setBackgroundColor(Color.GREEN);
                (new Handler()).postDelayed(this::showWinner, DELAYTIME);
            }
            else {
                ibPlayer1.setBackgroundColor(Color.BLACK);
                ibPlayer2.setBackgroundColor(color);
                selectedPlayer++;
            }
        }
        else {

            if (gameOver) {
                ibPlayer2.setBackgroundColor(Color.GREEN);
                (new Handler()).postDelayed(this::showWinner, DELAYTIME);
            }
            else {
                ibPlayer2.setBackgroundColor(Color.BLACK);
                selectedPlayer--;
                ibPlayer1.setBackgroundColor(color);
            }
        }

        plays++;
    }

    private void showWinner() {

        Intent winnerIntent = new Intent(getBaseContext(), WinnerActivity.class);
        Bundle bundle = new Bundle();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();

        if(selectedPlayer == 1) {
            player1Photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            bundle.putByteArray("image", byteArray);
            bundle.putString("winnerName", "Player 1");
        }
        else {
            player2Photo.compress(Bitmap.CompressFormat.PNG, 100, stream);
            byte[] byteArray = stream.toByteArray();

            bundle.putByteArray("image", byteArray);
            bundle.putString("winnerName", "Player 2");
        }

        winnerIntent.putExtra("winnerBundle", bundle);
        startActivityForResult(winnerIntent, WINNER);
    }

    private boolean verifyBoard(int player) {

        if(board[0][0] == player && board[0][1] == player && board[0][2] == player) {
            tile00.setBackgroundColor(Color.GREEN);
            tile01.setBackgroundColor(Color.GREEN);
            tile02.setBackgroundColor(Color.GREEN);

            return true;
        }

        else if(board[1][0] == player && board[1][1] == player && board[1][2] == player) {
            tile10.setBackgroundColor(Color.GREEN);
            tile11.setBackgroundColor(Color.GREEN);
            tile12.setBackgroundColor(Color.GREEN);

            return true;
        }

        else if(board[2][0] == player && board[2][1] == player && board[2][2] == player) {
            tile20.setBackgroundColor(Color.GREEN);
            tile21.setBackgroundColor(Color.GREEN);
            tile22.setBackgroundColor(Color.GREEN);

            return true;
        }

        else if(board[0][0] == player && board[1][1] == player && board[2][2] == player) {
            tile00.setBackgroundColor(Color.GREEN);
            tile11.setBackgroundColor(Color.GREEN);
            tile22.setBackgroundColor(Color.GREEN);

            return true;
        }

        else if(board[0][2] == player && board[1][1] == player && board[2][0] == player) {
            tile02.setBackgroundColor(Color.GREEN);
            tile11.setBackgroundColor(Color.GREEN);
            tile20.setBackgroundColor(Color.GREEN);

            return true;
        }

        else if(board[0][0] == player && board[1][0] == player && board[2][0] == player) {
            tile00.setBackgroundColor(Color.GREEN);
            tile10.setBackgroundColor(Color.GREEN);
            tile20.setBackgroundColor(Color.GREEN);

            return true;
        }

        else if(board[0][2] == player && board[1][2] == player && board[2][2] == player) {
            tile02.setBackgroundColor(Color.GREEN);
            tile12.setBackgroundColor(Color.GREEN);
            tile22.setBackgroundColor(Color.GREEN);

            return true;
        }

        else if(board[0][1] == player && board[1][1] == player && board[2][1] == player) {
            tile01.setBackgroundColor(Color.GREEN);
            tile11.setBackgroundColor(Color.GREEN);
            tile21.setBackgroundColor(Color.GREEN);

            return true;
        }

        return false;
    }

}