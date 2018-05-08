package com.example.venson.soho.Member;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.venson.soho.Home.CategoryTab.GetAllFragment;
import com.example.venson.soho.R;

import java.util.List;


public class MemberAlbumPicture extends Fragment {
    private RecyclerView rvPicture;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.member_album_picture, container, false);
        rvPicture = view.findViewById(R.id.rvPicture);
        ImageView ivPicture = view.findViewById(R.id.ivPicture);
        Bitmap[] picture = new Bitmap[10];
        picture[0] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);
        picture[1] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);
        picture[2] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);
        picture[3] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);
        picture[4] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);
        picture[5] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);
        picture[6] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);
        picture[7] = BitmapFactory.decodeResource(getResources(), R.drawable.dog2);

        rvPicture.setLayoutManager(new GridLayoutManager(getActivity(), 3));
        rvPicture.setAdapter(new PictureAdapter(getActivity(),picture));
        return view;
    }

    private class PictureAdapter extends RecyclerView.Adapter<PictureAdapter.MyViewHolder>{
        private LayoutInflater layoutInflater;
        private Bitmap[] pictureist;

        PictureAdapter(Context context, Bitmap[] pictureist) {
             layoutInflater = LayoutInflater.from(context);
             this.pictureist = pictureist;
         }

         class MyViewHolder extends RecyclerView.ViewHolder {
             private ImageView ivPicture;
            public MyViewHolder(View itemView) {
                super(itemView);
                ivPicture = itemView.findViewById(R.id.ivPicture);
            }
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View itemView = layoutInflater.from(parent.getContext()).inflate(R.layout.member_album_picture_cardview, parent, false);
            return new MyViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            holder.ivPicture.setImageBitmap(pictureist[position]);

        }



        @Override
        public int getItemCount() {
            return pictureist.length;
        }


    }
}
