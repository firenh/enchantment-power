package firenh.enchantmentpower.mixin;

import net.minecraft.world.inventory.AnvilMenu;
import net.minecraft.world.inventory.DataSlot;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
 
@Mixin(AnvilMenu.class)
public interface AnvilScreenHandlerAccessor {
    @Accessor("cost")
    public DataSlot getCost();

    @Accessor
    public int getRepairItemCountCost();
    
    @Accessor("repairItemCountCost")
    public void setRepairItemCountCost(int repairItemUsage);


}
