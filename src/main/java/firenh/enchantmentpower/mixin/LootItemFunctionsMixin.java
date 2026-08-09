package firenh.enchantmentpower.mixin;

import com.mojang.serialization.MapCodec;
import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.loot.OverenchantedLootItemFunction;
import net.minecraft.core.Registry;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunctions;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LootItemFunctions.class)
public class LootItemFunctionsMixin {
    @Inject(method = "bootstrap", at = @At("RETURN"))
    private static void bootstrap(Registry<MapCodec<? extends LootItemFunction>> registry, CallbackInfoReturnable<MapCodec<? extends LootItemFunction>> cir) {
        Registry.register(registry, EnchantmentPower.id("overenchant"), OverenchantedLootItemFunction.MAP_CODEC);
    }
}
