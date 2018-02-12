package com.acme.tictactoe.mvp;

/**
 * Created by Antonio on 12/02/2018.
 */

public interface MVPContract {
    public interface Presenter {
        void playerMove(int row, int col);
        void onResetSelected();
    }

    public interface View {
        void showWinner(String winnerPlayerText);

        void clearButtons();

        void setButtonText(int row, int col, String text);

        void showPlayerTurn(String playerTurnText);

        void showGameOver();
    }

}