package com.example.a5120app;

import android.os.Parcel;
import android.os.Parcelable;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

public class UserRest implements Parcelable {
    private String signUpDate;
    private Integer userId;
    private String name;
    private String dob;
    private String address;
    private String postcode;
    private String weight;
    private String height;

    public UserRest( String signUpDate, Integer userId, String name, String dob, String address, String postcode, String weight, String height) {
        this.signUpDate = signUpDate;
        this.userId = userId;
        this.name = name;
        this.dob = dob;
        this.address = address;
        this.postcode = postcode;
        this.weight = weight;
        this.height = height;
    }

    public UserRest(JsonArray jsonArray) {
        this.userId = jsonArray.get(0).getAsJsonObject().get("userId").getAsInt();
        this.name = jsonArray.get(0).getAsJsonObject().get("userName").getAsString();
        this.address = jsonArray.get(0).getAsJsonObject().get("address").getAsString();
        this.dob = jsonArray.get(0).getAsJsonObject().get("dob").getAsString();
        this.height = jsonArray.get(0).getAsJsonObject().get("height").getAsString();
        this.weight = jsonArray.get(0).getAsJsonObject().get("weight").getAsString();
        this.postcode = jsonArray.get(0).getAsJsonObject().get("postcode").getAsString();
    }

    protected UserRest(Parcel in) {
        signUpDate = in.readString();
        if (in.readByte() == 0) {
            userId = null;
        } else {
            userId = in.readInt();
        }
        name = in.readString();
        dob = in.readString();
        address = in.readString();
        postcode = in.readString();
        weight = in.readString();
        height = in.readString();
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
        dest.writeString(signUpDate);
        dest.writeString(name);
        dest.writeString(dob);
        dest.writeString(address);
        dest.writeString(postcode);
        dest.writeString(weight);
        dest.writeString(height);
    }

    //{"address":"Melbourne cbd","dob":"20000101","height":1.80,"postcode":"3000","userId":1,"userName":"test1","weight":70.00}
    @Override
    public String toString() {
        return "{" +
                "\"address\":" + "\"" + address + "\"" +
                ",\"dob\":" + "\"" + dob + "\"" +
                ",\"height\":" + height +
                ",\"userId\":" +  "\"" + userId + "\"" +
                ",\"signUpDate\":" + "\"" + signUpDate + "\"" +
                ",\"postcode\":" + "\"" + postcode + "\"" +
                ",\"userName\":" + "\"" + name + "\"" +
                ",\"weight\":" + weight +
                "}";
    }

    public String getSignUpDate() {
        return signUpDate;
    }

    public void setSignUpDate(String signUpDate) {
        this.signUpDate = signUpDate;
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

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }


}
