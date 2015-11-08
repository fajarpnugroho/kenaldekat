package id.symphonea.kenaldekat.view.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.login.LoginResult;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import id.symphonea.kenaldekat.Injector;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.model.response.DukunganResponse;
import id.symphonea.kenaldekat.presenter.DukunganPresenter;
import id.symphonea.kenaldekat.view.DividerItemDecoration;
import id.symphonea.kenaldekat.view.DukunganView;
import id.symphonea.kenaldekat.view.adapter.DukunganAdapter;
import id.symphonea.kenaldekat.view.widget.DialogDukungan;
import id.symphonea.kenaldekat.view.widget.FacebookLoginView;

public class DukunganFragment extends BaseFragment implements DukunganView,
        FacebookLoginView.Controller, DialogDukungan.Listener {

    @Bind(R.id.facebook_login) FacebookLoginView facebookLoginView;
    @Bind(R.id.recyclerview) RecyclerView recyclerView;
    @Bind(R.id.content) RelativeLayout content;

    @Inject DukunganPresenter presenter;

    private CallbackManager callbackManager;

    private DukunganAdapter adapter;

    private int ratingNum;
    private String dukungan;

    public DukunganFragment() {}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Injector.INSTANCE.getApplicationComponent().inject(this);
        callbackManager = CallbackManager.Factory.create();
        presenter.initView(this);
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
        initRecycler();

        if (AccessToken.getCurrentAccessToken() == null) {
            facebookLoginView.initView(this, callbackManager);
        }

        presenter.loadDukunganJson();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void successLoginFacebook(LoginResult loginResult) {
        facebookLoginView.hideView();
        content.setVisibility(View.VISIBLE);
        // Save data user to parse
    }

    @OnClick(R.id.btn_dukungan)
    void onBtnDukunganClick() {
        // Pass data from parse if user already give a rating.
        AlertDialog.Builder dialogDukungan = new AlertDialog.Builder(getActivity());
        dialogDukungan.setView(new DialogDukungan(getActivity()));
        dialogDukungan.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                // send rating and dukungan to parse

            }
        });
        dialogDukungan.setNegativeButton("TUTUP", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialogDukungan.show();
    }

    @Override
    public void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(),
                DividerItemDecoration.VERTICAL_LIST));
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        adapter = new DukunganAdapter();
    }

    @Override
    public void showListDukungan(DukunganResponse dukunganResponse) {
        adapter.setDukunganEntityList(dukunganResponse.data);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void setMyRating(int ratingNum) {
        this.ratingNum = ratingNum;
    }

    @Override
    public void setMyDukungan(String dukungan) {
        this.dukungan = dukungan;
    }

}
