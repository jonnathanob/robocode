package jonnathan;

import java.awt.Color;
import java.awt.geom.Point2D;
import java.io.PrintStream;

import robocode.AdvancedRobot;
import robocode.BulletHitBulletEvent;
import robocode.BulletMissedEvent;
import robocode.HitByBulletEvent;
import robocode.HitRobotEvent;
import robocode.HitWallEvent;
import robocode.Robot;
import robocode.ScannedRobotEvent;
import robocode.TurnCompleteCondition;
import robocode.robotinterfaces.peer.IStandardRobotPeer;
import robocode.util.Utils;

public class Jonnathan extends Robot {
    boolean onEnemy = false;

    boolean leftTurn = true;

    public void run() {
        setBodyColor(Color.orange);
        setGunColor(Color.orange);
        setRadarColor(Color.red);
        setScanColor(Color.red);
        setBulletColor(Color.red);

        ahead(getBattleFieldWidth() - getX());
        while (true) {
            if (!onEnemy)
                turnLeft(10);
            else
                fire(1);
            onEnemy = false;

        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (getEnergy() > 10) {
            fire(3);
            onEnemy = true;
        } else {
            if (getEnergy() >= 1)
                fire(1);
            else
                moveSomewhere(0);
        }
        scan();
    }

    public void onHitByBullet(HitByBulletEvent e) {
        if (leftTurn)
            turnLeft(Math.random() * 100);
        else
            turnRight(Math.random() * 100);
        leftTurn = !leftTurn;
        back(Math.random() * 200 + 20);
    }

    public void onBulletHitBullet(BulletHitBulletEvent e) {
        moveSomewhere(20);
    }

    public void onBulletMissed(BulletMissedEvent e) {
        moveSomewhere(0);
    }

    public void onHitRobot(HitRobotEvent e) {
        moveSomewhere(90);
    }

    public void onHitWall(HitWallEvent e) {
        if (e.getBearing() == 0)
            turnRight(90);
        else
            turnLeft(getHeading() % 90);

        ahead(100);
    }

    void moveSomewhere(int minTurn) {
        if (leftTurn)
            turnLeft(Math.random() * 100 + minTurn);
        else
            turnRight(Math.random() * 100 + minTurn);
        leftTurn = !leftTurn;
        ahead(100);
    }
}
