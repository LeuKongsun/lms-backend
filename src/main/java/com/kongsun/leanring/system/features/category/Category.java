package com.kongsun.leanring.system.features.category;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.features.course.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "categories")
public class Category extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")
    private Long id;

    @Column(
            name = "cat_name",
            nullable = false,
            length = 50
    )
    private String name;

    @Column(
            name = "cat_description",
            length = 30
    )
    private String description;


    @OneToMany(
            mappedBy = "category",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @JsonIgnore
    private List<Course> courses;
}
