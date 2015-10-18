package cn.andthink.myframework;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import cn.andthink.myframework.fragment.LogoFragment;
import cn.andthink.myframework.fragment.SignInFragment;
import cn.andthink.myframework.fragment.SignUpFragment;

/**
 * Created by wuhaiyang on 2015/7/29.
 */
public class LoginActivity extends FragmentActivity {

    public static final String TAG_LOGO = "logo";
    public static final String TAG_SIGN_IN = "sign_in";
    public static final String TAG_SIGN_UP = "sign_up";
    public static final String TAG_FILL_INFO = "fill_info";

    private RelativeLayout fl_container;
    private RelativeLayout rl_actin_bar;
    private TextView tv_entry;

    private Fragment mCurrentFragment;
    private String mCurrentTag;
    private FragmentManager mFragmentManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        fl_container = (RelativeLayout) findViewById(R.id.fl_container);
        rl_actin_bar = (RelativeLayout) findViewById(R.id.rl_actin_bar);
        tv_entry = (TextView) findViewById(R.id.tv_entry);
        rl_actin_bar.setVisibility(View.GONE);


        mFragmentManager = getSupportFragmentManager();


        mCurrentFragment = new LogoFragment();
        Bundle args = new Bundle();
        args.putInt(LogoFragment.ARG_KEY, 0);
        mCurrentFragment.setArguments(args);
        mCurrentTag = TAG_LOGO;

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, mCurrentFragment, mCurrentTag);
        fragmentTransaction.addToBackStack(mCurrentTag);
        fragmentTransaction.commit();

        tv_entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SecondeActivity.class);
                startActivity(intent);
                //
                overridePendingTransition(R.anim.entry_from_down, 0);
            }
        });

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {

//            overridePendingTransition(android.R.anim.slide_in_left,android.R.anim.slide_out_right);
        }
    };

    public void go2SignUp() {
        Fragment fragment = mFragmentManager.findFragmentByTag(TAG_SIGN_UP);
        if (fragment == null) {
            fragment = new SignUpFragment();
        }
        rl_actin_bar.setVisibility(View.VISIBLE);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.card_flip_horizontal_right_in, R.anim.card_flip_horizontal_left_out, R.anim.card_flip_horizontal_left_in, R.anim.card_flip_horizontal_right_out);
        ft.replace(R.id.fl_container, fragment, TAG_SIGN_UP);
        ft.addToBackStack(TAG_SIGN_UP);
        ft.commit();
    }

    public void go2SignIn() {
        Fragment fragment = mFragmentManager.findFragmentByTag(TAG_SIGN_IN);
        if (fragment == null) {
            fragment = new SignInFragment();
        }
        rl_actin_bar.setVisibility(View.VISIBLE);
        FragmentTransaction ft = mFragmentManager.beginTransaction();
        ft.setCustomAnimations(R.anim.card_flip_horizontal_right_in, R.anim.card_flip_horizontal_left_out, R.anim.card_flip_horizontal_left_in, R.anim.card_flip_horizontal_right_out);
        ft.replace(R.id.fl_container, fragment, TAG_SIGN_UP);
        ft.addToBackStack(TAG_SIGN_UP);
        ft.commit();
    }

    public void clickBack() {
        int count = mFragmentManager.getBackStackEntryCount();
        if (count <= 1) {
            finish();
        } else {
            mFragmentManager.popBackStack();
            if (count == 2) {
                FragmentManager.BackStackEntry backStackEntryAt = mFragmentManager.getBackStackEntryAt(0);
                if (backStackEntryAt.getName().equals(TAG_LOGO)) {
                    rl_actin_bar.setVisibility(View.GONE);
                }
            }
        }
       /* if (count <= 1) {
            finish();
        } else {
            mFragmentManager.popBackStack();
            if (count == 2) {
                FragmentManager.BackStackEntry backStackEntryAt = mFragmentManager.getBackStackEntryAt(0);
                if (backStackEntryAt.getName().equals(TAG_LOGO)) {
                    rl_actin_bar.setVisibility(View.GONE);
                }
            }
        }*/
    }

    @Override
    public void onBackPressed() {
        clickBack();
    }

}
