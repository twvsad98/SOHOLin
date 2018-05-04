package com.example.venson.soho;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.Toast;

import com.example.venson.soho.Message.ChatWebSocketClient;

import java.util.ArrayList;
import java.util.List;

public class Common {
//    public static String URL = "http://192.168.196.189:8080/Spot_MySQL_Web/";
//    public static String URL = "http://10.0.2.2:8080/SOHO";

    // check if the device connect to the network
    public static String URL = "http://10.0.2.2:8080/Test_Project_User/";

    public final static String PREF_FILE = "preference";
    public static boolean networkConnected(Activity activity) {
        ConnectivityManager conManager =
                (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = conManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnected();
    }

    public static void showToast(Context context, int messageResId) {
        Toast.makeText(context, messageResId, Toast.LENGTH_SHORT).show();
    }

    public static void showToast(Context context, String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private static List<String> friendlist = new ArrayList<>();

    public static List<String> getFriendList() {
        return friendlist;
    }

    public static void setFriendList(List<String> friendList) {
        Common.friendlist = friendList;
    }

    public static ChatWebSocketClient chatWebSocketClient;
}
