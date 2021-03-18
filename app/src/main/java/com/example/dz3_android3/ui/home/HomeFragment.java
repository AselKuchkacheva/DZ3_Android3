package com.example.dz3_android3.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dz3_android3.R;
import com.example.dz3_android3.data.RetrofitBuilder;
import com.example.dz3_android3.data.model.Post;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeFragment extends Fragment {

    private RecyclerView recyclerView;
    private PostAdapter adapter;
    private NavController navController;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new PostAdapter();
        getPosts();

    }

    private void getPosts() {

        RetrofitBuilder.getInstance().getPosts().enqueue(new Callback<List<Post>>() {
            @Override
            public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                adapter.addListPosts(response.body());
            }

            @Override
            public void onFailure(Call<List<Post>> call, Throwable t) {

            }
        });
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView(view);
        setListener();
    }

    private void initView(@NonNull View view) {
        recyclerView = view.findViewById(R.id.rv_posts);
        recyclerView.addItemDecoration(
                new DividerItemDecoration(getContext(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);

        navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment);

        view.findViewById(R.id.f_btn_add).setOnClickListener(v -> {
           navController.navigate(HomeFragmentDirections.actionHomeFragmentToInsertFragment());
        });
    }

    private void setListener() {
        adapter.setListener(new PostListener() {
            @Override
            public void onLongClick(Post post, int position) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(getContext());
                alertDialog.setMessage("Удалить этот пост?");
                alertDialog.setPositiveButton("Да", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        RetrofitBuilder.getInstance().deletePost(post.getId().toString()).enqueue(new Callback<Post>() {
                            @Override
                            public void onResponse(Call<Post> call, Response<Post> response) {
                                adapter.deletePost(response.body(), position);
                            }

                            @Override
                            public void onFailure(Call<Post> call, Throwable t) {

                            }
                        });
                    }
                });
                alertDialog.setNegativeButton("Нет", null);

                alertDialog.create().show();
            }

            @Override
            public void onClick(Post post) {
            navController.navigate(HomeFragmentDirections.actionHomeFragmentToInsertFragment().setPost(post));
            }
        });
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}