package com.example.exemplolistview.model.dao.helpers;

import android.content.Context;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;

public class DaoHelper<T> {
    protected Dao<T,Integer> dao;
    protected Class className;
    public static MyOrmLiteHelper mIstance =null;

    public DaoHelper(Context c,Class className){
        this.className=className;
        if(mIstance==null){
            mIstance=new MyOrmLiteHelper(c.getApplicationContext());
        }
    }

    public Dao<T,Integer> getDao(){
        try {
            return mIstance.getDao(className);
        }catch (SQLException e){
            e.printStackTrace();
            return null;
        }
    }
}
