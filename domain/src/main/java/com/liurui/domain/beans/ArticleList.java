package com.liurui.domain.beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LiuRui on 2018/4/18.
 */

public class ArticleList {

    private int curPage;

    private int offset;

    private boolean over;

    private int pageCount;

    private int size;

    private int total;

    private List<ArticleListItemBean> datas = new ArrayList<>();

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public List<ArticleListItemBean> getDatas() {
        return datas;
    }

    public void setDatas(List<ArticleListItemBean> datas) {
        this.datas = datas;
    }
}
