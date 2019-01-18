package jp.ac.chiba_fjb.x16g_d.ideatest02;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

public class Hint1 extends Fragment implements View.OnClickListener {

    private View mView;
    private RecyclerFragmentListener mFragmentListener = null;
    private Activity mActivity = null;


    public interface RecyclerFragmentListener {
        void onRecyclerEvent();
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hint1, container, false);
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }
    ImageView imageView;

    private void releaseImageView(){
        if(imageView != null){
            // src に画像を設定した場合はこっち
            imageView.setImageDrawable(null);
            // background に画像を設定した場合はこっち
            imageView.setBackgroundDrawable(null);
        }
    }
    Bitmap bitmap;

    private void releaseBitmap() {
        if (bitmap != null) {
            bitmap.recycle();
            bitmap = null;
        }
    }
    @Override
    public void onClick(View view) {

    }



    public Hint1() {
        // Required empty public constructor
    }
}
