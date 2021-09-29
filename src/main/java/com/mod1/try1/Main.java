package com.mod1.try1;

import com.mod1.try1.block.ModBlocks;
import com.mod1.try1.client.entity.mod_villager_jaw_renderer;
import com.mod1.try1.client.entity.mod_villager_neutral_renderer;
import com.mod1.try1.client.entity.mod_villager_none_renderer;
import com.mod1.try1.client.entity.mod_villager_renderer;
import com.mod1.try1.effect.ModEffects;
import com.mod1.try1.entity.ModEntities;
import com.mod1.try1.entity.custom.mod_villager_class;
import com.mod1.try1.entity.custom.mod_villager_jaw;
import com.mod1.try1.entity.custom.mod_villager_neutral;
import com.mod1.try1.entity.custom.mod_villager_none;
import com.mod1.try1.item.ModItems;
import com.mod1.try1.world.gen.ModOreGeneration;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod("try1")
public class Main
{
    public static final String MOD_ID = "try1";
    // Directly reference a log4j logger.
    private static final Logger LOGGER = LogManager.getLogger();

    public Main() {
        // Register the setup method for modloading
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModEntities.ENTITY_TYPES.register(eventBus);
        ModItems.register(eventBus);
        ModBlocks.register(eventBus);
        ModEffects.MOB_EFFECTS.register(eventBus);
        ModEffects.POTIONS.register(eventBus);
        eventBus.addListener(this::setup);
        eventBus.addListener(this::entityAttributeCreationEvent);
      //  eventBus.addListener(this::registerLayer);
        eventBus.addListener(this::registerRenderer);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        //eventBus.addListener(this::onAttributeCreate);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }



    //@SubscribeEvent
    public void entityAttributeCreationEvent(EntityAttributeCreationEvent event) {

       event.put(ModEntities.MOD_VILLAGER.get(), mod_villager_class.createAttributes().build());
        event.put(ModEntities.MOD_VILLAGER_NEUTRAL.get(), mod_villager_neutral.createAttributes().build());
        event.put(ModEntities.MOD_VILLAGER_JAW.get(), mod_villager_jaw.createAttributes().build());
        event.put(ModEntities.MOD_VILLAGER_NONE.get(), mod_villager_none.createAttributes().build());
    }



    private void setup(final FMLCommonSetupEvent event)
    {

        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        ModEffects.addPotionRecipes();
        event.enqueueWork(() -> {
            ModOreGeneration.registerConfiguredFeatures();
        });


      //     EntityAttributeCreationEvent creationEvent = new EntityAttributeCreationEvent()
      //  ;
      //  event.enqueueWork(()->{

      //      creationEvent.put( ModEntities.MOD_VILLAGER.get(),mod_villager_class.MAP );

     //   });
    }


    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("try1", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
    }

    private void clientSetup(final FMLClientSetupEvent event)
    {
        // some example code to dispatch IMC to another mod

    }

    private void processIMC(final InterModProcessEvent event)
    {
        // some example code to receive and process InterModComms from other mods
        LOGGER.info("Got IMC {}", event.getIMCStream().
                map(m->m.messageSupplier().get()).
                collect(Collectors.toList()));
    }
    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {
        // do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    //public static ModelLayerLocation MOD_VILLAGER_LAYER= new ModelLayerLocation(new ResourceLocation("textures/entity/modvillager.png"), "mod_villager");
    //@SubscribeEvent
   // public void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
   // {
    //    event.registerLayerDefinition(MOD_VILLAGER_LAYER, mod_villager_model::createBodyLayer);
   // }
    //@SubscribeEvent
    public void registerRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.MOD_VILLAGER.get(), mod_villager_renderer::new);
        event.registerEntityRenderer(ModEntities.MOD_VILLAGER_NEUTRAL.get(), mod_villager_neutral_renderer::new);
        event.registerEntityRenderer(ModEntities.MOD_VILLAGER_JAW.get(), mod_villager_jaw_renderer::new);
        event.registerEntityRenderer(ModEntities.MOD_VILLAGER_NONE.get(), mod_villager_none_renderer::new);
    }



    //@SubscribeEvent
   // public void onAttributeCreate(EntityAttributeCreationEvent event) {
        //event.put(ModEntities.NEW_MOB_1.get(), new_mob_1_class.setAttributes().build());
     //   event.put(ModEntities.MOD_VILLAGER.get(), mod_villager_class.createAttributes().build());
    //}

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD
    // Event bus for receiving Registry Events)
   // @Mod.EventBusSubscriber(bus=Mod.EventBusSubscriber.Bus.MOD)
   // public static class RegistryEvents {
  //      @SubscribeEvent
   //     public static void onBlocksRegistry(final RegistryEvent.Register<Block> blockRegistryEvent) {
            // register a new block here
   //         LOGGER.info("HELLO from Register Block");
   //     }
   // }
}
