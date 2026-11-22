package dev.hybridlabs.skies.data.server

import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.tag.HSItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput
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
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.Ingredient
import java.util.concurrent.CompletableFuture

class RecipeProvider(output: FabricPackOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
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
            val ingredient = Ingredient.of(input)
            val criterionName = getHasName(input)
            val criterion = has(input)
            offerCookingRecipe("smelting", ingredient, result, experience, 200, criterionName, criterion)
            offerCookingRecipe("smoking", ingredient, result, experience, 100, criterionName, criterion)
            offerCookingRecipe("campfire_cooking", ingredient, result, experience, 600, criterionName, criterion)
        }

        private fun offerEggCookingRecipes(
            inputTag: net.minecraft.tags.TagKey<Item>,
            result: Item,
            experience: Float,
        ) {
            val ingredient = Ingredient.of(itemLookup.getOrThrow(inputTag))
            val criterion = has(inputTag)
            offerCookingRecipe("smelting", ingredient, result, experience, 200, "has_egg", criterion)
            offerCookingRecipe("smoking", ingredient, result, experience, 100, "has_egg", criterion)
            offerCookingRecipe("campfire_cooking", ingredient, result, experience, 600, "has_egg", criterion)
        }

        private fun offerCookingRecipe(
            cooker: String,
            input: Ingredient,
            result: Item,
            experience: Float,
            cookingTime: Int,
            criterionName: String,
            criterion: net.minecraft.advancements.Criterion<*>,
        ) {
            val builder = when (cooker) {
                "smoking" -> SimpleCookingRecipeBuilder.smoking(
                    input, RecipeCategory.FOOD, result, experience, cookingTime
                )
                "campfire_cooking" -> SimpleCookingRecipeBuilder.campfireCooking(
                    input, RecipeCategory.FOOD, result, experience, cookingTime
                )
                else -> SimpleCookingRecipeBuilder.smelting(
                    input, RecipeCategory.FOOD, net.minecraft.world.item.crafting.CookingBookCategory.FOOD,
                    result, experience, cookingTime
                )
            }

            builder.unlockedBy(criterionName, criterion)
                .save(output, getItemName(result) + "_from_" + cooker)
        }
    }
}
