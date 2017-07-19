package com.lixiong.straight.project.search;

import com.orm.SugarRecord;

/**
 * 保存搜索的文本对象
 * Created by john on 2017/6/10.
 */

public class SearchText extends SugarRecord<SearchText>{
    private String searchText;

    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    @Override
    public String toString() {
        return "SearchText{" +
                "searchText='" + searchText + '\'' +
                '}';
    }
}
