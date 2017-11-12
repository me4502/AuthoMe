package com.me4502.authome;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@Mod(
        modid = AuthoMe.MOD_ID,
        name = AuthoMe.MOD_NAME,
        version = AuthoMe.VERSION
)
public class AuthoMe {

    public static final String MOD_ID = "authome";
    public static final String MOD_NAME = "AuthoMe";
    public static final String VERSION = "1.0-SNAPSHOT";

    private String password;

    /** This is the instance of your mod as created by Forge. It will never be null. */
    @Mod.Instance(MOD_ID)
    public static AuthoMe INSTANCE;

    /**
     * This is the first initialization event. Register tile entities here.
     * The registry events below will have fired prior to entry to this method.
     */
    @Mod.EventHandler
    public void preinit(FMLPreInitializationEvent event) {

    }

    /**
     * This is the second initialization event. Register custom recipes
     */
    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {

    }

    /**
     * This is the final initialization event. Register actions from other mods here
     */
    @Mod.EventHandler
    @SideOnly(Side.CLIENT)
    public void postinit(FMLPostInitializationEvent event) {
        this.password = System.getProperty("authme_password", null);
        if (this.password != null) {
            MinecraftForge.EVENT_BUS.register(this);
        }
    }

    @SideOnly(Side.CLIENT)
    @SubscribeEvent
    public void onChat(ClientChatReceivedEvent event) {
        if (password != null) {
            if (event.getMessage().getUnformattedText().contains("/register")) {
                Minecraft.getMinecraft().player.sendChatMessage("/register " + password + " " + password);
            } else if (event.getMessage().getUnformattedText().contains("/login")) {
                Minecraft.getMinecraft().player.sendChatMessage("/login " + password);
            }
        }
    }
}
