package com.example.dz3_android3.ui.home;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dz3_android3.R;
import com.example.dz3_android3.data.model.Post;

import java.util.ArrayList;
import java.util.List;


public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostHolder> {
    private final List<Post> list;
    //private PostListener listener;
    private PostListener listener;

    public void setListener(PostListener listener) {
        this.listener = listener;
//        this.listener = listener;
    }

    public PostAdapter() {
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public PostHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostHolder holder, int position) {
        if (position % 2 == 0){
            holder.itemView.setBackgroundColor(Color.LTGRAY);
        }else {
            holder.itemView.setBackgroundColor(Color.GRAY);
        }
        holder.onBind(list.get(position));

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public void addListPosts(List<Post> body) {
        list.addAll(body);
        notifyDataSetChanged();
    }

    public void deletePost(Post body, int position) {
        list.remove(position);
        notifyItemRemoved(position);
    }

    public class PostHolder extends RecyclerView.ViewHolder {
        private final TextView tvContent;
        private final TextView tvTitle;
        private final TextView tvUser;
        private final TextView tvGroup;

        public PostHolder(@NonNull View itemView) {
            super(itemView);

            tvContent = itemView.findViewById(R.id.tv_content);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvUser = itemView.findViewById(R.id.tv_user);
            tvGroup = itemView.findViewById(R.id.tv_group);

            itemView.setOnClickListener( v-> {
                listener.onClick(list.get(getAdapterPosition()));
                    }
            );

            itemView.setOnLongClickListener(v -> {
               listener.onLongClick(list.get(getAdapterPosition()), getAdapterPosition());
               return true;
            }
            );
        }

        public void onBind(Post post) {
            tvContent.setText(post.getContent());
            tvTitle.setText(post.getTitle());
            tvGroup.setText(String.valueOf(post.getGroup()));
            tvUser.setText(String.valueOf(post.getUser()));
        }
    }
    }

