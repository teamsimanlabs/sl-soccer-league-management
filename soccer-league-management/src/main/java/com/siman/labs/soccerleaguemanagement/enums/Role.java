package com.siman.labs.soccerleaguemanagement.enums;

public enum Role {
    COACH,
    REFEREE,
    ADMINISTRATOR;

    public static boolean isValid(String roleStr) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleStr)) {
                return true;
            }
        }
        return false;
    }

    public static Role fromString(String roleStr) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(roleStr)) {
                return role;
            }
        }
        throw new IllegalArgumentException("No constant with text " + roleStr + " found");
    }
}
