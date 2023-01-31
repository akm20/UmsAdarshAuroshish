package UserQuizManagement.demoUserQuiz.Entity;

import javax.persistence.*;

@Entity
@Table
public class Marks {
    @Id
    private int subject_id;
    private String subject_name;
    private int subject_mark;

//    @OneToMany
//    private User user;

    public Marks(int subject_id, String subject_name, int subject_mark, Users user) {
        this.subject_id = subject_id;
        this.subject_name = subject_name;
        this.subject_mark = subject_mark;
//        this.user = user;
    }

    public int getSubject_id() {
        return subject_id;
    }

    public void setSubject_id(int subject_id) {
        this.subject_id = subject_id;
    }

    public String getSubject_name() {
        return subject_name;
    }

    public void setSubject_name(String subject_name) {
        this.subject_name = subject_name;
    }

    public int getSubject_mark() {
        return subject_mark;
    }

    public void setSubject_mark(int subject_mark) {
        this.subject_mark = subject_mark;
    }

//    public User getUser() {
//        return user;
//    }
//
//    public void setUser(User user) {
//        this.user = user;
//    }
}
