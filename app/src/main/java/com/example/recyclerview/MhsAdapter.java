package com.example.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MhsAdapter extends RecyclerView.Adapter<MhsAdapter.MhsVH> {

    private ArrayList<MhsModel> mhsList ;
    private final  OnItemClickListener listener;

    public MhsAdapter(ArrayList<MhsModel> mhsList, OnItemClickListener listener) {
        this.mhsList = mhsList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public MhsVH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.item_listmhs, parent, false);

        return new MhsVH(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MhsVH holder, int position) {
        holder.tvNameVal.setText(mhsList.get(position).getName());
        holder.tvNimVal.setText(mhsList.get(position).getNim());
        holder.tvPhoneVal.setText(mhsList.get(position).getPhone());

        holder.bind(mhsList, position, listener);
    }

    public interface OnItemClickListener{
        void OnItemClick(ArrayList<MhsModel>mhsList, int position);
    }

    public void removeItem(int position){
       this.mhsList.remove(position);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mhsList.size();
    }

    public class MhsVH extends RecyclerView.ViewHolder{

        private TextView tvNameVal, tvNimVal, tvPhoneVal;
        private CardView cvItem;

        public MhsVH(@NonNull View itemView) {
            super(itemView);

            tvNameVal = itemView.findViewById(R.id.tvNameVal);
            tvNimVal = itemView.findViewById(R.id.tvNimVal);
            tvPhoneVal = itemView.findViewById(R.id.tvPhoneVal);

            cvItem = itemView.findViewById(R.id.cvItem);

        }

        public void bind(ArrayList<MhsModel> mhslist, int position,  OnItemClickListener listener){
            cvItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.OnItemClick(mhslist, position);
                    notifyDataSetChanged();
                }
            });
        }

    }
}
