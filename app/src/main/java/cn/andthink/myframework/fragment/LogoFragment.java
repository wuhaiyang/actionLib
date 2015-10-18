package cn.andthink.myframework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.andthink.myframework.LoginActivity;
import cn.andthink.myframework.R;

/**
 * Created by wuhaiyang on 2015/7/29.
 */
public class LogoFragment extends Fragment implements View.OnClickListener{
    public final static String ARG_KEY = "ARG";

    public int currentFlag = 0;
    private Button btnSignUp;
    private Button btnSignIn;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        currentFlag = arguments.getInt(ARG_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View view = inflater.inflate(R.layout.fra_logo, container, false);
            initView(view);
            initEvent();
            return view;
    }

    private void initEvent() {
        btnSignIn.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    private void initView(View view) {
        btnSignIn = (Button) view.findViewById(R.id.btn_sign_in);
        btnSignUp = (Button) view.findViewById(R.id.btn_sign_up);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        if (btnSignIn == v) {
            signIn();
        } else if (btnSignUp == v) {
            signUp();
        }
    }

    private void signUp() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((LoginActivity) activity).go2SignUp();
        }
    }

    private void signIn() {
        FragmentActivity activity = getActivity();
        if (activity != null) {
            ((LoginActivity) activity).go2SignIn();
        }
    }
}

