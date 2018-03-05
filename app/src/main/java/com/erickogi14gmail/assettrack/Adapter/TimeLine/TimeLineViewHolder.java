package com.erickogi14gmail.assettrack.Adapter.TimeLine;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.erickogi14gmail.assettrack.R;
import com.erickogi14gmail.assettrack.Utills.UtilListeners.OnclickRecyclerListener;
import com.github.vipulasri.timelineview.TimelineView;

import java.lang.ref.WeakReference;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by HP-HP on 05-12-2015.
 */
public class TimeLineViewHolder extends RecyclerView.ViewHolder {
    CardView mCard;
    @BindView(R.id.text_timeline_date)
    TextView mDate;
    @BindView(R.id.text_timeline_title)
    TextView mMessage;
    @BindView(R.id.time_marker)

    TimelineView mTimelineView;
    private WeakReference<OnclickRecyclerListener> listenerWeakReference;

    public TimeLineViewHolder(View itemView, int viewType, OnclickRecyclerListener listener) {
        super(itemView);
        mCard = itemView.findViewById(R.id.card);
        listenerWeakReference = new WeakReference<>(listener);
        mCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerWeakReference.get().onClickListener(getAdapterPosition());

            }
        });
        ButterKnife.bind(this, itemView);
        mTimelineView.initLine(viewType);
        mTimelineView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerWeakReference.get().onClickListener(getAdapterPosition());
            }
        });
    }
}
