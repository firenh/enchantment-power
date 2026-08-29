package firenh.enchantmentpower.mixin;

import firenh.enchantmentpower.gamerule.EPGamerules;
import firenh.enchantmentpower.gamerule.GameruleHack;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.player.Player;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Objects;

@Mixin(Player.class)
public class PlayerMixin {
    @Inject(method = "getEnchantmentSeed", at = @At("HEAD"), cancellable = true)
    private void getEnchantmentSeed(CallbackInfoReturnable<Integer> info) {
        try {
            boolean bypassReseeding = Objects.requireNonNull(
                    ((Player)(Object)this).level()
            ).getServer().getGameRules().get(EPGamerules.REROLL_ENCHANTMENTS_WHEN_SLOTS_REFRESHED);

            GameruleHack.reroll_enchantments.setValue(bypassReseeding);

            if (bypassReseeding) {
                info.setReturnValue(((Player)(Object)this).getRandom().nextInt());
            }
        } catch (NullPointerException _) {}
    }
}
