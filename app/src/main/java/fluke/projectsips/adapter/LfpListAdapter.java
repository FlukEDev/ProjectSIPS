package fluke.projectsips.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import fluke.projectsips.view.LfpListItem;


public class LfpListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 8;
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LfpListItem item;
        if (convertView != null)
            item = (LfpListItem) convertView;
        else
            item = new LfpListItem(parent.getContext());

        switch (position) {
            case 0:
                item.setNameText("จำนวนและร้อยละของประชากร จำแนกตามสถานภาพแรงงาน และเพศ");
                break;
            case 1:
                item.setNameText("จำนวนและร้อยละของประชากรอายุ 15 ปีขึ้นไป จำแนกตามระดับการศึกษาที่สำเร็จ และเพศ");
                break;
            case 2:
                item.setNameText("จำนวนและร้อยละของผู้มีงานทำ จำแนกตามระดับการศึกษาที่สำเร็จ และเพศ");
                break;
            case 3:
                item.setNameText("จำนวนและร้อยละของผู้มีงานทำ จำแนกตามอาชีพ และเพศ");
                break;
            case 4:
                item.setNameText("จำนวนและร้อยละของผู้มีงานทำ จำแนกตามอุตสาหกรรม และเพศ");
                break;
            case 5:
                item.setNameText("จำนวนและร้อยละของผู้มีงานทำ จำแนกตามสถานภาพการทำงาน และเพศ");
                break;
            case 6:
                item.setNameText("จำนวนและร้อยละของผู้มีงานทำ จำแนกตามชั่วโมงการทำงานต่อสัปดาห์ และเพศ");
                break;
            case 7:
                item.setNameText("จำนวนและอัตราการว่างงาน จำแนกตามเพศ");
                break;
        }

        return item;
        }
    }
