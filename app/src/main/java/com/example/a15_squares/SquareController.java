package com.example.a15_squares;

import android.view.MotionEvent;
import android.view.View;
import android.util.Log;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.SeekBar;

public class SquareController implements View.OnClickListener{

    private SquareView view;
    private SquareModel model;

    // constructor
    public SquareController(SquareView view, SquareModel model){
        this.view = view;
        this.model = model;
    }

    // Reacts to a click. Different functionality for reset and movable tile.

    public void onClick(View v){
        // cast to Button for ease of use
        Button b = (Button) v;

        // reset functionality
        if(b.getId() == R.id.b_reset)
        {
            view.initialize();
            return;
        }

        // get reference to blank tile
        Button z = model.getBlank();

        // swap text of tiles
        String temp = b.getText().toString();
        b.setText(z.getText().toString());
        z.setText(temp);

        // reallocate attributes of tiles to account for swap
        view.newBlank(b);
    }

}
