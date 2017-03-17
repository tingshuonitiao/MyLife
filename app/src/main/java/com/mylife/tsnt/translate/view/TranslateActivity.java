package com.mylife.tsnt.translate.view;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mylife.tsnt.R;
import com.mylife.tsnt.base.BaseActivity;
import com.mylife.tsnt.translate.model.TranslateBean;
import com.mylife.tsnt.translate.presenter.TranslatePresenter;
import com.mylife.tsnt.manager.ToastManager;

import java.util.List;

/**
 * Created by ting说你跳 on 2017/3/14.
 */

public class TranslateActivity extends BaseActivity<TranslatePresenter> implements ITranslateView, View.OnClickListener {
    private TextView tvResult;
    private EditText etInput;
    private Button btnQuery;

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
        tvResult = (TextView) findViewById(R.id.result);
        etInput = (EditText) findViewById(R.id.input);
        btnQuery = (Button) findViewById(R.id.query);

        btnQuery.setOnClickListener(this);
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
    }

    @Override
    public void loadFail(String errorMessage) {
        ToastManager.showToast(mContext, errorMessage);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.query:
                mPresenter.loadTranslation(etInput.getText().toString().trim());
                break;
            default:
                break;
        }
    }
}
