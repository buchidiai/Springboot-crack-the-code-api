/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.aspire.crackthecodeapi.service.util;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author louie
 */
public class Util {

    private static final String GAME_STATUS_IN_PROGRESS = "in-Progress";
    private static final String GAME_STATUS_FINISHED = "finished";
    private static final LocalDateTime DATE_TIME = LocalDateTime.now();

    public static boolean isUnique(String input) { // Create a Set to insert characters
        Set<Character> set = new HashSet<>();
        // get all characters form String
        char[] characters = input.toCharArray();
        for (Character c : characters) {
            if (!set.add(c)) {
                return false;
            }
        }
        return true;
    }

    public static String getGAME_STATUS_IN_PROGRESS() {
        return GAME_STATUS_IN_PROGRESS;
    }

    public static String getGAME_STATUS_FINISHED() {
        return GAME_STATUS_FINISHED;
    }

    public static LocalDateTime getDATE_TIME() {
        return DATE_TIME;
    }

}
