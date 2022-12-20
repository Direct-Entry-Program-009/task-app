package lk.ijse.dep9.app.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements SuperEntity {
    @Id
    private String username;
    @Column(name = "full_name",nullable = false)
    private String fullName;
    @Column(nullable = false)
    private String password;
}
