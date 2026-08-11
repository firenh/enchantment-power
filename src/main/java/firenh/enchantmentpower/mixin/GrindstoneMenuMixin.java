package firenh.enchantmentpower.mixin;

import com.mojang.datafixers.util.Pair;
import firenh.enchantmentpower.util.GrindstoneUtil;
import net.minecraft.world.Container;
import net.minecraft.world.inventory.GrindstoneMenu;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GrindstoneMenu.class)
public class GrindstoneMenuMixin {

//    @Inject(method = "createResult", at = @At("RETURN"))
//    private void createResult(CallbackInfo info) {
//        Container input = ((GrindstoneMenuAccessor) (GrindstoneMenu) (Object) this).getRepairSlots();
//        Container output = ((GrindstoneMenuAccessor) (GrindstoneMenu) (Object) this).getResultSlots();
//
//        if (GrindstoneUtil.isUsingBook(input)) {
//            Pair<ItemStack, ItemStack> update = GrindstoneUtil.makeResult(output.getItem(0));
//
//            output.setItem(0, update.getFirst());
//            input.setItem(1, update.getSecond());
//
//            ((GrindstoneMenuAccessor) (GrindstoneMenu) (Object) this).setResultSlots(output);
//            ((GrindstoneMenuAccessor) (GrindstoneMenu) (Object) this).setRepairSlots(input);
//
//        }
//
//
//    }
}
