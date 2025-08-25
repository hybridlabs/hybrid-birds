package dev.hybridlabs.birds.data.server

import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.tag.HybridBirdsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.data.recipes.FinishedRecipe
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
import net.minecraft.tags.TagKey
import net.minecraft.world.item.Item
import net.minecraft.world.item.Items
import net.minecraft.world.item.crafting.AbstractCookingRecipe
import net.minecraft.world.item.crafting.Ingredient
import net.minecraft.world.item.crafting.RecipeSerializer
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun buildRecipes(exporter: Consumer<FinishedRecipe>) {
        // misc recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HybridBirdsItems.TURDUCKEN.get(), 1)
            .requires(HybridBirdsItems.TURKEY.get())
            .requires(HybridBirdsItems.DUCK.get())
            .requires(Items.CHICKEN)
            .unlockedBy("has_turducken_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(HybridBirdsItemTags.TURDUCKEN_INGREDIENTS).build()))
            .save(exporter)

        // cooking recipes
        offerEggCookingRecipes(exporter, HybridBirdsItemTags.EGGS, HybridBirdsItems.COOKED_EGG.get(), 0.15f)

        offerCookingRecipes(exporter, HybridBirdsItems.DUCK.get(), HybridBirdsItems.COOKED_DUCK.get(), 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.GOOSE.get(), HybridBirdsItems.COOKED_GOOSE.get(), 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.TURKEY.get(), HybridBirdsItems.COOKED_TURKEY.get(), 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.TURDUCKEN.get(), HybridBirdsItems.COOKED_TURDUCKEN.get(), 0.15f)
    }

    private fun offerCookingRecipes(
        exporter: Consumer<FinishedRecipe>,
        input: Item,
        output: Item,
        experience: Float
    ) {
        simpleCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, 200, input, output, experience)
        simpleCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, 100, input, output, experience)
        simpleCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600, input, output, experience)
    }

    private fun offerEggCookingRecipes(
        exporter: Consumer<FinishedRecipe>,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float
    ) {
        offerEggCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING_RECIPE, 200, inputTag, output, experience)
        offerEggCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING_RECIPE, 100, inputTag, output, experience)
        offerEggCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING_RECIPE, 600, inputTag, output, experience)
    }

    private fun offerEggCookingRecipe(
        exporter: Consumer<FinishedRecipe>,
        cooker: String,
        serializer: RecipeSerializer<out AbstractCookingRecipe>,
        cookingTime: Int,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float
    ) {
        val builder = SimpleCookingRecipeBuilder
            .generic(Ingredient.of(inputTag), RecipeCategory.FOOD, output, experience, cookingTime, serializer)
            .unlockedBy("has_egg", has(inputTag))

        val recipeId = getItemName(output) + "_from_" + cooker
        builder.save(exporter, recipeId)
    }
}