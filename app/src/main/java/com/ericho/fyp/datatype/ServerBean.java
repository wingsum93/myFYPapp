package com.ericho.fyp.datatype;

import android.support.annotation.DrawableRes;

/**
 * class to store server address and corresponse drawable resource int
 * Created by EricH on 2/12/2016.
 */

public class ServerBean {
    private String url;//
    private String displayName;//
    @DrawableRes
    private int resourceInteger;

    public ServerBean() {
    }

    public ServerBean(String displayName, String url, @DrawableRes int resourceInteger) {
        this.displayName = displayName;
        this.url = url;
        this.resourceInteger = resourceInteger;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    @DrawableRes
    public int getResourceInteger() {
        return resourceInteger;
    }

    public void setResourceInteger(@DrawableRes int resourceInteger) {
        this.resourceInteger = resourceInteger;
    }
}
