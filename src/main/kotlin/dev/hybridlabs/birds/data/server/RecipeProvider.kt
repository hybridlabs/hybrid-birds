package dev.hybridlabs.birds.data.server

import dev.hybridlabs.birds.item.HybridBirdsItems
import dev.hybridlabs.birds.tag.HybridBirdsItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancement.criterion.InventoryChangedCriterion
import net.minecraft.data.server.recipe.RecipeJsonProvider
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.predicate.item.ItemPredicate
import net.minecraft.recipe.RecipeSerializer
import net.minecraft.recipe.book.RecipeCategory
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput) : FabricRecipeProvider(output) {
    override fun generate(exporter: Consumer<RecipeJsonProvider>) {
        // misc recipes
        ShapelessRecipeJsonBuilder.create(RecipeCategory.FOOD, HybridBirdsItems.TURDUCKEN, 1)
            .input(HybridBirdsItems.TURKEY)
            .input(HybridBirdsItems.DUCK)
            .input(Items.CHICKEN)
            .criterion("has_turducken_ingredient", InventoryChangedCriterion.Conditions.items(
                ItemPredicate.Builder.create().tag(HybridBirdsItemTags.TURDUCKEN_INGREDIENTS).build()))
            .offerTo(exporter)

        // cooking recipes
        offerFoodCookingRecipe(exporter, "duck_egg_smelting", RecipeSerializer.SMELTING, 200, HybridBirdsItems.DUCK_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "duck_egg_smoking", RecipeSerializer.SMELTING, 100, HybridBirdsItems.DUCK_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "duck_egg_campfire_cooking", RecipeSerializer.SMELTING, 600, HybridBirdsItems.DUCK_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerFoodCookingRecipe(exporter, "goose_egg_smelting", RecipeSerializer.SMELTING, 200, HybridBirdsItems.GOOSE_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "goose_egg_smoking", RecipeSerializer.SMELTING, 100, HybridBirdsItems.GOOSE_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "goose_egg_campfire_cooking", RecipeSerializer.SMELTING, 600, HybridBirdsItems.GOOSE_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerFoodCookingRecipe(exporter, "swan_egg_smelting", RecipeSerializer.SMELTING, 200, HybridBirdsItems.SWAN_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "swan_egg_smoking", RecipeSerializer.SMELTING, 100, HybridBirdsItems.SWAN_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "swan_egg_campfire_cooking", RecipeSerializer.SMELTING, 600, HybridBirdsItems.SWAN_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerFoodCookingRecipe(exporter, "turkey_egg_smelting", RecipeSerializer.SMELTING, 200, HybridBirdsItems.TURKEY_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "turkey_egg_smoking", RecipeSerializer.SMELTING, 100, HybridBirdsItems.TURKEY_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "turkey_egg_campfire_cooking", RecipeSerializer.SMELTING, 600, HybridBirdsItems.TURKEY_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerFoodCookingRecipe(exporter, "peacock_egg_smelting", RecipeSerializer.SMELTING, 200, HybridBirdsItems.PEACOCK_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "peacock_egg_smoking", RecipeSerializer.SMELTING, 100, HybridBirdsItems.PEACOCK_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "peacock_egg_campfire_cooking", RecipeSerializer.SMELTING, 600, HybridBirdsItems.PEACOCK_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerFoodCookingRecipe(exporter, "guineafowl_egg_smelting", RecipeSerializer.SMELTING, 200, HybridBirdsItems.GUINEA_FOWL_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "guineafowl_egg_smoking", RecipeSerializer.SMELTING, 100, HybridBirdsItems.GUINEA_FOWL_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "guineafowl_egg_campfire_cooking", RecipeSerializer.SMELTING, 600, HybridBirdsItems.GUINEA_FOWL_EGG, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, 200, Items.EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMELTING, 100, Items.EGG, HybridBirdsItems.COOKED_EGG, 0.15f)
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.SMELTING, 600, Items.EGG, HybridBirdsItems.COOKED_EGG, 0.15f)

        offerCookingRecipes(exporter, HybridBirdsItems.DUCK, HybridBirdsItems.COOKED_DUCK, 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.GOOSE, HybridBirdsItems.COOKED_GOOSE, 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.TURKEY, HybridBirdsItems.COOKED_TURKEY, 0.15f)
        offerCookingRecipes(exporter, HybridBirdsItems.TURDUCKEN, HybridBirdsItems.COOKED_TURDUCKEN, 0.15f)
    }

    private fun offerCookingRecipes(
        exporter: Consumer<RecipeJsonProvider>,
        input: Item,
        output: Item,
        experience: Float
    ) {
        offerFoodCookingRecipe(exporter, "smelting", RecipeSerializer.SMELTING, 200, input, output, experience)
        offerFoodCookingRecipe(exporter, "smoking", RecipeSerializer.SMOKING, 100, input, output, experience)
        offerFoodCookingRecipe(exporter, "campfire_cooking", RecipeSerializer.CAMPFIRE_COOKING, 600, input, output, experience)
    }
}
