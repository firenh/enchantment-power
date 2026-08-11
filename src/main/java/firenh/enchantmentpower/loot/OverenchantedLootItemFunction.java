//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package firenh.enchantmentpower.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import firenh.enchantmentpower.util.EnchantingUtil;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistryCodecs;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.util.context.ContextKey;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.storage.loot.LootContext;
import net.minecraft.world.level.storage.loot.Validatable;
import net.minecraft.world.level.storage.loot.ValidationContext;
import net.minecraft.world.level.storage.loot.functions.LootItemConditionalFunction;
import net.minecraft.world.level.storage.loot.functions.LootItemFunction;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.NumberProvider;
import net.minecraft.world.level.storage.loot.providers.number.NumberProviders;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public class OverenchantedLootItemFunction extends LootItemConditionalFunction {
    public static final MapCodec<OverenchantedLootItemFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            (i) ->
                    commonFields(i).and(i.group(NumberProviders.CODEC
                            .fieldOf("total_overenchantments").forGetter((f) -> f.totalOverenchantments), RegistryCodecs.homogeneousList(Registries.ENCHANTMENT)
                            .fieldOf("enchantments").forGetter((f) -> f.enchantments),
                            Codec.BOOL.optionalFieldOf("include_additional_cost_component", false).forGetter((f) -> f.includeAdditionalCostComponent))).apply(i, OverenchantedLootItemFunction::new));
    private final NumberProvider totalOverenchantments;
    private final HolderSet<Enchantment> enchantments;
    private final boolean includeAdditionalCostComponent;

    private OverenchantedLootItemFunction(final List<LootItemCondition> predicates, final NumberProvider levels, final HolderSet<Enchantment> enchantments, final boolean includeAdditionalCostComponent) {
        super(predicates);
        this.totalOverenchantments = levels;
        this.enchantments = enchantments;
        this.includeAdditionalCostComponent = includeAdditionalCostComponent;
    }

    public MapCodec<OverenchantedLootItemFunction> codec() {
        return MAP_CODEC;
    }

    public Set<ContextKey<?>> getReferencedContextParams() {
        return this.includeAdditionalCostComponent ? Set.of(LootContextParams.ADDITIONAL_COST_COMPONENT_ALLOWED) : Set.of();
    }

    public void validate(final ValidationContext context) {
        super.validate(context);
        Validatable.validate(context, "totalOverenchantments", this.totalOverenchantments);
    }

    public ItemStack run(final ItemStack itemStack, final LootContext context) {
        return EnchantingUtil.overenchant(context.getRandom(), itemStack.copy(), enchantments, totalOverenchantments.getInt(context));
    }

    public static Builder enchantWithLevels(final HolderLookup.Provider registries, final NumberProvider levels) {
        return (new Builder(levels)).withOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ON_RANDOM_LOOT));
    }

    public static class Builder extends LootItemConditionalFunction.Builder<Builder> {
        private final NumberProvider levels;
        private HolderSet<Enchantment> options;
        private boolean includeAdditionalCostComponent = false;

        public Builder(final NumberProvider levels) {
            this.levels = levels;
        }

        protected Builder getThis() {
            return this;
        }

        public Builder withOptions(final HolderSet<Enchantment> options) {
            this.options = options;
            return this;
        }

        public Builder includeAdditionalCostComponent() {
            this.includeAdditionalCostComponent = true;
            return this;
        }

        public LootItemFunction build() {
            return new OverenchantedLootItemFunction(this.getConditions(), this.levels, this.options, this.includeAdditionalCostComponent);
        }
    }
}
