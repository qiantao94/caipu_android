package com.qiantao.caicai.fragment;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.text.SpannableString;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;

import com.qiantao.caicai.R;
import com.qiantao.caicai.activity.CookListActivity;
import com.qiantao.caicai.databinding.FragmentSearchBinding;
import com.qiantao.caicai.entity.CookList;
import com.qiantao.caicai.http.HttpMethod;
import com.qiantao.caicai.http.HttpSubscribe;
import com.qiantao.caicai.util.CommonUtils;

import java.util.ArrayList;

/**
 * Created by qiantao on 2016/10/12.
 * 主界面下的搜索Fragment
 */

public class SearchFragment extends Fragment implements View.OnClickListener {
    private FragmentSearchBinding mBinding;
    private static final String TAG = "SearchFragment";
    public static final String RESULT = "result";
    private ProgressDialog mDialog;
    private Intent mIntent;
    private InputMethodManager mInputManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, null, false);
        setTipsColor();
        mBinding.setClickListener(this);
        mIntent = new Intent(getActivity(), CookListActivity.class);
        mInputManager = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        return mBinding.getRoot();
    }

    /**
     * 给Tips的部分文字上色
     */
    private void setTipsColor() {
        SpannableString string = new SpannableString(getResources().getString(R.string.tips));
        CommonUtils.getInstance().setTextColor(string, 9, 12, ContextCompat.getColor(getActivity(),R.color.colorPrimary));
        mBinding.tvTips.setText(string);
    }

    @Override
    public void onClick(View v) {
        mBinding.tvTips.setVisibility(View.INVISIBLE);
        mInputManager.hideSoftInputFromWindow(mBinding.etSearch.getWindowToken(), 0);
        String inputStr = mBinding.etSearch.getText().toString();
        if (!"".equals(inputStr)) {
            mDialog = ProgressDialog.show(getActivity(), "", "正在搜索：" + inputStr);
            HttpSubscribe<CookList> subscribe = new HttpSubscribe<CookList>() {
                @Override
                public void onNext(CookList cookList) {
                    mDialog.dismiss();
                    int fetchDataSize = cookList.getTngou().size();
                    if (fetchDataSize == 0) {
                        mBinding.tvTips.setVisibility(View.VISIBLE);
                    } else {
                        Log.i(TAG, cookList.getTngou().size() + "");
                        mIntent.putParcelableArrayListExtra(RESULT, (ArrayList<? extends Parcelable>) cookList.getTngou());
                        startActivity(mIntent);
                    }
                }

                @Override
                public void onError(Throwable e) {
                    super.onError(e);
                    mDialog.dismiss();
                }
            };
            HttpMethod.getInstance().searchCookByName(subscribe, inputStr);
        }
    }

}
