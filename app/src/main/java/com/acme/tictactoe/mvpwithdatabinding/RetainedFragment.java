package com.acme.tictactoe.mvpwithdatabinding;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * Created by Antonio on 18/03/2018.
 */

public class RetainedFragment extends Fragment {
    private MVPContract.Presenter mPresenter;

    // this method is only called once for this fragment
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    public void setPresenter(MVPContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public MVPContract.Presenter getPresenter() {
        return mPresenter;
    }}
