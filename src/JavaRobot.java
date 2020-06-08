/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

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
    private static int mouseX = -1;
    private static int mouseY = -1;
    private static final long rate = Math.floorDiv(1000,60);
    //</editor-fold>
    /**
     * Args are unused
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            RobotWrapper bot = new RobotWrapper();
            bot.mouseMove(100,100);
        } catch (AWTException e) {
            return;
        }
        final Runnable lol = () -> {
            // put all repeating code here
            Point mousePos = RobotWrapper.getMousePosition();
            mouseX = (int) mousePos.getX();
            mouseY = (int) mousePos.getY();
            System.out.println(mouseX + ", " + mouseY);
        };
        final ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(lol, 0, rate, TimeUnit.MILLISECONDS);
    }
}
