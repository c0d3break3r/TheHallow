package pugz.hallows.core.registry.other;

import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import pugz.hallows.common.item.crafting.AnointmentRecipe;
import pugz.hallows.core.Hallows;

public class HallowsRecipes {
    public static final IRecipeType<AnointmentRecipe> ANOINTMENT = register("anointment");

    private static <T extends IRecipe<?>> IRecipeType<T> register(final String key) {
        return Registry.register(Registry.RECIPE_TYPE, new ResourceLocation(Hallows.MOD_ID, key), new IRecipeType<T>() {
            public String toString() {
                return key;
            }
        });
    }
}