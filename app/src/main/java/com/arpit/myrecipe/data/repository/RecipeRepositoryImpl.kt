package com.arpit.myrecipe.data.repository

import com.arpit.myrecipe.data.remote.RecipeApiService
import com.arpit.myrecipe.data.remote.dto.RecipeDTO
import com.arpit.myrecipe.domain.repository.RecipeRepository

class RecipeRepositoryImpl(private val apiService: RecipeApiService): RecipeRepository {

    override suspend fun getAllRecipes(): List<RecipeDTO> {
                return apiService.getAllRecipes().recipes
    }
    override suspend fun getRecipeById(id: Int): RecipeDTO {
        return apiService.getRecipeByID(id)
    }

}
