package sg.edu.np.mad.prac3ver1;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MessageGroup extends AppCompatActivity {
    private String TAG = "MessageGroup";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message_group);

        //define buttons and frag,emts
        Button group1 = findViewById(R.id.group1);
        Button group2 = findViewById(R.id.group2);
        Fragment TextFragment = new TextFragment();
        Fragment ImageFragment = new ImageFragment();

        //when clicked
        group1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view1) {
                //define fragment transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                //set fragment layout to selected fragment
                if (!TextFragment.isAdded()){
                    Log.v(TAG, "Adding TextFragment");
                    ft.add(R.id.fragmentLayout, TextFragment);
                }
                if (ImageFragment.isAdded()){
                    Log.v(TAG, "Hiding ImageFragment");
                    ft.hide(ImageFragment);
                }
                ft.show(TextFragment);
                Log.v(TAG, "Showing TextFragment");
                //initialize
                Log.v(TAG, "Committing TextFragment");
                ft.commit();
            }
        });

        //when clicked
        group2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view2) {
                //define fragment transaction
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (!ImageFragment.isAdded()){
                    Log.v(TAG, "Adding ImageFragment");
                    ft.add(R.id.fragmentLayout, ImageFragment);
                }
                if (TextFragment.isAdded()){
                    Log.v(TAG, "Hiding TextFragment");
                    ft.hide(TextFragment);
                }
                ft.show(ImageFragment);
                Log.v(TAG, "Showing ImageFragment");
                //initialize
                Log.v(TAG, "Committing ImageFragment");
                ft.commit();
            }
        });
    }
}