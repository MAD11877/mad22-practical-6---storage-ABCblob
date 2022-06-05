package sg.edu.np.mad.prac3ver1;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ItemsAdapter extends RecyclerView.Adapter<ItemViewHolder> {
    ArrayList<User> data;

    public ItemsAdapter(ArrayList<User> input){
        data = input;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = null;
        Log.d("VIEWTYPE",String.valueOf(viewType));
        if(viewType == 7) {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview7, parent, false);
        }
        else {
            item = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerviewnormal, parent, false);
        }
        return new ItemViewHolder(item);
    }

    @Override
    public int getItemViewType(int position) {
        return Integer.parseInt(data.get(position).getName().substring(data.get(position).getName().length()-1));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        User user = data.get(position);
        holder.userName.setText(user.getName());
        holder.userDesc.setText(user.getDescription());

        holder.userPic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Log.v("BUTToN KLICKED", "Image clicked");

                AlertDialog.Builder builder = new AlertDialog.Builder(holder.userPic.getContext());
                builder.setMessage(user.getName()).setCancelable(true);

                builder.setPositiveButton("View", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Bundle extras = new Bundle();
                        extras.putInt("id",position);
                        Intent viewProfile = new Intent(holder.userPic.getContext(), MainActivity.class);
                        viewProfile.putExtras(extras);
                        holder.userPic.getContext().startActivity(viewProfile);
                    }
                });

                builder.setNegativeButton("Close", null);
                AlertDialog alert = builder.create();
                alert.setTitle("Profile");
                alert.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
}
