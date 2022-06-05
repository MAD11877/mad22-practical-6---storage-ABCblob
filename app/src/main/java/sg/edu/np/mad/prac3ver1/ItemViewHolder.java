package sg.edu.np.mad.prac3ver1;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class ItemViewHolder extends RecyclerView.ViewHolder {
    TextView userName;
    TextView userDesc;
    ImageView userPic;
    public ItemViewHolder(View ItemView){
        super(ItemView);
        userName = itemView.findViewById(R.id.userName);
        userDesc = itemView.findViewById(R.id.userDesc);
        userPic = itemView.findViewById(R.id.userPic);
    }
}
