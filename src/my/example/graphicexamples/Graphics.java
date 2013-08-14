package my.example.graphicexamples;


import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class Graphics extends Activity {
	private Button bt,bt2;
	private TextView text;
	private Bundle b;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_graphics);
		
		
		RelativeLayout view =new RelativeLayout(this);
		bt=(Button)findViewById(R.id.button1);
		//bt=new Button(this);
		
		//bt2=(Button)findViewById(R.id.button1);
		//view.addView(bt);
		
		//setContentView(view);
		//text=(TextView)findViewById(R.id.go);
		bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            	 /*Intent inte=new Intent(getApplicationContext(), MyAdapter.class);
            	 startActivity(inte);*/
            	Intent inte=new Intent(getApplicationContext(), Myact.class);
            	startActivity(inte);
            	/*b=new Bundle();
            	b.putString("ciao", text.getText().toString());
            	inte.putExtras(b);
            	
            	 startActivity(inte);*/
            }
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_graphics, menu);
		return true;
	}

}
