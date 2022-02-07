package com.example.proba;

import android.app.Application;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;

public class GlobalData extends Application {

    public static BookData[] allBooks;
    public static BookData[] allBooksRow1;
    public static BookData[] allBooksRow2;
    public static BookData[] allBooksPromo;

    public static LinkedList<UserData> allUsers;
    public static UserData loggedInUser = null;

    public static LinkedList<ReccomendationData> allReccomendations;
    public static LinkedList<ReccomendationData> allReccomendationsUser;

    @Override
    public void onCreate() {
        super.onCreate();
        System.out.println("GLOBAL daTA");

        fillBookArray();
        fillUserArray();
        fillReccomendationsArray();
    }

    private  void fillBookArray() {

        try {
            JSONArray array = new JSONArray(readJSON("books.json"));

            //JSONArray array=jsnObj.getJSONArray("books");
            int promo = 0;
            int noPromo = 0;

            for (int i = 0; i < array.length(); i++) {

                if (array.getJSONObject(i).getBoolean("promo"))
                    promo++;
                else
                    noPromo++;

            }

            int noPromo1 = noPromo / 2 + (noPromo % 2);
            int noPromo2 = noPromo / 2;

            GlobalData.allBooksRow1 = new BookData[noPromo1];
            GlobalData.allBooksRow2 = new BookData[noPromo2];
            GlobalData.allBooksPromo = new BookData[promo];
            GlobalData.allBooks = new BookData[array.length()];

            int noPromo1Cnt = 0;
            int noPromo2Cnt = 0;
            int promoCnt = 0;

            for (int i = 0; i < array.length(); i++) {

                int imgRes = getResources().getIdentifier("@drawable/" + array.getJSONObject(i).getString("image"), null, this.getPackageName());

                GlobalData.allBooks[i] = new BookData(array.getJSONObject(i).getString("name"), array.getJSONObject(i).getString("autor"), imgRes, array.getJSONObject(i).getString("description"), array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getInt("numOfPages"), array.getJSONObject(i).getInt("year"));

                LinkedList<CommentsData> coms = new LinkedList<>();
                for (int j = 0; j < array.getJSONObject(i).getJSONArray("comments").length(); j++) {
                    coms.add(new CommentsData(array.getJSONObject(i).getJSONArray("comments").getJSONObject(j).getString("user"), array.getJSONObject(i).getJSONArray("comments").getJSONObject(j).getInt("stars"), array.getJSONObject(i).getJSONArray("comments").getJSONObject(j).getString("comment")));
                }

                GlobalData.allBooks[i].comments = coms;

                if (array.getJSONObject(i).getBoolean("promo")) {
                    GlobalData. allBooksPromo[promoCnt] = new BookData(array.getJSONObject(i).getString("name"), array.getJSONObject(i).getString("autor"), imgRes, array.getJSONObject(i).getString("description"), array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getInt("numOfPages"), array.getJSONObject(i).getInt("year"));
                    GlobalData. allBooksPromo[promoCnt++].comments = coms;
                } else if (noPromo1Cnt < noPromo1) {
                    GlobalData. allBooksRow1[noPromo1Cnt] = new BookData(array.getJSONObject(i).getString("name"), array.getJSONObject(i).getString("autor"), imgRes, array.getJSONObject(i).getString("description"), array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getInt("numOfPages"), array.getJSONObject(i).getInt("year"));
                    GlobalData.allBooksRow1[noPromo1Cnt++].comments = coms;
                } else {
                    GlobalData.allBooksRow2[noPromo2Cnt] = new BookData(array.getJSONObject(i).getString("name"), array.getJSONObject(i).getString("autor"), imgRes, array.getJSONObject(i).getString("description"), array.getJSONObject(i).getInt("id"), array.getJSONObject(i).getInt("numOfPages"), array.getJSONObject(i).getInt("year"));
                    GlobalData.allBooksRow2[noPromo2Cnt++].comments = coms;
                }
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private void fillUserArray() {

        try {
            JSONArray array = new JSONArray(readJSON("users.json"));

            GlobalData.allUsers = new LinkedList<>();

            for (int i = 0; i < array.length(); i++) {
                GlobalData.allUsers.add(new UserData(array.getJSONObject(i).getString("username"),
                        array.getJSONObject(i).getString("pass"),
                        array.getJSONObject(i).getString("name"),
                        array.getJSONObject(i).getString("surname"),
                        array.getJSONObject(i).getString("adress"),
                        array.getJSONObject(i).getString("tel")));
            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    private  void fillReccomendationsArray() {
        try {
            JSONArray array = new JSONArray(readJSON("rec.json"));

            GlobalData.allReccomendations = new LinkedList<>();

            for (int i = 0; i < array.length(); i++) {

                    int resource = getResources().getIdentifier("@drawable/" + array.getJSONObject(i).getString("bookImage"), null, this.getPackageName());
                    GlobalData.allReccomendations.add(new ReccomendationData(array.getJSONObject(i).getString("userTo"),
                            array.getJSONObject(i).getString("bookId"),
                            array.getJSONObject(i).getString("bookAuthor"),
                            array.getJSONObject(i).getString("bookName"),
                            resource,
                            array.getJSONObject(i).getString("userFrom")));

            }

        } catch (JSONException e) {
            e.printStackTrace();
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


}
