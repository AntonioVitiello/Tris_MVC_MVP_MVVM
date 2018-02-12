package com.acme.tictactoe.mvp.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.acme.tictactoe.R;
import com.acme.tictactoe.mvp.MVPContract;
import com.acme.tictactoe.mvp.presenter.TicTacToePresenter;

public class TicTacToeActivity extends AppCompatActivity implements MVPContract.View {

    private static String TAG = TicTacToeActivity.class.getName();

    private ViewGroup buttonGrid;
    private View winnerPlayerViewGroup;
    private TextView winnerPlayerLabel;

    MVPContract.Presenter presenter;
    private View playerTurnViewGroup;
    private TextView playerTurnLabel;
    private View gameOverViewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mvp_tictactoe);
        winnerPlayerLabel = findViewById(R.id.winnerPlayerLabel);
        winnerPlayerViewGroup = findViewById(R.id.winnerPlayerViewGroup);
        buttonGrid = findViewById(R.id.buttonGrid);
        playerTurnViewGroup = findViewById(R.id.playerTurnViewGroup);
        playerTurnLabel = findViewById(R.id.playerTurnLabel);
        gameOverViewGroup = findViewById(R.id.gameOverViewGroup);

        presenter = new TicTacToePresenter(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.mvp_menu_tictactoe, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_reset:
                presenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    /**
     * Button onClick event in mvp_tictactoe.xml
     * @param v
     */
    public void onClickCell(View v) {
        Button button = (Button) v;
        String tag = button.getTag().toString();
        int row = Integer.valueOf(tag.substring(0,1));
        int col = Integer.valueOf(tag.substring(1,2));
        Log.i(TAG, "Click Row: [" + row + "," + col + "]");

        presenter.playerMove(row, col);
    }

    @Override
    public void setButtonText(int row, int col, String text) {
        Button btn = (Button) buttonGrid.findViewWithTag("" + row + col);
        if(btn != null) {
            btn.setText(text);
        }
    }

    @Override
    public void showWinner(String winnerPlayerText) {
        winnerPlayerLabel.setText(winnerPlayerText);
        playerTurnViewGroup.setVisibility(View.GONE);
        gameOverViewGroup.setVisibility(View.GONE);
        winnerPlayerViewGroup.setVisibility(View.VISIBLE);
    }

    @Override
    public void showPlayerTurn(String playerTurnText) {
        playerTurnLabel.setText(playerTurnText);
        playerTurnViewGroup.setVisibility(View.VISIBLE);
        gameOverViewGroup.setVisibility(View.GONE);
        winnerPlayerViewGroup.setVisibility(View.GONE);
    }

    @Override
    public void showGameOver() {
        playerTurnViewGroup.setVisibility(View.GONE);
        gameOverViewGroup.setVisibility(View.VISIBLE);
        winnerPlayerViewGroup.setVisibility(View.GONE);
    }

    @Override
    public void clearButtons() {
        for( int i = 0; i < buttonGrid.getChildCount(); i++ ) {
            ((Button) buttonGrid.getChildAt(i)).setText("");
        }
    }

}
