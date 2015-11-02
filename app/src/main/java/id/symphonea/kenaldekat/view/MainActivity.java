package id.symphonea.kenaldekat.view;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.squareup.okhttp.ResponseBody;

import javax.inject.Inject;

import id.symphonea.kenaldekat.Injector;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.api.ApiConfig;
import id.symphonea.kenaldekat.api.model.response.CandidatesResponse;
import id.symphonea.kenaldekat.api.service.CandidateService;
import retrofit.Call;
import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

public class MainActivity extends AppCompatActivity {

    @Inject CandidateService candidateService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Injector.INSTANCE.getApplicationComponent().inject(this);

        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        Call<CandidatesResponse> callCandidates = candidateService.listOfCandidates(0, 10, null,
                null, null, null, null, ApiConfig.API_KEY);
        callCandidates.enqueue(new Callback<CandidatesResponse>() {
            @Override
            public void onResponse(Response<CandidatesResponse> response, Retrofit retrofit) {
                if (response != null) {
                    CandidatesResponse candidatesResponse = response.body();
                    Log.v("CANDIDATES", candidatesResponse.data.toString());
                }
            }

            @Override
            public void onFailure(Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
