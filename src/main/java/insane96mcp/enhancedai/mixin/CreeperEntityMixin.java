package insane96mcp.enhancedai.mixin;

import insane96mcp.enhancedai.setup.ModSounds;
import insane96mcp.enhancedai.setup.Strings;
import net.minecraft.entity.monster.CreeperEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(CreeperEntity.class)
public class CreeperEntityMixin {
	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/monster/CreeperEntity;playSound(Lnet/minecraft/util/SoundEvent;FF)V"), method = "Lnet/minecraft/entity/monster/CreeperEntity;tick()V")
	public void tickOnPlaySound(CallbackInfo callbackInfo) {
		CreeperEntity $this = (CreeperEntity) (Object) this;
		if ($this.getPersistentData().getBoolean(Strings.Tags.JOHN_CENA))
			$this.playSound(ModSounds.CREEPER_CENA_FUSE.get(), 4.0f, 1.0f);
	}
}
