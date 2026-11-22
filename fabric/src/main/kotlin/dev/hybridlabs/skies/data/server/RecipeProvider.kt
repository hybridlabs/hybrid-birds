package dev.hybridlabs.skies.data.server

import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.tag.HSItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.criterion.InventoryChangeTrigger
import net.minecraft.advancements.criterion.ItemPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.core.registries.Registries
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.data.recipes.RecipeProvider as VanillaRecipeProvider
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.AbstractCookingRecipe
import net.minecraft.world.item.crafting.CampfireCookingRecipe
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import net.minecraft.world.item.crafting.SmeltingRecipe
import net.minecraft.world.item.crafting.SmokingRecipe
import java.util.concurrent.CompletableFuture

class RecipeProvider(output: FabricDataOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    FabricRecipeProvider(output, lookupProvider) {
    override fun createRecipeProvider(
        registries: HolderLookup.Provider,
        output: RecipeOutput,
    ): VanillaRecipeProvider = GeneratedRecipes(registries, output)

    override fun getName(): String = "Recipes"

    private class GeneratedRecipes(
        registries: HolderLookup.Provider,
        output: RecipeOutput,
    ) : VanillaRecipeProvider(registries, output) {
        private val itemLookup = registries.lookupOrThrow(Registries.ITEM)

        override fun buildRecipes() {
            // misc recipes
            ShapelessRecipeBuilder.shapeless(itemLookup, RecipeCategory.FOOD, HSItems.TURDUCKEN.get(), 1)
                .requires(HSItems.TURKEY.get())
                .requires(HSItems.DUCK.get())
                .requires(Items.CHICKEN)
                .unlockedBy(
                    "has_turducken_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(
                        ItemPredicate.Builder.item().of(itemLookup, HSItemTags.TURDUCKEN_INGREDIENTS).build()
                    )
                )
                .save(output)

            // cooking recipes
            offerEggCookingRecipes(HSItemTags.EGGS, HSItems.COOKED_EGG.get(), 0.15f)

            offerCookingRecipes(HSItems.PUFFIN.get(), HSItems.COOKED_PUFFIN.get(), 0.15f)
            offerCookingRecipes(HSItems.DUCK.get(), HSItems.COOKED_DUCK.get(), 0.15f)
            offerCookingRecipes(HSItems.GOOSE.get(), HSItems.COOKED_GOOSE.get(), 0.15f)
            offerCookingRecipes(HSItems.TURKEY.get(), HSItems.COOKED_TURKEY.get(), 0.15f)
            offerCookingRecipes(HSItems.TURDUCKEN.get(), HSItems.COOKED_TURDUCKEN.get(), 0.15f)
        }

        private fun offerCookingRecipes(
            input: Item,
            result: Item,
            experience: Float,
        ) {
            offerCookingRecipe(
                "smelting",
                RecipeSerializer.SMELTING_RECIPE,
                ::SmeltingRecipe,
                200,
                Ingredient.of(input),
                result,
                experience,
                getHasName(input),
                has(input),
            )
            offerCookingRecipe(
                "smoking",
                RecipeSerializer.SMOKING_RECIPE,
                ::SmokingRecipe,
                100,
                Ingredient.of(input),
                result,
                experience,
                getHasName(input),
                has(input),
            )
            offerCookingRecipe(
                "campfire_cooking",
                RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                ::CampfireCookingRecipe,
                600,
                Ingredient.of(input),
                result,
                experience,
                getHasName(input),
                has(input),
            )
        }

        private fun offerEggCookingRecipes(
            inputTag: TagKey<Item>,
            result: Item,
            experience: Float,
        ) {
            val ingredient = Ingredient.of(itemLookup.getOrThrow(inputTag))
            offerCookingRecipe(
                "smelting",
                RecipeSerializer.SMELTING_RECIPE,
                ::SmeltingRecipe,
                200,
                ingredient,
                result,
                experience,
                "has_egg",
                has(inputTag),
            )
            offerCookingRecipe(
                "smoking",
                RecipeSerializer.SMOKING_RECIPE,
                ::SmokingRecipe,
                100,
                ingredient,
                result,
                experience,
                "has_egg",
                has(inputTag),
            )
            offerCookingRecipe(
                "campfire_cooking",
                RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
                ::CampfireCookingRecipe,
                600,
                ingredient,
                result,
                experience,
                "has_egg",
                has(inputTag),
            )
        }

        private fun <T : AbstractCookingRecipe> offerCookingRecipe(
            cooker: String,
            serializer: RecipeSerializer<T>,
            recipeFactory: AbstractCookingRecipe.Factory<T>,
            cookingTime: Int,
            input: Ingredient,
            result: Item,
            experience: Float,
            criterionName: String,
            criterion: net.minecraft.advancements.Criterion<*>,
        ) {
            val builder = SimpleCookingRecipeBuilder.generic(
                input,
                RecipeCategory.FOOD,
                result,
                experience,
                cookingTime,
                serializer,
                recipeFactory,
            ).unlockedBy(criterionName, criterion)

            builder.save(output, getItemName(result) + "_from_" + cooker)
        }
    }
}
