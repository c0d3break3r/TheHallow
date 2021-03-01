package pugz.hallows.core.util;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.DamageSource;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.TickEvent;
import pugz.hallows.common.item.HallowsArmorMaterial;

public class Events {
    public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
        PlayerEntity player = event.player;
        World world = player.getEntityWorld();
        int charge = 0;
        CompoundNBT nbt = new CompoundNBT();

        for (ItemStack stack : player.getArmorInventoryList()) {
            if (stack.getItem() instanceof ArmorItem) {
                if (((ArmorItem)stack.getItem()).getArmorMaterial() instanceof HallowsArmorMaterial) {
                    HallowsArmorMaterial material = (HallowsArmorMaterial)((ArmorItem)stack.getItem()).getArmorMaterial();
                    if (world.getRandom().nextInt(3) == 0) charge += material.getCharge(((ArmorItem) stack.getItem()).getEquipmentSlot());

                    System.out.println(charge);
                }
            }
        }

        if (charge == 10) {
            for (Entity entity : world.getEntitiesInAABBexcluding(player, new AxisAlignedBB(player.getPosX() - 5.0D, player.getPosY() - 5.0D, player.getPosZ() - 5.0D, player.getPosX() + 5.0D, player.getPosY() + 5.0D, player.getPosZ() + 5.0D), Entity::isAlive)) {
                entity.attackEntityFrom(DamageSource.MAGIC, 6.0F);
            }

            charge = 0;
        }

        nbt.putFloat("Charge", charge);
        player.writeAdditional(nbt);
    }
}