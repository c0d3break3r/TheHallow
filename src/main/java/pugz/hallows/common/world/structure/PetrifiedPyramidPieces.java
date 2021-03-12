package pugz.hallows.common.world.structure;

import com.google.common.collect.ImmutableMap;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.Mirror;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.IServerWorld;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.structure.StructureManager;
import net.minecraft.world.gen.feature.structure.StructurePiece;
import net.minecraft.world.gen.feature.structure.TemplateStructurePiece;
import net.minecraft.world.gen.feature.template.BlockIgnoreStructureProcessor;
import net.minecraft.world.gen.feature.template.PlacementSettings;
import net.minecraft.world.gen.feature.template.Template;
import net.minecraft.world.gen.feature.template.TemplateManager;
import pugz.hallows.core.Hallows;
import pugz.hallows.core.registry.HallowsStructures;

import java.util.List;
import java.util.Map;
import java.util.Random;

public class PetrifiedPyramidPieces {
    private static final ResourceLocation PIECE = new ResourceLocation(Hallows.MOD_ID, "petrified_pyramid");
    private static final Map<ResourceLocation, BlockPos> PIECES = ImmutableMap.of(PIECE, BlockPos.ZERO);

    public static void func_236991_a_(TemplateManager manager, BlockPos pos, Rotation rotation, List<StructurePiece> pieces) {
        pieces.add(new PetrifiedPyramidPieces.Piece(manager, PIECE, pos, rotation, 0));
    }

    public static class Piece extends TemplateStructurePiece {
        private final ResourceLocation resourceLocation;
        private final Rotation rotation;

        public Piece(TemplateManager manager, ResourceLocation location, BlockPos pos, Rotation rotation, int yOffset) {
            super(HallowsStructures.Pieces.PETRIFIED_PYRAMID_PIECE, 0);
            this.resourceLocation = location;
            BlockPos blockpos = PetrifiedPyramidPieces.PIECES.get(location);
            this.templatePosition = pos.offset(blockpos.getX(), blockpos.getY() - yOffset, blockpos.getZ());
            this.rotation = rotation;
            this.func_207614_a(manager);
        }

        public Piece(TemplateManager manager, CompoundNBT nbt) {
            super(HallowsStructures.Pieces.PETRIFIED_PYRAMID_PIECE, nbt);
            this.resourceLocation = new ResourceLocation(nbt.getString("Template"));
            this.rotation = Rotation.valueOf(nbt.getString("Rot"));
            this.func_207614_a(manager);
        }

        private void func_207614_a(TemplateManager manager) {
            Template template = manager.get(this.resourceLocation);
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(PetrifiedPyramidPieces.PIECES.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            this.setup(template, this.templatePosition, placementsettings);
        }

        protected void addAdditionalSaveData(CompoundNBT tagCompound) {
            super.addAdditionalSaveData(tagCompound);
            tagCompound.putString("Template", this.resourceLocation.toString());
            tagCompound.putString("Rot", this.rotation.name());
        }

        protected void handleDataMarker(String function, BlockPos pos, IServerWorld worldIn, Random rand, MutableBoundingBox sbb) {
        }

        public boolean postProcess(ISeedReader reader, StructureManager manager, ChunkGenerator generator, Random random, MutableBoundingBox boundingBox, ChunkPos chunkPos, BlockPos pos) {
            PlacementSettings placementsettings = (new PlacementSettings()).setRotation(this.rotation).setMirror(Mirror.NONE).setRotationPivot(PetrifiedPyramidPieces.PIECES.get(this.resourceLocation)).addProcessor(BlockIgnoreStructureProcessor.STRUCTURE_BLOCK);
            BlockPos blockpos = PetrifiedPyramidPieces.PIECES.get(this.resourceLocation);
            //BlockPos blockpos1 = this.templatePosition.offset(Template.transformedVec3d(placementsettings, new BlockPos(3 - blockpos.getX(), 0, 0 - blockpos.getZ())));
            //int i = reader.getHeight(Heightmap.Type.WORLD_SURFACE_WG, blockpos1.getX(), blockpos1.getZ());
            BlockPos blockpos2 = this.templatePosition;
            //this.templatePosition = this.templatePosition.offset(0, i - 90 - 1, 0);
            boolean flag = super.postProcess(reader, manager, generator, random, boundingBox, chunkPos, pos);
            this.templatePosition = blockpos2;
            return false;
        }
    }
}