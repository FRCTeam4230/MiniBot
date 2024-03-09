package frc.robot;

//This is where we store constants
//It makes the code more organized if all the constants are in one place
public final class Constants {
    public static final class CANId{
        public static final int kLeftMotorFrontPort = 2;
        public static final int kLeftMotorBackPort = 1;
        public static final int kRightMotorFrontPort = 3;
        public static final int kRightMotorBackPort = 4;
    }
    public static final class driveTrain {
        public static final double speedMult = 0.80;
        public static final double rotMult = 0.80;
        public static final double RAMP_RATE = 0.2;
        public static final int LEFT_ENCODER_PORT1 = 0;
        public static final int LEFT_ENCODER_PORT2 = 1;
        public static final int RIGHT_ENCODER_PORT1 = 2;
        public static final int RIGHT_ENCODER_PORT2 = 3;
        public static final double encoderDistancePerPulse = 1 / 4138.8584;//Converts pulses to meters
    }

    public static final class BallCatcherMultipliers {

        public static final double MAX_SPEED = .56;
        public static final double MAX_TURN_SPEED = 0.5;

    }

    public static final class PIDConstants {
        public static final double DISTANCE_P = 3;
        public static final double DISTANCE_I = 0;
        public static final double DISTANCE_D = .5;
        public static final double DIRECTION_P = -0.1;

    }
}
