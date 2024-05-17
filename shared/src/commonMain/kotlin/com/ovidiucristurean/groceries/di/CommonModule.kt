package com.ovidiucristurean.groceries.di

import com.ovidiucristurean.groceries.data.RecipeRepositoryImpl
import com.ovidiucristurean.groceries.data.createDatabase
import com.ovidiucristurean.groceries.domain.RecipeRepository
import com.ovidiucristurean.groceries.ui.screen.addrecipe.AddRecipeScreenModel
import com.ovidiucristurean.groceries.ui.screen.editrecipe.EditRecipeScreenModel
import com.ovidiucristurean.groceries.ui.screen.home.HomeScreenViewModel
import com.ovidiucristurean.groceries.ui.screen.shopping.ShoppingScreenModel
import org.koin.dsl.module

val commonModule = module {

    single {
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

    factory { parameters ->
        ShoppingScreenModel(
            recipeId = parameters.get(),
            recipeRepository = get(),
            clipboardSaver = get()
        )
    }

    factory { parameters ->
        EditRecipeScreenModel(
            recipeId = parameters.get(),
            recipeRepository = get()
        )
    }
}
