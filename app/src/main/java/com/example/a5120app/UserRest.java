package com.example.a5120app;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UserRest implements Parcelable {
    private Integer userId;
    private String name;
    private String address;
    private String passwordHash;


    public UserRest( String passwordHash, Integer userId, String name, String address) {
        this.userId = userId;
        this.name = name;
        this.passwordHash = passwordHash;
    }

    public UserRest(JsonArray jsonArray) {
        this.userId = jsonArray.get(0).getAsJsonObject().get("userId").getAsInt();
        this.name = jsonArray.get(0).getAsJsonObject().get("userName").getAsString();
        this.passwordHash = jsonArray.get(0).getAsJsonObject().get("passwordHash").getAsString();
    }

    protected UserRest(Parcel in) {
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        name = in.readString();
        address = in.readString();
    }

    public static Creator<UserRest> getCREATOR() {
        return CREATOR;
    }

    public static final Creator<UserRest> CREATOR = new Creator<UserRest>() {
        @Override
        public UserRest createFromParcel(Parcel in) {
            return new UserRest(in);
        }

        @Override
        public UserRest[] newArray(int size) {
            return new UserRest[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        if (userId == null) {
            dest.writeByte((byte) 0);
        } else {
            dest.writeByte((byte) 1);
            dest.writeInt(userId);
        }
        dest.writeString(name);
        dest.writeString(address);
    }

//    {
//        "USER_ID": 4,
//            "USER_NAME": "test4",
//            "PASSWORD_HASH": "bcb15f821479b4d5772bd0ca866c00ad5f926e3580720659cc80d39c9d09802a"
//    }
    @Override
    public String toString() {
        return "{" +
                "\"userId\":" +  "\"" + userId + "\"" +
                ",\"userName\":" + "\"" + name + "\"" +
                ",\"password\":" + passwordHash +
                "}";
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }


}
