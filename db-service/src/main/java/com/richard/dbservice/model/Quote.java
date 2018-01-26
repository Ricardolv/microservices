package com.richard.dbservice.model;

import lombok.*;

import javax.persistence.*;

@Data
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(name = "quote", catalog = "test")
public class Quote {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NonNull
    @Column(name = "user_name")
    private String userName;

    @NonNull
    private String quote;

}
