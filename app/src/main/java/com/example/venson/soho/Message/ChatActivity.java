package com.example.venson.soho.Message;


import android.Manifest;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.venson.soho.Common;
import com.example.venson.soho.R;
import com.google.gson.Gson;

public class ChatActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "ChatActivity";
    private static final int REQ_TAKE_PICTURE = 0;
    private static final int REQ_PICK_IMAGE = 1;
    private static final int REQ_CROP_PICTURE = 2;
    private static final int REQ_PERMISSIONS_STORAGE = 101;
    private LocalBroadcastManager broadcastManager;
    private ScrollView scrollView;
    private ImageView ivCamera, ivPicture, ivSend;
    private LinearLayout linearLayout;
    private EditText etMessage;
    private String friend_em;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom_layout);
        findviews();

        broadcastManager = LocalBroadcastManager.getInstance(this);
        registerChatReceiver();

        friend_em = getIntent().getStringExtra("friend_em");
        String messageType = getIntent().getStringExtra("messageType");
        if (messageType != null) {
            String messageContent = getIntent().getStringExtra("messageContent");
            switch (messageType) {
                case "text":
                    showMessage(friend_em, messageContent, true);
                    break;
                case "image":
                    byte[] image = Base64.decode(messageContent, Base64.DEFAULT);
                    Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                    showImage(friend_em, bitmap, true);
                    break;
                default:
                    break;
            }
        }
    }


    ////////設定聊天對象
    @Override
    protected void onStart() {
        super.onStart();
        requestPermissions_Storage();
        ChatWebSocketClient.friendInChatEM = friend_em;
    }

    private void requestPermissions_Storage() {
        String[] permissions = {
                Manifest.permission.WRITE_EXTERNAL_STORAGE
        };

        int result = ContextCompat.checkSelfPermission(this, permissions[0]);
        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this,
                    permissions,
                    REQ_PERMISSIONS_STORAGE);
        }

    }

    private void findviews() {
        etMessage = findViewById(R.id.etMessage);
        linearLayout = findViewById(R.id.layout);
        scrollView = findViewById(R.id.chatScrollView);
        ivCamera = findViewById(R.id.ivCamera);
        ivPicture = findViewById(R.id.ivPicture);
        ivSend = findViewById(R.id.ivSend);
        ivCamera.setOnClickListener(this);
        ivPicture.setOnClickListener(this);
        ivSend.setOnClickListener(this);
    }

    ///////按鈕設定
    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.ivSend:
                String message = etMessage.getText().toString();
                if (message.trim().isEmpty()) {
                    Common.showToast(this, "message is empty");
                    return;
                }
                String sender_em = getUserEmail();
                showMessage(sender_em, message, false);
                etMessage.setText(null);

                ChatMessage chatMessage = new ChatMessage("chat", sender_em, friend_em, message, "text");
                String chatMessageJson = new Gson().toJson(chatMessage);
                Common.chatWebSocketClient.send(chatMessageJson);
                Log.d(TAG, "output: " + chatMessageJson);
                break;


            case R.id.ivCamera:
                break;

            case R.id.ivPicture:
                break;

            default:
                break;


        }

    }

    ///////訊息分左右邊
    private void showMessage(String sender_em, String message, boolean left) {

        View view;

        if (left) {
            view = View.inflate(this, R.layout.chat_message_left, null);
        } else {
            view = View.inflate(this, R.layout.chat_message_right, null);
        }
        TextView tvMessage = view.findViewById(R.id.cdChatMessage);
        tvMessage.setText(message);
        linearLayout.addView(view);////?
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private void showImage(String sender_em, Bitmap bitmap, boolean left) {

        View view;
        if (left) {
            view = View.inflate(this, R.layout.chat_image_left, null);
        } else {
            view = View.inflate(this, R.layout.chat_image_right, null);
        }

        ImageView imageView = view.findViewById(R.id.imageView);
        imageView.setImageBitmap(bitmap);
        linearLayout.addView(view);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }

    private void registerChatReceiver() {
        IntentFilter chatFilter = new IntentFilter("caht");
        ChatReceiver chatReceiver = new ChatReceiver();
        broadcastManager.registerReceiver(chatReceiver, chatFilter);

    }

    public String getUserEmail() {
        SharedPreferences preferences =
                getSharedPreferences(Common.PREF_FILE, MODE_PRIVATE);
        String userEmail = preferences.getString("userEmail", "");
        Log.d(TAG, "userEmail = " + userEmail);
        return userEmail;
    }


    /////接收到訊息
    private class ChatReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            ChatMessage chatMessage = new Gson().fromJson(message, ChatMessage.class);

            String sender_em = chatMessage.getSender_em();
            String messageType = chatMessage.getMessageType();

            if (sender_em.equals(friend_em)) {
                switch (messageType) {

                    case "text":
                        showMessage(sender_em, chatMessage.getContent(), true);
                        break;
                    case "image":
                        byte[] image = Base64.decode(chatMessage.getContent(), Base64.DEFAULT);
                        Bitmap bitmap = BitmapFactory.decodeByteArray(image, 0, image.length);
                        showImage(sender_em, bitmap, true);
                        break;
                    default:
                        break;
                }
            }
            Log.d(TAG, "receive message:" + message);

        }
    }


}
