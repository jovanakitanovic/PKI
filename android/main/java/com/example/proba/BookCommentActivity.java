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

public class BookCommentActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comment_books);

        Bundle b = getIntent().getExtras();
        int bookID = b.getInt("ID");

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

        Button commentCancel = findViewById(R.id.commentCancel);
        commentCancel.setOnClickListener(listen -> {
            Intent page = new Intent(this, BookInfoActivity.class);
            Bundle bookCommentBundle = new Bundle();
            bookCommentBundle.putInt("ID", bookID);
            page.putExtras(bookCommentBundle);
            this.startActivity(page);
        });

        Button commentAccept = findViewById(R.id.commentAccept);
        commentAccept.setOnClickListener(listen -> {

            EditText commentTextET = findViewById(R.id.bookCommentText);
            RatingBar starBar = findViewById(R.id.commentStas);

            String commentText = commentTextET.getText().toString();
            float num = starBar.getRating();

            if (commentText.equals("") && num == 0) {
                Toast.makeText(this, "ne možete potrditi prazan komentar", Toast.LENGTH_SHORT).show();
            } else {
                System.out.println("ID KNJIGE JE " + bookID);

                GlobalData.allBooks[bookID].comments.addFirst(new CommentsData(GlobalData.loggedInUser.username, (int) num, commentText));

                Intent page = new Intent(this, BookInfoActivity.class);
                Bundle bookCommentBundle = new Bundle();
                bookCommentBundle.putInt("ID", bookID + 1);
                page.putExtras(bookCommentBundle);
                this.startActivity(page);


            }

        });

        Button bookRecommendButton = findViewById(R.id.bookRecommendButton);
        bookRecommendButton.setOnClickListener(listen -> {
            Intent page = new Intent(this, RecommendationsActivity.class);
            Bundle bookCommentBundle = new Bundle();
            bookCommentBundle.putInt("ID", bookID);
            page.putExtras(bookCommentBundle);
            this.startActivity(page);
        });

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------

        BookInfoActivity.bookCommentMoved = false;
        BookInfoActivity.bookInfoMoved = false;
        RecommendationsActivity.userPanelMoved=false;
        // MyAccountActivity.recommentdionPaneMoved = false;

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------
        //------------MENI OPEN---------------------------------------------------------------------------------------------------------------------------

        ImageButton bookInfoMeniButton = findViewById(R.id.bookInfoMeniButton);
        bookInfoMeniButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu meni = new PopupMenu(BookCommentActivity.this, bookInfoMeniButton);
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
