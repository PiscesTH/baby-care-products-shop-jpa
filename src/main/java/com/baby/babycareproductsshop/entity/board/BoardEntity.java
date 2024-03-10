package com.baby.babycareproductsshop.entity.board;

import com.baby.babycareproductsshop.entity.BaseEntity;
import com.baby.babycareproductsshop.entity.user.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@OnDelete(action = OnDeleteAction.CASCADE)
@Table(name = "t_board")
public class BoardEntity extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(columnDefinition = "BIGINT UNSIGNED")
    private Long iboard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "iuser", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    private UserEntity userEntity;

    @Column(nullable = false)
    private Integer boardCode;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(nullable = false, length = 2500)
    private String contents;

    @ToString.Exclude
    @OneToMany(mappedBy = "boardEntity", cascade = CascadeType.PERSIST)
    private List<BoardCommentEntity> boardCommentEntityList = new ArrayList<>();
}
