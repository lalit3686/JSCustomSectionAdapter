package com.sectionadapter.example;

import java.util.ArrayList;

import com.example.jeffsharkeycustomsectionadapter.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	ListView mListView;
	ArrayList<String> mArrayList = new ArrayList<String>();
	SectionedAdapter adapter;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mListView = (ListView) findViewById(R.id.listview);

		adapter = new SectionedAdapter() {

			@Override
			protected View getHeaderView(String caption, int index, View convertView, ViewGroup parent) {
				convertView = getLayoutInflater().inflate(R.layout.section_header, null);
				TextView header = (TextView) convertView.findViewById(R.id.header);
				header.setText(caption);
				return convertView;
			}
		};

		for (int i = 0; i < 5; i++) {
			mArrayList.add("Item " + i);
			MyAdapter myAdapter = new MyAdapter();
			adapter.addSection("Header " + i, myAdapter);
		}
		mListView.setOnItemClickListener(new OnItemClickListener() {

			public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
				Toast.makeText(getApplicationContext(), arg0.getAdapter().getItem(position).toString(), Toast.LENGTH_LONG).show();
			}
		});
		mListView.setAdapter(adapter);
	}

	class MyAdapter extends BaseAdapter {

		public int getCount() {
			return mArrayList.size();
		}

		public Object getItem(int position) {
			return mArrayList.get(position);
		}

		public long getItemId(int position) {
			return position;
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			convertView = (TextView) getLayoutInflater().inflate(R.layout.section_item, null);
			TextView item = (TextView) convertView.findViewById(R.id.item);
			item.setText(mArrayList.get(position));
			return convertView;
		}
	}
}
