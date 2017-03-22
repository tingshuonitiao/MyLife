package com.mylife.tsnt.translate.view;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mylife.tsnt.R;
import com.mylife.tsnt.base.BaseActivity;
import com.mylife.tsnt.manager.ToastManager;
import com.mylife.tsnt.translate.model.TranslateBean;
import com.mylife.tsnt.translate.presenter.TranslatePresenter;

import java.util.List;

/**
 * Created by ting说你跳 on 2017/3/14.
 */

public class TranslateActivity extends BaseActivity<TranslatePresenter> implements ITranslateView, View.OnClickListener {
    private TextView     tvResult;
    private EditText     etInput;
    private LinearLayout llQuery;
    private CardView     cvResult;
    private TextView     tvQuery;
    private ImageView    imvQuery;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setContentView(R.layout.activity_translate);
        super.onCreate(savedInstanceState);
    }

    @Override
    public TranslatePresenter setPresenter() {
        return new TranslatePresenter(this);
    }

    @Override
    public void initView() {
        cvResult = (CardView) findViewById(R.id.cv_result);
        tvResult = (TextView) findViewById(R.id.tv_result);
        etInput = (EditText) findViewById(R.id.et_input);
        llQuery = (LinearLayout) findViewById(R.id.ll_query);
        tvQuery = (TextView) findViewById(R.id.tv_query);
        imvQuery = (ImageView) findViewById(R.id.imv_query);

        llQuery.setOnClickListener(this);
        etInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() == 0) {
                    llQuery.setEnabled(false);
                    tvQuery.setTextColor(Color.parseColor("#999999"));
                    imvQuery.setImageResource(R.mipmap.arrow_toright_gray);
                } else {
                    llQuery.setEnabled(true);
                    tvQuery.setTextColor(getResources().getColor(R.color.colorPrimary));
                    imvQuery.setImageResource(R.mipmap.arrow_toright_red);
                }
            }
        });
    }

    @Override
    public void initData() {
    }

    @Override
    public void loadSucceed(TranslateBean translateBean) {
        List<String> translations = translateBean.getTranslation();
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < translations.size(); i++) {
            buffer.append(translations.get(i) + " ");
        }
        tvResult.setText(buffer);
        etInput.setText("");
        cvResult.setVisibility(View.VISIBLE);
    }

    @Override
    public void loadFail(String errorMessage) {
        ToastManager.showToast(mContext, errorMessage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ll_query:
                mPresenter.loadTranslation(etInput.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
