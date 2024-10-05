package com.kongsun.leanring.system.features.category;

import com.kongsun.leanring.system.auditing.AuditingEntity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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

}
