package com.example.myrestraunts.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myrestraunts.R;
import com.example.myrestraunts.models.Restraunt;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class RestrauntListAdapter extends RecyclerView.Adapter<RestrauntListAdapter.RestrauntViewHolder> {

    private ArrayList<Restraunt> mRestraunts = new ArrayList<>();
    private Context mContext;


    public RestrauntListAdapter(Context context, ArrayList<Restraunt> restraunts ){
        mContext = context;
        mRestraunts = restraunts;
    }

    @Override
    public void onBindViewHolder(RestrauntListAdapter.RestrauntViewHolder holder, int position) {
        holder.bindRestraunt(mRestraunts.get(position));
    }

    @Override
    public RestrauntListAdapter.RestrauntViewHolder onCreateViewHolder( ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.restraunt_list_item,parent,false);
        RestrauntViewHolder viewHolder = new RestrauntViewHolder(view);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return mRestraunts.size();
    }

    public class RestrauntViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.restrauntImageView) ImageView mRestrauntImageView;
        @BindView(R.id.restrauntNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;
        @BindView(R.id.ratingTextView) TextView mRatingTextView;

        private Context mContext;


        public RestrauntViewHolder(View itemView){
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
         }

         public void bindRestraunt(Restraunt restraunt){
            mNameTextView.setText(restraunt.getName());
            mCategoryTextView.setText(restraunt.getCategories().get(0));
            mRatingTextView.setText("Rating: "+ restraunt.getRating()+"/5");
            Picasso.get().load(restraunt.getImageUrl()).into(mRestrauntImageView);
         }

    }

}


