package com.epam.university.java.core.task019;

public class RobotImpl implements Robot {
    private enum Direction {
        RIGHT,
        DOWN,
        LEFT,
        UP
    }

    private RobotPosition robotPosition;
    private RobotPosition startRobotPosition;
    private int stepX;
    private int stepY;
    private Direction currentDirection;
    private int currentAngle;

    /**
     * Constructor with init values.
     */
    public RobotImpl() {
        robotPosition = new RobotPositionImpl(0, 0);
        startRobotPosition = new RobotPositionImpl(0, 0);
        currentDirection = Direction.RIGHT;
    }

    @Override
    public RobotPosition getPosition() {
        return this.robotPosition;
    }

    @Override
    public void setPosition(RobotPosition position) {
        this.startRobotPosition = position;

    }

    @Override
    public void invokeAction(RobotCommand command) {
        switch (command) {
            case TURN_LEFT:
                turnLeft();
                break;
            case TURN_RIGHT:
                turnRight();
                break;
            case MOVE_FORWARD:
                switch (currentDirection) {
                    case UP:
                        robotPosition.setY(robotPosition.getY() + 1);
                        break;
                    case DOWN:
                        robotPosition.setY(robotPosition.getY() - 1);
                        break;
                    case LEFT:
                        robotPosition.setX(robotPosition.getX() - 1);
                        break;
                    case RIGHT:
                        robotPosition.setX(robotPosition.getX() + 1);
                        break;
                    default:
                        System.out.println("dir WTF?");
                        break;
                }

                robotPosition.setY(robotPosition.getY() + stepY);
                break;
            default:
                System.out.println("WTF?");
                break;
        }
    }

    public boolean isOnStartPosition() {
        return robotPosition.equals(startRobotPosition);
    }

    private void turnLeft() {
        currentAngle += 90;
        if (currentAngle == 90) {
            currentDirection = Direction.UP;
        }
        if (currentAngle == 180) {
            currentDirection = Direction.LEFT;
        }
        if (currentAngle == 270) {
            currentDirection = Direction.DOWN;
        }
        if (currentAngle == 360) {
            currentAngle = 0;
            currentDirection = Direction.RIGHT;
        }
    }

    private void turnRight() {
        currentAngle -= 90;
        if (currentAngle == -90) {
            currentDirection = Direction.DOWN;
        }
        if (currentAngle == -180) {
            currentDirection = Direction.LEFT;
        }
        if (currentAngle == -270) {
            currentDirection = Direction.UP;
        }
        if (currentAngle == -360) {
            currentAngle = 0;
            currentDirection = Direction.RIGHT;
        }
    }


}
