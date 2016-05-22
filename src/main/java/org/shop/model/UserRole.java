package org.shop.model;

/**
 * The enum User role.
 * Created by vprasanna on 5/22/2016.
 */
public enum UserRole {
    /**
     * Admin user role.
     */
    ADMIN(10),
    /**
     * User user role.
     */
    USER(0),
    /**
     * System user role.
     */
    SYSTEM(100);

    private final int level;
    UserRole(int level) {
        this.level = level;
    }

    /**
     * Gets level.
     *
     * @return the level
     */
    public int getLevel() {
        return level;
    }
}
