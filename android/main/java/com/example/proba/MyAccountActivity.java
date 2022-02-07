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
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class MyAccountActivity extends AppCompatActivity {

    public static boolean recommentdionPaneMoved = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.my_account);

        TextView nameSurnameTV = findViewById(R.id.myAccountNameSurname);
        nameSurnameTV.setText(GlobalData.loggedInUser.name + " " + GlobalData.loggedInUser.surname);

        TextView telTV = findViewById(R.id.myAccountTel);
        telTV.setText(GlobalData.loggedInUser.tel);

        TextView addressTV = findViewById(R.id.myAccountAddress);
        addressTV.setText(GlobalData.loggedInUser.adress);

        TextView usernameTV = findViewById(R.id.myAccountUsername);
        usernameTV.setText(GlobalData.loggedInUser.username);

        TableRow recs = findViewById(R.id.myAccountReccmendations);
        TableRow data = findViewById(R.id.myAccountData);

        System.out.println("DUZINA NIZA " + GlobalData.allReccomendationsUser.size());
        if (GlobalData.allReccomendationsUser.size() == 0) {
            data.getLayoutParams().height= ViewGroup.LayoutParams.MATCH_PARENT;
            recs.setVisibility(View.GONE);
        }

        ReccommendationsAdapter adapter = new ReccommendationsAdapter(GlobalData.allReccomendationsUser);
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.myAccountAllReccomendations);
        mRecyclerView.setLayoutManager(layoutManager1);
        mRecyclerView.setAdapter(adapter);

        Button myAccountDataChange = findViewById(R.id.myAccountDataChange);
        myAccountDataChange.setOnClickListener(listen -> {
            Intent page = new Intent(this, DataChangeActivity.class);
            this.startActivity(page);
        });

        Button myAccountPasswordChange = findViewById(R.id.myAccountPasswordChange);
        myAccountPasswordChange.setOnClickListener(listen -> {
            Intent page = new Intent(this, PasswordChangeActivity.class);
            this.startActivity(page);
        });

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (!recommentdionPaneMoved) {
                    recommentdionPaneMoved = true;
                    recs.animate().translationYBy((float) -600).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
                    System.out.println("ON TOUCH EVENT RECCOMMENDATIONS");
                }
                return false;

            }
        });

        LinearLayout myAccountDataLayout=findViewById(R.id.myAccountDataLayout);
        myAccountDataLayout.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                whitePanelOpened();
                return false;

            }
        });

        TableRow myDataMojiPodaci=findViewById(R.id.myDataMojiPodaci);
        myDataMojiPodaci.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                whitePanelOpened();
                return false;

            }
        });

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
                PopupMenu meni = new PopupMenu(MyAccountActivity.this, bookInfoMeniButton);
                meni.getMenuInflater().inflate(R.menu.popum_menu_without_my_account, meni.getMenu());
                meni.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem menuItem) {
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

    public void whitePanelOpened(){
        TableRow recs = findViewById(R.id.myAccountReccmendations);
        if (recommentdionPaneMoved) {
            recommentdionPaneMoved = false;
            recs.animate().translationYBy((float) 600).setInterpolator(new AccelerateInterpolator()).setDuration(1500);
            System.out.println("ON TOUCH EVENT RECCOMMENDATIONS");
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

    public void toRecommendations() {
        Intent page = new Intent(this, AllRecomendationsActivity.class);
        this.startActivity(page);
    }

    public void toMain() {
        Intent page = new Intent(this, MainActivity.class);
        this.startActivity(page);
    }
}
