package com.baby.babycareproductsshop.entity.review;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "t_review_pics")
public class ReviewPicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ireview_pic")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ireview", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private ReviewEntity review;

    @Column(name = "review_pic", length = 2500, nullable = false)
    private String reviewPictureUrl;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
