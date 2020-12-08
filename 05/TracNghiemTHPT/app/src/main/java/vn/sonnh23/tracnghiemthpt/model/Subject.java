package vn.sonnh23.tracnghiemthpt.model;

import com.google.gson.annotations.SerializedName;

public class Subject {
    @SerializedName("name")
    public String mName;

    @SerializedName("icon")
    public String mIcon;

    @SerializedName("src_exam")
    public String mSrcExam;

    public Subject(String name, String icon, String srcExam) {
        this.mName = name;
        this.mIcon = icon;
        this.mSrcExam = srcExam;
    }
}
