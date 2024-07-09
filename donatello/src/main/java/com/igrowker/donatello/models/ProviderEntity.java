package com.igrowker.donatello.models;

import com.igrowker.donatello.auth.entities.CustomUser;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Setter @Getter
@RequiredArgsConstructor @NoArgsConstructor
@Table(name = "Providers")
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
            referencedColumnName = "id"
    )
    private CustomUser User;

}
