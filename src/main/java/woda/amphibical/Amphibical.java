package woda.amphibical;

import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import software.bernie.geckolib3.GeckoLib;
import woda.amphibical.common.entity.FrugEntity;
import woda.amphibical.common.entity.RainFrogEntity;
import woda.amphibical.common.entity.RainFroggleEntity;
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
        event.put(AmphibicalEntities.RAIN_FROG.get(), RainFrogEntity.createAttributes().build());
        event.put(AmphibicalEntities.RAIN_FROGGLE.get(), RainFroggleEntity.createAttributes().build());
        event.put(AmphibicalEntities.FRUG.get(), FrugEntity.createAttributes().build());

    }
}
