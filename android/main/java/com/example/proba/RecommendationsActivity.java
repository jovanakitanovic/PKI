package com.example.proba;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.tabs.TabLayout;

import java.util.LinkedList;

public class RecommendationsActivity extends AppCompatActivity {

    public static boolean userPanelMoved = false;
    public static boolean created = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recommendations);

        Bundle b = getIntent().getExtras();
        int bookID = b.getInt("ID");

        created = true;

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

        RecyclerView userToRecommend = findViewById(R.id.recommendationUsers);
        recommenrInfoGrayPanel = findViewById(R.id.recommenrInfoGrayPanel);

        RecUsersAdapter usersToRecommend = new RecUsersAdapter(GlobalData.allUsers,bookID);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.recommendationUsers);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(usersToRecommend);


        userToRecommend.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!userPanelMoved) {
                    userPanelMoved = true;
                    recommenrInfoGrayPanel.animate().translationYBy((float) -400).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
                    System.out.println("ON TOUCH EVENT GRAY");
                }
                return false;
            }
        });

        ScrollView bookInfoData = findViewById(R.id.bookInfoDataRightPane);
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

        Button dataRecommendCancel = findViewById(R.id.dataRecommendCancel);
        dataRecommendCancel.setOnClickListener(listen -> {
            Intent page = new Intent(this, BookInfoActivity.class);
            Bundle bookCommentBundle = new Bundle();
            bookCommentBundle.putInt("ID", (bookID + 1));
            page.putExtras(bookCommentBundle);
            this.startActivity(page);
        });

        Button dataRecommendAccept = findViewById(R.id.dataRecommendAccept);
        dataRecommendAccept.setOnClickListener(listen -> {

            System.out.println("BROJ U NIZU " + recToIdArray.size());

            for (int i = 0; i < recToIdArray.size(); i++) {
                System.out.println("PREPORUČENO " + recToIdArray.get(i));
                GlobalData.allReccomendations.add(new ReccomendationData(recToIdArray.get(i),
                        "" + book.ID, book.bookAuthor, book.bookName, book.image, GlobalData.loggedInUser.username));
            }

            System.out.println("CEO NIZ " + GlobalData.allReccomendations);

            Intent page = new Intent(this, BookInfoActivity.class);
            Bundle bookCommentBundle = new Bundle();
            bookCommentBundle.putInt("ID", (bookID + 1));
            page.putExtras(bookCommentBundle);
            this.startActivity(page);
        });


        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------

        BookInfoActivity.bookCommentMoved = false;
        BookInfoActivity.bookInfoMoved = false;
        // MyAccountActivity.recommentdionPaneMoved = false;

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------
        //------------MENI OPEN---------------------------------------------------------------------------------------------------------------------------

        ImageButton bookInfoMeniButton = findViewById(R.id.bookInfoMeniButton);
        bookInfoMeniButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu meni = new PopupMenu(RecommendationsActivity.this, bookInfoMeniButton);
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


    public static LinkedList<String> recToIdArray = new LinkedList<>();
    private static TableRow recommenrInfoGrayPanel;

    public static void recommendToAdd(String id) {
        recToIdArray.add(id);
    }

    public static void clickedOnUser() {
        if (!userPanelMoved) {
            userPanelMoved = true;
            recommenrInfoGrayPanel.animate().translationYBy((float) -400).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
            System.out.println("ON TOUCH EVENT GRAY");
        }
    }

    public static void recommendToRemove(String id) {
        recToIdArray.remove(id);
    }

    public static boolean recommendToAlreadyInArray(String id) {
        boolean contains = recToIdArray.contains(id);
        return contains;
    }

    public void onTouchWhitePanel() {
        TableRow recommenrInfoGrayPanel = findViewById(R.id.recommenrInfoGrayPanel);

        if (userPanelMoved) {
            userPanelMoved = false;
            recommenrInfoGrayPanel.animate().translationYBy((float) 400).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
            System.out.println("ON TOUCH EVENT WHITE");
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