package fluke.projectsips.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import fluke.projectsips.view.GppListItem;


/**
 * Created by FLUKE on 3/15/2016 AD.
 */
public class GppListAdapter extends BaseAdapter {
    @Override
    public int getCount() {
        return 6;
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
        GppListItem item;
        if (convertView != null)
            item = (GppListItem) convertView;
        else
            item = new GppListItem(parent.getContext());

        switch (position) {
            case 0:
                item.setNameText("ผลิตภัณฑ์มวลรวมจังหวัดสระแก้ว (GPP)");
                break;
            case 1:
                item.setNameText("ผลิตภัณฑ์มวลรวมรายจังหวัด (GPP)");
                break;
            case 2:
                item.setNameText("ผลิตภัณฑ์มวลรวมรายภูมิภาค (GRP)");
                break;
            case 3:
                item.setNameText("ผลิตภัณฑ์มวลรวมกลุ่มเบญจบูรพาสุวรรณภูมิ");
                break;
            case 4:
                item.setNameText("ผลิตภัณฑ์มวลรวมในประเทศ");
                break;
            case 5:
                item.setNameText("ผลิตภัณฑ์มวลรวมในประเทศ (กำหนดตามความต้องการของผู้ใช้)");
                break;
        }
        return item;
        }
    }
