package com.example.dz3_android3.ui.home;

import com.example.dz3_android3.data.model.Post;

public interface PostListener {
    void onLongClick(Post post, int position);
    void onClick(Post post);
}
