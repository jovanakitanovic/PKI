package com.example.proba;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapterFront extends RecyclerView.Adapter<BookAdapterFront.ViewHolder> {

    private BookData[] allBooks;

    public BookAdapterFront(BookData[] allBooks){
        this.allBooks=allBooks;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.front_page_images_layout_front, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        /*TextView tv1=holder.itemView.findViewById(R.id.frontPageBookName);
        tv1.setText(allBooks[position].bookName);

        TextView tv2=holder.itemView.findViewById(R.id.frontPageBookAuthor);
        tv2.setText(allBooks[position].bookAuthor);*/

        ImageView iv=holder.itemView.findViewById(R.id.imageFrontPage);
        iv.setImageResource(allBooks[position].image);
    }

    @Override
    public int getItemCount() {
        return allBooks.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView card;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            card = (CardView) view.findViewById(R.id.frontPageBooks);
        }

        public CardView getCard() {
            return card;
        }
    }
}
