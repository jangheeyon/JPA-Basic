package hellojpa;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
//@Table(name="Member")
public class Member {
    @Id
    private Long id;
    @Column(name = "name")
    private String username;
    private Integer age;
    //@Enumerated(EnumType.ORDINAL)을 쓰면 별도의 데이터 타입이 추가되면 순서에서 문제가 됨
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    //날짜 타입 최신 버전은 @Temporal 쓰지 않아도 됨
    //private LocalDate testDate;
    //private LocalDateTime testDateTime;
    //날짜 타입 과거 버전
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;
    @Lob    //VARCHAR 보다 큰 컨텐츠
    private String description;

    public Member() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public RoleType getRoleType() {
        return roleType;
    }

    public void setRoleType(RoleType roleType) {
        this.roleType = roleType;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public void setLastModifiedDate(Date lastModifiedDate) {
        this.lastModifiedDate = lastModifiedDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
