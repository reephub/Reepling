package com.reepling.activities;

import android.animation.LayoutTransition;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.reepling.R;
import com.reepling.adapters.MoviesAdapter;
import com.reepling.model.Movie;
import com.reepling.utils.ActivityLauncher;
import com.reepling.utils.Dummies;
import com.reepling.views.MyDividerItemDecoration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Michaël on 08/02/2018.
 */

public class SearchUserActivity extends AppCompatActivity
        implements MoviesAdapter.MovieAdapterListener {

    private static final String TAG = SearchUserActivity.class.getSimpleName();

    //Views
    @BindView(R.id.search_recycler_view)
    RecyclerView searchRecyclerView;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private SearchView searchView;

    private Context mContext;

    private ActivityLauncher mActivityLauncher;

    //Temp
    private List<Movie> movieList ;
    private MoviesAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_user);

        mContext = this;
        mActivityLauncher =new ActivityLauncher(this);

        ButterKnife.bind(this);


        setSupportActionBar(toolbar);
        // toolbar fancy stuff
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(R.string.app_name);

        movieList = new ArrayList<>();
        mAdapter = new MoviesAdapter(this, movieList, this);

        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        searchRecyclerView.setLayoutManager(mLayoutManager);
        searchRecyclerView.setItemAnimator(new DefaultItemAnimator());
        searchRecyclerView.addItemDecoration(new MyDividerItemDecoration(this, DividerItemDecoration.VERTICAL, 36));
        searchRecyclerView.setAdapter(mAdapter);

        prepareMovieData();
    }


    private void prepareMovieData() {
        // adding contacts to contacts list
        movieList.clear();
        movieList.addAll(Dummies.getMoviesWithURLs());

        mAdapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_search_user, menu);

        // Associate searchable configuration with the SearchView
        final SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) MenuItemCompat.getActionView(menu.findItem(R.id.action_search));
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        // listening to search query text change
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                // filter recycler view when query submitted
                mAdapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String query) {
                // filter recycler view when text is changed
                mAdapter.getFilter().filter(query);
                return false;
            }
        });


        LinearLayout searchBar = searchView.findViewById(R.id.search_bar);
        searchBar.setLayoutTransition(new LayoutTransition());

        searchView.setIconifiedByDefault(true);
        searchView.setFocusable(true);
        searchView.setIconified(false);
        searchView.requestFocusFromTouch();

        return true;
    }

    @Override
    public void onBackPressed() {
        // close search view on back button pressed
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }

    @Override
    public void onMovieSelected(Movie movie) {
        if (null != movie){
            Log.i(TAG, "Value of movie " + movie);
            Toast.makeText(getApplicationContext(), "Selected: " + movie.getTitle() + ", " + movie.getGenre(), Toast.LENGTH_LONG).show();
        }

        Intent userIntent = new Intent(this, UserActivity.class);

        Bundle userBundle = new Bundle();
        userBundle.putString( UserActivity.BUNDLE_USER_NAME, movie.getTitle() );
        userBundle.putString( UserActivity.BUNDLE_USER_FIRST_NAME, movie.getGenre() );
        userBundle.putString( UserActivity.BUNDLE_USER_PROFILE_IMAGE, movie.getUrlThumbnail() );

        userIntent.putExtras( userBundle );

        startActivity( userIntent );
    }
}
