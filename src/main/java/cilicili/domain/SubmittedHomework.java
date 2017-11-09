package cilicili.domain;

import javax.persistence.*;
import java.util.Set;

/**
 * 提交的作业
 */
@Entity
public class SubmittedHomework {
    private Integer id;
    private User teacher;
    private User student;
    private String content;
    private Set<Resource> studentResourceSet;
    private Set<Resource> teacherResourceSet;
    private String comment;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @ManyToOne
    public User getTeacher() {
        return teacher;
    }

    public void setTeacher(User teacher) {
        this.teacher = teacher;
    }

    @ManyToOne
    public User getStudent() {
        return student;
    }

    public void setStudent(User student) {
        this.student = student;
    }

    @Column
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "submitted_homework_student_resource", joinColumns = {@JoinColumn(name = "submitted_homework_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    public Set<Resource> getStudentResourceSet() {
        return studentResourceSet;
    }

    public void setStudentResourceSet(Set<Resource> studentResourceSet) {
        this.studentResourceSet = studentResourceSet;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinTable(name = "submitted_homework_teacher_resource", joinColumns = {@JoinColumn(name = "submitted_homework_id")},
            inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    public Set<Resource> getTeacherResourceSet() {
        return teacherResourceSet;
    }

    public void setTeacherResourceSet(Set<Resource> teacherResourceSet) {
        this.teacherResourceSet = teacherResourceSet;
    }

    @Column(columnDefinition = "text")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
