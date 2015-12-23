package com.wealthpack.fbloginfinal;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;


public class MainActivityFragment extends Fragment {

    private CallbackManager callbackManager;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Log.v("FBLogin", "onCreateView");

        FacebookSdk.sdkInitialize(getActivity().getApplicationContext());
        callbackManager = CallbackManager.Factory.create();

        View view = inflater.inflate(R.layout.fragment_main, container, false);

        LoginButton loginButton = (LoginButton) view.findViewById(R.id.login_button);
        loginButton.setReadPermissions("user_friends");

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                CharSequence text = "Login success!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getActivity(), text, duration);
                toast.show();
                Log.v("FBLogin", "Yeah");
                // App code
            }

            @Override
            public void onCancel() {
                // App code
            }

            @Override
            public void onError(FacebookException exception) {
                CharSequence text = "Login error!";
                int duration = Toast.LENGTH_SHORT;
                Toast toast = Toast.makeText(getActivity(), text, duration);
                toast.show();
                Log.v("FBLogin", "onError");
                // App code
            }
        });

//        return inflater.inflate(R.layout.fragment_main, container, false);
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.v("FBLogin", "onActivityResult");

        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }
}
