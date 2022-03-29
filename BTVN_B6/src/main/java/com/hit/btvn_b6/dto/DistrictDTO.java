package com.hit.btvn_b6.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DistrictDTO {
    private Long code;
    private String name;
    private String type;
    private String name_with_type;
    private String path;
    private String path_with_type;
    private Long parent_code;
}
