package HelperClasses;

public class Interviewrequesthelper {

    private String firstname;
    private String interviewerId;

    public Interviewrequesthelper() {
    }

    public Interviewrequesthelper(String firstname, String interviewerId) {
        this.firstname = firstname;
        this.interviewerId = interviewerId;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(String interviewerId) {
        this.interviewerId = interviewerId;
    }
}
