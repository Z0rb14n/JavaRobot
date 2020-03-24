/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jon;

import java.awt.AWTException;
import java.awt.Point;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author adminasaurus
 */
public class JavaRobot {
    static int mouseX = -1;
    static int mouseY = -1;
    static final long rate = Math.floorDiv(1000,60);
    static RobotWrapper bot;
    //</editor-fold>
    /**
     * Args are unused
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
          bot = new RobotWrapper();
        } catch (AWTException e) {}
        final Runnable lol = () -> {
            // put all repeating code here
            // DO NOT MULTITHREAD
            // IF ANY, IT'S BETTER TO USE A WHILE TRUE THAN THIS.
            Point mousePos = RobotWrapper.getMousePosition();
            mouseX = (int) mousePos.getX();
            mouseY = (int) mousePos.getY();
            System.out.println(mouseX + ", " + mouseY);
        };
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(lol, 0, rate, TimeUnit.MILLISECONDS);
    }
}
