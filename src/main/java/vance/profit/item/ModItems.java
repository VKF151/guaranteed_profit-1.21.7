package vance.profit.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.component.DataComponentTypes;
import net.minecraft.component.type.DeathProtectionComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.Rarity;
import vance.profit.Guaranteed_profit;
import vance.profit.block.ModBlocks;
import vance.profit.item.custom.*;

public class ModItems {

    public static final Item SLOT_SPINNER = registerItem("slot_spinner", new Item(
            new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, "slot_spinner")))
                    .maxCount(7)
                    .fireproof()
                    .rarity(Rarity.EPIC)
    ));

    public static final Item CRAZY_SLOTS = registerItem("crazy_slots", new CrazySlotsItem(
            new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, "crazy_slots")))
                    .maxCount(1)
                    .fireproof()
    ));

    public static final Item CRAZY_SCYTHE = registerItem("crazy_scythe", new CrazyScytheItem(
            new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, "crazy_scythe")))
                    .maxCount(1)
                    .fireproof()
                    .rarity(Rarity.RARE)
                    .hoe(ModToolMaterials.CRAZY_SLOTS, 6.0F, -2.9F)
                    .attributeModifiers(CrazyScytheItem.createAttributeModifiers())
    ));

    public static final Item CRAZY_MACE = registerItem("crazy_mace", new CrazyMaceItem(
            new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, "crazy_mace")))
                    .maxCount(1)
                    .fireproof()
                    .maxDamage(2031)
                    .rarity(Rarity.RARE)
                    .attributeModifiers(CrazyMaceItem.createAttributeModifiers())
    ));

    public static final Item CRAZY_TRIDENT = registerItem("crazy_trident", new CrazyTridentitem(
            new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, "crazy_trident")))
                    .fireproof()
                    .maxCount(1)
                    .maxDamage(2031)
                    .rarity(Rarity.RARE)
                    .attributeModifiers(TridentItem.createAttributeModifiers())
    ));

    public static final Item CRAZY_AXE = registerItem("crazy_axe", new CrazyAxeItem(
            new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, "crazy_axe")))
                    .fireproof()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
                    .axe(ModToolMaterials.CRAZY_SLOTS, 5.0F, -3.0F)
    ));

    public static final Item CRAZY_SWORD = registerItem("crazy_sword", new CrazySwordItem(
            new Item.Settings()
                    .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, "crazy_sword")))
                    .fireproof()
                    .maxCount(1)
                    .rarity(Rarity.RARE)
                    .component(DataComponentTypes.DEATH_PROTECTION, DeathProtectionComponent.TOTEM_OF_UNDYING)
                    .sword(ModToolMaterials.CRAZY_SLOTS, 3.0F, -2.4F)
    ));

    private static Item registerItem(String name, Item item) {
        return Registry.register(Registries.ITEM, Identifier.of(Guaranteed_profit.MOD_ID, name), item);
    }

    public static final RegistryKey<ItemGroup> GUARANTEED_PROFIT_GROUP_KEY = RegistryKey.of(Registries.ITEM_GROUP.getKey(), Identifier.of(Guaranteed_profit.MOD_ID, "guaranteed_profit_group"));
    public static final ItemGroup GUARANTEED_PROFIT_GROUP = FabricItemGroup.builder()
            .icon(() -> new ItemStack(ModItems.CRAZY_SLOTS))
            .displayName(Text.translatable("itemGroup.guaranteed_profit"))
            .build();

    public static void registerModItems() {
        Guaranteed_profit.LOGGER.info("Registering Mod Items for " + Guaranteed_profit.MOD_ID);

        Registry.register(Registries.ITEM_GROUP, GUARANTEED_PROFIT_GROUP_KEY, GUARANTEED_PROFIT_GROUP);

        ItemGroupEvents.modifyEntriesEvent(GUARANTEED_PROFIT_GROUP_KEY).register(fabricItemGroupEntries -> {
            fabricItemGroupEntries.add(ModBlocks.SLOT_MACHINE.asItem());
            fabricItemGroupEntries.add(ModItems.CRAZY_SLOTS);
            fabricItemGroupEntries.add(ModItems.CRAZY_SWORD);
            fabricItemGroupEntries.add(ModItems.CRAZY_SCYTHE);
            fabricItemGroupEntries.add(ModItems.CRAZY_MACE);
            fabricItemGroupEntries.add(ModItems.CRAZY_TRIDENT);
            fabricItemGroupEntries.add(ModItems.CRAZY_AXE);
            fabricItemGroupEntries.add(ModItems.SLOT_SPINNER);
        });
    }
}
