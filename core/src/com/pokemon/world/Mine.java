package com.pokemon.world;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.pokemon.game.Pokemon;
import com.pokemon.model.*;
import com.pokemon.screen.BattleScreen;
import com.pokemon.screen.GameScreen;
import com.pokemon.transition.BattleBlinkTransition;
import com.pokemon.transition.BattleTransition;
import com.pokemon.transition.FadeInTransition;
import com.pokemon.transition.FadeOutTransition;
import com.pokemon.util.Action;
import com.pokemon.util.ObjectGenerator;

import java.util.ArrayList;

import static com.pokemon.game.Settings.SCALED_TILE_SIZE;
import static com.pokemon.screen.GameScreen.getAssetManager;
import static com.pokemon.screen.GameScreen.getTweenManager;

public class Mine implements World {
    private final TileMap map = new TileMap(17, 14);
    private Player player;
    private Pokemon game;
    private GameScreen gameScreen;
    private ArrayList<WorldObject> collisionObjects;
    private Portal mainWorldPortal;
    private BattleArea battleArea;

    public Mine(Player player, Pokemon game, GameScreen gameScreen) {
        this.player = player;
        this.game = game;
        this.gameScreen = gameScreen;

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                map.tiles[x][y] = new Tile(x, y);
            }
        }
        collisionObjects = ObjectGenerator.generateCollisionObject("MineCollision");
        renderList.clear();
        renderList.add(player);
        renderList.addAll(ObjectGenerator.generateObject("Mine"));
        mainWorldPortal = new Portal(8, 2, 1, 1);

        battleArea = new BattleArea(3, 2, 11, 9);
    }

    @Override
    public TileMap getMap() {
        return map;
    }

    @Override
    public ArrayList<WorldObject> getCollisionObjects() {
        return collisionObjects;
    }

    @Override
    public ArrayList<RenderHelper> getObjects() {
        return renderList;
    }

    @Override
    public String getBackground() {
        return "Mine";
    }

    @Override
    public void update(float delta) {
        // 광산은 막혀있어서 해당 요소 불필요
        /*if (player.x < 0) {
            player.x = 0;
        }
        if (player.y < 0) {
            player.y = 0;
        }
        if (player.x > map.getWidth() * SCALED_TILE_SIZE - SCALED_TILE_SIZE) {
            player.x = map.getWidth() * SCALED_TILE_SIZE - SCALED_TILE_SIZE;
        }
        if (player.y > map.getHeight() * SCALED_TILE_SIZE - SCALED_TILE_SIZE) {
            player.y = map.getHeight() * SCALED_TILE_SIZE - SCALED_TILE_SIZE;
        }*/
        if (player.overlaps(battleArea)) {
            if (battleArea.battleStarter(delta, player.x, player.y)) {
                player.finishMove();
                gameScreen.getGameMusic().stop();
                game.getBattleMusic().play();
                gameScreen.getTransitionScreen().startTransition(
                        new BattleBlinkTransition(4f, 4 , Color.GRAY, gameScreen.getTransitionShader(), getTweenManager(), getAssetManager()),
                        new BattleTransition(1F,  10, true, gameScreen.getTransitionShader(), getTweenManager(), getAssetManager()),
                        new Action() {
                            @Override
                            public void action() {
                                System.out.println("배틀시작");
                                game.setScreen(new BattleScreen(game,player,gameScreen));
                            }
                        }
                );
            }
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.X)) {
            if (mainWorldPortal.overlaps(player) && player.getFacing() == DIRECTION.SOUTH) {
                player.finishMove();
                gameScreen.getTransitionScreen().startTransition(
                        new FadeOutTransition(0.8f, Color.BLACK, getTweenManager(), getAssetManager()),
                        new FadeInTransition(0.8f, Color.BLACK, getTweenManager(), getAssetManager()),
                        new Action() {
                            @Override
                            public void action() {
                                GameScreen.setWorld(new MainWorld(player,game,gameScreen));
                                player.setX(11);
                                player.setY(19);
                            }
                        });
            }
        }

    }
}
