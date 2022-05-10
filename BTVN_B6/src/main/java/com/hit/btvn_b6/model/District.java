package com.hit.btvn_b6.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;

@Entity
@Table(name = "districts")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class District {

    @Id
    private Long code;

    @Nationalized
    private String name;

    private String slug;

    private String type;

    @Nationalized
    private String name_with_type;

    @Nationalized
    private String path;

    @Nationalized
    private String path_with_type;

    private Long parent_code;

    @ManyToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinColumn(name = "province_id")
    private Province province;


}
