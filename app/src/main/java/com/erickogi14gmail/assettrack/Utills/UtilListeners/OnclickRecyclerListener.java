package com.erickogi14gmail.assettrack.Utills.UtilListeners;

import android.view.View;

/**
 * Created by Eric on 12/18/2017.
 */

public interface OnclickRecyclerListener {
    void onClickListener(int position);

    void onLongClickListener(int position);

    void onCheckedClickListener(int position);

    void onMoreClickListener(int position);

    void onClickListener(int adapterPosition, View view);

    // void onDeleteListener(int adapterPosition);
}
