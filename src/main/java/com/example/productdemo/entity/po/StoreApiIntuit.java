package com.example.productdemo.entity.po;

public class StoreApiIntuit {
    private long seller_id;
    private String realm_id;
    private String auth_code;
    private String access_token;
    private String refresh_token;
    private long expires_in;
    private long x_refresh_token_expires_in;
    private String refresh_at;
    private String authorize_at;

    public StoreApiIntuit() {
    }

    public StoreApiIntuit(long seller_id, String realm_id, String auth_code, String access_token, String refresh_token, long expires_in, long x_refresh_token_expires_in, String refresh_at, String authorize_at) {
        this.seller_id = seller_id;
        this.realm_id = realm_id;
        this.auth_code = auth_code;
        this.access_token = access_token;
        this.refresh_token = refresh_token;
        this.expires_in = expires_in;
        this.x_refresh_token_expires_in = x_refresh_token_expires_in;
        this.refresh_at = refresh_at;
        this.authorize_at = authorize_at;
    }

    public long getSeller_id() {
        return seller_id;
    }

    public void setSeller_id(long seller_id) {
        this.seller_id = seller_id;
    }

    public String getRealm_id() {
        return realm_id;
    }

    public void setRealm_id(String realm_id) {
        this.realm_id = realm_id;
    }

    public String getAuth_code() {
        return auth_code;
    }

    public void setAuth_code(String auth_code) {
        this.auth_code = auth_code;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }

    public long getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(long expires_in) {
        this.expires_in = expires_in;
    }

    public long getX_refresh_token_expires_in() {
        return x_refresh_token_expires_in;
    }

    public void setX_refresh_token_expires_in(long x_refresh_token_expires_in) {
        this.x_refresh_token_expires_in = x_refresh_token_expires_in;
    }

    public String getRefresh_at() {
        return refresh_at;
    }

    public void setRefresh_at(String refresh_at) {
        this.refresh_at = refresh_at;
    }

    public String getAuthorize_at() {
        return authorize_at;
    }

    public void setAuthorize_at(String authorize_at) {
        this.authorize_at = authorize_at;
    }

    @Override
    public String toString() {
        return "StoreApiIntuit{" +
                "seller_id=" + seller_id +
                ", realm_id='" + realm_id + '\'' +
                ", auth_code='" + auth_code + '\'' +
                ", access_token='" + access_token + '\'' +
                ", refresh_token='" + refresh_token + '\'' +
                ", expires_in=" + expires_in +
                ", x_refresh_token_expires_in=" + x_refresh_token_expires_in +
                ", refresh_at=" + refresh_at +
                ", authorize_at=" + authorize_at +
                '}';
    }
}
