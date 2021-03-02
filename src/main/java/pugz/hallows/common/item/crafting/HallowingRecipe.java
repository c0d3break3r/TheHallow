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
import pugz.hallows.core.registry.other.HallowsRecipes;

import javax.annotation.Nonnull;

public class HallowingRecipe implements IRecipe<IInventory> {
    private final Ingredient base;
    private final Ingredient addition;
    private final ItemStack result;
    private final Attribute attribute;
    private final ResourceLocation recipeId;

    public HallowingRecipe(ResourceLocation recipeId, Ingredient base, Ingredient addition, ItemStack result, Attribute attribute) {
        this.recipeId = recipeId;
        this.base = base;
        this.addition = addition;
        this.result = result;
        this.attribute = attribute;
    }

    public boolean matches(IInventory inv, World worldIn) {
        return this.base.test(inv.getStackInSlot(0)) && this.addition.test(inv.getStackInSlot(1));
    }

    @Nonnull
    public ItemStack getCraftingResult(IInventory inv) {
        ItemStack itemstack = this.result.copy();
        CompoundNBT compoundnbt = inv.getStackInSlot(0).getTag();
        if (compoundnbt != null) {
            itemstack.setTag(compoundnbt.copy());
        }

        return itemstack;
    }

    public boolean canFit(int width, int height) {
        return width * height >= 2;
    }

    @Nonnull
    public Ingredient getBase() {
        return base;
    }

    @Nonnull
    public ItemStack getRecipeOutput() {
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
    public ItemStack getIcon() {
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
        public HallowingRecipe read(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.deserialize(JSONUtils.getJsonObject(json, "base"));
            Ingredient ingredient1 = Ingredient.deserialize(JSONUtils.getJsonObject(json, "addition"));
            ItemStack itemstack = ShapedRecipe.deserializeItem(JSONUtils.getJsonObject(json, "result"));
            Attribute attribute = deserializeAttribute(JSONUtils.getJsonObject(json, "attribute"));
            return new HallowingRecipe(recipeId, ingredient, ingredient1, itemstack, attribute);
        }

        public HallowingRecipe read(ResourceLocation recipeId, PacketBuffer buffer) {
            Ingredient ingredient = Ingredient.read(buffer);
            Ingredient ingredient1 = Ingredient.read(buffer);
            ItemStack itemstack = buffer.readItemStack();
            Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(buffer.readString()));
            return new HallowingRecipe(recipeId, ingredient, ingredient1, itemstack, attribute);
        }

        public void write(PacketBuffer buffer, HallowingRecipe recipe) {
            recipe.base.write(buffer);
            recipe.addition.write(buffer);
            buffer.writeItemStack(recipe.result);
        }

        @SuppressWarnings("deprecation")
        public static Attribute deserializeAttribute(JsonObject object) {
            String s = JSONUtils.getString(object, "attribute");
            Attribute attribute = Registry.ATTRIBUTE.getOptional(new ResourceLocation(s)).orElseThrow(() -> {
                return new JsonSyntaxException("Unknown attribute '" + s + "'");
            });
            if (object.has("data")) {
                throw new JsonParseException("Disallowed data tag found");
            } else {
                int i = JSONUtils.getInt(object, "amount", 1);
                return getAttribute(object, true);
            }
        }

        public static Attribute getAttribute(JsonObject json, boolean readNBT) {
            String attributeName = JSONUtils.getString(json, "attribute");

            Attribute attribute = ForgeRegistries.ATTRIBUTES.getValue(new ResourceLocation(attributeName));

            if (attribute == null)
                throw new JsonSyntaxException("Unknown attribute '" + attributeName + "'");

            else return attribute;
        }
    }
}