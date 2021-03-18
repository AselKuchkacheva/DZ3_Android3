package com.example.dz3_android3.ui.insert;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.dz3_android3.R;
import com.example.dz3_android3.data.RetrofitBuilder;
import com.example.dz3_android3.data.model.Post;
import com.example.dz3_android3.ui.insert.InsertFragmentArgs;
import com.example.dz3_android3.ui.insert.InsertFragmentDirections;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InsertFragment extends Fragment {

    private EditText etContent;
    private EditText etTitle;
    private EditText etUser;
    private EditText etGroup;
    private Post post;
    private NavController navController;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button btnSave = view.findViewById(R.id.btn_save);
        etContent = view.findViewById(R.id.et_content);
        etTitle = view.findViewById(R.id.et_title);
        etUser = view.findViewById(R.id.et_user);
        etGroup = view.findViewById(R.id.et_group);
        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        if (getArguments() != null)
            post = InsertFragmentArgs.fromBundle(getArguments()).getPost();

        if (post != null) {
            etContent.setText(post.getContent());
            etTitle.setText(post.getTitle());
            etGroup.setText(String.valueOf(post.getGroup()));
            etUser.setText(String.valueOf(post.getUser()));
        }

        btnSave.setOnClickListener(v -> {
            save();
        });
    }

    private void save() {

        String content = etContent.getText().toString();
        String title = etTitle.getText().toString();
        int user = Integer.parseInt(etUser.getText().toString());
        int group = Integer.parseInt(etGroup.getText().toString());

        if (post == null) {
            post = new Post(content, group, title, user);

            newPostRetrofit();

        } else {
            post.setContent(content);
            post.setTitle(title);
            post.setGroup(group);
            post.setUser(user);
            editPostRetrofit();
        }
        navController.navigate(InsertFragmentDirections.actionInsertFragmentToHomeFragment());
    }

    private void editPostRetrofit() {
        RetrofitBuilder.getInstance().updatePost(post.getId().toString(), post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    private void newPostRetrofit() {
        RetrofitBuilder.getInstance().insertPost(post).enqueue(new Callback<Post>() {
            @Override
            public void onResponse(Call<Post> call, Response<Post> response) {

            }

            @Override
            public void onFailure(Call<Post> call, Throwable t) {

            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_insert, container, false);
    }
}