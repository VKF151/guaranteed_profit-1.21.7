package vance.profit;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.UseItemCallback;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vance.profit.block.ModBlocks;
import vance.profit.block.custom.entity.ModBlockEntities;
import vance.profit.components.ModComponents;
import vance.profit.item.ModItems;

public class Guaranteed_profit implements ModInitializer {
	public static final String MOD_ID = "guaranteed_profit";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItems.registerModItems();
		ModBlocks.initialize();
		ModBlockEntities.initialize();
		ModComponents.initialize();

		AttackEntityCallback.EVENT.register((playerEntity, world, hand, entity, entityHitResult) -> {
			ItemStack stack = playerEntity.getMainHandStack();
			if (stack.get(ModComponents.ORIGINALITEM) != null && !playerEntity.isSpectator()) {
				stack.set(ModComponents.TRANSFORMABLE, true);
			}
			return ActionResult.PASS;
		});

		UseItemCallback.EVENT.register((playerEntity, world, hand) -> {
			ItemStack stack = playerEntity.getMainHandStack();
			ItemStack originalItem = stack.get(ModComponents.ORIGINALITEM);
			if (Boolean.TRUE.equals(stack.get(ModComponents.TRANSFORMABLE)) && !playerEntity.isSpectator()) {
				if (playerEntity.isSneaking() && !playerEntity.isSwimming()) {
						playerEntity.setStackInHand(hand, originalItem);
					world.playSound(null, playerEntity.getBlockPos(),
							SoundEvents.BLOCK_AMETHYST_CLUSTER_BREAK, SoundCategory.PLAYERS,
							0.35f, 1.0f);
				}
			}
			return ActionResult.PASS;
		});



	}
}