package cilicili.domain;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * 历史
 */
@Entity
public class History {
    private Integer id;
    private Timestamp createTime;
    private Type type;
    private String description;
    private String link;
    private User user;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    private void setId(Integer id) {
        this.id = id;
    }

    @Column
    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public enum Type {
        USER, //用户管理及行为记录
        COURSE, //课程管理
        LESSON, //进入某节课
        VIDEO, //播放视频
        SUBMITTED_HOMEWORK, //提交作业
        PUBLISHED_HOMEWORK, //发布作业
        QUESTION, //提出问题
        ANSWER, //回答问题
        BARRAGE, //发送弹幕

    }

    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @Column(columnDefinition = "text")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @ManyToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
