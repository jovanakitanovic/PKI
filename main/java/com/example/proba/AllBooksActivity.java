package com.example.proba;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class AllBooksActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_books);

        int logoSource = getResources().getIdentifier("@drawable/logo_no_background", null, this.getPackageName());
        ImageView logoImage = (ImageView) findViewById(R.id.frontPageLogo);
        logoImage.setImageResource(logoSource);

        BookAdapterAllBooks adapter1 = new BookAdapterAllBooks(GlobalData.allBooksRow1, R.layout.front_page_images_layout_white);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.allBooksRow1);
        mRecyclerView.setLayoutManager(layoutManager1);
        mRecyclerView.setAdapter(adapter1);

        BookAdapterAllBooks adapter2 = new BookAdapterAllBooks(GlobalData.allBooksRow2, R.layout.front_page_images_layout_white);
        LinearLayoutManager layoutManager2 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView2 = (RecyclerView) findViewById(R.id.allBooksRow2);
        mRecyclerView2.setLayoutManager(layoutManager2);
        mRecyclerView2.setAdapter(adapter2);

        BookAdapterAllBooks adapter3 = new BookAdapterAllBooks(GlobalData.allBooksPromo, R.layout.front_page_images_layout_gray);
        LinearLayoutManager layoutManager3 = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView3 = (RecyclerView) findViewById(R.id.allBooksPromo);
        mRecyclerView3.setLayoutManager(layoutManager3);
        mRecyclerView3.setAdapter(adapter3);

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------

        BookInfoActivity.bookCommentMoved=false;
        BookInfoActivity.bookInfoMoved=false;
        //MyAccountActivity.recommentdionPaneMoved=false;
        RecommendationsActivity.userPanelMoved=false;

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------
        //------------MENI OPEN---------------------------------------------------------------------------------------------------------------------------

        ImageButton bookInfoMeniButton = findViewById(R.id.bookInfoMeniButton);
        bookInfoMeniButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu meni = new PopupMenu(AllBooksActivity.this, bookInfoMeniButton);
                meni.getMenuInflater().inflate(R.menu.popum_menu, meni.getMenu());
                meni.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
                        if (menuItem.getTitle().equals("moj nalog")) {
                            toMyAccount();
                        }
                        if (menuItem.getTitle().equals("preporuke")) {
                            toRecommendations();
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

    public static void BookInfo(int bookId, Context context) {
        Intent bookInfo = new Intent(context, BookInfoActivity.class);
        Bundle b = new Bundle();
        b.putInt("ID", bookId);
        bookInfo.putExtras(b);
        context.startActivity(bookInfo);
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

    public void toRecommendations() {
        Intent page = new Intent(this, AllRecomendationsActivity.class);
        this.startActivity(page);
    }

    public void toMain() {
        Intent page = new Intent(this, MainActivity.class);
        this.startActivity(page);
    }
}
