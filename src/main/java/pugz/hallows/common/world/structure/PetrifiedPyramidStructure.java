package pugz.hallows.common.world.structure;

import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.util.registry.DynamicRegistries;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.*;
import net.minecraft.world.gen.feature.template.TemplateManager;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.registry.HallowsStructures;

import javax.annotation.Nonnull;

public class PetrifiedPyramidStructure extends Structure<NoFeatureConfig> {

    public PetrifiedPyramidStructure() {
        super(NoFeatureConfig.field_236558_a_);
        HallowsStructures.HALLOWS_STRUCTURE_START_PIECES.add(new ResourceLocation(Hallows.MOD_ID, "petrified_pyramid"));
    }

    @Nonnull
    @Override
    public String getStructureName() {
        return Hallows.MOD_ID + ":petrified_pyramid";
    }

    @Nonnull
    @Override
    public  IStartFactory<NoFeatureConfig> getStartFactory() {
        return PetrifiedPyramidStructure.Start::new;
    }

    @Nonnull
    @Override
    public GenerationStage.Decoration getDecorationStage() {
        return GenerationStage.Decoration.SURFACE_STRUCTURES;
    }

    public static class Start extends StructureStart<NoFeatureConfig> {
        public Start(Structure<NoFeatureConfig> structure, int p_i225806_2_, int p_i225806_3_, MutableBoundingBox boundingBox, int p_i225806_5_, long p_i225806_6_) {
            super(structure, p_i225806_2_, p_i225806_3_, boundingBox, p_i225806_5_, p_i225806_6_);
        }

        public void func_230364_a_(DynamicRegistries dynamicRegistries, ChunkGenerator chunkGenerator, TemplateManager manager, int p_230364_4_, int p_230364_5_, Biome biome, NoFeatureConfig config) {
            int i = p_230364_4_ * 16;
            int j = p_230364_5_ * 16;
            BlockPos.Mutable mutable = new BlockPos(i, 90, j).toMutable().setPos(this.bounds.func_215126_f().getX(), chunkGenerator.getSeaLevel() + 3, this.bounds.func_215126_f().getZ());
            IBlockReader reader = chunkGenerator.func_230348_a_(mutable.getX(), mutable.getZ());
            Rotation rotation = Rotation.randomRotation(this.rand);

            while (mutable.getY() <= chunkGenerator.getMaxBuildHeight() - 20) {
                if (reader.getBlockState(mutable).getMaterial() != Material.AIR && reader.getBlockState(mutable.up()).getMaterial() == Material.AIR && reader.getBlockState(mutable.up(5)).getMaterial() == Material.AIR) {
                    mutable.move(Direction.UP);
                    break;
                }
                mutable.move(Direction.UP);
            }

            BlockPos pos = new BlockPos(mutable);
            PetrifiedPyramidPieces.func_236991_a_(manager, pos, rotation, this.components);
            this.recalculateStructureSize();
        }
    }
}