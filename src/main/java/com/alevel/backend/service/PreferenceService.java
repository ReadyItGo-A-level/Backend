package com.alevel.backend.service;

import com.alevel.backend.dto.PostResponseDto;
import com.alevel.backend.domain.alcohol.AlcoholRepository;
import com.alevel.backend.domain.preference.Preference;
import com.alevel.backend.domain.preference.PreferenceRepository;
import com.alevel.backend.dto.PreferenceRequestDto;
import com.alevel.backend.dto.RecommendAlcoholDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class PreferenceService {

    private final PreferenceRepository preferenceRepository;
    private final AlcoholRepository alcoholRepository;
    private final PostService postService;

    @Autowired
    public PreferenceService(PreferenceRepository preferenceRepository, AlcoholRepository alcoholRepository, PostService postService) {
        this.preferenceRepository = preferenceRepository;
        this.alcoholRepository = alcoholRepository;
        this.postService = postService;
    }

    public Preference insert(Preference preference) {
        return preferenceRepository.save(preference);
    }

    public String getRecommendationId(PreferenceRequestDto preference) {

        String[] typeArray = preference.getType().split(",");
        Integer volume = preference.getVolume();
        Integer sugar = preference.getSugar();
        String flavor = preference.getFlavor();
        Integer minPrice = Integer.parseInt(preference.getPrice().split(",")[0]);
        Integer maxPrice = Integer.parseInt(preference.getPrice().split(",")[1]);

        String resultList = alcoholRepository.findRecommend(typeArray, volume, sugar, flavor, minPrice, maxPrice).toString();
        return resultList.substring(1).substring(0, resultList.length() -2);
    }

    public Map<String, Object> findRecommendationAlcohol (Long userid) {
        Map<String, Object> result = new HashMap<>();
        Preference preference = preferenceRepository.findByUserId(userid);
        if (preference != null) {
            List<Long> IdArray = Arrays.stream(preference.getRecommendation().replaceAll(" ", "").split(",")).collect(Collectors.toList())
                    .stream().map(Long::parseLong).collect(Collectors.toList());
            List<String> TypeList = alcoholRepository.findDistinctType();

            List<RecommendAlcoholDto> allAlcohols = alcoholRepository.findRecommendsById(IdArray);
            result.put("alcohols", allAlcohols);
            for (String type : TypeList) {
                List<RecommendAlcoholDto> typeAlcohol = alcoholRepository.findRecommendsByIdAndType(IdArray, type);
                switch (type) {
                    case "와인":
                        type = "wine";
                        break;
                    case "맥주":
                        type = "beer";
                        break;
                    case "전통주":
                        type = "sool";
                        break;
                    case "양주":
                        type = "liquor";
                        break;
                }
                result.put(type, typeAlcohol);
            }
        }
        return result;
    }

    public List<PostResponseDto> findRecommendationPost (Long userid) {
        List<PostResponseDto> result = null;
        String recommendation = preferenceRepository.findRecommendationByUserid(userid);
        if (recommendation != null) {
            String[] IdArray = recommendation.replaceAll(" ", "").split(",");
            String alcoholName;
            for (String id : IdArray) {
                alcoholName = alcoholRepository.findAllById(Long.parseLong(id)).getName();
                List<PostResponseDto> dto = postService.findByAlcoholName(alcoholName);

                if (dto != null) {
                    result.add(dto.get(0));
                }
            }
        }
        return result;
    }

    public List<PostResponseDto> findRecommendationTopPost () {
        return  postService.findRecommendationTopPost();
    }
}
