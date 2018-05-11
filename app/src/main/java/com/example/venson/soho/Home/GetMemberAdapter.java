package com.example.venson.soho.Home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.venson.soho.R;
import com.example.venson.soho.User;

import java.util.ArrayList;
import java.util.List;


public class GetMemberAdapter extends RecyclerView.Adapter<GetMemberAdapter.MyViewHolder> {

        private LayoutInflater layoutInflater;
        private List<User> users = new ArrayList<>();
        public GetMemberAdapter(List<User> users, Context context) {
            this.users = users;
            layoutInflater = LayoutInflater.from(context);
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView member_tvUser,member_tvDescripition,member_tvSkill;
            public MyViewHolder(View itemView) {
                super(itemView);
                member_tvUser = itemView.findViewById(R.id.home_member_tvUser);
                member_tvDescripition = itemView.findViewById(R.id.home_member_tvDescripition);
                member_tvSkill = itemView.findViewById(R.id.home_member_tvSkill);
            }

        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.member_item_cardview, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
            User user = users.get(position);
            myViewHolder.member_tvUser.setText(user.getUserName());
            myViewHolder.member_tvDescripition.setText(user.getUserSelfDes());
        }

        @Override
        public int getItemCount() {
            return users.size();
        }

}
