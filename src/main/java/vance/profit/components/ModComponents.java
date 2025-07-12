package vance.profit.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.component.type.ItemEnchantmentsComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.recipe.Recipe;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.dynamic.Codecs;
import vance.profit.Guaranteed_profit;

import java.util.List;
import java.util.function.UnaryOperator;

public class ModComponents {

    public static final ComponentType<ItemStack> ORIGINALITEM =
            register("original_item", itemStackBuilder -> itemStackBuilder.codec(ItemStack.CODEC));

    public static final ComponentType<Boolean> TRANSFORMABLE =
            register("transformable", booleanBuilder ->  booleanBuilder.codec(Codec.BOOL).packetCodec(PacketCodecs.BOOLEAN));

    public static final ComponentType<Integer> WEAPON_ID =
            register("weapon_id", integerBuilder -> integerBuilder.codec(Codecs.POSITIVE_INT).packetCodec(PacketCodecs.VAR_INT));

    public static final ComponentType<List<ItemEnchantmentsComponent>> TRANSFORMABLE_ENCHANTS =
            register("transformable_enchants", listBuilder -> listBuilder.codec(ItemEnchantmentsComponent.CODEC.listOf()).cache());

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Guaranteed_profit.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void initialize() {
        Guaranteed_profit.LOGGER.info("Registering {} components", Guaranteed_profit.MOD_ID);
    }

}
