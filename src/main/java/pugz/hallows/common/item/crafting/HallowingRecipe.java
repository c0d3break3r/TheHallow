package pugz.hallows.common.item.crafting;

import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonSyntaxException;
import net.minecraft.entity.ai.attributes.Attribute;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.*;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.PacketBuffer;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.World;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.ForgeRegistryEntry;
import pugz.hallows.core.registry.HallowsBlocks;
import pugz.hallows.core.registry.HallowsItems;
import pugz.hallows.core.registry.other.HallowsRecipes;

import javax.annotation.Nonnull;

public class HallowingRecipe implements IRecipe<IInventory> {
    private final Ingredient base;
    private final Ingredient addition;
    //private final Ingredient brew;
    private final ItemStack result;
    private final Attribute attribute;
    private final ResourceLocation recipeId;

    //public HallowingRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, Ingredient brew, ItemStack result, Attribute attribute) {
    public HallowingRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result, Attribute attribute) {
        this.recipeId = recipeId;
        this.base = base;
        this.addition = addition;
        //this.brew = brew;
        this.result = result;
        this.attribute = attribute;
    }

    public boolean matches(IInventory inv, World worldIn) {
        return this.base.test(inv.getItem(0)) && this.addition.test(inv.getItem(1));
    }

    @Nonnull
    public ItemStack assemble(IInventory inv) {
        ItemStack itemstack = this.result.copy();
        CompoundNBT compoundnbt = inv.getItem(0).getTag();
        if (compoundnbt != null) {
            itemstack.setTag(compoundnbt.copy());
        }

        return itemstack;
    }

    public boolean canCraftInDimensions(int width, int height) {
        return width * height >= 2;
    }

    @Nonnull
    public Ingredient getBase() {
        return base;
    }

    //public Ingredient getBrew() {
    //    return brew;
    //}

    @Nonnull
    public ItemStack getResultItem() {
        return this.result;
    }

    @Nonnull
    public Attribute getAttribute() {
        return attribute;
    }

    public boolean isValidAdditionItem(ItemStack addition) {
        return this.addition.test(addition);
    }

    @Nonnull
    public ItemStack getToastSymbol() {
        return new ItemStack(HallowsBlocks.ANOINTMENT_TABLE.get());
    }

    @Nonnull
    public ResourceLocation getId() {
        return this.recipeId;
    }

    @Nonnull
    public IRecipeSerializer<?> getSerializer() {
        return HallowsRecipes.HALLOWING.get();
    }

    @Nonnull
    public IRecipeType<?> getType() {
        return HallowsRecipes.Recipes.HALLOWING;
    }

    public static class Serializer extends ForgeRegistryEntry<IRecipeSerializer<?>> implements IRecipeSerializer<HallowingRecipe> {
        @Nonnull
        public HallowingRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "base"));
            Ingredient ingredient1 = Ingredient.fromJson(JSONUtils.getAsJsonArray(json, "addition"));
            ItemStack itemstack = ShapedRecipe.itemFromJson(JSONUtils.getAsJsonObject(json, "result"));
            Attribute attribute = deserializeAttribute(JSONUtils.getAsJsonObject(json, "attribute"));
            return new HallowingRecipe(recipeId, ingredient, ingredient1, itemstack, attribute);
        }

        public HallowingRecipe fromNetwork(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            Ingredient ingredient1 = Ingredient.fromNetwork(buffer);
            ItemStack itemstack = buffer.readItem();
            Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(buffer.readUtf()));
            return new HallowingRecipe(recipeId, ingredient, ingredient1, itemstack, attribute);
        }

        public void toNetwork(PacketBuffer buffer, HallowingRecipe recipe) {
            recipe.base.toNetwork(buffer);
            recipe.addition.toNetwork(buffer);
            buffer.writeItem(recipe.result);
        }

        @SuppressWarnings("deprecation")
        public static Attribute deserializeAttribute(JsonObject object) {
            String s = JSONUtils.getAsString(object, "attribute");
            Attribute attribute = Registry.ATTRIBUTE.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown attribute '" + s + "'");
            });
            if (object.has("data")) {
                throw new JsonParseException("Disallowed data tag found");
            } else {
                int i = JSONUtils.getAsInt(object, "amount", 1);
                return getAttribute(object);
            }
        }

        public static Attribute getAttribute(JsonObject json) {
            String attributeName = JSONUtils.getAsString(json, "attribute");

            Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attributeName));

            if (attribute == null)
                throw new JsonSyntaxException("Unknown attribute '" + attributeName + "'");

            else return attribute;
        }
    }
}