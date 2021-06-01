package me.morowo.MOrowoAddons.utils;

public enum Category {
    MAIN("morowoaddons_main_setting");

    private final String name;

    Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
