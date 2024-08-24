package HelperClasses;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class UserHelperClass implements Parcelable {
    //firstname,lastname,mobileno,address,dob,qualification,email,password,repassword
    private String firstname;
    private String lastname;
    private String mobileno;
    private String address;
    private String dob;
    private String qualification;

    private String email;

    private String password;
    private String repassword;

    private String male;
    private String female;

    private String time;
    private String date;

    private String userId;

    public UserHelperClass() {

    }

    public UserHelperClass(String firstname, String lastname, String mobileno, String address, String dob,String qualification, String email, String password, String repassword,String time,String date) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.mobileno = mobileno;
        this.address = address;
        this.dob = dob;
        this.male=male;
        this.female=female;
        this.qualification = qualification;
        this.email = email;
        this.password = password;
        this.repassword = repassword;
        this.time=time;
        this.date=date;
    }

    public UserHelperClass(String firstname)
    {
        this.firstname=firstname;
    }

    protected UserHelperClass(Parcel in) {
        firstname = in.readString();
        lastname = in.readString();
        mobileno = in.readString();
        address = in.readString();
        dob = in.readString();
        qualification = in.readString();
        email = in.readString();
        password = in.readString();
        repassword = in.readString();
        male = in.readString();
        female = in.readString();
        time = in.readString();
        date = in.readString();
        userId = in.readString();
    }

    public static final Creator<UserHelperClass> CREATOR = new Creator<UserHelperClass>() {
        @Override
        public UserHelperClass createFromParcel(Parcel in) {
            return new UserHelperClass(in);
        }

        @Override
        public UserHelperClass[] newArray(int size) {
            return new UserHelperClass[size];
        }
    };

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getMale() {
        return male;
    }

    public void setMale(String male) {
        this.male = male;
    }

    public String getFemale() {
        return female;
    }

    public void setFemale(String female) {
        this.female = female;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRepassword() {
        return repassword;
    }

    public void setRepassword(String repassword) {
        this.repassword = repassword;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "UserHelperClass{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", mobileno='" + mobileno + '\'' +
                ", address='" + address + '\'' +
                ", dob='" + dob + '\'' +
                ", qualification='" + qualification + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", repassword='" + repassword + '\'' +
                ", male='" + male + '\'' +
                ", female='" + female + '\'' +
                ", time='" + time + '\'' +
                ", date='" + date + '\'' +
                '}';
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(firstname);
        dest.writeString(lastname);
        dest.writeString(email);
        dest.writeString(address);
        dest.writeString(qualification);
    }
}
