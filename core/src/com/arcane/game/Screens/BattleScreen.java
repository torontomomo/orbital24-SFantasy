package com.arcane.game.Screens;

import com.arcane.game.Actors.Charlotte;
import com.arcane.game.Actors.Dracula;
import com.arcane.game.Actors.Group.Draculas;
import com.arcane.game.ArcaneOdyssey;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.FillViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import java.util.LinkedList;

public class BattleScreen extends ArcaneScreen{
    private Draculas draculas;
    private Charlotte charlotte;
    private Stage stage;
    private Viewport viewport;
    private float width;
    private float height;
    LinkedList<Texture> textures;
    public BattleScreen(ArcaneOdyssey game) {
        this.mainGame = game;
        textures = new LinkedList<>();
        width = mainGame.getTotalWidth();
        height = mainGame.getTotalHeight();
        this.viewport = new FillViewport(width, height);
        this.stage = new Stage(viewport);

        charlotte = new Charlotte(textures, "char.png");
        charlotte.setScale(5, 5);

        draculas = new Draculas();
        draculas.addActor(new Dracula(textures, "enemy1.png"));
        draculas.addActor(new Dracula(textures, "enemy1.png"));
        draculas.setPosition(width - draculas.getWidth(), 0);

        stage.addActor(charlotte);
        stage.addActor(draculas);
        stage.getActors().forEach((x) -> x.setY(100));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        stage.act();
        stage.draw();
    }


}