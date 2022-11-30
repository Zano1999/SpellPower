package net.spelldamage.forge;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegisterEvent;
import net.spelldamage.SpellDamageMod;
import net.minecraftforge.fml.common.Mod;
import net.spelldamage.api.enchantment.Enchantments_SpellDamage;
import net.spelldamage.internals.Attributes;

@Mod(SpellDamageMod.MOD_ID)
public class ForgeMod {
    public ForgeMod() {
        // Submit our event bus to let architectury register our content on the right time
        // EventBuses.registerModEventBus(SpellDamage.MOD_ID, FMLJavaModLoadingContext.get().getModEventBus());
        SpellDamageMod.init();
        SpellDamageMod.configureEnchantments();
    }

    @SubscribeEvent
    public void register(RegisterEvent event) {
        // These don't seem to do anything :D
        event.register(ForgeRegistries.Keys.ATTRIBUTES,
                helper -> {
                    for(var entry: Attributes.all.entrySet()) {
                        helper.register(entry.getValue().id, entry.getValue().attribute);
                    }
                }
        );
        event.register(ForgeRegistries.Keys.ENCHANTMENTS,
                helper -> {
                    for(var entry: Enchantments_SpellDamage.all.entrySet()) {
                        helper.register(entry.getKey(), entry.getValue());
                    }
                }
        );
        event.register(ForgeRegistries.Keys.MOB_EFFECTS,
                helper -> {
                    for(var entry: Attributes.all.entrySet()) {
                        helper.register(entry.getValue().id, entry.getValue().statusEffect);
                    }
                }
        );
    }
}