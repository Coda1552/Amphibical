package woda.amphibical.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.FrugEntity;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;
import woda.amphibical.common.entity.RainFrogEntity;

public class AmphibicalEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Amphibical.MOD_ID);

    public static final RegistryObject<EntityType<PinocchioSwordmasterEntity>> PINOCCHIO_SWORDMASTER = create("pinocchio_swordmaster", EntityType.Builder.of(PinocchioSwordmasterEntity::new, MobCategory.MONSTER).sized(0.6f, 1.85f));
    public static final RegistryObject<EntityType<RainFrogEntity>> RAIN_FROG = create("rain_frog", EntityType.Builder.of(RainFrogEntity::new, MobCategory.MONSTER).sized(0.6f, 1.85f));
    public static final RegistryObject<EntityType<FrugEntity>> FRUG = create("frug", EntityType.Builder.of(FrugEntity::new, MobCategory.MONSTER).sized(0.3f, 0.3f));


    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(Amphibical.MOD_ID + "." + name));
    }
}
