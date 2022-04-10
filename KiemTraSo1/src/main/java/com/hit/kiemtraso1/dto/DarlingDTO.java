package com.hit.kiemtraso1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Nationalized;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DarlingDTO {


    @Nationalized
    private String name;

    private String phone;
    private String email;

    @Nationalized
    private String favorite;

    private Long status;

}
