package com.example.foodie.ui.search;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.foodie.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter{

    //variables
    Context mContext;
    LayoutInflater inflater;
    List<com.example.foodie.ui.search.SearchViewModel> searchViewModelList;
    ArrayList<com.example.foodie.ui.search.SearchViewModel> arrayList;

    //constructor
    public ListViewAdapter(Context context, List<SearchViewModel> searchViewModelList) {
        mContext = context;
        this.searchViewModelList = searchViewModelList;
        inflater = LayoutInflater.from(mContext);
        this.arrayList = new ArrayList<>();
        this.arrayList.addAll(searchViewModelList);
    }

    public class ViewHolder{
        TextView mTitleTv, mDescTv;
        ImageView mIconIv;
    }

    @Override
    public int getCount() {
        return searchViewModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return searchViewModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int postition, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view==null){
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.search_row, null);

            //locate the views in search_row.xmlrow.xml
            holder.mTitleTv = view.findViewById(R.id.mainTitle);
            holder.mDescTv = view.findViewById(R.id.mainDesc);
            holder.mIconIv = view.findViewById(R.id.mainIcon);

            view.setTag(holder);

        }
        else {
            holder = (ViewHolder)view.getTag();
        }
        //set the results into textviews
        holder.mTitleTv.setText(searchViewModelList.get(postition).getTitle());
        holder.mDescTv.setText(searchViewModelList.get(postition).getDesc());
        //set the result in imageview
        holder.mIconIv.setImageResource(searchViewModelList.get(postition).getIcon());

        //listview item clicks
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //code later
                if (searchViewModelList.get(postition).getTitle().equals("Battery")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, com.example.foodie.ui.search.NewActivity.class);
                    intent.putExtra("actionBarTitle", "Battery");
                    intent.putExtra("contentTv", "This is Battery detail...");
                    mContext.startActivity(intent);
                }
                if (searchViewModelList.get(postition).getTitle().equals("Cpu")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, com.example.foodie.ui.search.NewActivity.class);
                    intent.putExtra("actionBarTitle", "Cpu");
                    intent.putExtra("contentTv", "This is Cpu detail...");
                    mContext.startActivity(intent);
                }
                if (searchViewModelList.get(postition).getTitle().equals("Display")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, com.example.foodie.ui.search.NewActivity.class);
                    intent.putExtra("actionBarTitle", "Display");
                    intent.putExtra("contentTv", "This is Display detail...");
                    mContext.startActivity(intent);
                }
                if (searchViewModelList.get(postition).getTitle().equals("Memory")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, com.example.foodie.ui.search.NewActivity.class);
                    intent.putExtra("actionBarTitle", "Memory");
                    intent.putExtra("contentTv", "This is Memory detail...");
                    mContext.startActivity(intent);
                }
                if (searchViewModelList.get(postition).getTitle().equals("Sensor")){
                    //start NewActivity with title for actionbar and text for textview
                    Intent intent = new Intent(mContext, com.example.foodie.ui.search.NewActivity.class);
                    intent.putExtra("actionBarTitle", "Sensor");
                    intent.putExtra("contentTv", "This is Sensor detail...");
                    mContext.startActivity(intent);
                }
            }
        });


        return view;
    }

    //filter
    public void filter(String charText){
        charText = charText.toLowerCase(Locale.getDefault());
        searchViewModelList.clear();
        if (charText.length()==0){
            searchViewModelList.addAll(arrayList);
        }
        else {
            for (com.example.foodie.ui.search.SearchViewModel searchViewModel : arrayList){
                if (searchViewModel.getTitle().toLowerCase(Locale.getDefault())
                        .contains(charText)){
                    searchViewModelList.add(searchViewModel);
                }
            }
        }
        notifyDataSetChanged();
    }

}