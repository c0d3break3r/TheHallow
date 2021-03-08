package pugz.hallows.core.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.item.HallowsArmorMaterial;
import pugz.hallows.common.item.HallowsItemTier;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryHelper;

public class HallowsItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Hallows.MOD_ID);

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

    public static void registerItems() {
        STYGIAN_INGOT = RegistryHelper.createItem("stygian_ingot", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        STYGIAN_HELMET = RegistryHelper.createItem("stygian_helmet", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_CHESTPLATE = RegistryHelper.createItem("stygian_chestplate", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_LEGGINGS = RegistryHelper.createItem("stygian_leggings", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_BOOTS = RegistryHelper.createItem("stygian_boots", () -> new ArmorItem(STYGIAN_MATERIAL, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_SWORD = RegistryHelper.createItem("stygian_sword", () -> new SwordItem(STYGIAN_TIER, 3, -2.4F, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_PICKAXE = RegistryHelper.createItem("stygian_pickaxe", () -> new PickaxeItem(STYGIAN_TIER, 1, -2.8F, new Item.Properties().group(ItemGroup.TOOLS)));
        STYGIAN_AXE = RegistryHelper.createItem("stygian_axe", () -> new AxeItem(STYGIAN_TIER, 5.0F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)));
        STYGIAN_SHOVEL = RegistryHelper.createItem("stygian_shovel", () -> new ShovelItem(STYGIAN_TIER, 1.5F, -3.0F, new Item.Properties().group(ItemGroup.TOOLS)));
        STYGIAN_HOE = RegistryHelper.createItem("stygian_hoe", () -> new HoeItem(STYGIAN_TIER, -4, 0.0F, new Item.Properties().group(ItemGroup.TOOLS)));

        OPAL = RegistryHelper.createItem("opal", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        HEMLOCK_LEAF = RegistryHelper.createItem("hemlock_leaf", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        NECROFIRE_TORCH = RegistryHelper.createItem("necrofire_torch", () -> new WallOrFloorItem(HallowsBlocks.NECROFIRE_TORCH.get(), HallowsBlocks.NECROFIRE_WALL_TORCH.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
        WITCHS_BREW = RegistryHelper.createItem("witch_brew", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        GHOST_CLOTH = RegistryHelper.createItem("ghost_cloth", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));

        ORANGE_WILL_O_WISP_BERRY = RegistryHelper.createItem("orange_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        YELLOW_WILL_O_WISP_BERRY = RegistryHelper.createItem("yellow_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        BLUE_WILL_O_WISP_BERRY = RegistryHelper.createItem("blue_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
        PURPLE_WILL_O_WISP_BERRY = RegistryHelper.createItem("purple_will_o_wisp_berry", () -> new Item(new Item.Properties().group(ItemGroup.FOOD)));
    }

    public static class Foods {
    }
}