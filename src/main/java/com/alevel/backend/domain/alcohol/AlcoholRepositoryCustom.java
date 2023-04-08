package com.alevel.backend.domain.alcohol;

import com.alevel.backend.dto.AlcoholResponseDto;
import com.alevel.backend.dto.RecommendAlcoholDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface AlcoholRepositoryCustom {

    Page<AlcoholResponseDto> findAllAlcohol(String type, String category, Pageable pageable, Long userId);

    List<RecommendAlcoholDto> findRecommendsById(List<Long> idArray);

    List<RecommendAlcoholDto> findRecommendsByIdAndType(List<Long> idArray, String type);

}
