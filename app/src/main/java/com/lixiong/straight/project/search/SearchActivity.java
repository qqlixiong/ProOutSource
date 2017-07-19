package com.lixiong.straight.project.search;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.lixiong.straight.R;
import com.lixiong.straight.common.utils.Config;
import com.lixiong.straight.common.utils.Constant;
import com.lixiong.straight.common.utils.IntentUtil;
import com.lixiong.straight.common.utils.UIUtils;
import com.lixiong.straight.common.utils.ViewUtils;
import com.lixiong.straight.interfaces.IActivity;
import com.lixiong.straight.my.bean.ServiceCategory;
import com.zhy.view.flowlayout.FlowLayout;
import com.zhy.view.flowlayout.TagAdapter;
import com.zhy.view.flowlayout.TagFlowLayout;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import butterknife.Bind;
import butterknife.OnClick;

public class SearchActivity extends AppCompatActivity implements IActivity, TextView.OnEditorActionListener, TextWatcher {

    @Bind(R.id.et_search_a)
    EditText etSearchA;
    @Bind(R.id.search_flow_layout)
    TagFlowLayout searchFlowLayout;
    @Nullable
    @Bind(R.id.recently_search_flow_layout)
    TagFlowLayout recentlySearchFlowLayout;
    @Nullable
    @Bind(R.id.rl_delete)
    RelativeLayout rlDelete;
    private List<ServiceCategory> category;
    private boolean isServiceCategory;

    @Override
    public int getLayoutId() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        ViewUtils.showView(recentlySearchFlowLayout);
        etSearchA.addTextChangedListener(this);
        etSearchA.setOnEditorActionListener(this);
        category = Constant.searchCategory();
        initSearchFlowLayout();
        initRecentlySearchFlowLayout();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        initRecentlySearchFlowLayout();
    }

    //最近搜索
    private void initRecentlySearchFlowLayout() {
        ViewUtils.showView(recentlySearchFlowLayout);
        List<SearchText> searchTextList = null;
        try {
            searchTextList = SearchText.listAll(SearchText.class);
            final List<String> listTemp = new ArrayList<>();
            for (SearchText searchTextTemp : searchTextList) {
                String text = searchTextTemp.getSearchText();
                if (!listTemp.contains(text)) {
                    listTemp.add(text);
                }
            }
            recentlySearchFlowLayout.setAdapter(new TagAdapter<String>(listTemp) {
                @Override
                public View getView(FlowLayout parent, int position, String s) {
                    View view = UIUtils.inflate(R.layout.search_history_item);
                    TextView tvSearchHistory = (TextView) view.findViewById(R.id.tv_search_history);
                    tvSearchHistory.setText(s);
                    return view;
                }
            });

            recentlySearchFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
                @Override
                public void onSelected(Set<Integer> selectPosSet) {
                    isServiceCategory = false;
                    for(int index : selectPosSet){
                        String value = listTemp.get(index);
                        for(ServiceCategory serviceCategory : category){
                            if(TextUtils.equals(value,serviceCategory.getItemName())){
                                isServiceCategory = true;
                                startSerializable(serviceCategory);
                            }
                        }
                        if(!isServiceCategory){
                            start(value);
                        }
                    }
                }
            });
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    private void initSearchFlowLayout() {
        searchFlowLayout.setAdapter(new TagAdapter<ServiceCategory>(category) {
            @Override
            public View getView(FlowLayout parent, int position, ServiceCategory serviceCategory) {
                View view = UIUtils.inflate(R.layout.search_x_item);
                TextView tvSearch = (TextView) view.findViewById(R.id.tv_search_x);
                tvSearch.setText(serviceCategory.getItemName());
                return view;
            }
        });

        searchFlowLayout.setOnSelectListener(new TagFlowLayout.OnSelectListener() {
            @Override
            public void onSelected(Set<Integer> selectPosSet) {
                for(int index : selectPosSet){
                    ServiceCategory serviceCategory = category.get(index);
                    startSerializable(serviceCategory);
                }
            }
        });
    }

    private void startSerializable(ServiceCategory serviceCategory) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(Config.SEARCH_CATEGORY,serviceCategory);
        startActivity(bundle);
    }

    @OnClick({R.id.search_a_cancel, R.id.tv_search_clear})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.search_a_cancel:
                onBackPressed();
                break;
            case R.id.tv_search_clear:
                SearchText.deleteAll(SearchText.class);
                ViewUtils.goneView(recentlySearchFlowLayout);
                break;
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        //回车键
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            String searchContent = etSearchA.getText().toString();
            if (!TextUtils.isEmpty(searchContent)) {
                start(searchContent);
                clearEditText();
            }
        }
        return true;
    }

    private void start(String searchContent) {
        Bundle bundle = new Bundle();
        bundle.putString(Config.SEARCH, searchContent);
        startActivity(bundle);
    }

    private void startActivity(Bundle bundle) {
        IntentUtil.startActivity(SearchActivity.this, SearchResultActivity.class, bundle);
    }

    private void clearEditText() {
        etSearchA.setText("");
    }

    @OnClick(R.id.rl_delete)
    public void onViewClicked() {
        clearEditText();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if(!TextUtils.isEmpty(s)){
            ViewUtils.showView(rlDelete);
        }else {
            ViewUtils.goneView(rlDelete);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}
