package com.program;

import com.program.r2_touch_android.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.view.MotionEvent;
import java.util.ArrayList;
import java.util.List;


public class Panel_activity extends Activity
	implements View.OnTouchListener 
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.panel_screen);
        WizardPagerAdapter adapter = new WizardPagerAdapter();
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        
        PreferenceManager.setDefaultValues(this, R.xml.settings, true);
        final SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        
        final string_parser ste = new string_parser ();
        
        Display display = getWindowManager().getDefaultDisplay();
        int screenSizeX = display.getWidth();
        int screenSizeY = display.getHeight();
        
        boolean dontuseCustom = prefs.getBoolean("useCustomSize", true);
		int buttonTSize = 12;
		int labelTSize = 12;
		try {
		buttonTSize = Integer.parseInt(prefs.getString("buttonTSize", "12"));
		labelTSize = Integer.parseInt(prefs.getString("labelTSize", "12"));
		} catch (NumberFormatException e) {
			
		}
        
        Button btn1 = (Button) findViewById(R.id.buttons1);
        btn1.setText(prefs.getString("pk1_name", "CloseD"));
    	Button btn2 = (Button) findViewById(R.id.buttons2);
    	btn2.setText(prefs.getString("pk2_name", "CloseD"));
    	Button btn3 = (Button) findViewById(R.id.buttons3);
    	btn3.setText(prefs.getString("pk3_name", "CloseD"));
    	Button btn4 = (Button) findViewById(R.id.buttons4);
    	btn4.setText(prefs.getString("pk4_name", "CloseD"));
    	Button btn5 = (Button) findViewById(R.id.buttons5);
    	btn5.setText(prefs.getString("pk5_name", "CloseD"));
    	Button btn6 = (Button) findViewById(R.id.buttons6);
    	btn6.setText(prefs.getString("pk6_name", "CloseD"));
    	Button btn7 = (Button) findViewById(R.id.buttons7);
    	btn7.setText(prefs.getString("pk7_name", "CloseD"));
    	Button btn8 = (Button) findViewById(R.id.buttons8);
    	btn8.setText(prefs.getString("pk8_name", "CloseD"));
    	Button btn9 = (Button) findViewById(R.id.buttons9);
    	btn9.setText(prefs.getString("pk9_name", "CloseD"));
    	Button btn10 = (Button) findViewById(R.id.buttons10);
    	btn10.setText(prefs.getString("pk10_name", "CloseD"));
    	Button btn11 = (Button) findViewById(R.id.buttons11);
    	btn11.setText(prefs.getString("pk11_name", "CloseD"));
    	Button btn12 = (Button) findViewById(R.id.buttons12); //>> Symbol
    	Button btn13 = (Button) findViewById(R.id.buttons13);
    	btn13.setText(prefs.getString("pk12_name", "CloseD")); 
    	Button btn14 = (Button) findViewById(R.id.buttons14);
    	btn14.setText(prefs.getString("pk13_name", "CloseD"));
    	Button btn15 = (Button) findViewById(R.id.buttons15);
    	btn15.setText(prefs.getString("pk14_name", "CloseD"));
    	Button btn16 = (Button) findViewById(R.id.buttons16);
    	btn16.setText(prefs.getString("pk15_name", "CloseD"));
    	Button btn17 = (Button) findViewById(R.id.buttons17);
    	btn17.setText(prefs.getString("pk16_name", "CloseD"));
    	Button btn18 = (Button) findViewById(R.id.buttons18);
    	btn18.setText(prefs.getString("pk17_name", "CloseD"));
    	Button btn19 = (Button) findViewById(R.id.buttons19);
    	btn19.setText(prefs.getString("pk18_name", "CloseD"));
    	Button btn20 = (Button) findViewById(R.id.buttons20);
    	btn20.setText(prefs.getString("pk19_name", "CloseD"));
    	Button btn21 = (Button) findViewById(R.id.buttons21);
    	btn21.setText(prefs.getString("pk20_name", "CloseD"));
    	Button btn22 = (Button) findViewById(R.id.buttons22); //<< Symbol
    	Button btn23 = (Button) findViewById(R.id.buttons23);
    	btn23.setText(prefs.getString("pk21_name", "CloseD"));
    	Button btn24 = (Button) findViewById(R.id.buttons24);
    	btn24.setText(prefs.getString("pk22_name", "CloseD"));
        
    	ImageButton btnnone = (ImageButton) findViewById(R.id.button_non);
    	Button btnbottom = (Button) findViewById(R.id.button_but);
    	Button btntop = (Button) findViewById(R.id.button_top);
    	Button btnall = (Button) findViewById(R.id.button_all);
    	
    	
    	TextView line1 = (TextView) findViewById (R.id.textView1);
        TextView line2 = (TextView) findViewById (R.id.textView1P);
        TextView line3 = (TextView) findViewById (R.id.textView1P2);
        ImageButton butsoundoff = (ImageButton) findViewById (R.id.button_non);
        Button butless = (Button) findViewById (R.id.button_but);
        Button butmid = (Button) findViewById (R.id.button_top);
        Button butmore = (Button) findViewById (R.id.button_all);
        Button but1 = (Button) findViewById (R.id.buttons1);
        Button but2 = (Button) findViewById (R.id.buttons2);
        Button but3 = (Button) findViewById (R.id.buttons3);
        Button but4 = (Button) findViewById (R.id.buttons4);
        Button but5 = (Button) findViewById (R.id.buttons5);
        Button but6 = (Button) findViewById (R.id.buttons6);
        Button but7 = (Button) findViewById (R.id.buttons7);
        Button but8 = (Button) findViewById (R.id.buttons8);
        Button but9 = (Button) findViewById (R.id.buttons9);
        Button but10 = (Button) findViewById (R.id.buttons10);
        Button but11 = (Button) findViewById (R.id.buttons11);
        Button but12 = (Button) findViewById (R.id.buttons12);
        Button but13 = (Button) findViewById (R.id.buttons13);
        Button but14 = (Button) findViewById (R.id.buttons14);
        Button but15 = (Button) findViewById (R.id.buttons15);
        Button but16 = (Button) findViewById (R.id.buttons16);
        Button but17 = (Button) findViewById (R.id.buttons17);
        Button but18 = (Button) findViewById (R.id.buttons18);
        Button but19 = (Button) findViewById (R.id.buttons19);
        Button but20 = (Button) findViewById (R.id.buttons20);
        Button but21 = (Button) findViewById (R.id.buttons21);
        Button but22 = (Button) findViewById (R.id.buttons22);
        Button but23 = (Button) findViewById (R.id.buttons23);
        Button but24 = (Button) findViewById (R.id.buttons24);
        
        if (dontuseCustom == true) {
        	if (screenSizeX <= 480)
        	{
        		line1.setTextSize(12);
        		line2.setTextSize(12);
        		line3.setTextSize(12);
        		butless.setTextSize(10);
        		butmid.setTextSize(10);
        		butmore.setTextSize(10);
        		but1.setTextSize(10);
        		but2.setTextSize(10);
        		but3.setTextSize(10);
        		but4.setTextSize(10);
        		but5.setTextSize(10);
        		but6.setTextSize(10);
        		but7.setTextSize(10);
        		but8.setTextSize(10);
        		but9.setTextSize(10);
        		but10.setTextSize(10);
        		but11.setTextSize(10);
        		but12.setTextSize(10);
        		but13.setTextSize(10);
        		but14.setTextSize(10);
        		but15.setTextSize(10);
        		but16.setTextSize(10);
        		but17.setTextSize(10);
        		but18.setTextSize(10);
        		but19.setTextSize(10);
        		but20.setTextSize(10);
        		but21.setTextSize(10);
        		but22.setTextSize(10);
        		but23.setTextSize(10);
        		but24.setTextSize(10);
        	}
        	else if (screenSizeX <= 720)
        	{
        		line1.setTextSize(24);
        		line2.setTextSize(24);
        		line3.setTextSize(24);
        		butless.setTextSize(18);
        		butmid.setTextSize(18);
        		butmore.setTextSize(18);
        		but1.setTextSize(18);
        		but2.setTextSize(18);
        		but3.setTextSize(18);
        		but4.setTextSize(18);
        		but5.setTextSize(18);
        		but6.setTextSize(18);
        		but7.setTextSize(18);
        		but8.setTextSize(18);
        		but9.setTextSize(18);
        		but10.setTextSize(18);
        		but11.setTextSize(18);
        		but12.setTextSize(18);
        		but13.setTextSize(18);
        		but14.setTextSize(18);
        		but15.setTextSize(18);
        		but16.setTextSize(18);
        		but17.setTextSize(18);
        		but18.setTextSize(18);
        		but19.setTextSize(18);
        		but20.setTextSize(18);
        		but21.setTextSize(18);
        		but22.setTextSize(18);
        		but23.setTextSize(18);
        		but24.setTextSize(18);
        	}
        	else if (screenSizeX <= 1080)
        	{
        		line1.setTextSize(26);
        		line2.setTextSize(36);
        		line3.setTextSize(36);
        		butless.setTextSize(18);
        		butmid.setTextSize(18);
        		butmore.setTextSize(18);
        		but1.setTextSize(18);
        		but2.setTextSize(18);
        		but3.setTextSize(18);
        		but4.setTextSize(18);
        		but5.setTextSize(18);
        		but6.setTextSize(18);
        		but7.setTextSize(18);
        		but8.setTextSize(18);
        		but9.setTextSize(18);
        		but10.setTextSize(18);
        		but11.setTextSize(18);
        		but12.setTextSize(18);
        		but13.setTextSize(18);
        		but14.setTextSize(18);
        		but15.setTextSize(18);
        		but16.setTextSize(18);
        		but17.setTextSize(18);
        		but18.setTextSize(18);
        		but19.setTextSize(18);
        		but20.setTextSize(18);
        		but21.setTextSize(18);
        		but22.setTextSize(18);
        		but23.setTextSize(18);
        		but24.setTextSize(18);
        	}
        	else
        	{
        	}
        }
        else {
        	line1.setTextSize(labelTSize);
    		line2.setTextSize(labelTSize);
    		line3.setTextSize(labelTSize);
    		butless.setTextSize(buttonTSize);
    		butmid.setTextSize(buttonTSize);
    		butmore.setTextSize(buttonTSize);
    		but1.setTextSize(buttonTSize);
    		but2.setTextSize(buttonTSize);
    		but3.setTextSize(buttonTSize);
    		but4.setTextSize(buttonTSize);
    		but5.setTextSize(buttonTSize);
    		but6.setTextSize(buttonTSize);
    		but7.setTextSize(buttonTSize);
    		but8.setTextSize(buttonTSize);
    		but9.setTextSize(buttonTSize);
    		but10.setTextSize(buttonTSize);
    		but11.setTextSize(buttonTSize);
    		but12.setTextSize(buttonTSize);
    		but13.setTextSize(buttonTSize);
    		but14.setTextSize(buttonTSize);
    		but15.setTextSize(buttonTSize);
    		but16.setTextSize(buttonTSize);
    		but17.setTextSize(buttonTSize);
    		but18.setTextSize(buttonTSize);
    		but19.setTextSize(buttonTSize);
    		but20.setTextSize(buttonTSize);
    		but21.setTextSize(buttonTSize);
    		but22.setTextSize(buttonTSize);
    		but23.setTextSize(buttonTSize);
    		but24.setTextSize(buttonTSize);
        }
    	
    	btn1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":OP00\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":OP00\r");
				String send = prefs.getString("pk1", ":OP00");
				ste.select_string(send);
			}
		});
    	btn2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":CL00\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":CL00\r");
				String send = prefs.getString("pk2", ":CL00");
				ste.select_string(send);
			}
		});
    	btn3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":ST00\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":ST00\r");
				String send = prefs.getString("pk3", ":ST00");
				ste.select_string(send);
			}
		});
    	btn4.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String send = prefs.getString("pk4", ":DSxx");
				executePanelAction(send, true, prefs, ste);
			}
		});
    	btn5.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				String send = prefs.getString("pk5", ":DS00");
				executePanelAction(send, false, prefs, ste);
			}
		});
    	btn6.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				/*
				Context context1 = getApplicationContext();
				if (Main_activity.panel1 == 1) {Toast.makeText(context1, ":ST01\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel2 == 1) {Toast.makeText(context1, ":ST02\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel3 == 1) {Toast.makeText(context1, ":ST03\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel4 == 1) {Toast.makeText(context1, ":ST04\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel5 == 1) {Toast.makeText(context1, ":ST05\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel6 == 1) {Toast.makeText(context1, ":ST06\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel7 == 1) {Toast.makeText(context1, ":ST07\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel8 == 1) {Toast.makeText(context1, ":ST08\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel9 == 1) {Toast.makeText(context1, ":ST09\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel10 == 1) {Toast.makeText(context1, ":ST10\r", Toast.LENGTH_LONG).show();}
				*/
				String send = prefs.getString("pk6", ":STXX");
				ste.select_string(send);
			}
		});
    	btn7.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				/*
				Context context1 = getApplicationContext();
				if (Main_activity.panel1 == 1) {Toast.makeText(context1, ":RC01\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel2 == 1) {Toast.makeText(context1, ":RC02\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel3 == 1) {Toast.makeText(context1, ":RC03\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel4 == 1) {Toast.makeText(context1, ":RC04\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel5 == 1) {Toast.makeText(context1, ":RC05\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel6 == 1) {Toast.makeText(context1, ":RC06\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel7 == 1) {Toast.makeText(context1, ":RC07\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel8 == 1) {Toast.makeText(context1, ":RC08\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel9 == 1) {Toast.makeText(context1, ":RC09\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel10 == 1) {Toast.makeText(context1, ":RC10\r", Toast.LENGTH_LONG).show();}
				*/
				String send = prefs.getString("pk7", ":RCXX");
				ste.select_string(send);
			}
		});
    	btn8.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				/*
				Context context1 = getApplicationContext();
				if (Main_activity.panel1 == 1) {Toast.makeText(context1, ":HD01\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel2 == 1) {Toast.makeText(context1, ":HD02\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel3 == 1) {Toast.makeText(context1, ":HD03\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel4 == 1) {Toast.makeText(context1, ":HD04\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel5 == 1) {Toast.makeText(context1, ":HD05\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel6 == 1) {Toast.makeText(context1, ":HD06\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel7 == 1) {Toast.makeText(context1, ":HD07\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel8 == 1) {Toast.makeText(context1, ":HD08\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel9 == 1) {Toast.makeText(context1, ":HD09\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel10 == 1) {Toast.makeText(context1, ":HD10\r", Toast.LENGTH_LONG).show();}
				*/
				String send = prefs.getString("pk8", ":HDXX");
				ste.select_string(send);
			}
		});
    	btn9.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				/*
				Context context1 = getApplicationContext();
				if (Main_activity.panel1 == 1) {Toast.makeText(context1, ":ST01\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel2 == 1) {Toast.makeText(context1, ":ST02\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel3 == 1) {Toast.makeText(context1, ":ST03\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel4 == 1) {Toast.makeText(context1, ":ST04\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel5 == 1) {Toast.makeText(context1, ":ST05\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel6 == 1) {Toast.makeText(context1, ":ST06\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel7 == 1) {Toast.makeText(context1, ":ST07\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel8 == 1) {Toast.makeText(context1, ":ST08\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel9 == 1) {Toast.makeText(context1, ":ST09\r", Toast.LENGTH_LONG).show();}
				if (Main_activity.panel10 == 1) {Toast.makeText(context1, ":ST10\r", Toast.LENGTH_LONG).show();}
				*/
				String send = prefs.getString("pk9", ":STXX");
				ste.select_string(send);
			}
		});
    	btn10.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":RC00\r", Toast.LENGTH_LONG).show();
				String send = prefs.getString("pk10", ":RC00");
				ste.select_string(send);
			}
		});
    	btn11.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE12\r", Toast.LENGTH_LONG).show();
				String send = prefs.getString("pk11", ":SE12");
				ste.select_string(send);
			}
		});
    	btn12.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ViewPager pager = (ViewPager) findViewById(R.id.pager);
				pager.setCurrentItem(1);
			}
		});
    	btn13.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE00\r", Toast.LENGTH_LONG).show();
				String send = prefs.getString("pk12", ":SE00");
				ste.select_string(send);
			}
		});
    	btn14.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE51\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":SE51\r");
				String send = prefs.getString("pk13", ":SE51");
				ste.select_string(send);
			}
		});
    	btn15.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE52\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":SE52\r");
				String send = prefs.getString("pk14", ":SE52");
				ste.select_string(send);
			}
		});
    	btn16.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE53\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":SE53\r");
				String send = prefs.getString("pk15", ":SE53");
				ste.select_string(send);
			}
		});
    	btn17.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE54\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":SE54\r");
				String send = prefs.getString("pk16", ":SE54");
				ste.select_string(send);
			}
		});
    	btn18.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE55\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":SE55\r");
				String send = prefs.getString("pk17", ":SE55");
				ste.select_string(send);
			}
		});
    	btn19.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE56\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":SE56\r");
				String send = prefs.getString("pk18", ":SE56");
				ste.select_string(send);
			}
		});
    	btn20.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":SE57\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":SE57\r");
				String send = prefs.getString("pk19", ":SE57");
				ste.select_string(send);
			}
		});
    	btn21.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":CL00\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":CL00\r");
				String send = prefs.getString("pk20", ":CL00");
				ste.select_string(send);
			}
		});
    	btn22.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ViewPager pager = (ViewPager) findViewById(R.id.pager);
				pager.setCurrentItem(0);
			}
		});
    	btn23.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":OP11\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":OP11\r");
				String send = prefs.getString("pk21", ":OP11");
				ste.select_string(send);
			}
		});
    	btn24.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				//Check to see what the current selected sound resource is. ex if its whis, $xyy x is 5
				//Context context1 = getApplicationContext();
				//Toast.makeText(context1, ":OP12\r", Toast.LENGTH_LONG).show();
				//Main_activity.sendstring(":OP12\r");
				String send = prefs.getString("pk22", ":OP12");
				ste.select_string(send);
			}
		});
    	
    	
        ImageView av = (ImageView) findViewById (R.id.imageView_Selection_Panels);
        ImageView bv = (ImageView) findViewById (R.id.imageView_Panels);
        ImageView cv = (ImageView) findViewById (R.id.panel1_imageview);
        ImageView dv = (ImageView) findViewById (R.id.panel2_imageview);
        ImageView ev = (ImageView) findViewById (R.id.panel3_imageview);
        ImageView fv = (ImageView) findViewById (R.id.panel4_imageview);
        ImageView gv = (ImageView) findViewById (R.id.panel5_imageview);
        ImageView hv = (ImageView) findViewById (R.id.panel6_imageview);
        ImageView iv = (ImageView) findViewById (R.id.panel7_imageview);
        ImageView jv = (ImageView) findViewById (R.id.panel8_imageview);
        ImageView kv = (ImageView) findViewById (R.id.panel9_imageview);
        ImageView mv = (ImageView) findViewById (R.id.panel10_imageview);
        ImageView ov = (ImageView) findViewById (R.id.panel11_imageview);
        ImageView pv = (ImageView) findViewById (R.id.panel12_imageview);
        
        if (Main_activity.panel1 == 0) {cv.setVisibility(View.INVISIBLE);}
 	   	else {cv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel2 == 0) {dv.setVisibility(View.INVISIBLE);}
 	   	else {dv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel3 == 0) {ev.setVisibility(View.INVISIBLE);}
 	   	else {ev.setVisibility(View.VISIBLE);}
        if (Main_activity.panel4 == 0) {fv.setVisibility(View.INVISIBLE);}
 	   	else {fv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel5 == 0) {gv.setVisibility(View.INVISIBLE);}
 	   	else {gv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel6 == 0) {hv.setVisibility(View.INVISIBLE);}
 	   	else {hv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel7 == 0) {iv.setVisibility(View.INVISIBLE);}
 	   	else {iv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel8 == 0) {jv.setVisibility(View.INVISIBLE);}
 	   	else {jv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel9 == 0) {kv.setVisibility(View.INVISIBLE);}
 	   	else {kv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel10 == 0) {mv.setVisibility(View.INVISIBLE);}
 	   	else {mv.setVisibility(View.VISIBLE);}
        if (Main_activity.panel11 == 0) {ov.setVisibility(View.INVISIBLE);}
 	   	else {ov.setVisibility(View.VISIBLE);}
        if (Main_activity.panel12 == 0) {pv.setVisibility(View.INVISIBLE);}
 	   	else {pv.setVisibility(View.VISIBLE);}
        
        ImageButton btnHome = (ImageButton) findViewById(R.id.home_Button);
        btnHome.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				// Goes back to the main screen
				finish();
			}
		});

        btnnone.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ImageView cv = (ImageView) findViewById (R.id.panel1_imageview);
		        ImageView dv = (ImageView) findViewById (R.id.panel2_imageview);
		        ImageView ev = (ImageView) findViewById (R.id.panel3_imageview);
		        ImageView fv = (ImageView) findViewById (R.id.panel4_imageview);
		        ImageView gv = (ImageView) findViewById (R.id.panel5_imageview);
		        ImageView hv = (ImageView) findViewById (R.id.panel6_imageview);
		        ImageView iv = (ImageView) findViewById (R.id.panel7_imageview);
		        ImageView jv = (ImageView) findViewById (R.id.panel8_imageview);
		        ImageView kv = (ImageView) findViewById (R.id.panel9_imageview);
		        ImageView mv = (ImageView) findViewById (R.id.panel10_imageview);
		        ImageView ov = (ImageView) findViewById (R.id.panel11_imageview);
		        ImageView pv = (ImageView) findViewById (R.id.panel12_imageview);
				
				Main_activity.panel1 = 0;
				Main_activity.panel2 = 0;
				Main_activity.panel3 = 0;
				Main_activity.panel4 = 0;
				Main_activity.panel5 = 0;
				Main_activity.panel6 = 0;
				Main_activity.panel7 = 0;
				Main_activity.panel8 = 0;
				Main_activity.panel9 = 0;
				Main_activity.panel10 = 0;
				Main_activity.panel11 = 0;
				Main_activity.panel12 = 0;
				cv.setVisibility(View.INVISIBLE);
		        dv.setVisibility(View.INVISIBLE);
		        ev.setVisibility(View.INVISIBLE);
		        fv.setVisibility(View.INVISIBLE);
		        gv.setVisibility(View.INVISIBLE);
		        hv.setVisibility(View.INVISIBLE);
		        iv.setVisibility(View.INVISIBLE);
		        jv.setVisibility(View.INVISIBLE);
		        kv.setVisibility(View.INVISIBLE);
		        mv.setVisibility(View.INVISIBLE);
		        ov.setVisibility(View.INVISIBLE);
		        pv.setVisibility(View.INVISIBLE);
			}
		});
    	btnbottom.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ImageView cv = (ImageView) findViewById (R.id.panel1_imageview);
		        ImageView dv = (ImageView) findViewById (R.id.panel2_imageview);
		        ImageView ev = (ImageView) findViewById (R.id.panel3_imageview);
		        ImageView fv = (ImageView) findViewById (R.id.panel4_imageview);
		        ImageView gv = (ImageView) findViewById (R.id.panel5_imageview);
		        ImageView hv = (ImageView) findViewById (R.id.panel6_imageview);
		        ImageView iv = (ImageView) findViewById (R.id.panel7_imageview);
		        ImageView jv = (ImageView) findViewById (R.id.panel8_imageview);
		        ImageView kv = (ImageView) findViewById (R.id.panel9_imageview);
		        ImageView mv = (ImageView) findViewById (R.id.panel10_imageview);
		        ImageView ov = (ImageView) findViewById (R.id.panel11_imageview);
		        ImageView pv = (ImageView) findViewById (R.id.panel12_imageview);
				
				Main_activity.panel1 = 1;
				Main_activity.panel2 = 1;
				Main_activity.panel3 = 1;
				Main_activity.panel4 = 1;
				Main_activity.panel5 = 1;
				Main_activity.panel6 = 1;
				Main_activity.panel7 = 0;
				Main_activity.panel8 = 0;
				Main_activity.panel9 = 0;
				Main_activity.panel10 = 0;
				Main_activity.panel11 = 1;
				Main_activity.panel12 = 1;
				
				cv.setVisibility(View.VISIBLE);
		        dv.setVisibility(View.VISIBLE);
		        ev.setVisibility(View.VISIBLE);
		        fv.setVisibility(View.VISIBLE);
		        gv.setVisibility(View.VISIBLE);
		        hv.setVisibility(View.VISIBLE);
		        iv.setVisibility(View.INVISIBLE);
		        jv.setVisibility(View.INVISIBLE);
		        kv.setVisibility(View.INVISIBLE);
		        mv.setVisibility(View.INVISIBLE);
		        ov.setVisibility(View.VISIBLE);
		        pv.setVisibility(View.VISIBLE);
			}
		});
    	btntop.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ImageView cv = (ImageView) findViewById (R.id.panel1_imageview);
		        ImageView dv = (ImageView) findViewById (R.id.panel2_imageview);
		        ImageView ev = (ImageView) findViewById (R.id.panel3_imageview);
		        ImageView fv = (ImageView) findViewById (R.id.panel4_imageview);
		        ImageView gv = (ImageView) findViewById (R.id.panel5_imageview);
		        ImageView hv = (ImageView) findViewById (R.id.panel6_imageview);
		        ImageView iv = (ImageView) findViewById (R.id.panel7_imageview);
		        ImageView jv = (ImageView) findViewById (R.id.panel8_imageview);
		        ImageView kv = (ImageView) findViewById (R.id.panel9_imageview);
		        ImageView mv = (ImageView) findViewById (R.id.panel10_imageview);
		        ImageView ov = (ImageView) findViewById (R.id.panel11_imageview);
		        ImageView pv = (ImageView) findViewById (R.id.panel12_imageview);
				
				Main_activity.panel1 = 0;
				Main_activity.panel2 = 0;
				Main_activity.panel3 = 0;
				Main_activity.panel4 = 0;
				Main_activity.panel5 = 0;
				Main_activity.panel6 = 0;
				Main_activity.panel7 = 1;
				Main_activity.panel8 = 1;
				Main_activity.panel9 = 1;
				Main_activity.panel10 = 1;
				Main_activity.panel11 = 0;
				Main_activity.panel12 = 0;
				
				cv.setVisibility(View.INVISIBLE);
		        dv.setVisibility(View.INVISIBLE);
		        ev.setVisibility(View.INVISIBLE);
		        fv.setVisibility(View.INVISIBLE);
		        gv.setVisibility(View.INVISIBLE);
		        hv.setVisibility(View.INVISIBLE);
		        iv.setVisibility(View.VISIBLE);
		        jv.setVisibility(View.VISIBLE);
		        kv.setVisibility(View.VISIBLE);
		        mv.setVisibility(View.VISIBLE);
		        ov.setVisibility(View.INVISIBLE);
		        pv.setVisibility(View.INVISIBLE);
			}
		});
    	btnall.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				ImageView cv = (ImageView) findViewById (R.id.panel1_imageview);
		        ImageView dv = (ImageView) findViewById (R.id.panel2_imageview);
		        ImageView ev = (ImageView) findViewById (R.id.panel3_imageview);
		        ImageView fv = (ImageView) findViewById (R.id.panel4_imageview);
		        ImageView gv = (ImageView) findViewById (R.id.panel5_imageview);
		        ImageView hv = (ImageView) findViewById (R.id.panel6_imageview);
		        ImageView iv = (ImageView) findViewById (R.id.panel7_imageview);
		        ImageView jv = (ImageView) findViewById (R.id.panel8_imageview);
		        ImageView kv = (ImageView) findViewById (R.id.panel9_imageview);
		        ImageView mv = (ImageView) findViewById (R.id.panel10_imageview);
		        ImageView ov = (ImageView) findViewById (R.id.panel11_imageview);
		        ImageView pv = (ImageView) findViewById (R.id.panel12_imageview);
				
				Main_activity.panel1 = 1;
				Main_activity.panel2 = 1;
				Main_activity.panel3 = 1;
				Main_activity.panel4 = 1;
				Main_activity.panel5 = 1;
				Main_activity.panel6 = 1;
				Main_activity.panel7 = 1;
				Main_activity.panel8 = 1;
				Main_activity.panel9 = 1;
				Main_activity.panel10 = 1;
				Main_activity.panel11 = 1;
				Main_activity.panel12 = 1;
				
				cv.setVisibility(View.VISIBLE);
		        dv.setVisibility(View.VISIBLE);
		        ev.setVisibility(View.VISIBLE);
		        fv.setVisibility(View.VISIBLE);
		        gv.setVisibility(View.VISIBLE);
		        hv.setVisibility(View.VISIBLE);
		        iv.setVisibility(View.VISIBLE);
		        jv.setVisibility(View.VISIBLE);
		        kv.setVisibility(View.VISIBLE);
		        mv.setVisibility(View.VISIBLE);
		        ov.setVisibility(View.VISIBLE);
		        pv.setVisibility(View.VISIBLE);
			}
		});
        
        
        if (bv != null) {
           bv.setOnTouchListener(this);
        }
        
    }

public boolean onTouch (View v, MotionEvent ev) 
{
    boolean handledHere = false;

    final int action = ev.getAction();
    int tolerance = 25;
    final int evX = (int) ev.getX();
    final int evY = (int) ev.getY();
    int nextImage = -1;			// resource id of the next image to display

    // If we cannot find the imageView, return.
    
    
    ImageView cv = (ImageView) findViewById (R.id.panel1_imageview);
    ImageView dv = (ImageView) findViewById (R.id.panel2_imageview);
    ImageView nv = (ImageView) findViewById (R.id.panel3_imageview);
    ImageView fv = (ImageView) findViewById (R.id.panel4_imageview);
    ImageView gv = (ImageView) findViewById (R.id.panel5_imageview);
    ImageView hv = (ImageView) findViewById (R.id.panel6_imageview);
    ImageView iv = (ImageView) findViewById (R.id.panel7_imageview);
    ImageView jv = (ImageView) findViewById (R.id.panel8_imageview);
    ImageView kv = (ImageView) findViewById (R.id.panel9_imageview);
    ImageView mv = (ImageView) findViewById (R.id.panel10_imageview);
    ImageView ov = (ImageView) findViewById (R.id.panel11_imageview);
    ImageView pv = (ImageView) findViewById (R.id.panel12_imageview);
    ImageView imageView = (ImageView) v.findViewById (R.id.imageView_Panels);
    if (imageView == null) return false;

    // When the action is Down, see if we should show the "pressed" image for the default image.
    // We do this when the default image is showing. That condition is detectable by looking at the
    // tag of the view. If it is null or contains the resource number of the default image, display the pressed image.
    Integer tagNum = (Integer) imageView.getTag ();
    int currentResource = (tagNum == null) ? R.drawable.dome_panel_wireframe : tagNum.intValue ();

    // Now that we know the current resource being displayed we can handle the DOWN and UP events.

    switch (action) {
    case MotionEvent.ACTION_DOWN :
        handledHere = true; 
       break;

    case MotionEvent.ACTION_UP :
       
       // Use imageView_selection_panels to determine which region the user touched.
       int touchColor = getHotspotColor (R.id.imageView_Selection_Panels, evX, evY);

       // Compare the touchColor to the expected values. Switch to a different image, depending on what color was touched.
       // Note that we use a Color Tool object to test whether the observed color is close enough to the real color to
       // count as a match. We do this because colors on the screen do not match the map exactly because of scaling and
       // varying pixel density.
       ColorTool ct = new ColorTool ();
       //nextImage = R.drawable.p2_ship_default;
       
       if (ct.closeMatch (Color.argb(255, 0, 255, 255), touchColor, tolerance)){
    	   if (cv.getVisibility() == View.VISIBLE) {cv.setVisibility(View.INVISIBLE); Main_activity.panel1 = 0;}
    	   else {cv.setVisibility(View.VISIBLE); Main_activity.panel1 = 1;}
       } //Panel 1
       else if (ct.closeMatch (Color.argb(255, 0, 19, 255), touchColor, tolerance)){
    	   if (dv.getVisibility() == View.VISIBLE) {dv.setVisibility(View.INVISIBLE); Main_activity.panel2 = 0;}
    	   else {dv.setVisibility(View.VISIBLE); Main_activity.panel2 = 1;}
       } //Panel 2
       else if (ct.closeMatch (Color.argb(255, 0, 255, 103), touchColor, tolerance)){
    	   if (nv.getVisibility() == View.VISIBLE) {nv.setVisibility(View.INVISIBLE); Main_activity.panel3 = 0;}
    	   else {nv.setVisibility(View.VISIBLE); Main_activity.panel3 = 1;}
       } //Panel 3
       else if (ct.closeMatch (Color.argb(255, 47, 130, 33), touchColor, tolerance)){
    	   if (fv.getVisibility() == View.VISIBLE) {fv.setVisibility(View.INVISIBLE); Main_activity.panel4 = 0;}
    	   else {fv.setVisibility(View.VISIBLE); Main_activity.panel4 = 1;}
       } //Panel 4
       else if (ct.closeMatch (Color.argb(255, 255, 0, 0), touchColor, tolerance)){
    	   if (gv.getVisibility() == View.VISIBLE) {gv.setVisibility(View.INVISIBLE); Main_activity.panel5 = 0;}
    	   else {gv.setVisibility(View.VISIBLE); Main_activity.panel5 = 1;}
       } //Panel5
       else if (ct.closeMatch (Color.argb(255, 205, 0, 255), touchColor, tolerance)){
    	   if (hv.getVisibility() == View.VISIBLE) {hv.setVisibility(View.INVISIBLE); Main_activity.panel6 = 0;}
    	   else {hv.setVisibility(View.VISIBLE); Main_activity.panel6 = 1;}
       } //Panel 6
       else if (ct.closeMatch (Color.argb(255, 255, 248, 0), touchColor, tolerance)){
    	   if (iv.getVisibility() == View.VISIBLE) {iv.setVisibility(View.INVISIBLE); Main_activity.panel7 = 0;}
    	   else {iv.setVisibility(View.VISIBLE); Main_activity.panel7 = 1;}
       } //Panel 7
       else if (ct.closeMatch (Color.argb(255, 255, 206, 0), touchColor, tolerance)){
    	   if (jv.getVisibility() == View.VISIBLE) {jv.setVisibility(View.INVISIBLE); Main_activity.panel8 = 0;}
    	   else {jv.setVisibility(View.VISIBLE); Main_activity.panel8 = 1;}
       } //Panel 8
       else if (ct.closeMatch (Color.argb(255, 0, 0, 0), touchColor, tolerance)){
    	   if (kv.getVisibility() == View.VISIBLE) {kv.setVisibility(View.INVISIBLE); Main_activity.panel9 = 0;}
    	   else {kv.setVisibility(View.VISIBLE); Main_activity.panel9 = 1;}
       } // Panel 9
       else if (ct.closeMatch (Color.argb(255, 255, 197, 255), touchColor, tolerance)){
    	   if (mv.getVisibility() == View.VISIBLE) {mv.setVisibility(View.INVISIBLE); Main_activity.panel10 = 0;}
    	   else {mv.setVisibility(View.VISIBLE); Main_activity.panel10 = 1;}
       } // Panel 10
       else if (ct.closeMatch (Color.argb(255, 128, 0, 128), touchColor, tolerance)){
    	   if (ov.getVisibility() == View.VISIBLE) {ov.setVisibility(View.INVISIBLE); Main_activity.panel11 = 0;}
    	   else {ov.setVisibility(View.VISIBLE); Main_activity.panel11 = 1;}
       } // Panel 11
       else if (ct.closeMatch (Color.argb(255, 0, 128, 128), touchColor, tolerance)){
    	   if (pv.getVisibility() == View.VISIBLE) {pv.setVisibility(View.INVISIBLE); Main_activity.panel12 = 0;}
    	   else {pv.setVisibility(View.VISIBLE); Main_activity.panel12 = 1;}
       } // Panel 12
       
       handledHere = true; 
       
       break;

    default:
       handledHere = false;
    } // end switch

    return handledHere;
}

public int getHotspotColor (int hotspotId, int x, int y) {
    ImageView img = (ImageView) findViewById (hotspotId);
    if (img == null) {
       Log.d ("ImageAreasActivity", "Hot spot image not found");
       return 0;
    } else {
      img.setDrawingCacheEnabled(true);
      Bitmap hotspots = Bitmap.createBitmap(img.getDrawingCache()); 
      if (hotspots == null) {
         Log.d ("ImageAreasActivity", "Hot spot bitmap was not created");
         return 0;
      } else {
        img.setDrawingCacheEnabled(false);
        return hotspots.getPixel(x, y);
      }
    }
}

public static int calculateInSampleSize(
        BitmapFactory.Options options, int reqWidth, int reqHeight) {
// Raw height and width of image
final int height = options.outHeight;
final int width = options.outWidth;
int inSampleSize = 1;

if (height > reqHeight || width > reqWidth) {

    final int halfHeight = height / 2;
    final int halfWidth = width / 2;

    // Calculate the largest inSampleSize value that is a power of 2 and keeps both
    // height and width larger than the requested height and width.
    while ((halfHeight / inSampleSize) > reqHeight
            && (halfWidth / inSampleSize) > reqWidth) {
        inSampleSize *= 2;
    }
}

return inSampleSize;
}

public static Bitmap decodeSampledBitmapFromResource(Resources res, int resId,
        int reqWidth, int reqHeight) {

    // First decode with inJustDecodeBounds=true to check dimensions
    final BitmapFactory.Options options = new BitmapFactory.Options();
    options.inJustDecodeBounds = true;
    BitmapFactory.decodeResource(res, resId, options);

    // Calculate inSampleSize
    options.inSampleSize = calculateInSampleSize(options, reqWidth, reqHeight);

    // Decode bitmap with inSampleSize set
    options.inJustDecodeBounds = false;
    return BitmapFactory.decodeResource(res, resId, options);
}

public class WizardPagerAdapter extends PagerAdapter {
    public Object instantiateItem(View collection, int position) {
        int resId = 0;
        switch (position) {
        case 0:
            resId = R.id.page_one;
            break;
        case 1:
            resId = R.id.page_two;
            break;
        }
        return findViewById(resId);
    }
    @Override
    public int getCount() {
        return 2;
    }
    @Override
    public boolean isViewFromObject(View arg0, Object arg1) {
        return arg0 == ((View) arg1);
    }
}

	private void executePanelAction(String send, boolean isOpen, SharedPreferences prefs, final string_parser ste) {
		int selectedCount = 0;
		List<String> commands = new ArrayList<>();
		StringBuilder panelNames = new StringBuilder();

		for (int i = 1; i <= 12; i++) {
			boolean isSelected = false;
			switch (i) {
			case 1: isSelected = (Main_activity.panel1 == 1); break;
			case 2: isSelected = (Main_activity.panel2 == 1); break;
			case 3: isSelected = (Main_activity.panel3 == 1); break;
			case 4: isSelected = (Main_activity.panel4 == 1); break;
			case 5: isSelected = (Main_activity.panel5 == 1); break;
			case 6: isSelected = (Main_activity.panel6 == 1); break;
			case 7: isSelected = (Main_activity.panel7 == 1); break;
			case 8: isSelected = (Main_activity.panel8 == 1); break;
			case 9: isSelected = (Main_activity.panel9 == 1); break;
			case 10: isSelected = (Main_activity.panel10 == 1); break;
			case 11: isSelected = (Main_activity.panel11 == 1); break;
			case 12: isSelected = (Main_activity.panel12 == 1); break;
			}

			if (isSelected) {
				selectedCount++;
				String key = isOpen ? ("dp" + i + "_open") : ("dp" + i + "_close");
				String slotStr = String.format("%02d", i - 1);
				String defaultCmd = isOpen ? (":DS" + slotStr + "O") : (":DS" + slotStr + "C");
				String cmd = prefs.getString(key, defaultCmd);
				if (cmd != null && !cmd.trim().isEmpty()) {
					commands.add(cmd.trim());
					if (panelNames.length() > 0) panelNames.append(", ");
					panelNames.append("P").append(i);
				}
			}
		}

		if (selectedCount == 0) {
			Toast.makeText(this, "No panels selected! Tap panels on the dome first.", Toast.LENGTH_SHORT).show();
			return;
		}

		// Group commands into chunks of at most 6 per line with no spaces or commas
		List<String> chunks = new ArrayList<>();
		StringBuilder currentChunk = new StringBuilder();
		int countInChunk = 0;

		for (String cmd : commands) {
			currentChunk.append(cmd);
			countInChunk++;
			if (countInChunk >= 6) {
				chunks.add(currentChunk.toString());
				currentChunk = new StringBuilder();
				countInChunk = 0;
			}
		}
		if (currentChunk.length() > 0) {
			chunks.add(currentChunk.toString());
		}

		// Send chunks on background thread to prevent blocking UI
		final List<String> chunksToSend = chunks;
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int c = 0; c < chunksToSend.size(); c++) {
					ste.select_string(chunksToSend.get(c));
					if (c < chunksToSend.size() - 1) {
						try {
							Thread.sleep(50);
						} catch (InterruptedException ignored) {}
					}
				}
			}
		}).start();

		String verb = isOpen ? "Opened" : "Closed";
		StringBuilder fullSent = new StringBuilder();
		for (String c : chunks) {
			fullSent.append(c);
		}
		Toast.makeText(this, verb + " " + panelNames + " (" + fullSent + ")", Toast.LENGTH_SHORT).show();
	}

}