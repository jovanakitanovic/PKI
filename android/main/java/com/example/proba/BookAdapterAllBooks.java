package com.example.proba;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class BookAdapterAllBooks extends RecyclerView.Adapter<BookAdapterAllBooks.ViewHolder> {

    private BookData[] allBooks;
    private int viewID;

    public BookAdapterAllBooks(BookData[] allBooks, int viewID) {
        this.allBooks = allBooks;
        this.viewID = viewID;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(viewID, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        TextView tv1 = holder.itemView.findViewById(R.id.frontPageBookName);
        tv1.setText(allBooks[position].bookName);

        TextView tv2 = holder.itemView.findViewById(R.id.frontPageBookAuthor);
        tv2.setText(allBooks[position].bookAuthor);

        ImageView iv = holder.itemView.findViewById(R.id.imageFrontPage);
        iv.setImageResource(allBooks[position].image);
    }

    @Override
    public int getItemCount() {
        return allBooks.length;
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final CardView card;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            card = (CardView) view.findViewById(R.id.frontPageBooks);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos=getAdapterPosition();

                    AllBooksActivity.BookInfo(allBooks[pos].ID, view.getContext());

                }
            });
        }

        public CardView getCard() {
            return card;
        }
    }
}
