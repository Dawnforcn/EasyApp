package tv.douyu.wrapper.holder;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.harreke.easyapp.enums.RippleStyle;
import com.harreke.easyapp.frameworks.recyclerview.RecyclerHolder;
import com.harreke.easyapp.helpers.ImageLoaderHelper;
import com.harreke.easyapp.utils.StringUtil;
import com.harreke.easyapp.widgets.rippleeffects.RippleDrawable;
import com.harreke.easyapp.widgets.rippleeffects.RippleOnClickListener;

import tv.douyu.R;
import tv.douyu.model.bean.Room;

/**
 * 由 Harreke（harreke@live.cn） 创建于 2014/12/18
 */
public class RoomHolder extends RecyclerHolder<Room> {
    private TextView nickname;
    private TextView online;
    private TextView room_name;
    private View room_ripple;
    private ImageView room_src;

    public RoomHolder(View itemView) {
        super(itemView);

        room_src = (ImageView) itemView.findViewById(R.id.room_src);
        room_name = (TextView) itemView.findViewById(R.id.room_name);
        nickname = (TextView) itemView.findViewById(R.id.room_nickname);
        online = (TextView) itemView.findViewById(R.id.room_online);
        room_ripple = itemView.findViewById(R.id.room_ripple);

        RippleDrawable.attach(room_ripple, RippleStyle.Light);
    }

    @Override
    public void setItem(Room room) {
        ImageLoaderHelper.loadImage(room_src, room.getRoom_src(), R.drawable.loading_16x9, R.drawable.retry_16x9);
        room_ripple.setTag(room.hashCode());
        room_name.setText(room.getRoom_name());
        nickname.setText(room.getNickname());
        online.setText(StringUtil.indentNumber(room.getOnline()));
    }

    @Override
    public void setOnClickListener(View.OnClickListener onClickListener) {
        RippleOnClickListener.attach(room_ripple, onClickListener);
    }
}