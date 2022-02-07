package com.example.proba;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------

        BookInfoActivity.bookCommentMoved=false;
        BookInfoActivity.bookInfoMoved=false;
       // MyAccountActivity.recommentdionPaneMoved=false;
        RecommendationsActivity.userPanelMoved=false;

        //------------BOOK ACTIVITY DATA OPEN---------------------------------------------------------------------------------------------------------------------------


        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);

        int logoSource = getResources().getIdentifier("@drawable/logo_no_background", null, this.getPackageName());
        ImageView logoImg = (ImageView) findViewById(R.id.frontPageLogo);
        logoImg.setImageResource(logoSource);

        //BookData[] allBooks = new BookData[10];

        TableRow frontLogin = findViewById(R.id.frontLogin);
        TableRow createAccount = findViewById(R.id.createAccount);

        createAccount.setVisibility(View.GONE);

        BookAdapterFront adapter = new BookAdapterFront(GlobalData.allBooksRow1);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        RecyclerView mRecyclerView = (RecyclerView) findViewById(R.id.frontPageBooks);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(adapter);

        Button loginButton = findViewById(R.id.loginButton);
        loginButton.setOnClickListener(view -> {
            //Toast.makeText(this, "pozvnano", Toast.LENGTH_SHORT).show();
            EditText usernameInputField = findViewById(R.id.frontLoginUsername);
            String user = usernameInputField.getText().toString();

            EditText passwordInputField = findViewById(R.id.frontLoginPass);
            String pass = passwordInputField.getText().toString();


            boolean userExists = false;

            if (!user.equals("") && !pass.equals("")) {

                for (int i = 0; i <GlobalData. allUsers.size(); i++) {
                    if (GlobalData.allUsers.get(i).username.equals(user) && GlobalData.allUsers.get(i).pass.equals(pass)) {
                        System.out.println("USERNAME " + GlobalData.allUsers.get(i).username + " PASSWORD " + GlobalData.allUsers.get(i).pass);

                        userExists = true;
                        passwordInputField.setText("");
                        usernameInputField.setText("");
                        GlobalData.loggedInUser = GlobalData.allUsers.get(i);
                        fillReccomendationsArray();

                        Intent allBooksPage = new Intent(this, AllBooksActivity.class);
                        startActivity(allBooksPage);
                    }
                }
                if (userExists == false)
                    Toast.makeText(this, "korisnik ne postoji u sistemu", Toast.LENGTH_SHORT).show();
            } else {
                //Toast.makeText(this, "morate popuniti sva polja", Toast.LENGTH_SHORT).show();
                GlobalData.loggedInUser = GlobalData.allUsers.get(0);

                fillReccomendationsArray();

                Intent allBooksPage = new Intent(this, AllBooksActivity.class);
                startActivity(allBooksPage);
            }
        });

        TextView noAccount = findViewById(R.id.loginNoAccount);
        noAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frontLogin.setVisibility(View.GONE);
                createAccount.setVisibility(View.VISIBLE);
            }
        });

        TextView calcelNoAccount = findViewById(R.id.createAccountCancel);
        calcelNoAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                frontLogin.setVisibility(View.VISIBLE);
                createAccount.setVisibility(View.GONE);
            }
        });

        Button createAccountButton = findViewById(R.id.createAccountButton);
        createAccountButton.setOnClickListener(view -> {
            createNewAccount();
        });

    }




    public void createNewAccount() {
        TextView usernameTV = (findViewById(R.id.createAccountUsername));
        String username = usernameTV.getText().toString();

        TextView passwordTV = findViewById(R.id.createAccountPassword);
        String password = passwordTV.getText().toString();

        TextView nameTV = findViewById(R.id.createAccountName);
        String name = nameTV.getText().toString();

        TextView surnameTV = findViewById(R.id.createAccountSurname);
        String surname = surnameTV.getText().toString();

        TextView telTV = findViewById(R.id.createAccountTel);
        String tel = telTV.getText().toString();

        TextView addressTV = findViewById(R.id.createAccountAddress);
        String address = addressTV.getText().toString();

        if (username.equals("") || password.equals("") || name.equals("") || surname.equals("") || tel.equals("") || address.equals("")) {
            Toast.makeText(this, "morate popuniti sva polja", Toast.LENGTH_SHORT).show();
        } else {
            boolean exists = false;
            for (int i = 0; i < GlobalData.allUsers.size(); i++) {
                if (GlobalData.allUsers.get(i).username.equals(username)) {
                    exists = true;
                }
            }

            if (exists) {
                Toast.makeText(this, "korisničko ime je zauzeto", Toast.LENGTH_SHORT).show();
                usernameTV.setText("");
            } else {
                usernameTV.setText("");
                passwordTV.setText("");
                nameTV.setText("");
                surnameTV.setText("");
                telTV.setText("");
                addressTV.setText("");

                EditText usernameInputField = findViewById(R.id.frontLoginUsername);
                usernameInputField.setText(username);

                EditText passwordInputField = findViewById(R.id.frontLoginPass);
                passwordInputField.setText(password);

                TableRow frontLogin = findViewById(R.id.frontLogin);
                TableRow createAccount = findViewById(R.id.createAccount);
                frontLogin.setVisibility(View.VISIBLE);
                createAccount.setVisibility(View.GONE);

                GlobalData.allUsers.add(new UserData(username, password, name, surname, tel, address));
            }
        }
    }

    private  void fillReccomendationsArray() {

            GlobalData.allReccomendationsUser = new LinkedList<>();

            for (int i = 0; i < GlobalData.allReccomendations.size(); i++) {
                System.out.println(GlobalData.allReccomendations.get(i).userTo + " " + GlobalData.loggedInUser.username);

                if (GlobalData.allReccomendations.get(i).userTo.equals(GlobalData.loggedInUser.username)) {
                    GlobalData.allReccomendationsUser.add(new ReccomendationData(GlobalData.allReccomendations.get(i).userTo,
                            GlobalData.allReccomendations.get(i).bookId,
                            GlobalData.allReccomendations.get(i).bookAuthor,
                            GlobalData.allReccomendations.get(i).bookName,
                            GlobalData.allReccomendations.get(i).bookImage,
                            GlobalData.allReccomendations.get(i).userFrom));
                }
            }

    }

    public  String readJSON(String dataFile) {
        String json = null;
        try {
            // Opening data.json file
            InputStream inputStream = getAssets().open(dataFile);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            // read values in the byte array
            inputStream.read(buffer);
            inputStream.close();
            // convert byte to string
            json = new String(buffer, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
            return json;
        }
        return json;
    }

};

