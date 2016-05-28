package com.kaala.scarnersdice;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.concurrent.Delayed;

public class MyActivity extends AppCompatActivity {

    public int u_all=0;
    public int u_turn=0;
    public int c_all=0;
    public int c_turn=0;
    private Random rand = new Random();
    public int num;

    String res="Your Turn Score : ";
    String res_c="Computer Turn Score : ";
    public int flag=0;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);

    }
    public void Roll(View v) {
        ImageView img = (ImageView) findViewById(R.id.img);
        TextView subtext = (TextView) findViewById(R.id.subtext);
        TextView text = (TextView) findViewById(R.id.text);

        num = rand.nextInt(7 - 1) + 1;
        switch (num) {
            case 1:
                img.setImageResource(R.drawable.dice1);
                break;
            case 2:
                img.setImageResource(R.drawable.dice2);
                break;
            case 3:
                img.setImageResource(R.drawable.dice3);
                break;
            case 4:
                img.setImageResource(R.drawable.dice4);
                break;
            case 5:
                img.setImageResource(R.drawable.dice5);
                break;
            case 6:
                img.setImageResource(R.drawable.dice6);
                break;
            default:
                img.setImageResource(R.drawable.dice1);
                break;
        }

        u_turn += num;

        if (num == 1) {
            u_turn = 0;
            subtext.setText(res + u_turn);
            computerTurn(v);
        }
        subtext.setText(res + u_turn);
    }
    public void Reset(View v)
    {
        ImageView img=(ImageView)findViewById(R.id.img);
        TextView subtext=(TextView)findViewById(R.id.subtext);
        TextView text=(TextView)findViewById(R.id.text);
        u_all=0;
        u_turn=0;
        c_all=0;
        c_turn=0;
        img.setImageResource(R.drawable.dice1);
        subtext.setText(res+'0');
        text.setText("Your Score: 0 Computer Score: 0");
    }

    public void Hold(View v)
    {
        ImageView img=(ImageView)findViewById(R.id.img);
        TextView subtext=(TextView)findViewById(R.id.subtext);
        TextView text=(TextView)findViewById(R.id.text);
        u_all+=u_turn;
        u_turn=0;
        text.setText("Your Score: "+u_all +" Computer Score: "+c_all);
        subtext.setText(res+'0');
        computerTurn(v);
    }
    public void computerTurn(View v)
    {
        TextView subtext=(TextView)findViewById(R.id.subtext);
        TextView text=(TextView)findViewById(R.id.text);
        Button b1=(Button)findViewById(R.id.button1);
        Button b2=(Button)findViewById(R.id.button2);
        b1.setClickable(false);
        b2.setClickable(false);
        Handler h2 = new Handler();


        while(c_turn<20)
        {
            num = rand.nextInt(7-1)+1;
            if(num==1)
            {
                c_turn=0;
                subtext.setText(res_c+c_turn);

                h2.postDelayed(new Runnable(){
                    @Override
                    public void run() {
                        TextView subtext=(TextView)findViewById(R.id.subtext);
                        subtext.setText("Computer rolled a one");
                    }
                }, 2000);
                break;
            }
            c_turn+=num;
        }
        c_all+=c_turn;

        text.setText("Your Score: "+u_all +" Computer Score: "+c_all);
        subtext.setText(res_c+c_turn);

        if(c_turn>=20) {
            h2.postDelayed(new Runnable(){
                @Override
                public void run() {
                    TextView subtext=(TextView)findViewById(R.id.subtext);
                    subtext.setText("Computer Holds");
                }
            }, 2000);
        }
        c_turn=0;
        b1.setClickable(true);
        b1.setEnabled(true);
        b2.setClickable(true);
        b2.setEnabled(true);
    }


}
