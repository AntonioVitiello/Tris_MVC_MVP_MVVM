package com.acme.tictactoe.mvpwithdatabinding.view;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.acme.tictactoe.R;
import com.acme.tictactoe.databinding.MvpWithDatabindingFragmentBinding;
import com.acme.tictactoe.mvpwithdatabinding.BaseFragment;

/**
 * Created by Vitiello Antonio on 24/03/2018.
 */

public class TicTacToeFragment extends BaseFragment {
    private MvpWithDatabindingFragmentBinding mBinding;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.mvp_with_databinding_fragment, container, false);
        mBinding.setPresenter(getPresenter());

        return mBinding.getRoot();
    }

}
