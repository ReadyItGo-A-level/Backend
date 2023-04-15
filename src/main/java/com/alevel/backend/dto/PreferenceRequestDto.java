package com.alevel.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class PreferenceRequestDto {
    private String type;
    private Long volume;
    private Long sugar;
    private String flavor;
    private String price;
}
