package com.asteroids.game.manager;

import com.asteroids.game.container.Container;
import com.asteroids.game.model.Bullet;
import com.asteroids.game.model.Player;

public class Collider<PlayerType extends Player> {
    private final Container<PlayerType> playersContainer;
    private final Container<Bullet> bulletsContainer;

    public Collider(Container<PlayerType> playersContainer, Container<Bullet> bulletsContainer) {
        this.playersContainer = playersContainer;
        this.bulletsContainer = bulletsContainer;
    }

    public void checkCollisions() {
        bulletsContainer.stream()
                .forEach(bullet -> playersContainer.stream()
                        .filter(player -> player.getShip().isPresent())
                        .filter(player -> player.getShip().get().collidesWith(bullet))
                        .findFirst()
                        .ifPresent(player -> {
                            player.noticeHit();
                            bullet.noticeHit();
                        }));
    }
}
