package com.almasb.fxglgames;

import com.almasb.fxgl.animation.Interpolators;
import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.core.math.FXGLMath;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.almasb.fxgl.dsl.FXGL.*;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public class LanguagesGameApp extends GameApplication {

    private WordData selectedWord = null;
    private List<Entity> selectedEntities;

    @Override
    protected void initSettings(GameSettings settings) {
        settings.setWidth(1600);
        settings.setHeight(900);
        settings.setTitle("Languages Game");
        settings.setVersion("0.1");
    }

    @Override
    protected void initGame() {
        selectedEntities = new ArrayList<>();

        getGameScene().setBackgroundColor(Color.LIGHTGRAY);
        getGameWorld().addEntityFactory(new GameFactory());

        run(() -> {
            spawnWords(List.of(
                    FXGLMath.random(Words.CHINESE_MANDARIN).get(),
                    FXGLMath.random(Words.CHINESE_MANDARIN).get(),
                    FXGLMath.random(Words.CHINESE_MANDARIN).get()
            ));
        }, Duration.seconds(3.3));
    }

    // TODO: only allow selecting distinct types, e.g. english name + target name + sound
    public void onClick(Entity word) {
        WordData parent = word.getObject("parent");

        selectedEntities.add(word);

        if (selectedWord == null) {
            selectedWord = parent;
        } else {
            if (selectedEntities.size() == 3) {
                var isOK = selectedEntities
                        .stream()
                        .allMatch(e -> e.getObject("parent") == parent);

                if (isOK) {
                    // TODO: disable selection of selectedEntities
                    selectedEntities.forEach(e -> {
                        Rectangle viewBG = e.getObject("viewBG");
                        viewBG.setStroke(Color.DARKGREEN);

                        animationBuilder()
                                .duration(Duration.seconds(0.23))
                                .interpolator(Interpolators.EXPONENTIAL.EASE_OUT())
                                .animate(viewBG.fillProperty())
                                .from(viewBG.getFill())
                                .to(Color.LIGHTGREEN)
                                .buildAndPlay();

                        despawnWithScale(e, Duration.seconds(1.0), Interpolators.BOUNCE.EASE_IN());
                    });
                } else {
                    // TODO: indicate failure
                }

                selectedEntities.clear();
            }
        }
    }

    // 3 words per spawn
    private void spawnWords(List<WordData> words) {
        var order0 = Arrays.asList(0, 1, 2);
        var order1 = Arrays.asList(0, 1, 2);
        var order2 = Arrays.asList(0, 1, 2);

        Collections.shuffle(order0);
        Collections.shuffle(order1);
        Collections.shuffle(order2);

        for (int i = 0; i < 3; i++) {
            var data = words.get(i);

            spawnWithScale("wordItem",
                    new SpawnData(100, order0.get(i) * 80).put("string", data.englishName()).put("parent", data),
                    Duration.seconds(1),
                    Interpolators.ELASTIC.EASE_OUT()
            );

            spawnWithScale("wordItem",
                    new SpawnData(400, order1.get(i) * 80).put("string", data.targetName()).put("parent", data),
                    Duration.seconds(1),
                    Interpolators.ELASTIC.EASE_OUT()
            );

            spawnWithScale("wordItem",
                    new SpawnData(700, order2.get(i) * 80).put("string", data.sound()).put("parent", data),
                    Duration.seconds(1),
                    Interpolators.ELASTIC.EASE_OUT()
            );
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
