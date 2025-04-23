package git.riphidura.dungeonspots.item;

import java.util.function.Function;

import dev.thomasglasser.sherdsapi.api.SherdsApiDataComponents;
import git.riphidura.dungeonspots.DungeonsPots;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


public class ModItems {
	public static final Item WAVE_SHERD = register("wave_pottery_sherd", Item::new, new Item.Settings().
			component(SherdsApiDataComponents.SHERD_PATTERN.get(), Identifier.of(DungeonsPots.MOD_ID, "wave_pottery_sherd")));
	public static final Item ARCH_SHERD = register("arch_pottery_sherd", Item::new, new Item.Settings().
			component(SherdsApiDataComponents.SHERD_PATTERN.get(), Identifier.of(DungeonsPots.MOD_ID, "arch_pottery_sherd")));
	public static final Item VAULT_SHERD = register("vault_pottery_sherd", Item::new, new Item.Settings().
			component(SherdsApiDataComponents.SHERD_PATTERN.get(), Identifier.of(DungeonsPots.MOD_ID, "vault_pottery_sherd")));
	public static final Item EYE_SHERD = register("eye_pottery_sherd", Item::new, new Item.Settings().
			component(SherdsApiDataComponents.SHERD_PATTERN.get(), Identifier.of(DungeonsPots.MOD_ID, "eye_pottery_sherd")));

    public static Item register(String name, Function<Item.Settings, Item> itemFactory, Item.Settings settings) {
        // Create the item key.
		RegistryKey<Item> itemKey = RegistryKey.of(RegistryKeys.ITEM, Identifier.of(DungeonsPots.MOD_ID, name));

        // Create the item instance.
		Item item = itemFactory.apply(settings.registryKey(itemKey));

		// Register the item.
		Registry.register(Registries.ITEM, itemKey, item);

		return item;
    }

	public static void initialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
		.register((itemGroup) -> itemGroup.addAfter(Items.SNORT_POTTERY_SHERD, ModItems.WAVE_SHERD));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
		.register((itemGroup) -> itemGroup.addAfter(ModItems.WAVE_SHERD, ModItems.ARCH_SHERD));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
		.register((itemGroup) -> itemGroup.addAfter(ModItems.ARCH_SHERD, ModItems.VAULT_SHERD));
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS)
		.register((itemGroup) -> itemGroup.addAfter(ModItems.VAULT_SHERD, ModItems.EYE_SHERD));
	}
}