package four.domain;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
public class Notice {
    private Integer id;
    private String content;
    private Source source;
    private Timestamp createTime;
    private String link;
    private Seen seen;
    private User user;

    public enum Source {
        COURSE, //课程通知，包括课程公告，课程变更，作业发布
        HOMEWORK, //作业被批改
        ANSWER, //问题被回答
    }

    public enum Seen {
        SEEN, UNSEEN
    }

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

    @Enumerated(EnumType.STRING)
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Column
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    @Enumerated(EnumType.STRING)
    public Seen getSeen() {
        return seen;
    }

    public void setSeen(Seen seen) {
        this.seen = seen;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
