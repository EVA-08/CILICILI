package cilicili.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 课程
 */
@Entity
public class Course {
    private Integer id;
    private String introduction;
    private String name;
    private User author;
    private Resource image;
    private Set<Lesson> lessonSet = new HashSet<>();
    private Set<User> registeredUserSet = new HashSet<>();
    private Set<Announcement> announcementSet = new HashSet<>();

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(columnDefinition = "text")
    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @OneToMany( mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Lesson> getLessonSet() {
        return lessonSet;
    }

    public void setLessonSet(Set<Lesson> lessonSet) {
        this.lessonSet = lessonSet;
    }

    @ManyToMany(mappedBy = "registeredCourseSet", fetch = FetchType.EAGER)
    public Set<User> getRegisteredUserSet() {
        return registeredUserSet;
    }

    public void setRegisteredUserSet(Set<User> registeredUserSet) {
        this.registeredUserSet = registeredUserSet;
    }

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    public Set<Announcement> getAnnouncementSet() {
        return announcementSet;
    }

    public void setAnnouncementSet(Set<Announcement> announcementSet) {
        this.announcementSet = announcementSet;
    }

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    public Resource getImage() {
        return image;
    }

    public void setImage(Resource image) {
        this.image = image;
    }

    /**
     * 增加一节课
     *
     * @param lesson 一节课
     */
    public void addLesson(Lesson lesson) {
        getLessonSet().add(lesson);
        lesson.setCourse(this);
    }
}
