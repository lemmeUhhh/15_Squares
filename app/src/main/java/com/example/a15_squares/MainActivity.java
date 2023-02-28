package com.example.a15_squares;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        setContentView(R.layout.activity_main);

        // instantiate the view, model, and controller objects
        SquareModel model = new SquareModel();
        SquareView view = new SquareView(model);
        SquareController controller = new SquareController(view, model);

        //creates the Buttons
        int[] buttonIDs = new int[] {R.id.b_11, R.id.b_12, R.id.b_13, R.id.b_14, R.id.b_21,
                R.id.b_22, R.id.b_23, R.id.b_24, R.id.b_31, R.id.b_32, R.id.b_33, R.id.b_34,
                R.id.b_41, R.id.b_42, R.id.b_43, R.id.b_44};

        // set listener for Buttons and add to View array
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                findViewById(buttonIDs[4*i+j]).setOnClickListener(controller);
                view.addButton(i, j, findViewById(buttonIDs[4*i+j]));
            }
        }

        // add reset Button
        findViewById(R.id.b_reset).setOnClickListener(controller);

        // add win text
        view.addWinText(findViewById(R.id.win));

        // start the game
        view.initialize();

    }
}