package com.alevel.backend.domain.alcohol;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AlcoholRepository extends JpaRepository<Alcohol, Long>, AlcoholRepositoryCustom {

    @Query(value = "select distinct t.id " +
                    "from ( " +
                        "select * from alcohol a where a.volume >= :minVolume and a.volume <= :maxVolume " +
                        "union all " +
                        "select * from alcohol a where  a.sugar <= :minSugar and a.sugar <= :maxSugar " +
                        "union all " +
                        "select * from alcohol a where a.flavor like concat('%', :flavor, '%') " +
                        "union all " +
                        "select * from alcohol a where a.price >= :minPrice and a.price <= :maxPrice " +
                    ") t " +
                    "where t.type in :typeArray " +
                    "group by t.id " +
                    "order by count(*) desc "
            , nativeQuery = true
    )
    List<Long> findRecommend(@Param("typeArray") String[] typeArray,
                                  @Param("minVolume") Long minVolume,
                                  @Param("maxVolume") Long maxVolume,
                                  @Param("minSugar") Long minSugar,
                                  @Param("maxSugar") Long maxSugar,
                                  @Param("flavor") String flavor,
                                  @Param("minPrice") Integer minPrice, @Param("maxPrice") Integer maxPrice);

    Alcohol findAllById(Long id);

    @Query(value="select distinct a.type from Alcohol a")
    List<String> findDistinctType();

    //search
    List<Alcohol> findByNameContaining(String keyword);
}
