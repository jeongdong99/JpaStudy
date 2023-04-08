package jpabook.start;

import com.mysql.cj.xdevapi.AddResult;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.criteria.Fetch;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Member extends BaseEntity{
    @Id @GeneratedValue
    @Column(name = "MEMBER_ID")
    private Long id;
    private String username;
    private int age;
    @Embedded
    private Period workPeriod;

    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Embedded
    private Address homeAddress;


    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="TEAM_IDs")
    private Team team;

    public Member(String username, Integer age, RoleType roleType){
        this.username = username;
        this.age = age;
        this.roleType = roleType;
    }

    @Override
    public String toString() {
        return "Member{" +
                "username='" + username + '\'' +
                ", age=" + age +
                ", roleType=" + roleType +
                '}';
    }

    public void setTeam(Team team) {

        this.team = team;
        team.getMembers().add(this);
    }
}
