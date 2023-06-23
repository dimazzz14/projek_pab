package com.example.projekpab;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.projekpab.databinding.ItemUnggahBinding;

import java.util.ArrayList;
import java.util.List;

public class UnggahViewAdapter extends RecyclerView.Adapter<UnggahViewAdapter.ViewHolder> {
    private List<Unggah> data = new ArrayList<>();
    private OnItemLongClickListener onItemLongClickListener;

    public void setData(List<Unggah> data) {
        this.data = data;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new ViewHolder(ItemUnggahBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false));
    }

    public void onBindViewHolder(@NonNull UnggahViewAdapter.ViewHolder holder, int position) {
        int pos = holder.getAdapterPosition();
        Unggah unggah = data.get(pos);
        holder.itemUnggahBinding.tvUsername.setText(unggah.getUsername());
        holder.itemUnggahBinding.tvNama.setText(unggah.getNama());
        holder.itemUnggahBinding.tvAlamat.setText(unggah.getAlamat());
        holder.itemUnggahBinding.tvTelepon.setText(unggah.getTelepon());
        holder.itemUnggahBinding.tvMotor.setText(unggah.getMotor());
        holder.itemUnggahBinding.tvJenis.setText(unggah.getJenis());
        holder.itemUnggahBinding.tvServis.setText(unggah.getServis());
        holder.itemUnggahBinding.tvCreatedDate.setText(unggah.getCreated_date());
        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onItemLongClickListener.onItemLongClick(v, unggah, pos);
                return false;
            }
        });
    }


    @Override
    public int getItemCount() {return data.size();
    }

    public void setOnItemLongClickListener(OnItemLongClickListener onItemLongClickListener) {
        this.onItemLongClickListener = onItemLongClickListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ItemUnggahBinding itemUnggahBinding;
        public ViewHolder(@NonNull ItemUnggahBinding itemView) {
            super(itemView.getRoot());
            itemUnggahBinding = itemView;
        }
    }

    public interface OnItemLongClickListener {
        void onItemLongClick(View v, Unggah unggah, int position);
    }
}
