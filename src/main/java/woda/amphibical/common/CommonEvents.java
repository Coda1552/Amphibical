package woda.amphibical.common;


import com.github.alexthe666.alexsmobs.entity.EntityRoadrunner;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.ai.goal.AvoidEntityGoal;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import woda.amphibical.Amphibical;
import woda.amphibical.common.entity.RainFrogEntity;
import woda.amphibical.registry.AmphibicalEntities;

@Mod.EventBusSubscriber(modid = Amphibical.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class CommonEvents {

    @SubscribeEvent()
    public void changeAI(EntityJoinWorldEvent event){
        if(event.getEntity() instanceof EntityRoadrunner){
            ((EntityRoadrunner) event.getEntity()).goalSelector.addGoal(2, new AvoidEntityGoal((PathfinderMob) event.getEntity(), RainFrogEntity.class, 6.0F, 1.0D, 1.2D));
        }
    }

    public static void EntitySpawns(BiomeLoadingEvent event){
        if (event.getCategory() == Biome.BiomeCategory.DESERT) {
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AmphibicalEntities.RAIN_FROG.get(),30, 2, 6));
            event.getSpawns().getSpawner(MobCategory.CREATURE).add(new MobSpawnSettings.SpawnerData(AmphibicalEntities.FRUG.get(),20, 4, 6));
        }
    }

    @SubscribeEvent()
    public static void commonSetup(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            SpawnPlacements.register(AmphibicalEntities.RAIN_FROG.get(), SpawnPlacements.Type.ON_GROUND,
                    Heightmap.Types.WORLD_SURFACE, RainFrogEntity::checkMobSpawnRules);
        });
    }
}
