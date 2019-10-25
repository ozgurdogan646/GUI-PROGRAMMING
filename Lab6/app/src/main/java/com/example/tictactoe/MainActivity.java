package com.example.tictactoe;


import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    static final String PLAYER_1_SYMBOL = "X";
    static  final String PLAYER_2_SYMBOL = "O";
    boolean player1Turn = true;

    byte [][] board = new byte[3][3];
    static final byte EMPTY_VALUE = 0;
    static final byte PLAYER_1_VALUE = 1;
    static  final byte PLAYER_2_VALUE = 2;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 3 ; j++ ) {
                Button btn = (Button) row.getChildAt(j);
                btn.setOnClickListener(new cellListener(i, j));
            }
        }
    }

    class cellListener implements View.OnClickListener {
        int row, col;


        public cellListener(int row, int col) {
            this.row = row;
            this.col = col;

        }
        @Override
        public void onClick (View v) {
            if (board[row][col] !=  EMPTY_VALUE) {
                Toast.makeText(MainActivity.this, "Cell is already occupied!", Toast.LENGTH_SHORT).show();
                return;
            }

            byte playerValue = EMPTY_VALUE;
            if (player1Turn) {
                ((Button)v).setText(PLAYER_1_SYMBOL);
                board[row][col]=PLAYER_1_VALUE;
                playerValue = PLAYER_1_VALUE;

            }else {
                ((Button)v).setText(PLAYER_2_SYMBOL);
                board[row][col]=PLAYER_2_VALUE;
                playerValue = PLAYER_2_VALUE;
            }
            player1Turn = !player1Turn;
            int gameState = gameEnded(row, col,playerValue);
            if (gameState > 0) {
                Toast.makeText(MainActivity.this, "Player!" + gameState + "Won!", Toast.LENGTH_SHORT).show();
                setBoardEnable(false);

            }



        }


    }


    public int gameEnded(int row, int col, byte playerValue) {
        // check column
        boolean win = true;
        for (int r = 0; r <3; r++) {
            if (board[r][col] != playerValue) {
                win= false;
                break;
            }


        }
        if(win) {
            return playerValue;
        }
        // check row
        win = true;
        for (int j= 0; j<3 ; j++) {
            if(board[row][j] != playerValue) {
                win=false;
                break;

            }

        }
        if(win) {
            return playerValue;
        }
        // check diognal
        win = true;
        for (int z = 0; z<3 ; z++) {
            if(board[z][z] != playerValue) {
                win=false;
                break;
            }

        }
        if(win)
            return playerValue;
        win = true;
        for (int z = 0 ,f=2; z<3 && f >=0  ; z++, f--) {
            if(board[z][f] != playerValue) {
                win = false;
                break;

            }

        }

        if(win) {
            return playerValue;
        }
        return -1;


    }

    void setBoardEnable(boolean enable) {
        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 3 ; j++ ) {
                Button btn = (Button) row.getChildAt(j);
                btn.setEnabled(enable);
            }
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }


    public boolean newGame(MenuItem menu) {
        setBoardEnable(true);
        for(int i = 0; i <3; i++) {
            for(int j= 0; j<3 ; j++) {
                board[i][j] = EMPTY_VALUE;
            }
        }


        TableLayout table = findViewById(R.id.table);
        for (int i = 0; i < 3; i++) {
            TableRow row = (TableRow) table.getChildAt(i);
            for (int j = 0; j < 3 ; j++ ) {
                Button btn = (Button) row.getChildAt(j);
                btn.setText("");
            }
        }

        return true;

    }
}
