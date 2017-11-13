package cilicili.domain;

import javax.persistence.*;

/**
 * 信息项，通过类别和描述来保存小份信息，减少数据库表的数目
 */
@Entity
public class Info {
    private Type type;
    private Integer id;
    private String description;

    @Enumerated(EnumType.STRING)
    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    @GeneratedValue
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public enum Type {
        EDUCATION, TEACHING, AWARD, APHORISM
    }
}
