package HelperClasses;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Interviewerhelper implements Parcelable {
    private String interviewerfirstname;

    private UserHelperClass firstname;
    private String interviewerlastname;
    private String intervieweraddress;
    private String interviewerdob;
    private String interviewerage;
    public String interviewermobno;
    private String interviewerQualification;
    private String interviewerworkposition;

  //
    private String intervieweremail;
    private String interPw;

    private String interviewerId;
//    private String interviwerfeedback;




    public Interviewerhelper() {

    }

    public Interviewerhelper(String interviewerfirstname, String interviewerlastname, String intervieweraddress, String interviewerdob, String interviewerage, String interviewermobno, String interviewerQualification, String interviewerworkposition, String intervieweremail, String interPw) {
        this.interviewerfirstname = interviewerfirstname;
        this.interviewerlastname = interviewerlastname;
        this.intervieweraddress = intervieweraddress;
        this.interviewerdob = interviewerdob;
        this.interviewerage = interviewerage;
        this.interviewermobno = interviewermobno;
        this.interviewerQualification = interviewerQualification;
        this.interviewerworkposition = interviewerworkposition;
       // this.interviwerfeedback=interviwerfeedback;
        this.intervieweremail = intervieweremail;
        this.interPw = interPw;
     //   this.interviwerfeedback=interviwerfeedback;
    }

    public Interviewerhelper(UserHelperClass firstname)
    {
        this.firstname=firstname;
    }

    public Interviewerhelper(String interviewerfirstname,String interviewerworkposition)
    {
        this.interviewerfirstname=interviewerfirstname;
        this.interviewerworkposition=interviewerworkposition;
    }

    protected Interviewerhelper(Parcel in) {
        interviewerfirstname = in.readString();
       /* firstname = in.readParcelable(UserHelperClass.class.getClassLoader());*/
        interviewerlastname = in.readString();
        intervieweraddress = in.readString();
        interviewerdob = in.readString();
        interviewerage = in.readString();
        interviewermobno = in.readString();
        interviewerQualification = in.readString();
        interviewerworkposition = in.readString();
        intervieweremail = in.readString();
        interPw = in.readString();
        interviewerId = in.readString();
    }

    public static final Creator<Interviewerhelper> CREATOR = new Creator<Interviewerhelper>() {
        @Override
        public Interviewerhelper createFromParcel(Parcel in) {
            return new Interviewerhelper(in);
        }

        @Override
        public Interviewerhelper[] newArray(int size) {
            return new Interviewerhelper[size];
        }
    };

    public String getInterviewerfirstname() {
        return interviewerfirstname;
    }

    public void setInterviewerfirstname(String interviewerfirstname) {
        this.interviewerfirstname = interviewerfirstname;
    }

    public String getInterviewerlastname() {
        return interviewerlastname;
    }

    public void setInterviewerlastname(String interviewerlastname) {
        this.interviewerlastname = interviewerlastname;
    }

    public String getIntervieweraddress() {
        return intervieweraddress;
    }

    public void setIntervieweraddress(String intervieweraddress) {
        this.intervieweraddress = intervieweraddress;
    }

    public String getInterviewerdob() {
        return interviewerdob;
    }

    public void setInterviewerdob(String interviewerdob) {
        this.interviewerdob = interviewerdob;
    }

    public String getInterviewerage() {
        return interviewerage;
    }

    public void setInterviewerage(String interviewerage) {
        this.interviewerage = interviewerage;
    }

    public String getInterviewermobno() {
        return interviewermobno;
    }

    public void setInterviewermobno(String interviewermobno) {
        this.interviewermobno = interviewermobno;
    }

    public String getIntervieweremail() {
        return intervieweremail;
    }

    public void setIntervieweremail(String intervieweremail) {
        this.intervieweremail = intervieweremail;
    }

    public String getInterPw() {
        return interPw;
    }

    public void setInterPw(String interPw) {
        this.interPw = interPw;
    }

    public String getInterviewerQualification() {
        return interviewerQualification;
    }

    public void setInterviewerQualification(String interviewerQualification) {
        this.interviewerQualification = interviewerQualification;
    }

    public String getInterviewerworkposition() {
        return interviewerworkposition;
    }

    public void setInterviewerworkposition(String interviewerworkposition) {
        this.interviewerworkposition = interviewerworkposition;
    }


   /* public String getInterviwerfeedback() {
        return interviwerfeedback;
    }

    public void setInterviwerfeedback(String interviwerfeedback) {
        this.interviwerfeedback = interviwerfeedback;
    }
*/

    @Override
    public String toString() {
        return "Interviewerhelper{" +
                "interviewerfirstname='" + interviewerfirstname + '\'' +
                ", interviewerlastname='" + interviewerlastname + '\'' +
                ", intervieweraddress='" + intervieweraddress + '\'' +
                ", interviewerdob='" + interviewerdob + '\'' +
                ", interviewerage='" + interviewerage + '\'' +
                ", interviewermobno='" + interviewermobno + '\'' +
                ", intervieweremail='" + intervieweremail + '\'' +
                ", interPw='" + interPw + '\'' +
                ", interviewerQualification='" + interviewerQualification + '\'' +
                ", interviewerworkposition='" + interviewerworkposition + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(interviewerfirstname);
        dest.writeString(interviewerlastname);
        dest.writeString(intervieweremail);
        dest.writeString(intervieweraddress);
        dest.writeString(interviewerQualification);
        dest.writeString(interviewerworkposition);
    }
}
