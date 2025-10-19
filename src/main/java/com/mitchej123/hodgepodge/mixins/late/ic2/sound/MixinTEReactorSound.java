package com.mitchej123.hodgepodge.mixins.late.ic2.sound;

import static gregtech.api.util.GTUtility.sendSoundToPlayers;

import net.minecraft.tileentity.TileEntity;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import gregtech.api.enums.SoundResource;
import ic2.core.block.reactor.tileentity.TileEntityNuclearReactorElectric;

@Mixin(value = TileEntityNuclearReactorElectric.class, remap = false)
public abstract class MixinTEReactorSound extends TileEntity {

    @Shadow
    public abstract float getReactorEnergyOutput();

    @Inject(method = "onNetworkUpdate", at = @At("HEAD"), cancellable = true, remap = false)
    public void hodgepodge$fixReactorSound(CallbackInfo ci) {
        if (getReactorEnergyOutput() > 0) {
            sendSoundToPlayers(worldObj, SoundResource.GTCEU_LOOP_COOLING, 1.0F, 1.0F, xCoord, yCoord, zCoord);
        }
    }
}
