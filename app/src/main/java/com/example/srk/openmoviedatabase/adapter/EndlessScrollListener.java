package com.example.srk.openmoviedatabase.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

/**
 * Created by Raghu on 10/30/2016.
 */

public abstract class EndlessScrollListener extends RecyclerView.OnScrollListener {

    private int threshold = 3;
    private int currentPage = 0;
    private boolean loading = true;
    private int totalItemCount = 0;

    RecyclerView.LayoutManager layoutManager;

    public EndlessScrollListener(RecyclerView.LayoutManager layoutManager) {
        this.layoutManager = layoutManager;
    }

    @Override
    public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);

        int lastVisibleItemPos = ((LinearLayoutManager) layoutManager).findLastVisibleItemPosition();
        int itemCount = layoutManager.getItemCount();

        if(loading && (itemCount > totalItemCount)) {
            loading = false;
            totalItemCount = itemCount;
        }

        if(!loading && (lastVisibleItemPos + threshold) > itemCount) {
            currentPage++;
            loading = true;
            onLoadMore(currentPage, itemCount, recyclerView);
        }
    }

    public void resetState() {
        this.currentPage = 0;
        this.totalItemCount = 0;
        this.loading = true;
    }

    public abstract void onLoadMore(int page, int totalItemCount, RecyclerView recyclerView);
}
