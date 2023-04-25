package com.example.mybatis.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicDataSource extends AbstractRoutingDataSource {

    public static final String DEFAULTDB = "master";

    public static final String PIG = "pig";

    public static ThreadLocal<String> contextHolder = new ThreadLocal<>();

    //这个可写可不写，甚至可以忽略
    public static ThreadLocal<String> getContextholder() {
        return contextHolder;
    }


    /**
     * 切换数据源
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return getDataSource();
    }

    /**
     * 设置数据源
     * @param dataSorce
     */
    public static void setDataSorce(String dataSorce){
        contextHolder.set(dataSorce);
    }

    /**
     * 获取数据源
     * @return
     */
    public static String getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除数据源
     */
    public static void clearDataSource(){
        contextHolder.remove();
    }
}

