package cilicili.domain;

import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 用户
 */
@Entity
public class User {
    private Integer id;
    private String username;
    private String password;
    private Identity identity;
    private String email;
    private Set<Course> registeredCourseSet = new HashSet<>();
    private Set<Notice> noticeSet = new HashSet<>();
    private Set<History> historySet = new HashSet<>();
    private Set<Course> createdCourseSet = new HashSet<>();

    public enum Identity {
        TEACHER, STUDENT
    }

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    public Identity getIdentity() {
        return identity;
    }

    public void setIdentity(Identity identity) {
        this.identity = identity;
    }

    @Column
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @NaturalId
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Column(nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(name = "user_course", joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "course_id")})
    public Set<Course> getRegisteredCourseSet() {
        return registeredCourseSet;
    }

    public void setRegisteredCourseSet(Set<Course> registeredCourseSet) {
        this.registeredCourseSet = registeredCourseSet;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<Notice> getNoticeSet() {
        return noticeSet;
    }

    public void setNoticeSet(Set<Notice> noticeSet) {
        this.noticeSet = noticeSet;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<History> getHistorySet() {
        return historySet;
    }

    public void setHistorySet(Set<History> historySet) {
        this.historySet = historySet;
    }

    @OneToMany(mappedBy = "author", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<Course> getCreatedCourseSet() {
        return createdCourseSet;
    }

    public void setCreatedCourseSet(Set<Course> createdCourseSet) {
        this.createdCourseSet = createdCourseSet;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", identity=" + identity +
                ", email='" + email + '\'' +
                ", registeredCourseSet=" + registeredCourseSet +
                '}';
    }

    /**
     * 增加一个课程
     * @param course 给定的课程
     */
    public void addRegisteredCourse(Course course) {
        registeredCourseSet.add(course);
        course.getRegisteredUserSet().add(this);
    }

    /**
     * 删除一个课程
     * @param course 给定的课程
     */
    public void removeRegisteredCourse(Course course) {
        registeredCourseSet.remove(course);
        course.getRegisteredUserSet().remove(this);
    }
}
