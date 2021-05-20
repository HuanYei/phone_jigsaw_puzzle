package com.HY.dao;

import com.HY.Sql.DBDao;
import com.HY.Sql.PHB;

import java.util.List;

public class Paixu {
    public static void paixu(PHB phb){
        List<PHB> list= DBDao.getInstance().query();

        for (int i=0;i<10;i++){
            if (list.get(i).getTime()>phb.getTime()||list.get(i).getTime()==0){
                for (int j=9;j>i;j--){
                    list.get(j).setTime(list.get(j-1).getTime());
                    list.get(j).setDay(list.get(j-1).getDay());
                    list.get(j).setBusu(list.get(j-1).getBusu());
                    list.get(j).setMosi(list.get(j-1).getMosi());
                }
                list.get(i).setTime(phb.getTime());
                list.get(i).setDay(phb.getDay());
                list.get(i).setBusu(phb.getBusu());
                list.get(i).setMosi(phb.getMosi());
                break;
            }
        }
        DBDao.getInstance().clearAll();
        for (int i=0;i<10;i++){
           DBDao.getInstance().insert(list.get(i));
        }
    }
    public static void paixu2(PHB phb){
        List<PHB> list= DBDao.getInstance().query2();

        for (int i=0;i<10;i++){
            if (list.get(i).getTime()>phb.getTime()||list.get(i).getTime()==0){
                for (int j=9;j>i;j--){
                    list.get(j).setTime(list.get(j-1).getTime());
                    list.get(j).setDay(list.get(j-1).getDay());
                    list.get(j).setBusu(list.get(j-1).getBusu());
                    list.get(j).setMosi(list.get(j-1).getMosi());
                }
                list.get(i).setTime(phb.getTime());
                list.get(i).setDay(phb.getDay());
                list.get(i).setBusu(phb.getBusu());
                list.get(i).setMosi(phb.getMosi());
                break;
            }
        }
        DBDao.getInstance().clearAll2();
        for (int i=0;i<10;i++){
            DBDao.getInstance().insert2(list.get(i));
        }
    }
}
