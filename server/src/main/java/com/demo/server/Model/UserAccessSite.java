package com.demo.server.Model;

import java.util.ArrayList;

public class UserAccessSite {

    private Long user_id;
    private ArrayList<String> listView;

    public UserAccessSite(Long user_id, ArrayList<String> listView) {
        this.user_id = user_id;
        this.listView = listView;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public ArrayList<String> getListView() {
        return listView;
    }

    public void setListView(ArrayList<String> listView) {
        this.listView = listView;
    }
}
