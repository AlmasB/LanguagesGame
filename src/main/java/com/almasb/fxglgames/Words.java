package com.almasb.fxglgames;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Almas Baimagambetov (almaslvl@gmail.com)
 */
public final class Words {

    public static final List<WordData> CHINESE_MANDARIN = new ArrayList<>();

    static {
        CHINESE_MANDARIN.add(new WordData("good", "好", "hǎo"));
        CHINESE_MANDARIN.add(new WordData("day of a month", "号", "hào"));
        CHINESE_MANDARIN.add(new WordData("study", "学", "xué"));
        CHINESE_MANDARIN.add(new WordData("snow", "雪", "xuě"));
        CHINESE_MANDARIN.add(new WordData("what", "什么", "shénme"));
        CHINESE_MANDARIN.add(new WordData("how", "怎么", "zěnme"));
        CHINESE_MANDARIN.add(new WordData("who", "谁", "shéi"));
        CHINESE_MANDARIN.add(new WordData("often", "经常", "jīngcháng"));
        CHINESE_MANDARIN.add(new WordData("hope", "希望", "xīwàng"));
    }
}
