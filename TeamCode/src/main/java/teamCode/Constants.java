package teamCode;

public class Constants
{

    public static final class DriveTrainConstants
    {
        public static final int kRandomValue = 0;
    }
    //Call values with
//    Constants.DriveTrainConstants.kRandomValue

    public static final class LiftArmConstants
    {
        public static final int kLiftArmCloseSample = 129;
        public static final int kLiftArmFarSample = 440;
        public static final int kLiftArmHighBasket = 2070;
        public static final int kLiftArmHighChamber = 1330;
        public static final int kLiftArmHome = 0;
        public static final int kLiftArmLowBasket = 1520;
        public static final int kLiftArmLowChamber = 540;
        public static final int kLiftArmScoreSpecimen = 200;
        public static final int kLiftArmFudgeFactorUp = 50;
        public static final int kLiftArmFudgeFactorDown = -50;
    }

    public static final class SlideArmConstants
    {
        public static final int kSlideArmCloseSample = 812;
        public static final int kSlideArmFarSample = 2025;
        public static final int kSlideArmHighBasket = 2220;
        public static final int kSlideArmHighChamber = 470;
        public static final int kSlideArmHome = 25;
        public static final int kSlideArmLowBasket = 900;
        public static final int kSlideArmLowChamber = 30;
        public static final int kSlideFudgeIn = -100;
        public static final int kSlideFudgeOut = 100;
    }
}
