package com.arcane.game.Actors.Characters;

import com.arcane.game.Actors.Cards.HandCards;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class Draculas extends Group {
    private final static float distance = 10F;
    private float nextPos = 0F;
    private static final float rightMargin = 0.05F;
    private static final float heightPortion = 0.3F;
    private static final float sizePortion = 0.1F;
    private float WORLD_WIDTH;
    private float WORLD_HEIGHT;
    private HandCards handCards;
    private Image arrow;
    //private Dracula dracula;

    public Draculas(float WORLD_WIDTH, float WORLD_HEIGHT, HandCards handCards) {
        super();
        setSize(0F, 0F);
        this.WORLD_WIDTH = WORLD_WIDTH;
        this.WORLD_HEIGHT = WORLD_HEIGHT;
        this.handCards = handCards;
        arrow = new Image(new TextureRegion(new Texture("UI/arrow.png")));
    }

    public boolean canSelectDracula() {
        return this.handCards.hasCardSelectedToDracula();
    }

    public void selectDracula(Dracula target) {
        float xOfArrow = target.getX() + target.getWidth() / 2;
        float yOfArrow = target.getY() + target.getHeight() + 10;
        arrow.setPosition(xOfArrow, yOfArrow);
        super.addActor(arrow);
    }

    public void unSelectDracula(Dracula target) {
        super.removeActor(arrow);
    }

    public void confirmSelection(Dracula target) {
        handCards.perform(target);
    }

    @Override
    public void addActor(Actor actor) {
        if (!(actor instanceof Dracula)) {
            return;
        }
        actor.setPosition(nextPos, 0F);
        float ratio = actor.getHeight() / actor.getWidth();
        actor.setSize(WORLD_WIDTH * sizePortion, WORLD_WIDTH * sizePortion * ratio);
        super.addActor(actor);
        nextPos += actor.getWidth() + distance;
        setSize(nextPos, 0F);
        setPosition(WORLD_WIDTH *(1.0F - rightMargin)- this.getWidth(),
                WORLD_HEIGHT * heightPortion);
    }
}