package com.ovidiucristurean.groceries.di

import com.ovidiucristurean.groceries.GroceriesDatabase
import com.ovidiucristurean.groceries.data.RecipeRepositoryImpl
import com.ovidiucristurean.groceries.data.createDatabase
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.ui.addrecipescreen.AddRecipeScreenModel
import com.ovidiucristurean.groceries.ui.homescreen.HomeScreenViewModel
import org.koin.dsl.module

val commonModule = module {

    single<GroceriesDatabase> {
        createDatabase(
            driverFactory = get()
        )
    }

    single<RecipeRepository> {
        RecipeRepositoryImpl(
            groceriesDatabase = get()
        )
    }

    factory {
        AddRecipeScreenModel(
            recipeRepository = get()
        )
    }

    factory {
        HomeScreenViewModel(
            recipeRepository = get()
        )
    }
}