package com.JustRent.models;

import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Table(name = "User")
public class User {

    @jakarta.persistence.Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @Column (name = "name")
    private String name;

    @Column (name = "surname")
    private String surname;
}
