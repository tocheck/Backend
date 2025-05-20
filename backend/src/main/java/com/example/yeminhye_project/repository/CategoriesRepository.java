package com.example.yeminhye_project.repository;

import com.example.yeminhye_project.entity.Categories;
import com.example.yeminhye_project.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CategoriesRepository extends JpaRepository<Categories, Long> {
    boolean existsByUsersAndCategoryName(Users userEntity, String CategoryName);

    Optional<Categories> findByCategoryName(String categoryName);

    List<Categories> findByUsers(Users userEntity);

    void deleteByUsersAndCategoryName(Users userEntity, String CategoryName);

    @Modifying
    @Query("UPDATE Categories c SET c.categoryName=:newCategoryName WHERE c.users=:userEntity AND c.categoryName=:categoryName")
    void updateCategoryNameByUsersAndCategoryName(@Param("userEntity") Users userEntity, @Param("categoryName") String categoryName, @Param("newCategoryName") String newCategoryName);

    @Modifying
    @Query("UPDATE Categories c SET c.hexCode=:newHexCode WHERE c.users=:userEntity AND c.categoryName=:categoryName")
    void updateHexCodeByUsersAndCategoryNameAndHexCode(@Param("userEntity") Users userEntity, @Param("categoryName") String categoryName, @Param("newHexCode") String newHexCode);
}
