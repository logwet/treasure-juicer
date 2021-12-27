package me.logwet.treasure_juicer.mixin.common;

import net.minecraft.world.level.biome.BiomeDefaultFeatures;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.Slice;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(BiomeDefaultFeatures.class)
public abstract class BiomeDefaultFeaturesMixin {
    @Unique private static final float FACTOR = 4F / 3F;

    @ModifyConstant(
            method = "<clinit>",
            constant = @Constant(floatValue = 0.01F),
            slice =
                    @Slice(
                            from =
                                    @At(
                                            value = "FIELD",
                                            target =
                                                    "Lnet/minecraft/world/level/levelgen/feature/StructureFeature;END_CITY:Lnet/minecraft/world/level/levelgen/feature/StructureFeature;",
                                            opcode = Opcodes.GETSTATIC),
                            to =
                                    @At(
                                            value = "FIELD",
                                            target =
                                                    "Lnet/minecraft/world/level/levelgen/feature/StructureFeature;BASTION_REMNANT:Lnet/minecraft/world/level/levelgen/feature/StructureFeature;",
                                            opcode = Opcodes.GETSTATIC)))
    private static float modifyProbability(float original) {
        return original * FACTOR;
    }
}
