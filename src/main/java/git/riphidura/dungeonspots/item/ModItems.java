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
import net.minecraft.util.Rarity;


public class ModItems {
	public static final Item MY_SHERD = register("my_sherd", Item::new, new Item.Settings().
			component(SherdsApiDataComponents.SHERD_PATTERN.get(), Identifier.of(DungeonsPots.MOD_ID, "my_sherd"))
			.rarity(Rarity.UNCOMMON));

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
		.register((itemGroup) -> itemGroup.addAfter(Items.SNORT_POTTERY_SHERD, ModItems.MY_SHERD));
	}
}