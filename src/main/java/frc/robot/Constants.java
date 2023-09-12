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
        public static final double speedMult = 0.64;
        public static final double rotMult = 0.45;
        public static final double RAMP_RATE = 0.2;
        public static final int LEFT_ENCODER_PORT1 = 0;
        public static final int LEFT_ENCODER_PORT2 = 1;
        public static final int RIGHT_ENCODER_PORT1 = 2;
        public static final int RIGHT_ENCODER_PORT2 = 3;
    }
}
