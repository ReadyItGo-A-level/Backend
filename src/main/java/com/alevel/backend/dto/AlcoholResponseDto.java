package com.alevel.backend.dto;

import lombok.Getter;

@Getter
public class AlcoholResponseDto {
    private final Long id;
    private final String name;
    private final String volume;
    private final String size;
    private final Integer price;
    private final String image;

    private final boolean isScraped;

    public AlcoholResponseDto(Long id, String name, String volume, String size, Integer price, String image, boolean isScraped) {
        this.id = id;
        this.name = name;
        this.volume = volume;
        this.size = size;
        this.price = price;
        this.image = image;
        this.isScraped = isScraped;
    }

}
