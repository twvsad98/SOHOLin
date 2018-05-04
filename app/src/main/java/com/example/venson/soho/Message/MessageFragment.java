package com.example.venson.soho.Message;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.venson.soho.Common;
import com.example.venson.soho.MainActivity;
import com.example.venson.soho.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ricky on 2018/4/21.
 */

public class MessageFragment extends Fragment {
    private static String TAG = "MessageFragment";
    private RecyclerView rvFriends;
    private LocalBroadcastManager localBroadcastManager;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        MainActivity activity = (MainActivity) getActivity();

        View view = inflater.inflate(R.layout.message_fragment, container, false);

        localBroadcastManager = LocalBroadcastManager.getInstance(activity);
        registerFriendStateReceiver();
        rvFriends = view.findViewById(R.id.recyclerView);
        rvFriends.setLayoutManager(new LinearLayoutManager(activity));
        rvFriends.setAdapter(new FriendAdapter(activity));


        return view;
    }

    private void registerFriendStateReceiver() {
        MainActivity activity = (MainActivity) getActivity();
        IntentFilter openFilter = new IntentFilter("open");
        IntentFilter closeFilter = new IntentFilter("close");
        FriendStateReceiver friendStateReceiver = new FriendStateReceiver(activity);
        localBroadcastManager.registerReceiver(friendStateReceiver, openFilter);
        localBroadcastManager.registerReceiver(friendStateReceiver, closeFilter);
    }


    private class FriendAdapter extends RecyclerView.Adapter<FriendAdapter.FriendViewHolder> {
        MainActivity activity;

        FriendAdapter(MainActivity activity) {
            this.activity = activity;
        }

        class FriendViewHolder extends RecyclerView.ViewHolder {
            TextView tvUserName;

            FriendViewHolder(View itemView) {
                super(itemView);
                tvUserName = itemView.findViewById(R.id.tvChatUserListName);

            }
        }

        @Override
        public int getItemCount() {
            return Common.getFriendList().size();
        }

        @Override
        public FriendViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater.from(activity);
            View itemView = layoutInflater.inflate(R.layout.chat_userlist_item, parent, false);
            return new FriendViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(FriendViewHolder holder, int position) {
            final String friend_em = Common.getFriendList().get(position);

            holder.tvUserName.setText(friend_em);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(activity, ChatActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("friend_em", friend_em);
                    intent.putExtras(bundle);
                    activity.startActivity(intent);
                }
            });

        }


    }


    private class FriendStateReceiver extends BroadcastReceiver {
        MainActivity activity;

        public FriendStateReceiver(MainActivity activity) {
            this.activity = activity;
        }

        @Override
        public void onReceive(Context context, Intent intent) {
            String message = intent.getStringExtra("message");
            StateMessage stateMessage = new Gson().fromJson(message, StateMessage.class);
            String type = stateMessage.getType();
            String friend_em = stateMessage.getUserEmail();
            String friend = friend_em;
            int user_id = 1;
            switch (type) {
                case "open":
                    if (friend_em.equals(user_id)) {
                        // 取得server上的所有user
                        List<String> friendList = new ArrayList<>(stateMessage.getUserEmails());
                        Common.setFriendList(friendList);
                        // 將自己從聊天清單中移除，否則會看到自己在聊天清單上
                        friendList.remove(user_id);
                    } else {
                        // 如果其他user連線而且清單上沒有該user，就將該user新增至目前聊天清單上
                        if (!Common.getFriendList().contains(friend_em)) {
                            Common.getFriendList().add(friend_em);
                        }
                        Common.showToast(activity, friend + " is online");
                    }
                    // 重刷聊天清單
                    rvFriends.getAdapter().notifyDataSetChanged();
                    break;


                case "close":
                    rvFriends.getAdapter().notifyDataSetChanged();
                    Common.showToast(activity, friend + " is offline");
                    break;
            }
            Log.d(TAG, "message: " + message);
            Log.d(TAG, "friendList: " + Common.getFriendList());
        }
    }
}