package cn.andthink.myframework.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import cn.andthink.myframework.LoginActivity;
import cn.andthink.myframework.R;

/**
 * Created by wuhaiyang on 2015/7/29.
 */
public class SignUpFragment extends Fragment {
    private Button btn_back;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sign_up,container,false);
        btn_back = (Button) view.findViewById(R.id.btn_back);
        btn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backAction();
            }
        });
        return view;
    }

    private void backAction() {
        LoginActivity activity = (LoginActivity) getActivity();
        activity.clickBack();
    }


}
