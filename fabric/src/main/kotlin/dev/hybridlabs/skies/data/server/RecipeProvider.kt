package dev.hybridlabs.skies.data.server

import dev.hybridlabs.skies.item.HSItems
import dev.hybridlabs.skies.tag.HBItemTags
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider
import net.minecraft.advancements.critereon.InventoryChangeTrigger
import net.minecraft.advancements.critereon.ItemPredicate
import net.minecraft.core.HolderLookup
import net.minecraft.data.recipes.RecipeCategory
import net.minecraft.data.recipes.RecipeOutput
import net.minecraft.data.recipes.ShapelessRecipeBuilder
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder
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
import java.util.function.Consumer

class RecipeProvider(output: FabricDataOutput, lookupProvider: CompletableFuture<HolderLookup.Provider>) :
    FabricRecipeProvider(output, lookupProvider) {
    override fun buildRecipes(exporter: RecipeOutput) {
        // misc recipes
        ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, HSItems.TURDUCKEN.get(), 1)
            .requires(HSItems.TURKEY.get())
            .requires(HSItems.DUCK.get())
            .requires(Items.CHICKEN)
            .unlockedBy(
                "has_turducken_ingredient", InventoryChangeTrigger.TriggerInstance.hasItems(
                    ItemPredicate.Builder.item().of(HBItemTags.TURDUCKEN_INGREDIENTS).build()
                )
            )
            .save(exporter)

        // cooking recipes
        offerEggCookingRecipes(exporter, HBItemTags.EGGS, HSItems.COOKED_EGG.get(), 0.15f)

        offerCookingRecipes(exporter, HSItems.PUFFIN.get(), HSItems.COOKED_PUFFIN.get(), 0.15f)
        offerCookingRecipes(exporter, HSItems.DUCK.get(), HSItems.COOKED_DUCK.get(), 0.15f)
        offerCookingRecipes(exporter, HSItems.GOOSE.get(), HSItems.COOKED_GOOSE.get(), 0.15f)
        offerCookingRecipes(exporter, HSItems.TURKEY.get(), HSItems.COOKED_TURKEY.get(), 0.15f)
        offerCookingRecipes(exporter, HSItems.TURDUCKEN.get(), HSItems.COOKED_TURDUCKEN.get(), 0.15f)
    }

    private fun offerCookingRecipes(
        exporter: RecipeOutput,
        input: Item,
        output: Item,
        experience: Float,
    ) {
        simpleCookingRecipe(
            exporter,
            "smelting",
            RecipeSerializer.SMELTING_RECIPE,
            ::SmeltingRecipe,
            200,
            input,
            output,
            experience
        )
        simpleCookingRecipe(
            exporter,
            "smoking",
            RecipeSerializer.SMOKING_RECIPE,
            ::SmokingRecipe,
            100,
            input,
            output,
            experience
        )
        simpleCookingRecipe(
            exporter,
            "campfire_cooking",
            RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
            ::CampfireCookingRecipe,
            600,
            input,
            output,
            experience
        )
    }

    private fun offerEggCookingRecipes(
        exporter: RecipeOutput,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float,
    ) {
        offerEggCookingRecipe(
            exporter,
            "smelting",
            RecipeSerializer.SMELTING_RECIPE,
            ::SmeltingRecipe,
            200,
            inputTag,
            output,
            experience
        )
        offerEggCookingRecipe(
            exporter,
            "smoking",
            RecipeSerializer.SMOKING_RECIPE,
            ::SmokingRecipe,
            100,
            inputTag,
            output,
            experience
        )
        offerEggCookingRecipe(
            exporter,
            "campfire_cooking",
            RecipeSerializer.CAMPFIRE_COOKING_RECIPE,
            ::CampfireCookingRecipe,
            600,
            inputTag,
            output,
            experience
        )
    }

    private fun <T : AbstractCookingRecipe> offerEggCookingRecipe(
        exporter: RecipeOutput,
        cooker: String,
        serializer: RecipeSerializer<T>,
        recipeFactory: AbstractCookingRecipe.Factory<T>,
        cookingTime: Int,
        inputTag: TagKey<Item>,
        output: Item,
        experience: Float,
    ) {
        val builder = SimpleCookingRecipeBuilder.generic(
            Ingredient.of(inputTag),
            RecipeCategory.FOOD,
            output,
            experience,
            cookingTime,
            serializer,
            recipeFactory
        ).unlockedBy("has_egg", has(inputTag))

        val recipeId = getItemName(output) + "_from_" + cooker
        builder.save(exporter, recipeId)
    }
}
