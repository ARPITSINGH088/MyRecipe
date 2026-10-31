package com.arpit.myrecipe.domain.repository

import com.arpit.myrecipe.data.remote.dto.RecipeDTO

interface RecipeRepository {

    suspend fun getAllRecipes(): List<RecipeDTO>

    suspend fun getRecipeById(id:Int): RecipeDTO
}