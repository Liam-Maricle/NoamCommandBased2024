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
        public static final int kLiftArmCloseSample = 175;
        public static final int kLiftArmFarSample = 520;
        public static final int kLiftArmHighBasket = 2400;
        public static final int kLiftArmHighChamber = 2400;
        public static final int kLiftArmHome = 0;
        public static final int kLiftArmLowBasket = 2400;
        public static final int kLiftArmLowChamber = 675;
        public static final int kLiftArmScoreSpecimen = 200;
        public static final int kLiftArmFudgeFactorUp = 50;
        public static final int kLiftArmFudgeFactorDown = -50;
    }

    public static final class SlideArmConstants
    {
        public static final int kSlideArmCloseSample = 0;
        public static final int kSlideArmFarSample = 1970;
        public static final int kSlideArmHighBasket = 2220;
        public static final int kSlideArmHighChamber = 750;
        public static final int kSlideArmHome = 25;
        public static final int kSlideArmLowBasket = 200;
        public static final int kSlideArmLowChamber = 0;
        public static final int kSlideFudgeIn = -100;
        public static final int kSlideFudgeOut = 100;
    }
}
