package HelperClasses;

public class InterviwerDataSubjectvise {
    private String interviewerfirstname;
    private String interviewerworkposition;

    public InterviwerDataSubjectvise() {
    }

    public InterviwerDataSubjectvise(String interviewerfirstname, String interviewerworkposition) {
        this.interviewerfirstname = interviewerfirstname;
        this.interviewerworkposition = interviewerworkposition;
    }

    public String getInterviewerfirstname() {
        return interviewerfirstname;
    }

    public void setInterviewerfirstname(String interviewerfirstname) {
        this.interviewerfirstname = interviewerfirstname;
    }

    public String getInterviewerworkposition() {
        return interviewerworkposition;
    }

    public void setInterviewerworkposition(String interviewerworkposition) {
        this.interviewerworkposition = interviewerworkposition;
    }

    @Override
    public String toString() {
        return "InterviwerDataSubjectvise{" +
                "interviewerfirstname='" + interviewerfirstname + '\'' +
                ", interviewerworkposition='" + interviewerworkposition + '\'' +
                '}';
    }
}
