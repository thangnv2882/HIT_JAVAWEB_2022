package com.hitbtvn_b5.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "URLs")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class URL {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long URLId;

    private String originalURL;

    private String shortenURL;

    @CreationTimestamp
    private Timestamp created;

}
