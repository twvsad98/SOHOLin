package com.example.venson.soho.Home.Case_Member_Tab;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.venson.soho.MyTask;
import com.example.venson.soho.R;

/**
 * Created by venson on 2018/4/28.
 */

public class HomeMemberFragment extends Fragment {
    private static final String TAG = "getAllMemeberFragment";
    private MyTask memberGetAllTask;
    private RecyclerView rvMemeber;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.home_member_fragment, container, false);
        findView(view);
        rvMemeber.setLayoutManager(new LinearLayoutManager(getActivity()));
        return view;
    }


    private void findView(View view) {
        rvMemeber = view.findViewById(R.id.rvMember);
    }

//    private class GetMemberAdapter extends RecyclerView.Adapter<GetMemberAdapter.MyViewHolder> {
//        private LayoutInflater layoutInflater;
//        private List<MemberClass> users;
//        public GetMemberAdapter(List<MemberClass> users, Context context) {
//            this.users = users;
//            layoutInflater = LayoutInflater.from(context);
//        }
//
//        class MyViewHolder extends RecyclerView.ViewHolder {
//            TextView member_tvUser,member_tvDescripition,member_tvSkill;
//            public MyViewHolder(View itemView) {
//                super(itemView);
//                member_tvUser = itemView.findViewById(R.id.home_member_tvUser);
//                member_tvDescripition = itemView.findViewById(R.id.home_member_tvDescripition);
//                member_tvSkill = itemView.findViewById(R.id.home_member_tvSkill);
//            }
//
//        }
//
//        @Override
//        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
//            View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.member_item_cardview, parent, false);
//            return new MyViewHolder(itemView);
//        }
//
//        @Override
//        public void onBindViewHolder(MyViewHolder myViewHolder, int position) {
//            MemberClass user = users.get(position);
//            myViewHolder.member_tvUser.setText(user.getName());
//            myViewHolder.member_tvDescripition.setText(user.getDescription());
//            myViewHolder.member_tvSkill.setText(user.getSkill());
//        }
//
//
//        @Override
//        public int getItemCount() {
//            return users.size();
//        }
//
//
//    }


} // end
