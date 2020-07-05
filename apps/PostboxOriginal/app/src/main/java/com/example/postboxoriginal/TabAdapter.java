package com.example.postboxoriginal;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;
public class TabAdapter extends FragmentStateAdapter {
    private static final int CARD_ITEM_SIZE = 8;
    public TabAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }
    @NonNull @Override public Fragment createFragment(int position) {
        switch (position){
            case 0:
                Fragment f1=new FirstPeriodFrag();
                return f1;
            case 1:
                Fragment f2=new SecondPeriodFrag();
                return f2;
            case 2:
                Fragment f3=new ThirdPeriodFrag();
                return f3;
            case 3:
                Fragment f4=new FourthPeriodFrag();
                return f4;
            case 4:
                Fragment f5=new FifthPeriodFrag();
                return f5;
            case 5:
                Fragment f6=new SixthPeriodFrag();
                return f6;
            case 6:
                Fragment f7=new SeventhPeriodFrag();
                return f7;
            case 7:
                Fragment f8=new EighthPeriodFrag();
                return f8;
            default: return null;
        }
    }
    @Override public int getItemCount() {
        return CARD_ITEM_SIZE;
    }
}