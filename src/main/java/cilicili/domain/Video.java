package cilicili.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * 视频
 */
@Entity
public class Video {
    private Integer id;
    private String name;
    private String path;
    private Set<Barrage> barrageSet = new HashSet<>();
    private Lesson lesson;

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

    @Column
    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    @OneToMany(mappedBy = "video", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    public Set<Barrage> getBarrageSet() {
        return barrageSet;
    }

    public void setBarrageSet(Set<Barrage> barrageSet) {
        this.barrageSet = barrageSet;
    }

    @OneToOne
    public Lesson getLesson() {
        return lesson;
    }

    public void setLesson(Lesson lesson) {
        this.lesson = lesson;
    }

}
