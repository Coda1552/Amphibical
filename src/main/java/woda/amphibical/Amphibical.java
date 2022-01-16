package woda.amphibical;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import woda.amphibical.common.entity.PinocchioSwordmasterEntity;
import woda.amphibical.registry.AmphibicalEntities;
import woda.amphibical.registry.AmphibicalItems;

@Mod(Amphibical.MOD_ID)
public class Amphibical {
    public static final String MOD_ID = "amphibical";

    public Amphibical() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        AmphibicalEntities.ENTITIES.register(bus);
        AmphibicalItems.ITEMS.register(bus);

        bus.addListener(this::createEntityAttributes);
    }

    private void createEntityAttributes(EntityAttributeCreationEvent event) {
        event.put(AmphibicalEntities.PINOCCHIO_SWORDMASTER.get(), PinocchioSwordmasterEntity.createAttributes().build());
    }
}
