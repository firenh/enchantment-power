package firenh.enchantmentpower.mixin;

import net.minecraft.world.Container;
import net.minecraft.world.inventory.DataSlot;
import net.minecraft.world.inventory.GrindstoneMenu;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(GrindstoneMenu.class)
public interface GrindstoneMenuAccessor {
    @Accessor("resultSlots")
    public Container getResultSlots();

    @Accessor("repairSlots")
    public Container getRepairSlots();

    @Accessor("resultSlots")
    public void setResultSlots(Container result);

    @Accessor("repairSlots")
    public void setRepairSlots(Container repair);

}
