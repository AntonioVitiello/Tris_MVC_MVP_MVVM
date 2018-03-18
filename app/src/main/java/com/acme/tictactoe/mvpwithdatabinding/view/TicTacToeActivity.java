package com.acme.tictactoe.mvpwithdatabinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.acme.tictactoe.R;
import com.acme.tictactoe.databinding.MvpWithDatabindingTictactoeBinding;
import com.acme.tictactoe.mvpwithdatabinding.MVPContract;
import com.acme.tictactoe.mvpwithdatabinding.presenter.TicTacToePresenter;

public class TicTacToeActivity extends AppCompatActivity implements MVPContract.View {
    private static String LOG_TAG = TicTacToeActivity.class.getName();
    MVPContract.Presenter mPresenter;
    private MvpWithDatabindingTictactoeBinding mBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setPresenter(new TicTacToePresenter(this));
        mBinding = DataBindingUtil.setContentView(this, R.layout.mvp_with_databinding_tictactoe);
        mBinding.setPresenter(mPresenter);
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void setPresenter(MVPContract.Presenter presenter) {
        this.mPresenter = presenter;
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
                mPresenter.onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
