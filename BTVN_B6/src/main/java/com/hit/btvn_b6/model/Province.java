package com.hit.btvn_b6.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "provinces")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Province {

    @Id
    private Long code;

    @Nationalized
    private String name;

    private String slug;

    private String type;

    @Nationalized
    private String name_with_type;


    public Province(Long code, String name, String slug, String type, String name_with_type) {
        this.code = code;
        this.name = name;
        this.slug = slug;
        this.type = type;
        this.name_with_type = name_with_type;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY, mappedBy = "province")
    @JsonIgnore
    private List<District> districts;

}
