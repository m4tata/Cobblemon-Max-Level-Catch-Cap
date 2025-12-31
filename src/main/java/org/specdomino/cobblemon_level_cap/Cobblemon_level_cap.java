package org.specdomino.cobblemon_level_cap;

import com.cobblemon.mod.common.entity.pokeball.EmptyPokeBallEntity;
import net.fabricmc.api.ModInitializer;

import com.cobblemon.mod.common.Cobblemon;
import com.cobblemon.mod.common.api.events.CobblemonEvents;
import com.cobblemon.mod.common.api.Priority;
import com.cobblemon.mod.common.api.events.pokeball.ThrownPokeballHitEvent;
import com.cobblemon.mod.common.api.storage.party.PlayerPartyStore;
import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.cobblemon.mod.common.pokemon.Pokemon;
import kotlin.Unit;
import net.fabricmc.api.ModInitializer;
import net.minecraft.entity.Entity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Cobblemon_level_cap implements ModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger("cobblemon-level-cap");

    @Override
    public void onInitialize() {
        LOGGER.info("Init: Level Cap Mod");

        CobblemonEvents.THROWN_POKEBALL_HIT.subscribe(Priority.HIGH, (ThrownPokeballHitEvent event) -> {
            EmptyPokeBallEntity ball = event.getPokeBall();
            Entity owner = ball.getOwner();

            if (!(owner instanceof ServerPlayerEntity player)) return Unit.INSTANCE;

            if (player.isCreative()) return Unit.INSTANCE;

            PokemonEntity hitPokemonEntity = event.getPokemon();
            int wildLevel = hitPokemonEntity.getPokemon().getLevel();

            PlayerPartyStore party = Cobblemon.INSTANCE.getStorage().getParty(player);
            int maxPartyLevel = 5;

            for (Pokemon p : party) {
                if (p.getLevel() > maxPartyLevel) {
                    maxPartyLevel = p.getLevel();
                }
            }

            if (wildLevel > maxPartyLevel) {
                event.cancel();
                player.sendMessage(
                        Text.literal("This Pokémon is too Strong! Max Catch Level: " + maxPartyLevel)
                                .formatted(Formatting.RED),
                        true
                );
            }
            return Unit.INSTANCE;
        });
    }
}
