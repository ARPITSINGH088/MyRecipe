package com.arpit.myrecipe.presentation.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.arpit.myrecipe.data.remote.KtorClient
import com.arpit.myrecipe.data.remote.RecipeApiService
import com.arpit.myrecipe.data.remote.dto.RecipeDTO
import com.arpit.myrecipe.data.repository.RecipeRepositoryImpl
import com.arpit.myrecipe.domain.repository.RecipeRepository
import kotlinx.coroutines.launch
import kotlin.collections.sorted


class HomeViewModel : ViewModel() {
    private val repository : RecipeRepository = RecipeRepositoryImpl(apiService = RecipeApiService(
        KtorClient.client))

    var isLoading by mutableStateOf(false)
        private set

    var errorMessage by mutableStateOf<String?>(null)
        private set

    var recipes by mutableStateOf(emptyList<RecipeDTO>())
        private set

    var categories by mutableStateOf(listOf("All"))
        private set


    var selectedCategory by mutableStateOf("All")
        private set

    private var allRecipes : List<RecipeDTO> = emptyList()

    fun fetchRecipes(){

        isLoading=false
        errorMessage=null

        viewModelScope.launch {

            try {
                val result = repository.getAllRecipes()
                allRecipes = result

                val cuisines = result.mapNotNull { it.cuisine }.distinct().sorted()
                categories = listOf("All") + cuisines

                applyFilters()
            }
            catch (e:Exception){
                errorMessage = e.message ?: "An unexpected error Occurred"
            } finally {
                isLoading=false
            }



        }


    }

    fun onCategorySelected(category: String){
        selectedCategory = category
        applyFilters()

    }

    private fun applyFilters(){
         recipes = if (selectedCategory=="All")allRecipes
        else allRecipes.filter { it.cuisine==selectedCategory }
    }
}