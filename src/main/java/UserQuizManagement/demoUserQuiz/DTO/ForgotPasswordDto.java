package UserQuizManagement.demoUserQuiz.DTO;

public class ForgotPasswordDto {
    private String userEmail;
    private String question;
    private String answer;
    private String newPassword;

    public ForgotPasswordDto(String userEmail, String question, String answer,String newPassword) {
        this.userEmail = userEmail;
        this.question = question;
        this.answer = answer;
        this.newPassword=newPassword;
    }
     public ForgotPasswordDto(){

     }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
