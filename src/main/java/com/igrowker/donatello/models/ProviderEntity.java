package com.igrowker.donatello.models;

import com.igrowker.donatello.auth.entities.CustomUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter @Getter
@NoArgsConstructor
@Table(name = "Providers")
@Entity
public class ProviderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "contact")
    private String contact;

    @Column(name = "address")
    private String address;

    @ManyToOne(
            targetEntity = CustomUser.class,
            fetch = FetchType.EAGER
    )
    @JoinColumn(
            name = "ID_User",
            referencedColumnName = "user_id"
    )
    private CustomUser user;

}