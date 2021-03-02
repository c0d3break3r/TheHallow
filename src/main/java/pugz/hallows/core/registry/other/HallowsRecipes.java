package pugz.hallows.core.registry.other;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.item.crafting.HallowingRecipe;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryUtil;

public class HallowsRecipes {
    public static final DeferredRegister<IRecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Hallows.MOD_ID);

    public static RegistryObject<IRecipeSerializer<HallowingRecipe>> HALLOWING;

    public static class Recipes {
        public static final IRecipeType<HallowingRecipe> HALLOWING = RegistryUtil.createRecipe("hallowing");
    }

    public static void registerRecipeSerializers() {
        HALLOWING = RegistryUtil.createRecipeSerializer("hallowing", new HallowingRecipe.Serializer());
    }
}