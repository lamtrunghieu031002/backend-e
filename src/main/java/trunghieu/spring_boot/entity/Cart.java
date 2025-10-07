package trunghieu.spring_boot.entity;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "cart")
public class Cart {

    private Long id;
    @OneToMany
    private User user;

    @ManyToOne
    private Product product;
}
