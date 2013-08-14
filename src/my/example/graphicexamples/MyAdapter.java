package my.example.graphicexamples;

import java.util.ArrayList;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class MyAdapter extends ListActivity {

		 private ArrayList<String> dataname = new ArrayList<String>();
		 private ArrayList<String> datacode = new ArrayList<String>();
		 Context con = this;


		 public static class viewHolder {
		  TextView tname;
		  ImageView tcode;
		 }

		 private class EfficientAdapter extends BaseAdapter {

		  private Context context;
		  LayoutInflater inflater;

		  public EfficientAdapter(Context context) {
		   // TODO Auto-generated constructor stub
		   this.context = context;
		   inflater = LayoutInflater.from(context);

		  }

		  @Override
		  public int getCount() {
		   // TODO Auto-generated method stub
		   return datacode.size();
		  }

		  @Override
		  public Object getItem(int position) {
		   // TODO Auto-generated method stub
		   return position;
		  }

		  @Override
		  public long getItemId(int position) {
		   // TODO Auto-generated method stub
		   return position;
		  }

		  @Override
		  public View getView(int position, View convertView, ViewGroup parent) {
		   viewHolder holder;
		   if (convertView == null) {
		    convertView = inflater.inflate(R.layout.listinflate, null);
		    holder = new viewHolder();

		    convertView.setTag(holder);

		   } else {
		    holder = (viewHolder) convertView.getTag();
		   } 
		   holder.tname = (TextView) convertView
		     .findViewById(R.id.textViewName);
		   holder.tcode = (ImageView) convertView
		     .findViewById(R.id.textViewCode);
		   holder.tname.setText(dataname.get(position));
		  // new RemoteImageView(parent.getContext(),datacode.get(position),null).execute(holder.tcode);

		  

		   return convertView;
		  }

		 }

		 /** Called when the activity is first created. */
		 @Override
		 public void onCreate(Bundle savedInstanceState) {
		  super.onCreate(savedInstanceState);
		  requestWindowFeature(Window.FEATURE_NO_TITLE);
		  setContentView(R.layout.listview);

		  for (int i = 0; i < 10; i++) {
		   dataname.add("Name" + i);
		   datacode.add("http://www.marcodamico.net/gallery/FASHION/113/tumb/X1A4317.jpg");
		   
		  }

		  setListAdapter(new EfficientAdapter(con));
		 }

		 @Override
		 protected void onListItemClick(ListView l, View v, int position, long id) {
		  // TODO Auto-generated method stub
		  super.onListItemClick(l, v, position, id);
		  Toast.makeText(con, "Item :"+position, Toast.LENGTH_LONG).show();
		 }
		}