package com.nowcoder.community.entity;

/**
 * @program: community
 * @description:
 * @author: DM
 * @create: 2022-12-14 09:15
 **/

//封装分页相关信息
public class Page {
    //当前页码
    private int current=1;
    private int limit=10;
    //数据总数
    private int rows;
    //查询路径 用于分页链接
    private String path;

    public Page() {
    }

    public Page(int current, int limit, int rows, String path) {
        this.current = current;
        this.limit = limit;
        this.rows = rows;
        this.path = path;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        if(current>=1){
            this.current = current;}
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        if (limit>=1&&limit<=100){
            this.limit = limit;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        if (rows>=0){
            this.rows = rows;
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
    //获取当前页的起始行
    public int getOffset(){
        //current * limit-limit
        return (current-1)*limit;
    }

    //获取总页数
    public int getTotal(){
        // rows/limit[+1]
        if (rows%limit==0){
            return rows/limit;
        }else {
            return rows=limit+1;
        }
    }
    //获取起始页码
    public int getFrom(){
        int from =current-2;
        return from<1?1:from;
    }

    //获取结束页码
    public int getTo(){
        int to=current+2;
        int total=getTotal();
        return to>total?total:to;
    }
}