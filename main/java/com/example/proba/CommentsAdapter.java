package com.example.proba;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class CommentsAdapter extends RecyclerView.Adapter<CommentsAdapter.ViewHolder> {

    private LinkedList<CommentsData> allComments;

    public CommentsAdapter(LinkedList<CommentsData> allComments){
        this.allComments=allComments;
        System.out.println("COMMENT ADAPTER "+this.allComments.size());
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.comment_layout, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tv1=holder.itemView.findViewById(R.id.bookInfoCommentUser);
        tv1.setText(allComments.get(position).user);

        TextView tv2=holder.itemView.findViewById(R.id.bookInfoCommentText);
        tv2.setText(allComments.get(position).comment);

        RatingBar iv=holder.itemView.findViewById(R.id.bookInfoCommentRating);
        iv.setRating(allComments.get(position).num);
    }

    @Override
    public int getItemCount() {
        return allComments.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final ScrollView scroll;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            scroll = (ScrollView) view.findViewById(R.id.bookInfoComments);
        }

        public ScrollView getScroll() {
            return scroll;
        }
    }
}
