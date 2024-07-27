package pl.coderslab.domain;

import javax.persistence.*;

@Entity
@Table(name="categories")
public class Category {

    @Id@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id ;
    String name;

    public String getName() {
        return name;
    }

    public Long getId() {
        return id;
    }
}
