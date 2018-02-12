package com.acme.tictactoe.mvc.controller;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.acme.tictactoe.R;
import com.acme.tictactoe.mvc.model.Board;
import com.acme.tictactoe.mvc.model.Player;

public class TicTacToeActivity extends AppCompatActivity {

    private static String TAG = TicTacToeActivity.class.getName();

    private Board model;

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;
    private View playerTurnViewGroup;
    private TextView playerTurnLabel;
    private View gameOverViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvc_tictactoe);
        winnerPlayerLabel = (TextView) findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        playerTurnViewGroup = findViewById(R.id.playerTurnViewGroup);
        playerTurnLabel = findViewById(R.id.playerTurnLabel);
        gameOverViewGroup = findViewById(R.id.gameOverViewGroup);

        buttonGrid = (ViewGroup) findViewById(R.id.buttonGrid);

        model = new Board();
        playerTurnLabel.setText(model.getCurrentTurn().toString());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mvc_menu_tictactoe, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                reset();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onClickCell(View v) {

        Button button = (Button) v;

        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        Player playerThatMoved = model.mark(row, col);

        if(playerThatMoved != null) {
            button.setText(playerThatMoved.toString());
            if (model.getWinner() != null) {
                winnerPlayerLabel.setText(playerThatMoved.toString());
                winnerPlayerViewGroup.setVisibility(View.VISIBLE);
                playerTurnViewGroup.setVisibility(View.GONE);
            } else {
                if(model.isGameOver()){
                    playerTurnViewGroup.setVisibility(View.GONE);
                    gameOverViewGroup.setVisibility(View.VISIBLE);
                } else {
                    playerTurnLabel.setText(model.getCurrentTurn().toString());
                }
            }
        }

    }

    private void reset() {
        playerTurnViewGroup.setVisibility(View.VISIBLE);
        winnerPlayerViewGroup.setVisibility(View.GONE);
        gameOverViewGroup.setVisibility(View.GONE);
        winnerPlayerLabel.setText("");

        model.restart();
        playerTurnLabel.setText(model.getCurrentTurn().toString());

        for( int i = 0; i < buttonGrid.getChildCount(); i++ ) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

}
