package pugz.hallows.core.registry;

import com.minecraftabnormals.abnormals_core.common.items.AbnormalsSpawnEggItem;
import com.minecraftabnormals.abnormals_core.core.util.registry.ItemSubRegistryHelper;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import pugz.hallows.common.item.HallowsArmorMaterial;
import pugz.hallows.common.item.HallowsItemTier;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsItems {
    public static final ItemSubRegistryHelper HELPER = Hallows.REGISTRY_HELPER.getItemSubHelper();

    public static final HallowsArmorMaterial STYGIAN_MATERIAL = new HallowsArmorMaterial(new ResourceLocation(Hallows.MOD_ID, "stygian"), 40, new int[]{4, 7, 9, 4}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.5F, 0.0F, new int[]{1, 3, 4, 2}, () -> Ingredient.fromItems(HallowsItems.STYGIAN_INGOT.get()));
    public static final HallowsItemTier STYGIAN_TIER = new HallowsItemTier(5, 2231, 9.5F, 4.5F, 18, () -> Ingredient.fromItems(HallowsItems.STYGIAN_INGOT.get()), 3);

    public static RegistryObject<Item> STYGIAN_INGOT;
    public static RegistryObject<Item> STYGIAN_HELMET;
    public static RegistryObject<Item> STYGIAN_CHESTPLATE;
    public static RegistryObject<Item> STYGIAN_LEGGINGS;
    public static RegistryObject<Item> STYGIAN_BOOTS;
    public static RegistryObject<Item> STYGIAN_SWORD;
    public static RegistryObject<Item> STYGIAN_PICKAXE;
    public static RegistryObject<Item> STYGIAN_AXE;
    public static RegistryObject<Item> STYGIAN_SHOVEL;
    public static RegistryObject<Item> STYGIAN_HOE;

    public static RegistryObject<Item> OPAL;
    public static RegistryObject<Item> HEMLOCK_LEAF;
    public static RegistryObject<Item> NECROFIRE_TORCH;
    public static RegistryObject<Item> WITCHS_BREW;
    public static RegistryObject<Item> GHOST_CLOTH;
    public static RegistryObject<Item> HAUNT_FUR;

    public static RegistryObject<Item> ORANGE_WILL_O_WISP_BERRY;
    public static RegistryObject<Item> ORANGE_WILL_O_WISP_CANDY;
    public static RegistryObject<Item> YELLOW_WILL_O_WISP_BERRY;
    public static RegistryObject<Item> YELLOW_WILL_O_WISP_CANDY;
    public static RegistryObject<Item> BLUE_WILL_O_WISP_BERRY;
    public static RegistryObject<Item> BLUE_WILL_O_WISP_CANDY;
    public static RegistryObject<Item> PURPLE_WILL_O_WISP_BERRY;
    public static RegistryObject<Item> PURPLE_WILL_O_WISP_CANDY;

    public static RegistryObject<AbnormalsSpawnEggItem> GHOST_SPAWN_EGG;
    public static RegistryObject<AbnormalsSpawnEggItem> HAUNT_SPAWN_EGG;

    public static void registerItems() {
        STYGIAN_INGOT = HELPER.createItem("stygian_ingot", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        STYGIAN_HELMET = HELPER.createItem("stygian_helmet", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_CHESTPLATE = HELPER.createItem("stygian_chestplate", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_LEGGINGS = HELPER.createItem("stygian_leggings", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_BOOTS = HELPER.createItem("stygian_boots", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_SWORD = HELPER.createItem("stygian_sword", () -> new SwordItem(STYGIAN_TIER, 3, -2.4F, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_PICKAXE = HELPER.createItem("stygian_pickaxe", () -> new PickaxeItem(STYGIAN_TIER, 1, -2.8F, new Item.Properties().group(ItemGroup.TOOLS)));
        STYGIAN_AXE = HELPER.createItem("stygian_axe", () -> new AxeItem(STYGIAN_TIER, 5.0F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)));
        STYGIAN_SHOVEL = HELPER.createItem("stygian_shovel", () -> new ShovelItem(STYGIAN_TIER, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)));
        STYGIAN_HOE = HELPER.createItem("stygian_hoe", () -> new HoeItem(STYGIAN_TIER, -4, 0.0F, new Item.Properties().group(ItemGroup.TOOLS)));

        OPAL = HELPER.createItem("opal", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        HEMLOCK_LEAF = HELPER.createItem("hemlock_leaf", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        WITCHS_BREW = HELPER.createItem("witch_brew", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        GHOST_CLOTH = HELPER.createItem("ghost_cloth", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        HAUNT_FUR = HELPER.createItem("haunt_fur", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));

        ORANGE_WILL_O_WISP_BERRY = HELPER.createItem("orange_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        YELLOW_WILL_O_WISP_BERRY = HELPER.createItem("yellow_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        BLUE_WILL_O_WISP_BERRY = HELPER.createItem("blue_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        PURPLE_WILL_O_WISP_BERRY = HELPER.createItem("purple_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        ORANGE_WILL_O_WISP_CANDY = HELPER.createItem("orange_will_o_wisp_candy", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        YELLOW_WILL_O_WISP_CANDY = HELPER.createItem("yellow_will_o_wisp_candy", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        BLUE_WILL_O_WISP_CANDY = HELPER.createItem("blue_will_o_wisp_candy", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        PURPLE_WILL_O_WISP_CANDY = HELPER.createItem("purple_will_o_wisp_candy", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));

        GHOST_SPAWN_EGG = HELPER.createSpawnEggItem("ghost", HallowsEntities.GHOST::get, 6263617, 13940616);
        HAUNT_SPAWN_EGG = HELPER.createSpawnEggItem("haunt", HallowsEntities.HAUNT::get, 6263617, 13940616);
    }

    public static class Foods {
    }
}