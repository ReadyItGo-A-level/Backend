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
        String flavor = preference.getFlavor();
        Integer minPrice = Integer.parseInt(preference.getPrice().split(",")[0]);
        Integer maxPrice = Integer.parseInt(preference.getPrice().split(",")[1]);

        long minVolume = 0L, maxVolume = 0L;
        switch (preference.getVolume().intValue()) {
            case 1:
                maxVolume = 5;
            case 2:
                minVolume = 5;
                maxVolume = 10;
            case 3:
                minVolume = 10;
                maxVolume = 15;
            case 4:
                minVolume = 15;
                maxVolume = 18;
            case 5:
                minVolume = 18;
                maxVolume = 40;
        }

        long minSugar = 0L, maxSugar = 0L;
        switch (preference.getSugar().intValue()) {
            case 1:
                minSugar = 1;
                maxSugar = 1;
            case 2:
                minSugar = 1;
                maxSugar = 3;
            case 3:
                minSugar = 3;
                maxSugar = 3;
            case 4:
                minSugar = 3;
                maxSugar = 5;
            case 5:
                minSugar = 5;
                maxSugar = 5;
        }

        String resultList = alcoholRepository.findRecommend(typeArray, minVolume, maxVolume, minSugar, maxSugar, flavor, minPrice, maxPrice).toString();
        return resultList.substring(1).substring(0, resultList.length() - 2);
    }

    public Map<String, List<RecommendAlcoholDto>> findRecommendationAlcohol (Long userid) {
        Map<String, List<RecommendAlcoholDto>> result = new HashMap<>();
        Preference preference = preferenceRepository.findByUserId(userid);
        if (preference != null) {
            List<Long> IdArray = Arrays.stream(preference.getRecommendation().replaceAll(" ", "").split(",")).collect(Collectors.toList())
                    .stream().map(Long::parseLong).collect(Collectors.toList());
            List<String> TypeList = alcoholRepository.findDistinctType();

            List<RecommendAlcoholDto> allAlcohols = alcoholRepository.findRecommendsById(IdArray);
            if (allAlcohols.size() > 5) allAlcohols = allAlcohols.subList(0, 5);
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
                if (result.get(type).size() > 5) result.put(type, result.get(type).subList(0, 5));
            }
        }
        return result;
    }

    public List<PostResponseDto> findRecommendationPost (Long userid) {
        Preference preference = preferenceRepository.findByUserId(userid);
        List<PostResponseDto> result = postService.findByPreference(preference);

        if (result.size() > 5) return result.subList(0, 5);
        else return result;
    }

    public List<PostResponseDto> findRecommendationTopPost () {
        return  postService.findRecommendationTopPost();
    }
}
