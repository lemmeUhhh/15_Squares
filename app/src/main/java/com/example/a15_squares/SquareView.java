package com.example.a15_squares;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class SquareView {


    private Button[][] buttons;

    private TextView winText;
    private SquareModel model;
    Random gen = new Random();

    /*
     * ctor for SquareView
     * takes reference to model, creates Button array
     */
    public SquareView(SquareModel m)
    {
        model = m;
        buttons = new Button[4][4];
    }

    /*
     * Adds the referenced Button into the array at the specified indices.
     */
    public void addButton(int row, int col, Button b)
    {
        buttons[row][col] = b;
    }

    /*
     * Adds the referenced win text to a local variable.
     */
    public void addWinText(TextView t)
    {
        winText = t;
    }

    /*
     * Initializes/resets the game.
     */
    public void initialize()
    {
        // make win text invisible
        winText.setVisibility(View.INVISIBLE);

        // set all buttons to visible and non-clickable
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                buttons[i][j].setVisibility(View.VISIBLE);
                buttons[i][j].setClickable(false);

            }
        }
        // generate array of indices
        int[] indices = new int[16];
        for(int i = 0; i < 16; i++)
        {
            indices[i] = i;
        }

        // randomize indices
        int temp = 0;
        int rand = 0;
        for(int i = 0; i < 16; i++)
        {
            rand = gen.nextInt(16);
            temp = indices[i];
            indices[i] = indices[rand];
            indices[rand] = temp;
        }

        // assign indices to Buttons, sets invisible and movable tiles
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                buttons[i][j].setText(Integer.toString(indices[4*i+j]));
                if(indices[4*i+j] == 0)
                {
                    model.setBlank(buttons[i][j]);
                    buttons[i][j].setVisibility(View.INVISIBLE);
                    if(i>0) buttons[i-1][j].setClickable(true);
                    if(i<3) buttons[i+1][j].setClickable(true);
                    if(j>0) buttons[i][j-1].setClickable(true);
                    if(j<3) buttons[i][j+1].setClickable(true);
                }
            }
        }


    }

    /*
     * Swaps properties of moved and blank tiles.
     */
    public void newBlank(Button b)
    {
        // get reference to previous blank tile
        Button z = model.getBlank();

        // swap visibility
        b.setVisibility(View.INVISIBLE);
        z.setVisibility(View.VISIBLE);

        // reset clickability for all buttons
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                buttons[i][j].setClickable(false);
            }
        }

        // set clickability for movable buttons
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                if(buttons[i][j].getId() == b.getId())
                {
                    model.setBlank(buttons[i][j]);
                    buttons[i][j].setVisibility(View.INVISIBLE);
                    if(i>0) buttons[i-1][j].setClickable(true);
                    if(i<3) buttons[i+1][j].setClickable(true);
                    if(j>0) buttons[i][j-1].setClickable(true);
                    if(j<3) buttons[i][j+1].setClickable(true);
                }
            }
        }

        // change reference to blank tile
        model.setBlank(b);

        // check for win state
        if(this.checkForWin())
        {
            Log.d("you", "did it");
            // show win text
            winText.setVisibility(View.VISIBLE);
        }
    }

    /*
     * Checks for win state.
     */
    public boolean checkForWin()
    {
        for(int i = 0; i < 4; i++)
        {
            for(int j = 0; j < 4; j++)
            {
                // check all tile numbers against correct value
                int index = Integer.valueOf(buttons[i][j].getText().toString());
                if( (index != (4*i + j + 1)) && (index != 0) ) return false;
            }
        }
        return true;
    }

}//class SquareView