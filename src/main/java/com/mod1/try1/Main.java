package com.mod1.try1;

import com.mod1.try1.block.ModBlocks;
import com.mod1.try1.client.entity.mod_villager_renderer;
import com.mod1.try1.client.entity.model.mod_villager_model;
import com.mod1.try1.client.entity.model.new_mob_1_model;
import com.mod1.try1.client.entity.new_mob_1_renderer;
import com.mod1.try1.effect.ModEffects;
import com.mod1.try1.entity.ModEntities;
import com.mod1.try1.entity.custom.mod_villager_class;
import com.mod1.try1.entity.custom.new_mob_1_class;
import com.mod1.try1.item.ModItems;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRenderers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.DefaultAttributes;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fmllegacy.common.network.PacketLoggingHandler;
import net.minecraftforge.fmllegacy.network.FMLMCRegisterPacketHandler;
import net.minecraftforge.fmlserverevents.FMLServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.rmi.registry.RegistryHandler;
import java.util.HashMap;
import java.util.Map;
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
        eventBus.addListener(this::registerLayer);
        eventBus.addListener(this::registerRenderer);
        // Register the enqueueIMC method for modloading
        eventBus.addListener(this::enqueueIMC);
        // Register the processIMC method for modloading
        eventBus.addListener(this::processIMC);
        //eventBus.addListener(this::onAttributeCreate);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }



    @SubscribeEvent
    public void entityAttributeCreationEvent(EntityAttributeCreationEvent event) {

        event.put(ModEntities.MOD_VILLAGER.get(), DefaultAttributes.getSupplier(null));
    }



    private void setup(final FMLCommonSetupEvent event)
    {

        LOGGER.info("HELLO FROM PREINIT");
        LOGGER.info("DIRT BLOCK >> {}", Blocks.DIRT.getRegistryName());
        ModEffects.addPotionRecipes();
        //DefaultAttributes.getSupplier(ModEntities.MOD_VILLAGER.get());
     //   Map<EntityType<? extends LivingEntity>, AttributeSupplier> MOD_ATTRIBUTES = ForgeHooks.getAttributesView();
       // EntityAttributeCreationEvent creationEvent = new EntityAttributeCreationEvent(MOD_ATTRIBUTES);
        //creationEvent.put(ModEntities.MOD_VILLAGER.get(),mod_villager_class.createAttributes().build());

        //RenderingRegistry.registerEntityRenderingHandler(ModEntityTypes.ANGEL.get(), AngelEntityRender::new);

        //
        //EntityRenderers.register();
        //
       // Map<EntityType<? extends LivingEntity>, AttributeSupplier> MOD1_ATTRIBUTES = new HashMap<>();
      //  new EntityAttributeCreationEvent(MOD1_ATTRIBUTES);
       //  ParallelDispatchEvent.enqueueWork(() ->{
      //      EntityAttributeCreationEvent.put(ModEntities.NEW_MOB_1.get(), new_mob_1_class.setAttributes().build());
      //  });
        //DeferredWorkQueue.runLater(
        //() -> {

        //
        // }
        // )
    }

    private void enqueueIMC(final InterModEnqueueEvent event)
    {
        // some example code to dispatch IMC to another mod
        InterModComms.sendTo("try1", "helloworld", () -> { LOGGER.info("Hello world from the MDK"); return "Hello world";});
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

    public static ModelLayerLocation MOD_VILLAGER_LAYER= new ModelLayerLocation(new ResourceLocation("textures/entity/modvillager.png"), "mod_villager");
    @SubscribeEvent
    public void registerLayer(EntityRenderersEvent.RegisterLayerDefinitions event)
    {
        event.registerLayerDefinition(MOD_VILLAGER_LAYER, mod_villager_model::createBodyLayer);
    }
    @SubscribeEvent
    public void registerRenderer(EntityRenderersEvent.RegisterRenderers event)
    {
        event.registerEntityRenderer(ModEntities.MOD_VILLAGER.get(), mod_villager_renderer::new);
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
