package com.acme.tictactoe.mvpwithdatabinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.acme.tictactoe.R;
import com.acme.tictactoe.databinding.MvpWithDatabindingActivityBinding;
import com.acme.tictactoe.mvpwithdatabinding.BaseActivity;
import com.acme.tictactoe.mvpwithdatabinding.MVPContract;

public class TicTacToeActivity extends BaseActivity {
    private static String LOG_TAG = TicTacToeActivity.class.getName();
    private MvpWithDatabindingActivityBinding mBinding;
    private MVPContract.View mAddedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = DataBindingUtil.setContentView(this, R.layout.mvp_with_databinding_activity);
        mBinding.setPresenter(getPresenter());

        mAddedFragment = getPresenter().nextFragment(getSupportFragmentManager());
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
                getPresenter().onResetSelected();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
