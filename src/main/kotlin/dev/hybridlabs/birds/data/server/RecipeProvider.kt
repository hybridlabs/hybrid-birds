package dev.hybridlabs.birds.data.server

import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.tag.HybridBirdsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.data.server.recipe.CookingRecipeJsonBuilder
import net.minecraft.data.server.recipe.RecipeExporter
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.recipe.*
import net.minecraft.recipe.book.RecipeCategory
import net.minecraft.registry.RegistryWrapper
import net.minecraft.registry.tag.TagKey
import java.util.concurrent.CompletableFuture

class RecipeProvider(
    output: FabricDataOutput,
    registriesFuture: CompletableFuture<RegistryWrapper.WrapperLookup>?
) : FabricRecipeProvider(output, registriesFuture) {
    override fun generate(exporter: RecipeExporter) {
        // misc recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridBirdsItems.TURDUCKEN, 1)
            .input(HybridBirdsItems.TURKEY)
            .input(HybridBirdsItems.DUCK)
            .input(Items.CHICKEN)
            .criterion("has_turducken_ingredient", InventoryChangedCriterion.Conditions.items(
                ItemPredicate.Builder.create().tag(HybridBirdsItemTags.TURDUCKEN_INGREDIENTS).build()))
            .offerTo(exporter)

        // cooking recipes
        offerEggCookingRecipes(exporter, HybridBirdsItemTags.EGGS, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerCookingRecipes(exporter, HybridBirdsItems.DUCK, HybridBirdsItems.COOKED_DUCK, 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.GOOSE, HybridBirdsItems.COOKED_GOOSE, 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.TURKEY, HybridBirdsItems.COOKED_TURKEY, 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.TURDUCKEN, HybridBirdsItems.COOKED_TURDUCKEN, 0.15f)
    }

    private fun offerCookingRecipes(
        exporter: RecipeExporter,
        input: Item,
        output: Item,
        experience: Float
    ) {
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, ::SmeltingRecipe, 200, input, output, experience)
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, ::SmokingRecipe, 100, input, output, experience)
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, ::CampfireCookingRecipe, 600, input, output, experience)
    }

    private fun offerEggCookingRecipes(
        exporter: RecipeExporter,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float
    ) {
        offerEggCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, ::SmeltingRecipe, 200, inputTag, output, experience)
        offerEggCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, ::SmokingRecipe, 100, inputTag, output, experience)
        offerEggCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, ::CampfireCookingRecipe, 600, inputTag, output, experience)
    }

    private fun <T : AbstractCookingRecipe> offerEggCookingRecipe(
        exporter: RecipeExporter,
        cooker: String,
        serializer: RecipeSerializer<T>,
        recipeFactory: AbstractCookingRecipe.RecipeFactory<T>,
        cookingTime: Int,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float
    ) {
        val builder = CookingRecipeJsonBuilder
            .create(Ingredient.fromTag(inputTag), RecipeCategory.FOOD, output, experience, cookingTime, serializer, recipeFactory)
            .criterion("has_egg", conditionsFromTag(inputTag))

        val recipeId = getItemPath(output) + "_from_" + cooker
        builder.offerTo(exporter, recipeId)
    }
}