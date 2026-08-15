package firenh.enchantmentpower.gamerule;

import com.mojang.brigadier.arguments.ArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.serialization.Codec;
import firenh.enchantmentpower.EnchantmentPower;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.flag.FeatureFlagSet;
import net.minecraft.world.level.gamerules.*;

import java.util.function.ToIntFunction;

public class EPGamerules {
    public static void init() { }

    public static GameRule<Integer> ANVIL_COST_CAP = registerInteger("anvil_cost_cap",
            GameRuleCategory.PLAYER,
            39,
            -1,
            Integer.MAX_VALUE
    );

    public static GameRule<Integer> ANVIL_COST_PERCENTAGE_MULTIPLIER = registerInteger("anvil_cost_percentage_multiplier",
            GameRuleCategory.PLAYER,
            50,
            0,
            1000
    );

    private static GameRule<Integer> registerInteger(final String id, final GameRuleCategory category, final int defaultValue, final int min, final int max) {
        return register(id, category, GameRuleType.INT, IntegerArgumentType.integer(min, max), Codec.intRange(min, max), defaultValue, FeatureFlagSet.of(), GameRuleTypeVisitor::visitInteger, (i) -> i);
    }

    private static <T> GameRule<T> register(final String id, final GameRuleCategory category, final GameRuleType typeHint, final ArgumentType<T> argumentType, final Codec<T> codec, final T defaultValue, final FeatureFlagSet requiredFeatures, final GameRules.VisitorCaller<T> visitorCaller, final ToIntFunction<T> commandResultFunction) {
        return (GameRule<T>) Registry.register(
                BuiltInRegistries.GAME_RULE,
                EnchantmentPower.id(id),
                new GameRule<T>(
                        category,
                        typeHint,
                        argumentType,
                        visitorCaller,
                        codec,
                        commandResultFunction,
                        defaultValue,
                        FeatureFlagSet.of()
                )
        );
    }
}
