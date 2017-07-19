package com.zaaach.citypicker.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.zaaach.citypicker.R;
import com.zaaach.citypicker.model.City;
import com.zaaach.citypicker.model.LocateState;
import com.zaaach.citypicker.utils.PinyinUtils;
import com.zaaach.citypicker.view.WrapHeightGridView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CityListAdapter extends BaseAdapter {
    private Context mContext;
    private LayoutInflater inflater;
    private List<City> mCities;
    private HashMap<String, Integer> letterIndexes;
    private String[] sections;
    private OnCityClickListener onCityClickListener;
    private int locateState = LocateState.LOCATING;
    private String locatedCity;
    private String[] cityLetters = new String[]{"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M",
            "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
    private List<List<String>> list;
    private List<String> listA = new ArrayList<>();
    private List<String> listB = new ArrayList<>();
    private List<String> listC = new ArrayList<>();
    private List<String> listD = new ArrayList<>();
    private List<String> listE = new ArrayList<>();
    private List<String> listF = new ArrayList<>();
    private List<String> listG = new ArrayList<>();
    private List<String> listH = new ArrayList<>();
    private List<String> listI = new ArrayList<>();
    private List<String> listJ = new ArrayList<>();
    private List<String> listK = new ArrayList<>();
    private List<String> listL = new ArrayList<>();
    private List<String> listM = new ArrayList<>();
    private List<String> listN = new ArrayList<>();
    private List<String> listO = new ArrayList<>();
    private List<String> listP = new ArrayList<>();
    private List<String> listQ = new ArrayList<>();
    private List<String> listR = new ArrayList<>();
    private List<String> listS = new ArrayList<>();
    private List<String> listT = new ArrayList<>();
    private List<String> listU = new ArrayList<>();
    private List<String> listV = new ArrayList<>();
    private List<String> listW = new ArrayList<>();
    private List<String> listX = new ArrayList<>();
    private List<String> listY = new ArrayList<>();
    private List<String> listZ = new ArrayList<>();

    public CityListAdapter(Context mContext, List<City> mCities) {
        this.mContext = mContext;
        this.mCities = mCities;
        this.inflater = LayoutInflater.from(mContext);
        if (mCities == null) {
            mCities = new ArrayList<>();
        }
        list = new ArrayList<>();
        list.add(0,listA);
        list.add(1,listB);
        list.add(2,listC);
        list.add(3,listD);
        list.add(4,listE);
        list.add(5,listF);
        list.add(6,listG);
        list.add(7,listH);
        list.add(8,listI);
        list.add(9,listJ);
        list.add(10,listK);
        list.add(11,listL);
        list.add(12,listM);
        list.add(13,listN);
        list.add(14,listO);
        list.add(15,listP);
        list.add(16,listQ);
        list.add(17,listR);
        list.add(18,listS);
        list.add(19,listT);
        list.add(20,listU);
        list.add(21,listV);
        list.add(22,listW);
        list.add(23,listX);
        list.add(24,listY);
        list.add(25,listZ);
        int size = mCities.size();
        letterIndexes = new HashMap<>();
        sections = new String[size];

        for (int index = 0; index < size; index++) {
            String city = mCities.get(index).getName();
            //当前城市拼音首字母
            String previousLetter = PinyinUtils.getFirstLetter(mCities.get(index).getPinyin());
            for(int j=0;j<cityLetters.length;j++){
                if(TextUtils.equals(cityLetters[j],previousLetter)){
                    list.get(j).add(city);
                }
            }
        }
        for(int i=0;i<cityLetters.length;i++){
            String currentLetter = cityLetters[i];
            letterIndexes.put(currentLetter, i);
            sections[i] = currentLetter;
        }
    }

    /**
     * 更新定位状态
     *
     * @param state
     */
    public void updateLocateState(int state, String city) {
        this.locateState = state;
        this.locatedCity = city;
        notifyDataSetChanged();
    }

    /**
     * 获取字母索引的位置
     *
     * @param letter
     * @return
     */
    public int getLetterPosition(String letter) {
        Integer integer = letterIndexes.get(letter);
        return integer == null ? -1 : integer;
    }

    @Override
    public int getCount() {
        return mCities == null ? 0 : 27;
    }

    @Override
    public City getItem(int position) {
        return mCities == null ? null : mCities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View view, ViewGroup parent) {
        CityViewHolder holder;
                if (view == null) {
                    view = inflater.inflate(R.layout.cp_item_city_listview, parent, false);
                    holder = new CityViewHolder();
                    holder.letter = (TextView) view.findViewById(R.id.tv_item_city_list_view_letter);
                    holder.gridView = (WrapHeightGridView) view.findViewById(R.id.grid_view_city);
                    view.setTag(holder);
                } else {
                    holder = (CityViewHolder) view.getTag();
                }
        final HotCityGridAdapter cityGridAdapter = new HotCityGridAdapter(mContext);
        if(position == 0){
            List<String> list = new ArrayList<>();
            list.add("深圳");
            holder.letter.setText("定位城市");
            cityGridAdapter.setCities(list);

        }
                if (position >= 1) {
                    String currentLetter = cityLetters[position-1];
                    holder.letter.setText(currentLetter);
                    cityGridAdapter.setCities(list.get(position-1));
                }
        holder.gridView.setAdapter(cityGridAdapter);
        holder.gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                onCityClickListener.onCityClick(cityGridAdapter.getItem(position));
            }
        });
        return view;
    }

    public static class CityViewHolder {
        TextView letter;
        WrapHeightGridView gridView;
    }

    public void setOnCityClickListener(OnCityClickListener listener) {
        this.onCityClickListener = listener;
    }

    public interface OnCityClickListener {
        void onCityClick(String name);

        void onLocateClick();
    }
}
