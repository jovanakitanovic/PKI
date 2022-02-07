package com.example.proba;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class PasswordChangeActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_change);

        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        EditText oldPassword = findViewById(R.id.passwordChangeOldPassword);
        EditText newPassword = findViewById(R.id.passwordChangeNewPassword);
        EditText newPasswordAgain = findViewById(R.id.passwordChangeNewPasswordAgain);


        Button dataChangeCancel = findViewById(R.id.passwordChangeCancel);
        dataChangeCancel.setOnClickListener(listen -> {
            Intent page = new Intent(this, MyAccountActivity.class);
            this.startActivity(page);
        });

        Button dataChangeAccept = findViewById(R.id.passwordChangeAccept);
        dataChangeAccept.setOnClickListener(listen->{

            String oldPass=oldPassword.getText().toString();
            String newPass=newPassword.getText().toString();
            String newPassAgain=newPasswordAgain.getText().toString();

            int index=-1;

            if(oldPass.equals("") || newPass.equals("") || newPassAgain.equals("") ){
                Toast.makeText(this, "morate popuniti sva polja", Toast.LENGTH_SHORT).show();
            }
            else
            {
                if(!oldPass.equals(GlobalData.loggedInUser.pass)) {
                    Toast.makeText(this, "uneta stara lozinka nije ispravna", Toast.LENGTH_SHORT).show();
                    oldPassword.setText("");
                    newPassword.setText("");
                    newPasswordAgain.setText("");
                }
                else
                    if(!newPass.equals(newPassAgain)){
                        Toast.makeText(this, "nove lozinke se ne poklapaju", Toast.LENGTH_SHORT).show();
                        newPassword.setText("");
                        newPasswordAgain.setText("");
                    }
                    else{
                        for(int i=0;i<GlobalData.allUsers.size();i++){
                            System.out.println("LISTENED "+GlobalData.allUsers.get(i).username);
                            if(GlobalData.allUsers.get(i).username.equals(GlobalData.loggedInUser.username)) {
                                System.out.println(GlobalData.allUsers.get(i).username+" "+GlobalData.loggedInUser.username);
                                index = i;
                            }
                        }

                        if(index>=0){
                            GlobalData.allUsers.get(index).pass=newPass;
                            GlobalData.loggedInUser.pass=newPass;

                            toMyAccount();
                        }
                    }
            }


        } );

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------

        BookInfoActivity.bookCommentMoved=false;
        BookInfoActivity.bookInfoMoved=false;
        //MyAccountActivity.recommentdionPaneMoved=false;
        RecommendationsActivity.userPanelMoved=false;

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------
        //------------LOGO IMAGE ---------------------------------------------------------------------------------------------------------------------------

        int logoSource = getResources().getIdentifier("@drawable/logo_no_background", null, this.getPackageName());
        ImageView logoImage = (ImageView) findViewById(R.id.frontPageLogo);
        logoImage.setImageResource(logoSource);
        logoImage.setOnClickListener(listen->{
            Intent page = new Intent(this, AllBooksActivity.class);
            this.startActivity(page);
        });

        //------------LOGO IMAGE END---------------------------------------------------------------------------------------------------------------------------
        //------------MENI OPEN---------------------------------------------------------------------------------------------------------------------------

        ImageButton bookInfoMeniButton = findViewById(R.id.bookInfoMeniButton);
        bookInfoMeniButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                PopupMenu meni = new PopupMenu(PasswordChangeActivity.this, bookInfoMeniButton);
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
