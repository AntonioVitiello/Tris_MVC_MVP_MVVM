package com.acme.tictactoe.mvpwithdatabinding;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.acme.tictactoe.mvpwithdatabinding.presenter.TicTacToePresenter;

/**
 * Created by Antonio on 18/03/2018.
 */

public class BaseActivity extends AppCompatActivity implements MVPContract.View {
    private static final String LOG_TAG = "BaseActivity";
    private static final String TAG_RETAINED_FRAGMENT = "RetainedFragment";
    private MVPContract.Presenter mPresenter;
    private RetainedFragment mRetainedFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // find the retained fragment on activity restarts
        FragmentManager fm = getSupportFragmentManager();
        mRetainedFragment = (RetainedFragment) fm.findFragmentByTag(TAG_RETAINED_FRAGMENT);

        // create the fragment and data the first time
        if (mRetainedFragment == null) {
            mRetainedFragment = new RetainedFragment();
            fm.beginTransaction().add(mRetainedFragment, TAG_RETAINED_FRAGMENT).commit();
            mRetainedFragment.setPresenter(new TicTacToePresenter(this));
        }

        setPresenter(mRetainedFragment.getPresenter());
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.start();
    }

    @Override
    public void onPause() {
        super.onPause();
        // if this activity will not be recreated, ie. user is leaving it or the activity is otherwise finishing
        Log.d(LOG_TAG, "onPause: isFinishing() = " + isFinishing());
        if (isFinishing()) {
            // remove retained fragment object to perform its own cleanup
            FragmentManager fm = getSupportFragmentManager();
            fm.beginTransaction().remove(mRetainedFragment).commit();
        }
    }

    public MVPContract.Presenter getPresenter() {
        return mPresenter;
    }

    @Override
    public void setPresenter(MVPContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
