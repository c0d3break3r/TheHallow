package pugz.hallows.common.world.tree;

import net.minecraft.block.trees.Tree;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import pugz.hallows.core.registry.HallowsFeatures;

import javax.annotation.Nullable;
import java.util.Random;

public class AsphodelTree extends Tree {
    @Nullable
    protected ConfiguredFeature<BaseTreeFeatureConfig, ?> getTreeFeature(Random randomIn, boolean largeHive) {
        return HallowsFeatures.Configured.ASPHODEL;
    }
}