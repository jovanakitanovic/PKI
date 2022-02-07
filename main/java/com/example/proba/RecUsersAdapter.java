package com.example.proba;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.LinkedList;

public class RecUsersAdapter extends RecyclerView.Adapter<RecUsersAdapter.ViewHolder> {

    private LinkedList<UserData> allUsers;
    private String bookId;
    private LinkedList<UserData> noClick;

    public RecUsersAdapter(LinkedList<UserData> allUsers,int bookID){

        this.bookId=""+(bookID+1);
        RecommendationsActivity.recToIdArray=new LinkedList<>();
        noClick=new LinkedList<>();

        this.allUsers=new LinkedList<>();
        for (int i=0;i<allUsers.size();i++)
            if(!allUsers.get(i).username.equals(GlobalData.loggedInUser.username))
                this.allUsers.add(allUsers.get(i));


        for(int i=0;i<GlobalData.allReccomendations.size();i++){
            if(GlobalData.allReccomendations.get(i).userFrom.equals(GlobalData.loggedInUser.username) && GlobalData.allReccomendations.get(i).bookId.equals(bookId))
                RecommendationsActivity.recommendToAdd(GlobalData.allReccomendations.get(i).userTo);
        }
       //System.out.println("COMMENT ADAPTER "+this.allComments.size());
    }


    @NonNull
    @Override
    public RecUsersAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.users_recommendations_layout, parent, false);

        return new RecUsersAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecUsersAdapter.ViewHolder holder, int position) {

            TextView tv = holder.itemView.findViewById(R.id.recUsername);
            tv.setText(allUsers.get(position).username);

            if(RecommendationsActivity.recommendToAlreadyInArray(allUsers.get(position).username))
            {
                Resources res = holder.itemView.getResources();
                Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.rounded_style_light_gray_4_sides, null);

                LinearLayout layout=  holder.itemView.findViewById(R.id.recUserBackgroundPanel);
                layout.setBackground(drawable);

                noClick.add(allUsers.get(position));
                RecommendationsActivity.recommendToRemove(allUsers.get(position).username);
            }

    }

    @Override
    public int getItemCount() {
        return allUsers.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {
        private final LinearLayout layout;

        public ViewHolder(View view) {
            super(view);
            // Define click listener for the ViewHolder's View

            layout = (LinearLayout) view.findViewById(R.id.recommendationUsers);
            view.findViewById(R.id.recUserBackgroundPanel).setOnClickListener(listen->{
                int pos=getAdapterPosition();

                System.out.println("POS JE "+pos);

                if(!noClick.contains(allUsers.get(pos))) {
                    if (RecommendationsActivity.recommendToAlreadyInArray(allUsers.get(pos).username)) {
                        System.out.println("VEĆ JE U NIZU " + pos);
                        RecommendationsActivity.recommendToRemove(allUsers.get(pos).username);

                        Resources res = view.getResources();
                        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.rounded_style_white_4_sides, null);

                        LinearLayout layout = view.findViewById(R.id.recUserBackgroundPanel);
                        layout.setBackground(drawable);

                    } else {
                        System.out.println("STAVITI U NIZ " + (pos));
                        RecommendationsActivity.recommendToAdd(allUsers.get(pos).username);

                        Resources res = view.getResources();
                        Drawable drawable = ResourcesCompat.getDrawable(res, R.drawable.rounded_style_light_gray_4_sides, null);

                        LinearLayout layout = view.findViewById(R.id.recUserBackgroundPanel);
                        layout.setBackground(drawable);
                    }
                }

                RecommendationsActivity.clickedOnUser();
            });
        }

        public LinearLayout getScroll() {
            return layout;
        }
    }

}
