package id.symphonea.kenaldekat.view.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginResult;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.view.DukunganView;
import id.symphonea.kenaldekat.view.widget.FacebookLoginView;

public class DukunganFragment extends BaseFragment implements DukunganView,
        FacebookLoginView.Controller {

    @Bind(R.id.facebook_login) FacebookLoginView facebookLoginView;

    private CallbackManager callbackManager;

    public DukunganFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        callbackManager = CallbackManager.Factory.create();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dukungan, container, false);
        ButterKnife.bind(this, view);
        facebookLoginView.setController(this);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (AccessToken.getCurrentAccessToken() == null) {
            facebookLoginView.initView(this, callbackManager);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void successLoginFacebook(LoginResult loginResult) {
        facebookLoginView.hideView();
    }
}
