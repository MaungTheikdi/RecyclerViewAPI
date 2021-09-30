package com.theikdi.recyclerviewapi.hotls_sencond;

import android.widget.Filter;

import java.util.ArrayList;

public class HotelFilter extends Filter {
    HotelAdapter adapter;
    ArrayList<Hotels> filterList;

    public HotelFilter(ArrayList<Hotels> filterList, HotelAdapter adapter)
    {
        this.adapter=adapter;
        this.filterList=filterList;

    }
    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        FilterResults results=new FilterResults();
        //CHECK CONSTRAINT VALIDITY
        if(constraint != null && constraint.length() > 0)
        {
            //CHANGE TO UPPER
            constraint=constraint.toString().toUpperCase();
            //STORE OUR FILTERED PLAYERS
            ArrayList<Hotels> filteredPets=new ArrayList<>();

            for (int i=0;i<filterList.size();i++)
            {
                //CHECK
                if(filterList.get(i).getName().toUpperCase().contains(constraint))
                {
                    //ADD PLAYER TO FILTERED PLAYERS
                    filteredPets.add(filterList.get(i));
                }
            }

            results.count=filteredPets.size();
            results.values=filteredPets;

        }else
        {
            results.count=filterList.size();
            results.values=filterList;
        }

        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.hotels= (ArrayList<Hotels>) results.values;
        //REFRESH
        adapter.notifyDataSetChanged();

    }
}
