package com.example.manisha.p1popularmovies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * Created by Manisha on 9/20/2017.
 */

public class MovieAdapter extends ArrayAdapter<String> {

    private final Context mContext;

    public MovieAdapter(Context context, String[] names) {
        super(context, R.layout.movies_grid_item_layout, names);
        this.mContext = context;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            LayoutInflater myInflater = LayoutInflater.from(getContext());
            view = myInflater.inflate(R.layout.movies_grid_item_layout, parent, false);
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.movie_image);
        String IMAGE_BASE_URL = "https://image.tmdb.org/t/p/w185";
        String imageURL = IMAGE_BASE_URL + getItem(position);

        Picasso.with(mContext)
                .load(imageURL)
                .error(R.drawable.no_image_available)
                .placeholder(R.drawable.loading)
                .into(imageView);

        return view;
    }
}
