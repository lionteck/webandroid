package my.example.graphicexamples;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

class RemoteImageView extends AsyncTask<Object,Object,Bitmap> implements OnClickListener{
	ArrayList<ImageView> first;
	ProgressDialog dialog;
	Context context;
	int resize=-1;
	int HEIGHT=0;
	int WIDTH=1;
	int height,width;
	String url;
	BitmapFactory.Options opt;
	LinearLayout.LayoutParams par;
	Bitmap m;
	ImageView v;
	
	public RemoteImageView(Context cxt,String url,LinearLayout.LayoutParams par,ImageView v) {
		this.v=v;
        context = cxt;
        this.par=par;
        this.first=new ArrayList<ImageView>();
        this.url=url;
        dialog = new ProgressDialog(context);
    }
	
	public RemoteImageView resizeHeight(int height){
		this.resize= this.HEIGHT;
		this.height=height;
		return this;
	}
	
    public RemoteImageView resizeWidth(int width){
    	this.resize= this.WIDTH;
    	this.width=width;
    	return this;
	}
	
	@Override
	protected Bitmap doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		for(int x=0;x<arg0.length;x++){
			first.add((ImageView)arg0[x]);
		}
		
	    URL url;
		Bitmap bmp=null;
		try {
			url = new URL(this.url);
			URLConnection conn = url.openConnection();
			conn.connect();
			 opt = new BitmapFactory.Options();
	        InputStream inputStream = conn.getInputStream();
	        BufferedInputStream bufferedInputStream = new BufferedInputStream(inputStream);
	        bmp = BitmapFactory.decodeStream(bufferedInputStream,null,opt);
	        
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
		if(result!=null){
			m=result;
			for(int x=0;x<first.size();x++){
			   
			   if(resize==-1){
				   first.get(x).setImageBitmap(result);
				   first.get(x).setLayoutParams(new LinearLayout.LayoutParams(result.getWidth(),result.getHeight()));
				   
			   }
			   else{
				   if(this.resize== this.HEIGHT){
					  //Toast.makeText(context,Math.floor((float)opt.outWidth*(((float)height/(float)opt.outHeight)))+"", Toast.LENGTH_LONG).show();
					   first.get(x).setImageBitmap(Bitmap.createScaledBitmap(result,(int)Math.floor((float)opt.outWidth*(((float)height/(float)opt.outHeight))), height, true));
					   first.get(x).setLayoutParams(new LinearLayout.LayoutParams((int)Math.floor((float)opt.outWidth*(((float)height/(float)opt.outHeight))), height)); 
				   }
				   else{
					   first.get(x).setImageBitmap(Bitmap.createScaledBitmap(result, width, (int)Math.floor((float)opt.outHeight*(((float)width/(float)opt.outWidth))), true));
					   first.get(x).setLayoutParams(new LinearLayout.LayoutParams(width, (int)Math.floor((float)opt.outHeight*(((float)width/(float)opt.outWidth))))); 
					   
				   }
			   }
			}
		}
       
    }
	
	public void cloneImageView(ImageView im){
		if(m!=null){
			ViewGroup.LayoutParams imp= im.getLayoutParams();
			float ratio_f=(float)context.getResources().getDisplayMetrics().widthPixels/(float)context.getResources().getDisplayMetrics().heightPixels;
			float ratio=(float)opt.outWidth/(float)opt.outHeight;
			int height=0;
			int width=0;
			if(ratio>ratio_f){
				width=context.getResources().getDisplayMetrics().widthPixels;
				height= (int)Math.floor((float)opt.outHeight*(float)(1/ratio));
				Log.i("info",width+" "+height+" "+(context.getResources().getDisplayMetrics().heightPixels-height));			
				im.setPadding(0, (context.getResources().getDisplayMetrics().heightPixels-height)/2,0,0);
				
			}
			else{
				height=context.getResources().getDisplayMetrics().heightPixels;
				width=(int)Math.floor((float)opt.outWidth*(float)(ratio));
				Log.i("info",width+" "+height+" "+(context.getResources().getDisplayMetrics().widthPixels-width));			
				im.setPadding(imp.width-width,0,0,0);
			    
			}
			im.setImageBitmap(Bitmap.createScaledBitmap(m,width, height, true));
			im.setLayoutParams(new FrameLayout.LayoutParams(width, height));
		}
	}

	@Override
	public void onClick(View d) {
		// TODO Auto-generated method stub
		cloneImageView(v);
		
	}
	
	
	

}
