package com.ekwing.mycustomguidedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ekwing.mycustomguidedemo.adapter.PhoneViewPagerAdapter;
import com.ekwing.mycustomguidedemo.adapter.RecyclerViewAdapter;
import com.ekwing.mycustomguidedemo.bean.Databean;

import java.util.ArrayList;
import java.util.List;

public class Main3Activity extends AppCompatActivity {
    private ViewPager vpShowContent;
    private List<View> mDataView=new ArrayList<>();
    private Context mContext;
    private PhoneViewPagerAdapter  mPhoneViewPagerAdapter;
    private View viewShow;//指示条

    private boolean isSelect=true;
    private TextView tvVowel;//元音
    private TextView tvConsonant;//辅音

    private int left_tvVowel;
    private int left_tvConsonant;

    private View mView;
    private View mView1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        mContext=this;
        initView();
        initViewPager();
        getOffset();
        viewPagerListener();
        showContentLayout();

    }

    /**
     * 实例化控件
     */
    private void initView() {
        viewShow=findViewById(R.id.view_line_one);
        tvVowel=findViewById(R.id.tv_yuanyin);
        tvConsonant=findViewById(R.id.tv_fuyin);
    }

    /**
     * ViewPager的滑动监听
     *
     */
    private void viewPagerListener() {
        vpShowContent.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                AnimatorSet mAnimatorSet=new AnimatorSet();
                ObjectAnimator objectAnimatorTranX;

                if (isSelect){
                    objectAnimatorTranX = ObjectAnimator.ofFloat(viewShow, "translationX", 0.0f, left_tvConsonant-left_tvVowel);
                    isSelect=false;
                }else {
                    objectAnimatorTranX = ObjectAnimator.ofFloat(viewShow, "translationX", left_tvConsonant-left_tvVowel, 0);
                    isSelect=true;
                }

                mAnimatorSet.play(objectAnimatorTranX);
                mAnimatorSet.setDuration(100);
                mAnimatorSet.start();
            }

            @Override
            public void onPageScrollStateChanged(int state) {


            }
        });
    }

    /**
     * 得到偏移量
     */
    public void  getOffset(){
        ViewTreeObserver viewTreeObserver=viewShow.getViewTreeObserver();
        viewTreeObserver.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
            @Override
            public boolean onPreDraw() {

                left_tvVowel=tvVowel.getLeft();
                left_tvConsonant=tvConsonant.getLeft();
                return true;
            }
        });

    }

    /**
     * ViewPager初始化
     */
    private void initViewPager() {
        vpShowContent=findViewById(R.id.vp_show_content);
         mView= LayoutInflater.from(mContext).inflate(R.layout.phone_ticanalysis_page_one,null);
         mView1=LayoutInflater.from(mContext).inflate(R.layout.phonetic_analysis_page_two,null);
        mDataView.add(mView);
        mDataView.add(mView1);
        mPhoneViewPagerAdapter=new PhoneViewPagerAdapter(mDataView);
        vpShowContent.setAdapter(mPhoneViewPagerAdapter);
    }

    public void showContentLayout(){
        RecyclerView recyclerView=mView.findViewById(R.id.rv_list);
        List<Databean> mData=new ArrayList<>();
        for (int i=0;i<4;i++){
            Databean databean=new Databean("元音");
            mData.add(databean);
        }
        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(mData,mContext);
        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(recyclerViewAdapter);



    }
    public void showContentDetial(){
        RecyclerView recyclerView=mView.findViewById(R.id.rv_list_item);

        List<Databean> mData=new ArrayList<>();
        for (int i=0;i<4;i++){
            Databean databean=new Databean("");
            mData.add(databean);
        }




       // View view=LayoutInflater.from(mContext).inflate(R.layout.list_item_detial_show,null);

        RecyclerViewAdapter recyclerViewAdapter=new RecyclerViewAdapter(mData,mContext);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(this);
        ((LinearLayoutManager) layoutManager).setOrientation(LinearLayoutManager.HORIZONTAL);

        recyclerView.setAdapter(recyclerViewAdapter);


    }

}
