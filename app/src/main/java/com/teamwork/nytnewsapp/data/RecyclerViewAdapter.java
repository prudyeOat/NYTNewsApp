package com.teamwork.nytnewsapp.data;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.teamwork.nytnewsapp.R;

import java.util.ArrayList;
import java.util.List;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private RecyclerViewClickListener mListener;
    private ArrayList<String> strings;
    private List<TextView> items;
    private int row_index;
    private Context context;
    private RowViewHolder rowHolder;


    public RecyclerViewAdapter(RecyclerViewClickListener listener, ArrayList<String> strings) {
        mListener = listener;
        this.strings=strings;
        this.items = new ArrayList<>();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
   context = parent.getContext();
        View v = LayoutInflater.from(context).inflate(R.layout.options_row, parent, false);
        return new RowViewHolder(v, mListener);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //holder.name
        if (holder instanceof RowViewHolder) {

            rowHolder = (RowViewHolder) holder;

           rowHolder.serviceName.setText(strings.get(position));



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

        //    rowHolder.serviceName.setTextColor(context.getResources().getColor(R.color.black));
        }

    }
    public   class RowViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private RecyclerViewClickListener mListener;
        private TextView serviceName;
        public ConstraintLayout layout;


        RowViewHolder(View v, RecyclerViewClickListener listener) {
            super(v);


            mListener = listener;

            serviceName = (TextView) v.findViewById(R.id.options_text);

            v.setOnClickListener(this);
/*
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    makeAllWhite();
                   // items.add(rowHolder.name);
                   // name.setBackgroundColor(Color.parseColor("#d5d5d5"));
                }
            });
*/
        }

        @Override
        public void onClick(View view) {

        }


    }

    @Override
    public int getItemCount() {
        return strings.size();
    }
    public interface RecyclerViewClickListener {

        void onClick(View view, int position);
    }




    }

