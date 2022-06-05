package sg.edu.np.mad.prac3ver1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String TAG = "Main Activity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        //create dumb-y user
//        String desc = "This is a relatively long piece of text that has no spelling mistakes so as to " +
//                "anger the android studio gods and tell me the whole program doesn't run";
//        User testUser = new User("userTest",desc,1,false);

        Intent receiving = getIntent();
        int index = receiving.getIntExtra("id", -1);
        User testUser = ListActivity.userList.get(index);

        //define buttons and text
        TextView nameText = findViewById(R.id.nameText);
        TextView descText = findViewById(R.id.descText);
        Button followButton = findViewById(R.id.followButton);
        Button messageButton = findViewById(R.id.messageButton);

//        //add random int from MainActivity2 to name
//        Intent receivingEnd = getIntent();
//        int randomInt = receivingEnd.getIntExtra("RandomInt",0);
//        testUser.setName(String.format("%s %d",testUser.getName(), randomInt));

        //set name and desc to User class
        nameText.setText(testUser.getName());
        descText.setText(testUser.getDescription());

        //when follow button clicked
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Follow Button pressed");

                //define toast  Msg
                String toastMsg;

                //flip follow state and toast message
                if (testUser.isFollowed() == false) {
                    testUser.setFollowed(true);
                    followButton.setText("unfollow");
                    toastMsg = "Followed";

                }
                else {
                    testUser.setFollowed(false);
                    followButton.setText("follow");
                    toastMsg = "Unfollowed";
                }

                //show toast
                Toast toast = Toast.makeText(getApplicationContext(), toastMsg, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

        //when message button clicked, go to MessageGroup activity
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.v(TAG, "Message Button pressed");
                Intent gotoMessageGroup = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(gotoMessageGroup);
            }
        });
    }
}

