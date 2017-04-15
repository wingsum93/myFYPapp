package com.ericho.fyp.datatype.internal;

/**
 * Created by steve_000 on 15/4/2017.
 * for project myFYPapp
 * package name com.ericho.fyp.datatype.internal
 */

public class StorePref {

    public static StorePref Server = new Builder().setPrefenceName("pref").setKey("server_address").build();

    StorePref(Builder builder) {
        this.preferenceName = builder.preferenceName;
        this.key = builder.key;
    }

    private String preferenceName;
    private String key;

    public String getPreferenceName() {
        return preferenceName;
    }

    public String getKey() {
        return key;
    }

    private static class Builder {
        private String preferenceName;
        private String key;

        private Builder setPrefenceName(String value) {
            preferenceName = value;
            return this;
        }

        private Builder setKey(String value) {
            key = value;
            return this;
        }

        private StorePref build() {
            return new StorePref(this);
        }
    }
}
