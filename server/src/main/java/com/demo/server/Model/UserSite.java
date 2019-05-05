package com.demo.server.Model;

public class UserSite {

    private Long user_id;
    private Long site_id;
    private String username;
    private String site_name;
    private String url;

    public UserSite(Long user_id, Long site_id, String username, String site_name, String url) {
        this.user_id = user_id;
        this.site_id = site_id;
        this.username = username;
        this.site_name = site_name;
        this.url = url;
    }

    public Long getUser_id() {
        return user_id;
    }

    public void setUser_id(Long user_id) {
        this.user_id = user_id;
    }

    public Long getSite_id() {
        return site_id;
    }

    public void setSite_id(Long site_id) {
        this.site_id = site_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSite_name() {
        return site_name;
    }

    public void setSite_name(String site_name) {
        this.site_name = site_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
