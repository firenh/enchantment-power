//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package firenh.enchantmentpower.loot;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import firenh.enchantmentpower.EnchantmentPower;
import firenh.enchantmentpower.util.EnchantingUtil;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.core.registries.codec.RegistryCodecs;
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
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProvider;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;
import org.jspecify.annotations.NonNull;

import java.util.Optional;
import java.util.Set;

public class OverenchantedLootItemFunction extends LootItemConditionalFunction {
    public static final MapCodec<OverenchantedLootItemFunction> MAP_CODEC = RecordCodecBuilder.mapCodec(
            (i) ->
                    commonFields(i).and(
                        i.group(
                            ContextIntProviders.CODEC
                                .fieldOf("total_overenchantments")
                                .forGetter((f) -> f.totalOverenchantments),
                            RegistryCodecs.holderSet(Registries.ENCHANTMENT)
                                .fieldOf("enchantments")
                                .forGetter((f) -> f.enchantments),
                            Codec.BOOL.optionalFieldOf("include_additional_cost_component", false).forGetter((f) -> f.includeAdditionalCostComponent))
                    ).apply(i, OverenchantedLootItemFunction::new)
    );
    private final Holder<ContextIntProvider> totalOverenchantments;
    private final HolderSet<Enchantment> enchantments;
    private final boolean includeAdditionalCostComponent;

    private OverenchantedLootItemFunction(final Optional<Holder<LootItemCondition>> condition, final Holder<ContextIntProvider> levels, final HolderSet<Enchantment> enchantments, final boolean includeAdditionalCostComponent) {
        super(condition);
        this.totalOverenchantments = levels;
        this.enchantments = enchantments;
        this.includeAdditionalCostComponent = includeAdditionalCostComponent;
    }

    public @NonNull MapCodec<OverenchantedLootItemFunction> codec() {
        return MAP_CODEC;
    }

    public @NonNull Set<ContextKey<?>> getReferencedContextParams() {
        return this.includeAdditionalCostComponent ? Set.of(LootContextParams.ADDITIONAL_COST_COMPONENT_ALLOWED) : Set.of();
    }

    public void validate(final @NonNull ValidationContext context) {
        super.validate(context);
        Validatable.validateHolder(context, "totalOverenchantments", this.totalOverenchantments);
    }

    public @NonNull ItemStack run(final ItemStack itemStack, final LootContext context) {
        return EnchantingUtil.overenchant(context.getRandom(), itemStack.copy(), enchantments, totalOverenchantments.value().getInt(context));
    }

    public static Builder enchantWithLevels(final HolderLookup.Provider registries, final Holder<ContextIntProvider> levels) {
        return (new Builder(levels)).withOptions(registries.lookupOrThrow(Registries.ENCHANTMENT).getOrThrow(EnchantmentTags.ON_RANDOM_LOOT));
    }

    public static class Builder extends LootItemConditionalFunction.Builder<Builder> {
        private final Holder<ContextIntProvider> levels;
        private HolderSet<Enchantment> options;
        private boolean includeAdditionalCostComponent = false;

        public Builder(final Holder<ContextIntProvider> levels) {
            this.levels = levels;
        }

        protected @NonNull Builder getThis() {
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

        public @NonNull LootItemFunction build() {
            return new OverenchantedLootItemFunction(this.getCondition(), this.levels, this.options, this.includeAdditionalCostComponent);
        }
    }
}
