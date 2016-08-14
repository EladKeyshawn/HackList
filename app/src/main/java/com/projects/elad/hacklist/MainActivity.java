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

import com.projects.elad.hacklist.fragments.FragmentAll;
import com.projects.elad.hacklist.fragments.FragmentUpcoming;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;

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



  }

//** bullshit doesnt work! **//

//  private void setUpRefineMenu() {
//    MenuObject close = new MenuObject();
//    close.setResource(R.drawable.ic_ok_tick);
//
//    MenuObject send = new MenuObject("Only Travel");
//    send.setResource(R.drawable.ic_x_red);
//
//
//
//    List<MenuObject> menuObjects = new ArrayList<>();
//    menuObjects.add(close);
//    menuObjects.add(send);
//
//
//
//    MenuParams menuParams = new MenuParams();
//    menuParams.setActionBarSize(toolbar.getHeight());
//    menuParams.setMenuObjects(menuObjects);
//    menuParams.setClosableOutside(true);
//    // set other settings to meet your needs
//    mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
//
//
//  }

  private void setupViewPager(ViewPager viewPager) {
    ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
    adapter.addFragment(new FragmentAll(), "ALL");
    adapter.addFragment(new FragmentUpcoming(), "UPCOMING");
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

}
