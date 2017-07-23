package com.asteroids.game.manager;


import com.asteroids.game.container.Container;
import com.asteroids.game.model.Player;
import com.asteroids.game.model.Ship;
import com.badlogic.gdx.math.Vector2;

import java.util.Random;

public class Respawner<PlayerType extends Player> {
    private static final Random random = new Random();
    private final Container<PlayerType> playersContainer;
    private final float widthBound;
    private final float heightBound;

    public Respawner(Container<PlayerType> playersContainer, float widthBound, float heightBound) {
        this.playersContainer = playersContainer;
        this.widthBound = widthBound;
        this.heightBound = heightBound;
    }

    public void respawn() {
        playersContainer.stream()
                .filter(player -> !player.getShip().isPresent())
                .forEach(this::respawnFor);
    }

    public void respawnFor(Player player) {
        player.setShip(new Ship(player, randomRespawnPoint(), 0));
    }

    private Vector2 randomRespawnPoint() {
        return new Vector2(random.nextInt(Math.round(widthBound)), random.nextInt(Math.round(heightBound)));
    }
}
