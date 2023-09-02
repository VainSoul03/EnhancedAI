package insane96mcp.enhancedai.modules.base.feature;

import insane96mcp.enhancedai.modules.Modules;
import insane96mcp.insanelib.base.Feature;
import insane96mcp.insanelib.base.Label;
import insane96mcp.insanelib.base.Module;
import insane96mcp.insanelib.base.config.Config;
import insane96mcp.insanelib.base.config.LoadFeature;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraftforge.common.ForgeMod;
import net.minecraftforge.event.entity.EntityAttributeModificationEvent;

@Label(name = "Attacking")
@LoadFeature(module = Modules.Ids.BASE)
public class Attacking extends Feature {

	@Config
	@Label(name = "Melee Attacks Attribute Based", description = "If true melee monsters (zombies, etc) will attack based off the forge:attack_range attribute. Increasing it will make mobs attack for farther away. Be aware that the attack doesn't check if there are block between the target and the mob so might result in mobs attacking through walls (like spiders already do in vanilla)")
	public static Boolean meleeAttacksAttributeBased = false;

	@Config
	@Label(name = "Attack Speed.Enabled", description = "If true melee monsters (zombies, etc) attack rate is defined by their attack speed -40%, minumum once every 0.5 seconds with no weapon. This effectively buffs any mob that has no weapon.")
	public static Boolean meleeAttackSpeedBased = true;
	@Config(min = 0d, max = 4d)
	@Label(name = "Attack Speed.Multiplier", description = "Multiplies the attack speed of monsters by this value. By default 0.6 means that mobs attack 40% slower than the player with the same equipment")
	public static Double attackSpeedMultiplier = 0.6d;

	public Attacking(Module module, boolean enabledByDefault, boolean canBeDisabled) {
		super(module, enabledByDefault, canBeDisabled);
	}

	public static void attributeModificationEvent(EntityAttributeModificationEvent event) {
		for (EntityType<? extends LivingEntity> entityType : event.getTypes()) {
			if (!event.has(entityType, ForgeMod.ENTITY_REACH.get()))
				event.add(entityType, ForgeMod.ENTITY_REACH.get(), entityType.getWidth() * 2d);
			if (!event.has(entityType, Attributes.ATTACK_SPEED))
				event.add(entityType, Attributes.ATTACK_SPEED, 4d);
		}
	}

	public static Boolean shouldChangeAttackRange() {
		return isEnabled(Attacking.class) && meleeAttacksAttributeBased;
	}

	public static Boolean shouldUseAttackSpeedAttribute() {
		return isEnabled(Attacking.class) && meleeAttackSpeedBased;
	}
}
