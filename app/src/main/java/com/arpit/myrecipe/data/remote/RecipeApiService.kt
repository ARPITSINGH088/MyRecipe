package com.arpit.myrecipe.data.remote

import com.arpit.myrecipe.data.remote.dto.AddRecipeRequest
import com.arpit.myrecipe.data.remote.dto.RecipeDTO
import com.arpit.myrecipe.data.remote.dto.RecipesResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.contentType

class RecipeApiService(private val client: HttpClient) {

    suspend fun getAllRecipes(): RecipesResponse{
        return client.get(urlString = "${KtorClient.BASE_URL}recipes").body()

    }

    suspend fun getRecipeByID(id: Int): RecipeDTO{
        return client.get(urlString = "${KtorClient.BASE_URL}recipes/$id").body()
    }

    suspend fun addRecipe(request: AddRecipeRequest){
        client.post(urlString = "${KtorClient.BASE_URL}recipes/add"){
            contentType(ContentType.Application.Json)
            setBody(request)
        }
    }
}