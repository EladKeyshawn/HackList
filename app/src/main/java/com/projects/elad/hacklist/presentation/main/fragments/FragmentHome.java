package com.projects.elad.hacklist.presentation.main.fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.flipboard.bottomsheet.BottomSheetLayout;
import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.IAdapter;
import com.mikepenz.fastadapter.IItem;
import com.mikepenz.fastadapter.adapters.ItemAdapter;
import com.projects.elad.hacklist.HacklistApplication;
import com.projects.elad.hacklist.R;
import com.projects.elad.hacklist.presentation.main.adapters.ListItem;
import com.projects.elad.hacklist.presentation.presenters.HomePresenter;
import com.projects.elad.hacklist.presentation.views.HomeMvpView;
import com.projects.elad.hacklist.util.Utils;
import com.squareup.picasso.Picasso;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * A simple {@link Fragment} subclass.
 */
public class FragmentHome extends Fragment implements FastAdapter.OnLongClickListener, SearchView.OnQueryTextListener, HomeMvpView {


    @Inject HomePresenter homePresenter;
    @BindView(R.id.all_hackathons_list)
    RecyclerView hackEventsList;
    @BindView(R.id.fragment_home_bottomsheet)
    BottomSheetLayout bottomsheet;

    private FastAdapter<ListItem> fastAdapter;
    private ItemAdapter<ListItem> itemAdapter;
    private Context context;
    private SearchView searchBox;
    private ImageView eventPic;
    private Button saveBtn;
    private Button webviewBtn;
    private Button applyButtn;

    public FragmentHome() {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_travel_only: {
                if (item.isChecked()) { // unchecking it now
                    item.setChecked(false);
                    itemAdapter.filter("");
                } else {
                    item.setChecked(true);
                    itemAdapter.filter("travel"); // filter out all that contain "no"
                }
                return true;
            }
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        ((HacklistApplication)(getActivity().getApplication())).getComponent().inject(this);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        context = super.getActivity();

        View ourView = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, ourView);


        fastAdapter = new FastAdapter<>();
        itemAdapter = new ItemAdapter<>();

        fastAdapter.withSelectOnLongClick(false);
        fastAdapter.withSelectable(false);
        fastAdapter.withOnLongClickListener(this);
        fastAdapter.withOnClickListener((v, adapter, item, position) -> {
            popBottomSheet(item);
            return true;
        });
        itemAdapter.withFilterPredicate((item, constraint) -> {

            if (constraint.toString().length() > 0) {
                switch (constraint.toString()) {
                    case "travel":
                        return item.getTravel().equals("no") || item.getTravel().equals("unknown");

                    default:
                        return !item.getTitle().toLowerCase().contains(constraint.toString().toLowerCase());

                }
            } else {
                return true;
            }


        });

        hackEventsList.setLayoutManager(new LinearLayoutManager(context));
        hackEventsList.setAdapter(itemAdapter.wrap(fastAdapter));

        searchBox = (SearchView) getActivity().findViewById(R.id.trip_search_edit);
        searchBox.clearFocus();

        searchBox.setOnQueryTextListener(this);


        return ourView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        homePresenter.attachView(this);
        homePresenter.loadHackEvent();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        homePresenter.detachView();
    }

    @Override
    public boolean onLongClick(View v, IAdapter adapter, IItem item, int position) {
        Toast.makeText(context, "long click", Toast.LENGTH_SHORT).show();
        return true;
    }


    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        itemAdapter.filter(newText);
        return true;
    }


    @Override
    public Context getContext() {
        return context;
    }

    public void openWebsiteDialog(String url) {


        WebView webview = (WebView) LayoutInflater.from(getContext()).inflate(R.layout.website_webview, null);
        webview.loadUrl(url);
        webview.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return false;
            }
        });
        AlertDialog mAlertDialog = new AlertDialog.Builder(getContext(), R.style.Theme_AppCompat_Light_Dialog_Alert)
                .setView(webview)
                .setPositiveButton(android.R.string.ok, null)
                .show();


    }

    @Override
    public void showHackEvents(List<ListItem> listItems) {
        itemAdapter.add(listItems);
    }

    public void popBottomSheet(final ListItem item) {

        View.OnClickListener bottomSheetClickListener = view -> {
            boolean saved = homePresenter.isBookmarkSaved(item.getTitle());
            switch (view.getId()) {
                case R.id.bottom_sheet_save:
                    if (saved) {
                        saveBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_unselected_bottomsheet, 0, 0);
                        homePresenter.deleteBookmark(item.getTitle());
                    } else {
                        saveBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_selected_bottomsheet, 0, 0);
                        homePresenter.saveListItem(item);
                    }
                    break;
                case R.id.bottom_sheet_webview:
                    bottomsheet.dismissSheet();
                    openWebsiteDialog(item.getWebsite());
                    break;
                case R.id.bottom_sheet_apply:
                    bottomsheet.dismissSheet();
                    openWebsiteDialog(item.getWebsite());
            }
        };
        bottomsheet.setPeekSheetTranslation(1200);
        bottomsheet.showWithSheetView(LayoutInflater.from(context).inflate(R.layout.bottomsheet_event_item, bottomsheet, false));

        eventPic = (ImageView) bottomsheet.findViewById(R.id.bottom_sheet_event_logo);
        saveBtn = (Button) bottomsheet.findViewById(R.id.bottom_sheet_save);
        webviewBtn = (Button) bottomsheet.findViewById(R.id.bottom_sheet_webview);
        applyButtn = (Button) bottomsheet.findViewById(R.id.bottom_sheet_apply);

        boolean isSaved = homePresenter.isBookmarkSaved(item.getTitle());

        if (!isSaved) {
            saveBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_unselected_bottomsheet, 0, 0);
        } else {
            saveBtn.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_heart_selected_bottomsheet, 0, 0);

        }
        String logoLink = Utils.getPageIdFromUrl(item.getFacebookUrl());
        if (eventPic != null) {
            Picasso.with(context)
                    .load(logoLink)
                    .placeholder(R.mipmap.ic_launcher)
                    .into(eventPic);
        }

        saveBtn.setOnClickListener(bottomSheetClickListener);
        webviewBtn.setOnClickListener(bottomSheetClickListener);
        applyButtn.setOnClickListener(bottomSheetClickListener);
    }
}
