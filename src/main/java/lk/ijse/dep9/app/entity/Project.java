package lk.ijse.dep9.app.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = "taskSet")
@EqualsAndHashCode(exclude = "taskSet")
public class Project implements SuperEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @JoinColumn(name = "username",referencedColumnName = "username",nullable = false)
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "project",fetch = FetchType.EAGER,cascade ={CascadeType.REMOVE})
    //oneto many and manytomany has fetch
    //fetch can setted by eager and lazy
    @Setter(AccessLevel.NONE)
    private Set<Task> taskSet = new HashSet<>();

    public Project(String name, User user) {
        this.name = name;
        this.user = user;
    }

}
