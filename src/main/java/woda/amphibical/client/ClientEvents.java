package woda.amphibical.client;

import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import woda.amphibical.Amphibical;
import woda.amphibical.client.renderer.FrugRenderer;
import woda.amphibical.client.renderer.RainFrogRenderer;
import woda.amphibical.client.renderer.RainFroggleRenderer;
import woda.amphibical.registry.AmphibicalEntities;

@Mod.EventBusSubscriber(modid = Amphibical.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEvents {

    @SubscribeEvent
    public static void clientSetup(FMLClientSetupEvent event) {
        EntityRenderers.register(AmphibicalEntities.RAIN_FROG.get(), RainFrogRenderer::new);
        EntityRenderers.register(AmphibicalEntities.RAIN_FROGGLE.get(), RainFroggleRenderer::new);
        EntityRenderers.register(AmphibicalEntities.FRUG.get(), FrugRenderer::new);
    }
}
