package id.symphonea.kenaldekat.view.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.view.adapter.CandidatesAdapter;

public class FacebookLoginView extends FrameLayout {

    @Bind(R.id.login_button) LoginButton loginButton;

    private Controller controller;

    public FacebookLoginView(Context context) {
        this(context, null);
    }

    public FacebookLoginView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FacebookLoginView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        LayoutInflater.from(context).inflate(R.layout.facebook_login, this);

        ButterKnife.bind(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }

    public void initView(Fragment fragment, CallbackManager callbackManager) {

        setVisibility(VISIBLE);

        loginButton.setReadPermissions("email");
        loginButton.setFragment(fragment);
        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                // get email and save to parse.
                if (controller != null) {
                    controller.successLoginFacebook(loginResult);
                }
            }

            @Override
            public void onCancel() {

            }

            @Override
            public void onError(FacebookException error) {

            }
        });
    }

    public void hideView() {
        setVisibility(GONE);
    }

    public interface Controller {
        void successLoginFacebook(LoginResult loginResult);
    }
}
