package com.example.ivymoda;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.ivymoda.database.Category;
import java.util.List;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {

    private List<Category> mList;
    private IClickItemListener mListener;

    public interface IClickItemListener {
        void onUpdateClick(Category category);
        void onDeleteClick(Category category);
    }

    public CategoryAdapter(List<Category> list, IClickItemListener listener) {
        this.mList = list;
        this.mListener = listener;
    }

    public void setData(List<Category> list){
        this.mList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryViewHolder holder, int position) {
        Category category = mList.get(position);
        if (category == null) return;

        holder.tvName.setText(category.name);
        holder.tvId.setText("ID: " + category.id);
        holder.imgEdit.setOnClickListener(v -> mListener.onUpdateClick(category));
        holder.imgDelete.setOnClickListener(v -> mListener.onDeleteClick(category));
    }

    @Override
    public int getItemCount() {
        return mList != null ? mList.size() : 0;
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvId;
        ImageView imgEdit, imgDelete;

        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvCategoryName);
            tvId = itemView.findViewById(R.id.tvCategoryId);
            imgEdit = itemView.findViewById(R.id.btnEdit);
            imgDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}