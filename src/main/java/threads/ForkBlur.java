package threads;

import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {


    private int[] mSource;
    private int[] mStart;
    private int[] mLength;
    private int[] mDestination;

    private int mBlurWidth = 15;

    public ForkBlur(int[]mSource, int mStart, int mLength, int[] mDestination) {

    this.mSource = mSource;
    this.mStart = mStart;
    this.mLength = mLength;
    this.mDestination = mDestination;

}


public int[] getmDestination() {
        return mDestination;
}


    public void setmDestination(int[] mDestination) {
        this.mDestination = mDestination;
    }

    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for( int index = mStart; index < mStart + mLength; index++ ) {
            float rt = 0;
            float gt = 0;
            float bt = 0;
            for(int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mIndex = Math.min(Math.max(mi + index, 0),
                        mSource.length - 1);
                int pixel = mSource(index);
                rt += (float) ((pixel & 0x00ff0000) >> 16) / mBlurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8) / mBlurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0) / mBlurWidth;

            }

            int dpixel = (0xff000000   ) |
                    (((int)rt) << 16 |
                            (((int)rt) << 8 |
                                    (((int)rt) << 0);
                            mDestination[index] = dpixel;

        }
}


//    private int mBlurWidth = 15;

    protected static int sThreshold = 100000;

    @Override
    protected void compute() {
        if(mLength < sThreshold) {
            System.out.println("Compute Directly");
            computeDirectly();
            return;
        }

        int split = mLength / 2;
        invokeAll(new ForkBlur(mSource, mStart, split, mDestination),
                new ForkBlur(mSource, mStart + mSplit, mLength - split, mDestination));

    }

}
