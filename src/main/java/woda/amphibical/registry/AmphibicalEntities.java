package woda.amphibical.registry;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;

public class AmphibicalEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITIES, Amphibical.MOD_ID);

    public static final RegistryObject<EntityType<PinocchioSwordmasterEntity>> PINOCCHIO_SWORDMASTER = create("pinocchio_swordmaster", EntityType.Builder.of(PinocchioSwordmasterEntity::new, MobCategory.MONSTER).sized(0.6f, 185f));

    private static <T extends Entity> RegistryObject<EntityType<T>> create(String name, EntityType.Builder<T> builder) {
        return ENTITIES.register(name, () -> builder.build(Amphibical.MOD_ID + "." + name));
    }
}
