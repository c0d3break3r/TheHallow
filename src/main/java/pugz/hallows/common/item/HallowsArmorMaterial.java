package pugz.hallows.common.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.IArmorMaterial;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;

import javax.annotation.Nonnull;
import java.util.function.Supplier;

public class HallowsArmorMaterial implements IArmorMaterial {
    private static final int[] MAX_DAMAGE_ARRAY = new int[]{13, 15, 16, 11};
    private final ResourceLocation name;
    private final int maxDamageFactor;
    private final int[] damageReductionAmountArray;
    private final int enchantability;
    private final SoundEvent soundEvent;
    private final float toughness;
    private final float knockbackResistance;
    private final int[] CHARGE_ARRAY;
    private final LazyValue<Ingredient> repairMaterial;

    public HallowsArmorMaterial(ResourceLocation name, int maxDamageFactor, int[] damageReductionAmountArray, int enchantability, SoundEvent soundEvent, float toughness, float knockbackResistance, int[] charges, Supplier<Ingredient> repairMaterial) {
        this.name = name;
        this.maxDamageFactor = maxDamageFactor;
        this.damageReductionAmountArray = damageReductionAmountArray;
        this.enchantability = enchantability;
        this.soundEvent = soundEvent;
        this.toughness = toughness;
        this.knockbackResistance = knockbackResistance;
        this.CHARGE_ARRAY = charges;
        this.repairMaterial = new LazyValue<>(repairMaterial);
    }

    @Override
    public int getDurabilityForSlot(EquipmentSlotType slotIn) {
        return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
    }

    @Override
    public int getDefenseForSlot(EquipmentSlotType slotIn) {
        return this.damageReductionAmountArray[slotIn.getIndex()];
    }

    @Override
    public int getEnchantmentValue() {
        return this.enchantability;
    }

    @Nonnull
    @Override
    public SoundEvent getEquipSound() {
        return this.soundEvent;
    }

    @Nonnull
    @Override
    public Ingredient getRepairIngredient() {
        return this.repairMaterial.get();
    }

    @Nonnull
    @Override
    public String getName() {
        return this.name.toString();
    }

    @Override
    public float getToughness() {
        return this.toughness;
    }

    @Override
    public float getKnockbackResistance() {
        return this.knockbackResistance;
    }

    public int getCharge(EquipmentSlotType slotIn) {
        return CHARGE_ARRAY[slotIn.getIndex()];
    }
}