package com.penguinlabs.instagram;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;


public class HomeFragment extends Fragment {

    RecyclerView storyrecyclerview;
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);

        //view binding
        storyrecyclerview = view.findViewById(R.id.storyrecyclerview);
        arrayList = new ArrayList<>();
        storyrecyclerview.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false));

        RequestQueue requestQueue = Volley.newRequestQueue(getContext());

        String url = "";

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray jsonArray) {

                Log.d("serverresponse", "" + jsonArray);

                for (int i = 0; i < jsonArray.length(); i++) {
                    try {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        hashMap = new HashMap<>();
                        hashMap.put("username", "username");
                        hashMap.put("userprofile", "userprofile");
                        arrayList.add(hashMap);

                    } catch (JSONException e) {
                        throw new RuntimeException(e);

                    }


                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {

                Log.d("responseerror", "" + volleyError);

            }
        });

        requestQueue.add(jsonArrayRequest);

        StoryAdapter storyAdapter = new StoryAdapter();
        storyrecyclerview.setAdapter(storyAdapter);

        // Inflate the layout for this fragment
        return view;

    }


    //adapter
    public class StoryAdapter extends RecyclerView.Adapter<StoryAdapter.Viewholder> {


        @NonNull
        @Override
        public Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = getLayoutInflater();
            View view = layoutInflater.inflate(R.layout.card_story, parent, false);

            return new Viewholder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull Viewholder holder, int position) {

            hashMap = arrayList.get(position);

            String name = hashMap.get("username");
            String image = hashMap.get("userprofile");

            holder.username.setText(name);

            Glide.with(getContext()).load(image).into(holder.profileimage);


        }

        @Override
        public int getItemCount() {
            return arrayList.size();
        }

        //viewholder
        class Viewholder extends RecyclerView.ViewHolder {

            ImageView profileimage;
            TextView username;


            public Viewholder(@NonNull View itemView) {
                super(itemView);

                profileimage = itemView.findViewById(R.id.profileImage);
                username = itemView.findViewById(R.id.userName);


            }
        }


    }


}