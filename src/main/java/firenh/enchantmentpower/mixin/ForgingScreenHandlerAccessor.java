package firenh.enchantmentpower.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.ItemCombinerMenu;
import net.minecraft.world.inventory.ResultContainer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(ItemCombinerMenu.class)
public interface ForgingScreenHandlerAccessor {
    @Accessor
    public Container getInputSlots();

    @Accessor
    public ResultContainer getResultSlots();

    @Accessor("player")
    public Player getPlayer();
}
