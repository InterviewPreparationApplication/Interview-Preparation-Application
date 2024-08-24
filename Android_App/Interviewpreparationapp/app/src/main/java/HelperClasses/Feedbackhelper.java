package HelperClasses;

public class Feedbackhelper {

    private String userId;
    private Object interviewername;
    private String feedback;

    private String Feedback;

    public Feedbackhelper() {
    }

    public Feedbackhelper( Object interviewername, String feedback) {
        //this.userId = userId;
        this.interviewername = interviewername;
        this.feedback = feedback;
    }

    public Feedbackhelper(String Feedback) {
        this.Feedback=Feedback;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Object getInterviewername() {
        return interviewername;
    }

    public void setInterviewername(String interviewername) {
        this.interviewername = interviewername;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    @Override
    public String toString() {
        return "Feedbackhelper{" +
                "userId='" + userId + '\'' +
                ", interviewername='" + interviewername + '\'' +
                ", feedback='" + feedback + '\'' +
                '}';
    }
}
