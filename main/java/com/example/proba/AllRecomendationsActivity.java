package com.example.proba;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllRecomendationsActivity extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_recommendations);


        ReccommendationsAdapter adapter = new ReccommendationsAdapter(GlobalData.allReccomendationsUser);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.myAccountAllReccomendations);
        mRecyclerView.setLayoutManager(layoutManager1);
        mRecyclerView.setAdapter(adapter);


        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------

        BookInfoActivity.bookCommentMoved = false;
        BookInfoActivity.bookInfoMoved = false;
        MyAccountActivity.recommentdionPaneMoved=false;
        RecommendationsActivity.userPanelMoved=false;

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------
        //------------LOGO IMAGE ---------------------------------------------------------------------------------------------------------------------------

        int logoSource = getResources().getIdentifier("@drawable/logo_no_background", null, this.getPackageName());
        ImageView logoImage = (ImageView) findViewById(R.id.frontPageLogo);
        logoImage.setImageResource(logoSource);
        logoImage.setOnClickListener(listen ->

        {
            Intent page = new Intent(this, AllBooksActivity.class);
            this.startActivity(page);
        });

        //------------LOGO IMAGE END---------------------------------------------------------------------------------------------------------------------------
        //------------MENI OPEN---------------------------------------------------------------------------------------------------------------------------

        ImageButton bookInfoMeniButton = findViewById(R.id.bookInfoMeniButton);
        bookInfoMeniButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu meni = new PopupMenu(AllRecomendationsActivity.this, bookInfoMeniButton);
                meni.getMenuInflater().inflate(R.menu.popum_menu_without_recs, meni.getMenu());
                meni.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("moj nalog")) {
                            toMyAccount();
                        }
                        if (menuItem.getTitle().equals("odjavljivanje")) {
                            GlobalData.loggedInUser = null;
                            toMain();
                        }
                        return false;
                    }
                });
                meni.show();
            }
        });
//------------MENI OPEN END--------------------------------------------------------------------------------------------------------------------------

    }

    @Override
    protected void onRestart() {
        super.onRestart();
        //Toast.makeText(this, "on restart", Toast.LENGTH_SHORT).show();

        if (GlobalData.loggedInUser == null) {
            //Toast.makeText(this, "nema korisnika", Toast.LENGTH_SHORT).show();
            Intent page = new Intent(this, MainActivity.class);
            this.startActivity(page);
        }
    }

    public void toMyAccount() {
        Intent page = new Intent(this, MyAccountActivity.class);
        this.startActivity(page);
    }

    public void toMain() {
        Intent page = new Intent(this, MainActivity.class);
        this.startActivity(page);
    }
}

