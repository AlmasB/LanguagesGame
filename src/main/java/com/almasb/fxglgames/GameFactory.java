package com.almasb.fxglgames;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.dsl.components.ProjectileComponent;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import javafx.geometry.Point2D;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class GameFactory implements EntityFactory {

    @Spawns("wordItem")
    public Entity newWordEntity(SpawnData data) {
        String string = data.get("string");

        var textView = new Text(string);
        textView.setFont(Font.font(26));

        var bg = new Rectangle(
                10 + textView.getLayoutBounds().getWidth() * 1.35,
                10 + textView.getLayoutBounds().getHeight() * 1.2,
                Color.WHEAT
        );
        bg.setArcWidth(5);
        bg.setArcHeight(5);
        bg.setStrokeWidth(2);
        bg.setStroke(Color.BLACK);

        return entityBuilder(data)
                .bbox(new HitBox(BoundingShape.box(bg.getWidth(), bg.getHeight())))
                .view(new StackPane(
                        bg, textView
                ))
                .with("viewBG", bg)
                .with(new ProjectileComponent(new Point2D(0, 1), 70).allowRotation(false))
                .onClick(e -> {
                    FXGL.<LanguagesGameApp>getAppCast().onClick(e);
                })
                .build();
    }
}
