package jonnathan;

import java.awt.Color;
import java.io.PrintStream;

import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.robotinterfaces.peer.IStandardRobotPeer;

public class Jonnathan extends Robot {
    PrintStream out;
    IStandardRobotPeer peer;

    boolean scanned = false;

    public void run() {
        setBodyColor(Color.orange);
        setGunColor(Color.orange);
        setRadarColor(Color.red);
        setScanColor(Color.red);
        setBulletColor(Color.red);

        while (true) {
            turnGunLeft(20);
            // System.out.println(scanned);
            scanned = false;
            // System.out.println("hi");
            // peer.move(100); // Move ahead 100
            // peer.turnGun(Math.PI * 2); // Spin gun around
            // peer.move(-100); // Move back 100
            // peer.turnGun(Math.PI * 2); // Spin gun around

        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getDistance() < 20) {
            fire(1);
        } else {
            fire(1);
            turnLeft(Math.random() * 180);
            ahead(50);
        }
        scan();
    }

    // public void onHitByBullet(HitByBulletEvent e) {
    // peer.turnBody(Math.PI / 2 + e.getBearingRadians());
    // }

    // public void onStatus(StatusEvent e) {
    // }

    // public void onBulletHit(BulletHitEvent e) {
    // }

    // public void onBulletHitBullet(BulletHitBulletEvent e) {
    // }

    // public void onBulletMissed(BulletMissedEvent e) {
    // }

    // public void onDeath(DeathEvent e) {
    // }

    // public void onHitRobot(HitRobotEvent e) {
    // }

    // public void onHitWall(HitWallEvent e) {
    // }

    // public void onRobotDeath(RobotDeathEvent e) {
    // }

    // public void onWin(WinEvent e) {
    // }
}
