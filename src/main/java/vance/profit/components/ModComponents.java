package vance.profit.components;

import com.mojang.serialization.Codec;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.network.codec.PacketCodecs;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import vance.profit.Guaranteed_profit;

import java.util.function.UnaryOperator;

public class ModComponents {

    public static final ComponentType<ItemStack> ORIGINALITEM =
            register("original_item", itemStackBuilder -> itemStackBuilder.codec(ItemStack.CODEC));

    public static final ComponentType<Boolean> TRANSFORMABLE =
            register("transformable", booleanBuilder ->  booleanBuilder.codec(Codec.BOOL).packetCodec(PacketCodecs.BOOLEAN));

    private static <T>ComponentType<T> register(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Guaranteed_profit.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    public static void initialize() {
        Guaranteed_profit.LOGGER.info("Registering {} components", Guaranteed_profit.MOD_ID);
    }

}
