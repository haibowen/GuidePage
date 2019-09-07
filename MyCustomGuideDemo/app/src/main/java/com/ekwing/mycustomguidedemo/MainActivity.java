package com.ekwing.mycustomguidedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.ekwing.mycustomguidedemo.adapter.ViewPagerAdatpater;
import com.ekwing.mycustomguidedemo.customview.MyDialog;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    private ImageView ivFinish;//销毁的图标
    private ImageView ivTakePhoto;//开始拍照
    private ViewPager vpshow;
    private List<View> mData = new ArrayList<>();//View集合
    private Context mContext;
    private ViewPagerAdatpater mViewPagerAdatpater;
    private List<ImageView> mDataIcon=new ArrayList<>();//小圆点集合
    private ImageView ivGuideCircleFirst,ivGuideCircleSecond,ivGuideCircleThird,ivGuideCircleFourth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mContext = this;

        initViewCircle();
        initViewPager();
        viewPagerListener();
        finishView();
        takePhotoClick();

    }

    private void takePhotoClick() {
        ivTakePhoto=mData.get(3).findViewById(R.id.iv_take_photo);
        ivTakePhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyDialog myDialog=new MyDialog(mContext);
                myDialog.show();
                Toast.makeText(mContext,"我被点击了",Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void finishView() {
        ivFinish=findViewById(R.id.iv_finsh);
        ivFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    /**
     * ViewPager的滑动监听
     */
    private void viewPagerListener() {
        vpshow.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                updateIndicators(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    /**
     * ViewPager的初始化
     */
    private void initViewPager() {
        View mViewFirst = getLayoutInflater().from(mContext).inflate(R.layout.vp_view_first, null);
        View mViewSecond = getLayoutInflater().from(mContext).inflate(R.layout.vp_view_second, null);
        View mViewThird = getLayoutInflater().from(mContext).inflate(R.layout.vp_view_third, null);
        View mViewFourth=getLayoutInflater().from(mContext).inflate(R.layout.vp_view_fourth,null);

        mData.add(mViewFirst);
        mData.add(mViewSecond);
        mData.add(mViewThird);
        mData.add(mViewFourth);
        mViewPagerAdatpater = new ViewPagerAdatpater(mData, mContext);
        vpshow.setAdapter(mViewPagerAdatpater);
    }

    /**
     * 小圆点数据的初始化
     */
    private void initViewCircle() {
        ivGuideCircleFirst=findViewById(R.id.iv_show_circle_one);
        ivGuideCircleSecond=findViewById(R.id.iv_show_circle_two);
        ivGuideCircleThird=findViewById(R.id.iv_show_circle_three);
        ivGuideCircleFourth=findViewById(R.id.iv_show_circle_four);
        mDataIcon.add(ivGuideCircleFirst);
        mDataIcon.add(ivGuideCircleSecond);
        mDataIcon.add(ivGuideCircleThird);
        mDataIcon.add(ivGuideCircleFourth);
        vpshow = findViewById(R.id.vp_show);
    }

    /**
     * 通过切换两个不同的drawable来更新指示器。
     */
    private void updateIndicators(int position) {
        for (int i = 0; i < mDataIcon.size(); i++) {
            mDataIcon.get(i).setBackgroundResource(
                    i == position ? R.mipmap.iv_guide_select_circle : R.mipmap.iv_guide_unselect_circle
            );
        }
    }


}
