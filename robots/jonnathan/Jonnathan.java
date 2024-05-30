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
    PrintStream out;
    IStandardRobotPeer peer;
    boolean onEnemy = false;

    boolean scanned = false;
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
            // System.out.println("J");

            // System.out.println(onEnemy);
            // System.out.println(scanned);
            // scanned = false;
            // System.out.println("hi");
            // peer.move(100); // Move ahead 100
            // peer.turnGun(Math.PI * 2); // Spin gun around
            // peer.move(-100); // Move back 100
            // peer.turnGun(Math.PI * 2); // Spin gun around

        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        // e.getDistance();
        // System.out.println("Enemy");
        int angle = (int) (Math.toDegrees(
                Utils.normalAbsoluteAngle(getGunHeading() + e.getBearingRadians()))
                + 0.5);
        System.out.println(getRadarHeading());
        System.out.println(getGunHeading());
        // turnGunLeft(angle);
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
        // Utils.normalAbsoluteAngle(Math.atan2(absBearing, distance))
        // double xForce = 0, yForce = 0;
        // System.out.println(getOthers());
        // System.out.println("UGI");
        // double absBearing = e.getBearingRadians() + getHeadingRadians();
        // enemyPoints[count] = new Point2D.Double(getX() + e.getDistance() *
        // Math.sin(absBearing),
        // getY() + e.getDistance() * Math.cos(absBearing));
        // if (++count >= getOthers()) {
        // count = 0;
        // }
        // for (int i = 0; i < getOthers() && enemyPoints[i] != null; i++) {
        // double distance = enemyPoints[i].distance(getX(), getY());
        // xForce -= Math.sin(absBearing) / (distance * distance);
        // yForce -= Math.cos(absBearing) / (distance * distance);
        // System.out.println(absBearing);

        // }
        // double angle = Math.atan2(xForce, yForce);

        // if (xForce == 0 && yForce == 0) {

        // } else if (Math.abs(angle - getHeadingRadians()) < Math.PI / 2) {
        // setTurnRightRadians(Utils.normalRelativeAngle(angle - getHeadingRadians()));
        // setAhead(Double.POSITIVE_INFINITY);
        // } else {
        // setTurnLeftRadians(Utils.normalRelativeAngle(angle + Math.PI -
        // getHeadingRadians()));
        // setAhead(Double.NEGATIVE_INFINITY);
        // }
        // if (e.getDistance() < 20) {
        // fire(1);
        // } else {
        // fire(1);
        // turnLeft(Math.random() * 180);
        // ahead(50);
        // }
        // scan();
    }

    public void onHitByBullet(HitByBulletEvent e) {
        if (leftTurn)
            turnLeft(Math.random() * 100);
        else
            turnRight(Math.random() * 100);
        leftTurn = !leftTurn;
        back(Math.random() * 200 + 20);
    }

    // public void onStatus(StatusEvent e) {
    // }

    // public void onBulletHit(BulletHitEvent e) {
    // }

    public void onBulletHitBullet(BulletHitBulletEvent e) {
        System.out.println(e.getBullet().getHeading());
        moveSomewhere(20);
    }

    public void onBulletMissed(BulletMissedEvent e) {
        moveSomewhere(0);
        // moveSomewhere();
    }

    // public void onDeath(DeathEvent e) {
    // }

    public void onHitRobot(HitRobotEvent e) {
        moveSomewhere(90);
    }

    public void onHitWall(HitWallEvent e) {
        if (e.getBearing() == 0)
            turnRight(90);
        else
            turnLeft(getHeading() % 90);

        System.out.println(e.getBearing());
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

    // public void onRobotDeath(RobotDeathEvent e) {
    // }

    // public void onWin(WinEvent e) {
    // }
}
