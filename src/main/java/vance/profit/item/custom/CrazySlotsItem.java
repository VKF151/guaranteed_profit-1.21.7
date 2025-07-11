package vance.profit.item.custom;

import net.minecraft.component.DataComponentTypes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.Rarity;
import net.minecraft.world.World;
import vance.profit.components.ModComponents;
import vance.profit.item.ModItems;

import java.util.Random;



public class CrazySlotsItem extends Item {
    public CrazySlotsItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity player, Hand hand) {

        if (!world.isClient()) {
            Random random = new Random();
            int randomNumber = random.nextInt(6) + 1;
            ItemStack newItem = getWeaponForNumber(randomNumber);

            if (randomNumber <= 3) {
                if (isHealthLow(player)) {
                    ItemStack newLuckyItem = getWeaponForNumber(randomNumber + 3);
                    player.setStackInHand(hand, newLuckyItem);
                } else if (randomNumber == 3) {
                    player.setStackInHand(hand, newItem);
                    player.getStackInHand(hand).set(DataComponentTypes.ITEM_NAME, Text.translatable("item.guaranteed_profit.crazy_crossbow"));
                    player.getStackInHand(hand).set(DataComponentTypes.RARITY, Rarity.RARE);
                    player.giveItemStack(new ItemStack(Items.SPECTRAL_ARROW));

                } else player.setStackInHand(hand, newItem);

            } else player.setStackInHand(hand, newItem);

            player.getStackInHand(hand).set(ModComponents.ORIGINALITEM, ModItems.CRAZY_SLOTS.getDefaultStack());
            player.getStackInHand(hand).set(ModComponents.TRANSFORMABLE, false);



            world.playSound(null, player.getBlockPos(), SoundEvents.BLOCK_AMETHYST_BLOCK_RESONATE, SoundCategory.PLAYERS, 0.55f, 1.25f);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }

    private ItemStack getWeaponForNumber(int number) {
        return switch (number) {
            case 1 -> new ItemStack(ModItems.CRAZY_AXE);
            case 2 -> new ItemStack(ModItems.CRAZY_TRIDENT);
            case 3 -> new ItemStack(Items.CROSSBOW);
            case 4 -> new ItemStack(ModItems.CRAZY_MACE);
            case 5 -> new ItemStack(ModItems.CRAZY_SCYTHE);
            case 6 -> new ItemStack(ModItems.CRAZY_SWORD);
            default -> ItemStack.EMPTY;
        };
    }

    /*
            case 1 -> new ItemStack(ModItems.CRAZY_AXE);
            case 2 -> new ItemStack(ModItems.CRAZY_TRIDENT);
            case 3 -> new ItemStack(Items.CROSSBOW);
            case 4 -> new ItemStack(ModItems.CRAZY_MACE);
            case 5 -> new ItemStack(ModItems.CRAZY_SCYTHE);
            case 6 -> new ItemStack(ModItems.CRAZY_SWORD);
     */

    private Boolean isHealthLow(PlayerEntity player) {
        return player.getHealth() <= 8.0F;
    }
}




