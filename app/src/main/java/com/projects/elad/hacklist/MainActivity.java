package com.projects.elad.hacklist;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.projects.elad.hacklist.fragments.FragmentHome;
import com.projects.elad.hacklist.fragments.FragmentBookmarks;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


  private Toolbar toolbar;
  private TabLayout tabLayout;
  private ViewPager viewPager;
//  private ContextMenuDialogFragment mMenuDialogFragment;

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    toolbar = (Toolbar) findViewById(R.id.toolbar);
    setSupportActionBar(toolbar);


    viewPager = (ViewPager) findViewById(R.id.viewpager);
    setupViewPager(viewPager);

    tabLayout = (TabLayout) findViewById(R.id.tabs);
    tabLayout.setupWithViewPager(viewPager);
    tabLayout.getTabAt(0).setIcon(R.drawable.ic_home_selected);
    tabLayout.getTabAt(1).setIcon(R.drawable.ic_star_unselected);
    tabLayout.setOnTabSelectedListener(
        new TabLayout.ViewPagerOnTabSelectedListener(viewPager) {

          @Override
          public void onTabSelected(TabLayout.Tab tab) {
            super.onTabSelected(tab);
            if (tab.getPosition() == 1){
              tab.setIcon(R.drawable.ic_star_selected);
            } else if (tab.getPosition() == 0) {
              tab.setIcon(R.drawable.ic_home_selected);
            }
          }

          @Override
          public void onTabUnselected(TabLayout.Tab tab) {
            super.onTabUnselected(tab);
            if (tab.getPosition() == 1){
              tab.setIcon(R.drawable.ic_star_unselected);
            } else if (tab.getPosition() == 0) {
              tab.setIcon(R.drawable.ic_home_unselected);
            }

          }

          @Override
          public void onTabReselected(TabLayout.Tab tab) {
            super.onTabReselected(tab);
          }
        }
    );



  }



  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new FragmentHome(), "");
    adapter.addFragment(new FragmentBookmarks(), "");
    viewPager.setAdapter(adapter);
  }



  class ViewPagerAdapter extends FragmentPagerAdapter {
    private final List<Fragment> mFragmentList = new ArrayList<>();
    private final List<String> mFragmentTitleList = new ArrayList<>();

    public ViewPagerAdapter(FragmentManager manager) {
      super(manager);
    }

    @Override
    public Fragment getItem(int position) {
      return mFragmentList.get(position);
    }

    @Override
    public int getCount() {
      return mFragmentList.size();
    }

    public void addFragment(Fragment fragment, String title) {
      mFragmentList.add(fragment);
      mFragmentTitleList.add(title);
    }

    @Override
    public CharSequence getPageTitle(int position) {
      return mFragmentTitleList.get(position);
    }
  }


  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case R.id.main_menu_action_refine:
//        mMenuDialogFragment.show(getSupportFragmentManager(), "ContextMenuDialogFragment");
        break;
    }
    return super.onOptionsItemSelected(item);
  }
}
