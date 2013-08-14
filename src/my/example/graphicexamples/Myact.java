package my.example.graphicexamples;



import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

public class Myact extends Activity implements JsonListener {
	private TextView text;
	private FrameLayout fr,sl;
	private ListView v;
	private LinearLayout linear,f,n;
	private String URL_BASE="http://www.marcodamico.net/";
	FrameLayout vert,hor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//ProgressDialog.show(getApplicationContext(), "load", "loading");
		
		
		setContentView(R.layout.imagegallery);
		
		 linear=(LinearLayout)findViewById(R.id.gallery);
		 hor=(FrameLayout)findViewById(R.id.horlay);
		 vert=(FrameLayout)findViewById(R.id.vertlay);
		//new Connection(this).execute(findViewById(R.id.mia));
		//new RemoteImageView(this,"http://cdn.zendesk.com/images/p-customers/logo-elance.png").execute(findViewById(R.id.mia));
		new JsonAsynch(this,URL_BASE+"php/ajax.php?do=loadgallerytumb&name=about%20me%20|%20fw%202014&menu=FASHION",this).execute();
		new JsonAsynch(this,URL_BASE+"php/ajax.php?do=loadgallerytumb&name=about%20me%20|%20fw%202014&menu=FASHION",this).execute();
		/*String []a={"ciao","miao","kphhki","pkhjpoihj"};
		v=(ListView)findViewById(R.id.listView1);
		 ArrayAdapter<String> adapter = new ArrayAdapter<String>( this, R.layout.row, a);
			    v.setAdapter(adapter);
		
		fr=(FrameLayout)findViewById(R.id.button);
		 sl=(FrameLayout)findViewById(R.id.slide);
		 //Bundle extras = getIntent().getExtras();
		 
		 text=(TextView)findViewById(R.id.resp);
		 
		 fr.setOnClickListener(new View.OnClickListener() {
	            
	            private boolean pos=false;
	            @Override
	            public void onClick(View v) {
	            	//Toast.makeText(getApplicationContext(),sl.getLayoutParams().height, Toast.LENGTH_SHORT).show();
	            	if(!pos){
	            		open();
	            	
	            	}
	            	else{
	            		close();
	            	}
	            }
	            
                 public void open(){
                	 ValueAnimator anim = ValueAnimator.ofInt(sl.getMeasuredWidth(), 250);
                	    anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                	        @Override
                	        public void onAnimationUpdate(ValueAnimator valueAnimator) {
                	            int val = (Integer) valueAnimator.getAnimatedValue();
                	            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) sl.getLayoutParams();
                	            layoutParams.width = val;
                	            sl.setLayoutParams(layoutParams);
                	        }
                	    });
                	    anim.setDuration(500);
                	    anim.start(); 
                	/* AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(getApplicationContext(),
                	R.anim.firstanim);
                	set.setTarget(sl);
                	set.start();*/
                	/* sl.setLayoutParams(new FrameLayout.LayoutParams(100,LayoutParams.FILL_PARENT));
 	            	
 	            	fr.setX(100);*/
 	            	/*pos=true;
	            }
	            
	            public void close(){
                   sl.setLayoutParams(new FrameLayout.LayoutParams(0,LayoutParams.FILL_PARENT));
	            	
	            	fr.setX(0);
	            	pos=false;
	            }
	            
		 });
		 //String d=extras.getString("ciao");
		// text.setText(d);*/
		
		
	}
	
	public void onConfigurationChanged(Configuration newConfig) {
	    super.onConfigurationChanged(newConfig);

	    // Checks the orientation of the screen
	    if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
	    	
	    	hor.setVisibility(LinearLayout.INVISIBLE);
	    	vert.setVisibility(LinearLayout.VISIBLE);
	    }
	    else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
	    	hor.setVisibility(LinearLayout.VISIBLE);
	    	vert.setVisibility(LinearLayout.INVISIBLE);
	    }
	}
	
	private class Connection extends AsyncTask<Object,Object,Bitmap> {
		ImageView first;
		ProgressDialog dialog;
		Context context;
		String url;
		
		public Connection(Context cxt,String url) {
            context = cxt;
            this.url=url;
            dialog = new ProgressDialog(context);
        }
		
		@Override
		protected Bitmap doInBackground(Object... arg0) {
			// TODO Auto-generated method stub
			first=(ImageView)arg0[0];
		    URL url;
			Bitmap bmp=null;
			try {
				url = new URL(this.url);
				URLConnection conn = url.openConnection();
				conn.connect();
		        InputStream inputStream = conn.getInputStream();
		        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
		        bmp = BitmapFactory.decodeStream(bufferedInputStream);
		        
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return bmp;
		}
		
		protected void onPreExecute() {
		      super.onPreExecute();
		      dialog.setTitle("loading "+this.url);
		      dialog.show();
		     
		 } 
		
		protected void onPostExecute(Bitmap result) {
			dialog.dismiss();
			if(result!=null)
			   first.setImageBitmap(result);
	       
	    }
		
	
	}

	@Override
	public void done(String json) {
		// TODO Auto-generated method stub
		Toast.makeText(this, json, Toast.LENGTH_LONG).show();
		try {
			JSONObject obj=new JSONObject(json);
			JSONArray arr=obj.getJSONArray("data");
			f=(LinearLayout)findViewById(R.id.gallery);
			n=(LinearLayout)findViewById(R.id.galleryvert);
			for(int x=0;x<arr.length();x++){
				ImageView g=new ImageView(this);
				ImageView h=new ImageView(this);
				g.setTop(0);
				h.setTop(0);
				f.addView(g);
				n.addView(h);
				RemoteImageView r=new RemoteImageView(this,URL_BASE+arr.getJSONObject(x).getString("src"),new LinearLayout.LayoutParams(100,100),(ImageView)findViewById(R.id.bigimage));
				r.resizeHeight(100).execute(g,h);
				g.setOnClickListener(r);
				h.setOnClickListener(r);
				//Toast.makeText(this, arr.getJSONObject(x).getString("src"), Toast.LENGTH_LONG).show();
			}
			if(getResources().getConfiguration().orientation==LinearLayout.HORIZONTAL){
				vert.setVisibility(LinearLayout.INVISIBLE);
				hor.setVisibility(LinearLayout.VISIBLE);
			}
			else{
				hor.setVisibility(LinearLayout.INVISIBLE);
				vert.setVisibility(LinearLayout.VISIBLE);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	
	
}
