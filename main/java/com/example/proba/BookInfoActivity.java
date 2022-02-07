package com.example.proba;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class BookInfoActivity extends AppCompatActivity {
    static boolean bookInfoMoved = false;
    static boolean bookCommentMoved = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.book_info);

        Bundle b = getIntent().getExtras();
        int bookID = b.getInt("ID") - 1;

        System.out.println("BOOK ID " + bookID);

        int logoSource = getResources().getIdentifier("@drawable/logo_no_background", null, this.getPackageName());
        ImageView logoImage = (ImageView) findViewById(R.id.frontPageLogo);
        logoImage.setImageResource(logoSource);
        logoImage.setOnClickListener(listen -> {
            Intent page = new Intent(this, AllBooksActivity.class);
            this.startActivity(page);
        });

        BookData book = GlobalData.allBooks[bookID];

        System.out.println("BOOK NAME " + book.bookName);

        ImageView bookInfoImage = (ImageView) findViewById(R.id.bookInfoImage);
        bookInfoImage.setImageResource(book.image);

        TextView bookInfoName = findViewById(R.id.bookInfoName);
        bookInfoName.setText(book.bookName);

        TextView bookInfoAutor = findViewById(R.id.bookInfoAutor);
        bookInfoAutor.setText(book.bookAuthor);

        TextView bookInfoDescription = findViewById(R.id.bookInfoDescription);
        bookInfoDescription.setText(book.opis);

        TextView bookInfoNumOfPages = findViewById(R.id.bookInfoNumOfPages);
        bookInfoNumOfPages.setText("broj stana " + book.numPages);

        TextView bookInfoYear = findViewById(R.id.bookInfoYear);
        bookInfoYear.setText("godina izdanja " + book.year);


        CommentsAdapter comments = new CommentsAdapter(book.comments);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.bookInfoComments);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(comments);

        boolean commented = false;

        for (int i = 0; i < book.comments.size(); i++) {
            if (book.comments.get(i).user.equals(GlobalData.loggedInUser.username)) {
                commented = true;

                TextView tv = findViewById(R.id.bookInfoMyCommentText);
                tv.setText(book.comments.get(i).comment);

                RatingBar rb = findViewById(R.id.bookInfoMyCommentRating);
                rb.setRating(book.comments.get(i).num);
            }
        }
        TableRow askForComment = findViewById(R.id.bookInfoAskForComment);
        TableRow myComment = findViewById(R.id.bookInfoMyComment);
        if (commented) {
            askForComment.setVisibility(View.GONE);
            myComment.setVisibility(View.VISIBLE);
        } else {
            askForComment.setVisibility(View.VISIBLE);
            myComment.setVisibility(View.GONE);
        }

        TableRow bookInfoGrayPanel = findViewById(R.id.bookInfoGrayPanel);
        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!bookInfoMoved) {
                    bookInfoMoved = true;
                    bookInfoGrayPanel.animate().translationYBy((float) -600).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
                    System.out.println("ON TOUCH EVENT GRAY");
                }
                if (bookCommentMoved) {
                    bookCommentMoved = false;
                    myComment.animate().translationYBy((float) 300).setInterpolator(new AccelerateInterpolator()).setDuration(1500);

                }
                return false;
            }
        });

        ScrollView bookInfoData = findViewById(R.id.bookInfoDataRightPane);

        TableRow bookIngoWhitePanel = findViewById(R.id.bookIngoWhitePanel);

        bookInfoImage.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                onTouchWhitePanel();
                return false;
            }
        });
        bookInfoData.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                onTouchWhitePanel();
                return false;
            }
        });

        ScrollView bookInfoComment = findViewById(R.id.bookInfoComment);

        bookInfoComment.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!bookCommentMoved) {
                    bookCommentMoved = true;
                    myComment.animate().translationYBy((float) -300).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
                    System.out.println("ON TOUCH EVENT COMMENT");

                }
                return false;
            }
        });

        Button commentButton=findViewById(R.id.commentButton);
        commentButton.setOnClickListener(listen->{
            Intent page = new Intent(this, BookCommentActivity.class);
            Bundle bookCommentBundle = new Bundle();
            bookCommentBundle.putInt("ID", (bookID));
            page.putExtras(bookCommentBundle);
            this.startActivity(page);
        });

        Button bookRecommendButton=findViewById(R.id.bookRecommendButton);
        bookRecommendButton.setOnClickListener(listen->{
            Intent page = new Intent(this, RecommendationsActivity.class);
            Bundle bookCommentBundle = new Bundle();
            bookCommentBundle.putInt("ID", (bookID));
            page.putExtras(bookCommentBundle);
            this.startActivity(page);
        });

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------

        BookInfoActivity.bookCommentMoved = false;
        BookInfoActivity.bookInfoMoved = false;
       // MyAccountActivity.recommentdionPaneMoved = false;
        RecommendationsActivity.userPanelMoved=false;

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------
        //------------MENI OPEN---------------------------------------------------------------------------------------------------------------------------

        ImageButton bookInfoMeniButton = findViewById(R.id.bookInfoMeniButton);
        bookInfoMeniButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu meni = new PopupMenu(BookInfoActivity.this, bookInfoMeniButton);
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

    public void onTouchWhitePanel() {
        TableRow bookInfoGrayPanel = findViewById(R.id.bookInfoGrayPanel);
        TableRow myComment = findViewById(R.id.bookInfoMyComment);

        if (bookInfoMoved) {
            bookInfoMoved = false;
            bookInfoGrayPanel.animate().translationYBy((float) 600).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
            System.out.println("ON TOUCH EVENT WHITE");
        }
        if (bookCommentMoved) {
            bookCommentMoved = false;
            myComment.animate().translationYBy((float) 300).setInterpolator(new AccelerateInterpolator()).setDuration(1500);

        }
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
