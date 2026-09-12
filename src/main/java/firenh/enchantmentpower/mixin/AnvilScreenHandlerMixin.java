package firenh.enchantmentpower.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import firenh.enchantmentpower.util.AnvilUtil;
import net.minecraft.world.inventory.AnvilMenu;

@Mixin(AnvilMenu.class)
public class AnvilScreenHandlerMixin {
    @ModifyConstant(method = "createResult", constant = @Constant(intValue = 40))
    private int injected(int value) {
		return Integer.MAX_VALUE;
	}

    @Inject(at = @At("RETURN"), method = "createResult")
    public void updateResult(CallbackInfo info) {
        AnvilUtil.updateAnvilResult((AnvilMenu)(Object)this);
    }
}
