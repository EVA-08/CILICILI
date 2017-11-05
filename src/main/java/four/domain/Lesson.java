package four.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Lesson {
    private Integer id;
    private String name;
    private String description;
    private Set<Resource> resourceSet = new HashSet<>();
    private Video video;
    private Set<PublishedHomework> publishedHomeworkSet = new HashSet<>();
    private Set<Question> QuestionSet = new HashSet<>();
    private Course course;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JoinTable(name = "lesson_resource", joinColumns = {@JoinColumn(name = "lesson_id")},
    inverseJoinColumns = {@JoinColumn(name = "resource_id")})
    public Set<Resource> getResourceSet() {
        return resourceSet;
    }

    public void setResourceSet(Set<Resource> resourceSet) {
        this.resourceSet = resourceSet;
    }

    @OneToOne(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<PublishedHomework> getPublishedHomeworkSet() {
        return publishedHomeworkSet;
    }

    public void setPublishedHomeworkSet(Set<PublishedHomework> publishedHomeworkSet) {
        this.publishedHomeworkSet = publishedHomeworkSet;
    }

    @OneToMany(mappedBy = "lesson", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Question> getQuestionSet() {
        return QuestionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        QuestionSet = questionSet;
    }

    @Column(columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @ManyToOne
    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }
    /**
     * 增加一个资源
     * @param resource 资源对象
     */
    public void addResource(Resource resource) {
        resourceSet.add(resource);
    }
}

