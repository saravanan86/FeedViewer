package com.xfinity.feedviewer;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.xfinity.feedviewer.vo.FeedDataVO;
import com.xfinity.feedviewer.vo.FeedListVO;

import java.util.ArrayList;

/**
 * Created by kathires on 8/30/16.
 */

public class FeedListFragment extends Fragment {

    public static final String EXTRA_FEED_NAME = "feedName";
    public static final String EXTRA_FEED_VALUE = "feedValue";
    private ArrayList<FeedDataVO> data;
    private boolean menuToggle = false;
    private RecyclerView recyclerView;
    private FeedListAdapter adapter;
    private int lastPosition = 0;
    private CallBacks callBacks;

    public interface CallBacks{

        void onItemSelected( int position );

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        callBacks = (CallBacks)context;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        callBacks = null;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        try {

            data = FeedListVO.getFeedListVO().getFeedList();
            setHasOptionsMenu(true);

        }catch(Exception e){


        }

    }


    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater menuInflater) {
        menuInflater.inflate(R.menu.fragment_menu, menu);
        super.onCreateOptionsMenu(menu, menuInflater);

        SearchManager searchManager = (SearchManager)getActivity().getSystemService(Context.SEARCH_SERVICE);
        MenuItem searchMenuItem = menu.findItem(R.id.search);
        SearchView searchView = (SearchView) searchMenuItem.getActionView();

        searchView.setSearchableInfo(searchManager.getSearchableInfo( getActivity().getComponentName()));
        searchView.setSubmitButtonEnabled(true);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                adapter = new FeedListAdapter( FeedListVO.getFeedListVO().getFeedList( newText ), callBacks );
                recyclerView.setAdapter(adapter);
                recyclerView.invalidate();
                return false;
            }
        });

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if( id == R.id.menu_item_toggle ){

            if( !menuToggle ){
                item.setIcon( R.drawable.ic_view_module_white_24dp );
                recyclerView.setLayoutManager(new GridLayoutManager( getActivity().getApplicationContext(), 2 ));
            }else{
                item.setIcon( R.drawable.ic_view_list_white_24dp );
                recyclerView.setLayoutManager( new LinearLayoutManager( getActivity().getApplicationContext() ) );
            }

            menuToggle = !menuToggle;

        }else if( id == R.id.menu_item_back ){

            this.getActivity().finish();

        }

        return super.onOptionsItemSelected(item);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        //setHasOptionsMenu(true);
        super.onCreateView(inflater, container, savedInstanceState);
        adapter = new FeedListAdapter( data, callBacks );
        View view = inflater.inflate( R.layout.feed_list_fragment, container, false );
        recyclerView = (RecyclerView) view.findViewById( R.id.recycler );
        RecyclerView.ItemDecoration itemDecoration = new EmptyDividerItemDecoration();
        recyclerView.addItemDecoration(itemDecoration);
        recyclerView.setAdapter( adapter );//getArguments().getString( EXTRA_FEED_NAME ),getArguments().getString( EXTRA_FEED_VALUE )
        recyclerView.setLayoutManager( new LinearLayoutManager( getActivity().getApplicationContext() ) );

        return view;

    }

    public static FeedListFragment getNewInstance(String feedName, String feedValue ){
    //public static FeedListFragment getNewInstance( ArrayList<FeedDataVO> feedList ){

        FeedListFragment fragment = new FeedListFragment();
        Bundle args = new Bundle();
        args.putString( EXTRA_FEED_NAME, feedName );
        args.putString( EXTRA_FEED_VALUE, feedValue );

        fragment.setArguments( args );

        return fragment;
    }

    class EmptyDividerItemDecoration extends RecyclerView.ItemDecoration {}
}


