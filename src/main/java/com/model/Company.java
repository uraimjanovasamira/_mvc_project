package com.model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "companies")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String companyName;
    String locatedCountry;

    @ManyToOne
    Course course;






}
