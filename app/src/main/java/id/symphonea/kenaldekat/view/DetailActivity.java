package id.symphonea.kenaldekat.view;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import id.symphonea.kenaldekat.R;
import id.symphonea.kenaldekat.view.fragment.DukunganFragment;
import id.symphonea.kenaldekat.view.fragment.MediaFragment;
import id.symphonea.kenaldekat.view.fragment.ProfileFragment;
import id.symphonea.kenaldekat.view.fragment.SimpleTextFragment;

public class DetailActivity extends BaseActivity implements DetailView {

    @Bind(R.id.toolbar) Toolbar toolbar;
    @Bind(R.id.sliding_tabs) TabLayout slidingTabs;
    @Bind(R.id.viewpager) ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        ButterKnife.bind(this);

        initToolbar();
        initPager();
    }

    @Override
    public void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void initPager() {
        Bundle bundle = getIntent().getExtras();

        String paslonId = bundle.getString(MainActivity.EXTRA_PESERTA_ID);

        DetailPageAdapter adapter = new DetailPageAdapter(getSupportFragmentManager());

        adapter.addFragment(ProfileFragment.newInstance(paslonId), "PROFILE");

        adapter.addFragment(MediaFragment.newInstance(paslonId), "MEDIA");
        //adapter.addFragment(new DukunganFragment(), "DUKUNGAN");

        viewPager.setAdapter(adapter);
        slidingTabs.setupWithViewPager(viewPager);
    }

    private class DetailPageAdapter extends FragmentStatePagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> titles = new ArrayList<>();

        public DetailPageAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return titles.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }

        public void addFragment(Fragment fragment, String title) {
            titles.add(title);
            fragments.add(fragment);
        }
    }
}
