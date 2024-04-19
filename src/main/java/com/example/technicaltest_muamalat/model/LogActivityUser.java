package com.example.technicaltest_muamalat.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.DynamicInsert;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "mt_log_activity_user", schema = "public")
@DynamicInsert
public class LogActivityUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

//    @NotBlank
    @Column(name = "create_date")
    private Date createDate;

    @NotNull
    @Column(name = "tipe_log_id")
    private int tipeLogId;

    @NotNull
    @Column(name = "user_id")
    private int userId;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "mt_tipe_log", joinColumns = @JoinColumn(name = "tipe_log_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private TipeLog tipeLog;

    @OneToOne(cascade = CascadeType.MERGE)
    @JoinTable(name = "mt_user", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "id"))
    private User user;
}
