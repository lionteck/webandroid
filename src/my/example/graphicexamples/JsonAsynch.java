package my.example.graphicexamples;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class JsonAsynch extends AsyncTask<Object,Object,String> {
	ImageView first;
	ProgressDialog dialog;
	Context context;
	String url;
	JsonListener json;
	
	public JsonAsynch(Context cxt,String url,JsonListener json) {
        context = cxt;
        this.url=url;
        this.json=json;
        dialog = new ProgressDialog(context);
    }
	
	@Override
	protected String doInBackground(Object... arg0) {
		// TODO Auto-generated method stub
		
	    URL url;
		String ret="";
		try {
			url = new URL(this.url);
			URLConnection conn = url.openConnection();
			conn.connect();
			InputStreamReader inputStream = new InputStreamReader(conn.getInputStream());
	        
			
	        char fin[]=new char[2000];
			int len=0;
			while((len=inputStream.read(fin,0,2000))>=0){
				
				ret+=new String(fin).substring(0,len);
			}
	        
	        
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	protected void onPreExecute() {
	      super.onPreExecute();
	      dialog.setTitle("loading "+this.url);
	      dialog.show();
	     
	 } 
	
	protected void onPostExecute(String result) {
		dialog.dismiss();
		json.done(result);
		   
       
    }
	

}

