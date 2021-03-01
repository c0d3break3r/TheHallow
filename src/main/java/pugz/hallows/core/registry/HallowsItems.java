package pugz.hallows.core.registry;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.WallOrFloorItem;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import pugz.hallows.common.item.HallowsArmorMaterial;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.util.RegistryUtil;

public class HallowsItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Hallows.MOD_ID);

    public static final HallowsArmorMaterial STYGIAN_TIER = new HallowsArmorMaterial(new ResourceLocation(Hallows.MOD_ID, "stygian"), 40, new int[]{4, 7, 9, 4}, 18, SoundEvents.ITEM_ARMOR_EQUIP_NETHERITE, 3.5F, 0.0F, new int[]{1, 3, 4, 2}, () -> Ingredient.fromItems(HallowsItems.STYGIAN_INGOT.get()));

    public static RegistryObject<Item> STYGIAN_INGOT;
    public static RegistryObject<Item> STYGIAN_HELMET;
    public static RegistryObject<Item> STYGIAN_CHESTPLATE;
    public static RegistryObject<Item> STYGIAN_LEGGINGS;
    public static RegistryObject<Item> STYGIAN_BOOTS;

    public static RegistryObject<Item> OPAL;
    public static RegistryObject<Item> HEMLOCK_LEAF;
    public static RegistryObject<Item> NECROFIRE_TORCH;

    public static void registerItems() {
        STYGIAN_INGOT = RegistryUtil.createItem("stygian_ingot", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        STYGIAN_HELMET = RegistryUtil.createItem("stygian_helmet", () -> new ArmorItem(STYGIAN_TIER, EquipmentSlotType.HEAD, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_CHESTPLATE = RegistryUtil.createItem("stygian_chestplate", () -> new ArmorItem(STYGIAN_TIER, EquipmentSlotType.CHEST, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_LEGGINGS = RegistryUtil.createItem("stygian_leggings", () -> new ArmorItem(STYGIAN_TIER, EquipmentSlotType.LEGS, new Item.Properties().group(ItemGroup.COMBAT)));
        STYGIAN_BOOTS = RegistryUtil.createItem("stygian_boots", () -> new ArmorItem(STYGIAN_TIER, EquipmentSlotType.FEET, new Item.Properties().group(ItemGroup.COMBAT)));

        OPAL = RegistryUtil.createItem("opal", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        HEMLOCK_LEAF = RegistryUtil.createItem("hemlock_leaf", () -> new Item(new Item.Properties().group(ItemGroup.MATERIALS)));
        NECROFIRE_TORCH = RegistryUtil.createItem("necrofire_torch", () -> new WallOrFloorItem(HallowsBlocks.NECROFIRE_TORCH.get(), HallowsBlocks.NECROFIRE_WALL_TORCH.get(), new Item.Properties().group(ItemGroup.DECORATIONS)));
    }
}