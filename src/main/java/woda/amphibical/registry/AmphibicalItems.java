package woda.amphibical.registry;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.food.Foods;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import woda.amphibical.Amphibical;

public class AmphibicalItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, Amphibical.MOD_ID);

    public static final RegistryObject<Item> PINOCCHIO_SWORDMASTER_SPAWN_EGG = ITEMS.register("pinocchio_swordmaster_spawn_egg", () -> new ForgeSpawnEggItem(AmphibicalEntities.PINOCCHIO_SWORDMASTER, 0x872c29, 0x718b4d, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> RAIN_FROG_SPAWN_EGG = ITEMS.register("rain_frog_spawn_egg", () -> new ForgeSpawnEggItem(AmphibicalEntities.RAIN_FROG, 0x872c21, 0x218b4d, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> FRUG_SPAWN_EGG = ITEMS.register("frug_spawn_egg", () -> new ForgeSpawnEggItem(AmphibicalEntities.FRUG, 0x372c22, 0x218b7d, new Item.Properties().tab(CreativeModeTab.TAB_MISC)));
    public static final RegistryObject<Item> FRUG = ITEMS.register("frug", () -> new Item(new Item.Properties().tab(CreativeModeTab.TAB_MISC).stacksTo(16).food(Foods.ROTTEN_FLESH)));

}
