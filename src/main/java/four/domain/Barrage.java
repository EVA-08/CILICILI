package four.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Barrage {

    private Integer id;
    private String content;
    private Timestamp createTime;
    private User author;
    private Timestamp videoTime;
    private Video video;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @Column(columnDefinition = "text")
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Column
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @ManyToOne
    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    @Column
    public Timestamp getVideoTime() {
        return videoTime;
    }

    public void setVideoTime(Timestamp videoTime) {
        this.videoTime = videoTime;
    }

    @ManyToOne
    public Video getVideo() {
        return video;
    }

    public void setVideo(Video video) {
        this.video = video;
    }
}
