package com.teamwork.nytnewsapp.data;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.teamwork.nytnewsapp.R;
import com.teamwork.nytnewsapp.model.Result;
import com.teamwork.nytnewsapp.utils.GlideApp;


import java.util.ArrayList;
import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private ArrayList<Result> articles;
    private List<TextView> items;
    private int row_index;
    private Context context;
    private RowViewHolder rowHolder;


    public ArticleAdapter(RecyclerViewClickListener listener, List<Result> articles) {
        mListener = listener;
        this.articles= (ArrayList<Result>) articles;
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.times_layout, parent, false);
        return new RowViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //holder.name
        if (holder instanceof RowViewHolder) {
             Result article = articles.get(position);
            if (article == null) return;

            rowHolder = (RowViewHolder) holder;

            rowHolder.title.setText(article.getTitle());
            rowHolder.author.setText(article.getByline());
            rowHolder.date.setText(article.getPublishedDate());
            rowHolder.section.setText(article.getSection());

            RequestOptions requestOptions;  requestOptions = new RequestOptions()
                    .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC); // because file name is always same
            //.skipMemoryCache(true);

            Log.d("Logging data", articles.get(position).getUrl());
            String urlImage = article.getMedia().get(0).getMediaMetadata().get(2).getUrl();

            GlideApp
                    .with(context)
                    .load(urlImage)
                    .apply(requestOptions)
                    .into(rowHolder.image);

            rowHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index = position;
                    notifyDataSetChanged();

                    // mListener.onClick();
                    mListener.onClick(view, position);
                }
            });

            if(row_index==position){ }

            rowHolder.title.setTextColor(context.getResources().getColor(R.color.black));
        }

    }
    public   class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private TextView title,date,author,section;
        private ImageView image;
        public ConstraintLayout layout;


        RowViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);


            mListener = listener;

            title = (TextView) v.findViewById(R.id.title);
            author = (TextView) v.findViewById(R.id.author);
           date = (TextView) v.findViewById(R.id.date);
            section = (TextView) v.findViewById(R.id.section);
            image =  v.findViewById(R.id.thumbnail);
            v.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {

        }


    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }
/*
    public void setResults(ArrayList<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();
    }
*/

}

