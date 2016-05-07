package com.example.slidemenu.util;

import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.RelativeLayout;

/**
 * Created by tianmeng on 2016/5/7.
 */
public class AnimationUtils {
    public static int runningAnimationCount = 0;

    //旋转出去的动画
    public static void rotateOutAnim(RelativeLayout layout,long delay) {
        int childCount = layout.getChildCount();
        //如果隐藏，找到所有的子View,禁用
        for(int i=0;i<childCount;i++) {
            layout.getChildAt(i).setEnabled(false);
        }
        RotateAnimation ra = new RotateAnimation(
                0f,-180f,//开始、结束的角度   逆时针
                Animation.RELATIVE_TO_SELF,0.5f,   //相对的X坐标点（指定旋转中心X值）
                Animation.RELATIVE_TO_SELF,1.0f);  //相对的Y坐标点（指定旋转中心Y值）
        ra.setDuration(500);
        ra.setFillAfter(true);                     //设置动画停留在结束位置
        ra.setStartOffset(delay);                    //设置动画开始延时
        layout.startAnimation(ra);
        ra.setAnimationListener(new MyAnimationListener());
    }

    public static void rotateInAnim(RelativeLayout layout,long delay) {
        int childCount = layout.getChildCount();
        for(int i=0;i<childCount;i++) {
            layout.getChildAt(i).setEnabled(true);
        }
        RotateAnimation ra = new RotateAnimation(
               -180f,0f,//开始、结束的角度   顺时针
                Animation.RELATIVE_TO_SELF,0.5f,   //相对的X坐标点（指定旋转中心X值）
                Animation.RELATIVE_TO_SELF,1.0f);  //相对的Y坐标点（指定旋转中心Y值）
        ra.setDuration(500);
        ra.setFillAfter(true);
        ra.setStartOffset(delay);
        layout.startAnimation(ra);
        ra.setAnimationListener(new MyAnimationListener());
    }

    static class MyAnimationListener implements Animation.AnimationListener {

        @Override
        public void onAnimationStart(Animation animation) {
            runningAnimationCount++;
        }

        @Override
        public void onAnimationEnd(Animation animation) {
            runningAnimationCount--;
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }
}
