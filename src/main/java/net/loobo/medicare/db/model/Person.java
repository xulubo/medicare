package net.loobo.medicare.db.model;


import lombok.Builder;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;


import javax.persistence.*;
import java.util.List;

@Builder
@Entity
@Table(name = "person")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    @Id
    @GeneratedValue(generator = "string-id-generator")
    @GenericGenerator(name = "string-id-generator", strategy = "net.loobo.medicare.db.StringIdGenerator")
    private String id;
    private String firstName;
    private String lastName;

    // Optional: Reverse mapping
    @OneToMany(mappedBy = "person")
    private List<Prescription> prescriptions;
}
