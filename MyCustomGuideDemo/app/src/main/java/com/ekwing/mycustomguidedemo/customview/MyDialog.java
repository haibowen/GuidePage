package com.ekwing.mycustomguidedemo.customview;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.ekwing.mycustomguidedemo.Main2Activity;
import com.ekwing.mycustomguidedemo.R;

public class MyDialog extends Dialog {
    private MyDialog mDialog;
    private ImageView ivShowSelet;
    private TextView tvContentSpot;  //识别
    private TextView tvBackPhoto;//返回
    private boolean isSelect=true;//是否选择
    private Context mcontext;
    public MyDialog(@NonNull Context context) {
        super(context);
        setContentView(R.layout.dialog_show_ocr_select);
        mcontext=context;
        initView();
        toBack();
        toSpot();
        showSelect();
    }

    public MyDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
        setContentView(R.layout.dialog_show_ocr_select);
        mcontext=context;

        initView();
        toBack();
        toSpot();
        showSelect();
    }

    /**
     * 选择框的点击事件
     */
    private void showSelect() {
        ivShowSelet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isSelect){
                    ivShowSelet.setBackgroundResource(R.mipmap.iv_grouping_select);
                    isSelect=false;
                }else {
                    ivShowSelet.setBackgroundResource(R.mipmap.iv_grouping_unselect);
                    isSelect=true;
                }


            }
        });
    }

    /**
     * 继续识别的点击事件
     */
    private void toSpot() {
        tvContentSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mcontext.startActivity(new Intent(mcontext, Main2Activity.class));


            }
        });
    }

    /**
     * 返回调整的点击事件
     */
    private void toBack() {
       tvBackPhoto.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               dismiss();

           }
       });
    }

    /**
     * 初始化
     */
    private void initView() {
        mDialog=this;
        mDialog.setCanceledOnTouchOutside(false);
        mDialog.setCancelable(false);
        mDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
        tvContentSpot=findViewById(R.id.tv_content_spot);
        tvBackPhoto=findViewById(R.id.tv_back_photo);
        ivShowSelet=findViewById(R.id.iv_show_select);
        ivShowSelet.setBackgroundResource(R.mipmap.iv_grouping_unselect);
    }


}
