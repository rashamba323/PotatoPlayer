package com.potato.potatoplayer;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import android.widget.RelativeLayout;

import com.potato.fragments.OnlineFragment;
import com.potato.fragments.TestFragment;
import com.vk.sdk.VKAccessToken;
import com.vk.sdk.VKCallback;
import com.vk.sdk.VKScope;
import com.vk.sdk.VKSdk;
import com.vk.sdk.api.VKError;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {


    private static String[] myScore = new String[]{
            VKScope.AUDIO,
            VKScope.NOHTTPS
    };

    private boolean isResumed = false;


    @BindView(R.id.main_button_search)
    Button mainButtonSearch;
    @BindView(R.id.main_button_online)
    Button mainButtonOnline;
    @BindView(R.id.main_button_download)
    Button mainButtonDownload;
    @BindView(R.id.main_buttons_container)
    RelativeLayout mainButtonsContainer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().add(R.id.main_container, new OnlineFragment()).commit();
        }

        Log.d("LoggedOut","wakeUpSession");
        VKSdk.wakeUpSession(this, new VKCallback<VKSdk.LoginState>() {
            @Override
            public void onResult(VKSdk.LoginState res) {
                Log.d("LoggedOut","isResumed = "+isResumed);
                Log.d("LoggedOut","res = "+res);
//                if(isResumed){
                    switch (res){
                        case LoggedOut:
                            Log.d("LoggedOut","TestArea");
                            showLogin();
                            break;
                        case LoggedIn:
                            showLogout();
                            break;
                        case Pending:
                            break;
                        case Unknown:
                            break;
                    }
//                }
            }

            @Override
            public void onError(VKError error) {

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        VKCallback<VKAccessToken> callback = new VKCallback<VKAccessToken>() {
            @Override
            public void onResult(VKAccessToken res) {
                startTestActvity();
            }

            @Override
            public void onError(VKError error) {

            }
        };

        if(!VKSdk.onActivityResult(requestCode, resultCode, data, callback)){
            super.onActivityResult(requestCode, resultCode, data);

        }
    }

    private void startTestActvity(){
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void onResume() {
        super.onResume();
        isResumed = true;
        if(!VKSdk.isLoggedIn()){
            showLogout();
        } else {
            showLogin();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        isResumed = false;
    }

    private void showLogin(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new LoginFragment())
                .commitAllowingStateLoss();
    }

    private void showLogout(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new OnlineFragment())
                .commitAllowingStateLoss();
    }

    private void testFragment(){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_container, new LoginFragment())
                .commitAllowingStateLoss();
    }

    @OnClick({R.id.main_button_search, R.id.main_button_online, R.id.main_button_download})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_button_search:
                testFragment();
                break;
            case R.id.main_button_online:
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.main_container, new OnlineFragment())
                        .commit();
                break;
            case R.id.main_button_download:
                break;
        }
    }

    public static class LoginFragment extends android.support.v4.app.Fragment{


//        public LoginFragment(){
//            super();
//        }

        @Nullable
        @Override
        public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
            Log.d("LoginFragment","onCreateViewSTART!");
//            super.onCreate(savedInstanceState);

            View view = inflater.inflate(R.layout.fragment_login, container, false);
            view.findViewById(R.id.sign_in_button).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    VKSdk.login(getActivity(), myScore);
                }
            });


            Log.d("LoginFragment","onCreateView return view "+view);

            return view;


        }
    }
}