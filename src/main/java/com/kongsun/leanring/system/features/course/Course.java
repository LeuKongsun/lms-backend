package com.kongsun.leanring.system.features.course;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.kongsun.leanring.system.auditing.AuditingEntity;
import com.kongsun.leanring.system.features.attendance.Attendance;
import com.kongsun.leanring.system.features.category.Category;
import com.kongsun.leanring.system.features.enroll.Enroll;
import com.kongsun.leanring.system.features.schedule.Schedule;
import com.kongsun.leanring.system.features.teacher.Teacher;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "courses")
public class Course extends AuditingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cou_id")
    private Long id;

    @Column(
            name = "cou_name",
            nullable = false,
            length = 50
    )
    private String name;

    @Column(
            name = "cou_description"
    )
    private String description;

    @Column(
            name = "cou_price",
            nullable = false
    )
    private BigDecimal price = BigDecimal.ZERO;

    @Column(
            name = "cou_discount"
    )
    private BigDecimal discount = BigDecimal.ZERO;

    @Column(
            name = "cou_image"
    )
    private String image;

    @ManyToOne
    @JoinColumn(
            name = "cat_id"
    )
    private Category category;

    @ManyToOne
    @JoinColumn(name = "tea_id")
    private Teacher teacher;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonIgnore
    private List<Schedule> schedules;


    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonIgnore
    private List<Attendance> attendances;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL,  orphanRemoval = true)
    @JsonIgnore
    private List<Enroll> enrolls;


    public BigDecimal priceAfterDiscount() {
        if (discount != null && discount.compareTo(BigDecimal.ZERO) > 0) {
            BigDecimal discountAmount = price.multiply(discount).divide(BigDecimal.valueOf(100));
            return price.subtract(discountAmount);
        }
        return price;
    }

}
