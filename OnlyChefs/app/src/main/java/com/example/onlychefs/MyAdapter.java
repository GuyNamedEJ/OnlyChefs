package com.example.onlychefs;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

import static androidx.core.content.ContextCompat.startActivity;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    public Context context;
    private List<String> titles;
    private List<Integer> images;

    public MyAdapter(Context context, List<String> titles, List<Integer> images) {
        this.context = context;
        this.titles = titles;
        this.images = images;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.grid_item, parent, false);
        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.mTextView.setText(titles.get(position));
        holder.mImageView.setImageResource(images.get(position));
    }

    @Override
    public int getItemCount() {
        return titles.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mTextView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            mImageView = itemView.findViewById(R.id.imageView);
            mTextView = itemView.findViewById(R.id.textView);


            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String searchText = mTextView.getText().toString().toLowerCase();
                    Intent intent = new Intent(v.getContext(), CategorySearch.class);
                    intent.putExtra("CATEGORY",searchText);
                    v.getContext().startActivity(intent);
                }
            });
        }
    }


}
