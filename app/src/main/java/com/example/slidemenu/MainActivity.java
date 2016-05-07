package com.example.slidemenu;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;

import com.example.slidemenu.util.AnimationUtils;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private RelativeLayout rl_level1;
    private RelativeLayout rl_level2;
    private RelativeLayout rl_level3;
    boolean isLevel1Display = true;
    boolean isLevel2Display = true;
    boolean isLevel3Display = true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();

    }

    private void initViews() {
        //添加点击事件
        findViewById(R.id.ib_home).setOnClickListener(this);
        findViewById(R.id.ib_menu).setOnClickListener(this);

        rl_level1 = (RelativeLayout) findViewById(R.id.rl_level1);
        rl_level2 = (RelativeLayout) findViewById(R.id.rl_level2);
        rl_level3 = (RelativeLayout) findViewById(R.id.rl_level3);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if(keyCode == KeyEvent.KEYCODE_MENU) {
            if(AnimationUtils.runningAnimationCount>0) {
                //当前有动画正在执行，取消当前事件
                return true;
            }
            if(isLevel1Display) {
                long delay = 0;
                //隐藏三级菜单
                if(isLevel3Display) {
                    AnimationUtils.rotateOutAnim(rl_level3,0);
                    isLevel3Display = false;
                    delay += 200;
                }
                //隐藏二级菜单
                if(isLevel2Display) {
                    AnimationUtils.rotateOutAnim(rl_level2,delay);
                    isLevel2Display = false;
                    delay += 200;
                }
                //隐藏一级菜单
                AnimationUtils.rotateOutAnim(rl_level1,delay);
            }else{
                AnimationUtils.rotateInAnim(rl_level1,0);
                AnimationUtils.rotateInAnim(rl_level2,200);
                AnimationUtils.rotateInAnim(rl_level3,400);
                isLevel2Display = true;
                isLevel3Display = true;
            }
            isLevel1Display = !isLevel1Display;
        }
        return super.onKeyDown(keyCode,event);
    }

    public void onClick(View v) {
        if(AnimationUtils.runningAnimationCount>0) {
            //当前有动画正在执行，取消当前事件
            return;
        }
        switch(v.getId()) {
            case R.id.ib_home:
                if(isLevel2Display) {
                    long delay = 0;
                    //三级菜单已显示，先转出去
                    if(isLevel3Display) {
                        AnimationUtils.rotateOutAnim(rl_level3,0);
                        isLevel3Display = false;
                        delay += 200;
                    }
                    //二级菜单已显示，转出去
                    AnimationUtils.rotateOutAnim(rl_level2,delay);
                } else {
                    //二级菜单没有显示，转出来
                    AnimationUtils.rotateInAnim(rl_level2,0);
                }
                isLevel2Display = !isLevel2Display;
                break;
            case R.id.ib_menu:
                if(isLevel3Display) {
                    //三级菜单已显示，转出去
                    AnimationUtils.rotateOutAnim(rl_level3,0);
                } else {
                    //三级菜单没有显示，转出来
                    AnimationUtils.rotateInAnim(rl_level3,0);
                }
                isLevel3Display = !isLevel3Display;
                break;
            default:
                break;
        }
    }

}
