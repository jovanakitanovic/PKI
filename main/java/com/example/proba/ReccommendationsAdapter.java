package com.example.proba;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class ReccommendationsAdapter extends RecyclerView.Adapter<ReccommendationsAdapter.ViewHolder> {

    private LinkedList<ReccomendationData> allRecs;

    public ReccommendationsAdapter(LinkedList<ReccomendationData> allRecs){
        this.allRecs=allRecs;
        System.out.println("COMMENT ADAPTER "+this.allRecs.size());
    }

    @NonNull
    @Override
    public ReccommendationsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.reccommendations_layout, parent, false);

        return new ReccommendationsAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReccommendationsAdapter.ViewHolder holder, int position) {
        ImageView iv=holder.itemView.findViewById(R.id.recommendationImage);
        iv.setImageResource(allRecs.get(position).bookImage);

        TextView name=holder.itemView.findViewById(R.id.recommendationBookName);
        name.setText(allRecs.get(position).bookName);

        TextView bookAutor=holder.itemView.findViewById(R.id.recommendationBookAuthor);
        bookAutor.setText(allRecs.get(position).bookAuthor);

        TextView userFrom=holder.itemView.findViewById(R.id.recommendationUserFrom);
        userFrom.setText(allRecs.get(position).userFrom);
    }

    @Override
    public int getItemCount() {
        return allRecs.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            layout = (LinearLayout) view.findViewById(R.id.myAccountAllReccomendations);
            view.findViewById(R.id.recommendationImage).setOnClickListener(listen->{
                int pos=getAdapterPosition();

                AllBooksActivity.BookInfo(Integer.parseInt(allRecs.get(pos).bookId), view.getContext());
            });
        }

        public LinearLayout getScroll() {
            return layout;
        }
    }
}
