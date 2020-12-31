package com.yc.bbnmd1.rediscache;


import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName ResultUtils
 * @description: 返回结果工具类
 **/
public class ResultUtils {

    /**
     * @Title: success
     * @Description: 无参成功返回   默认值  code : "0" , msg : "请求成功" , count : 0 , data : null
     **/
    public static ResultBody success(){
        return success((Object)null);
    }


    public static ResultBody success(Object object){
        return success(0,object);
    }


    /**
     * @Title: success
     * @Description:  有参成功返回   默认值  code : "0" , msg : "请求成功"
     * @param count :  数据条数
     * @param object : 数据
     **/
    public static ResultBody success(Integer count,Object object){
        return new ResultBody().success(count,object);
    }

    /**
     * @Title: success
     * @Description:  有参成功返回   默认值  code : "0"
     * @param msg : 提示信息
     * @param count :  数据条数
     * @param object :  数据
     **/
    public static ResultBody success(String msg,Integer count,Object object){
        return new ResultBody().success(msg,count,object);
    }

    /**
     * @Title: error
     * @Description: 有参成功返回     默认值  code : "0"
     * @param code :
     * @param object : 数据
     **/
    public static ResultBody success(Code code,Object object){
        return new ResultBody().success(code,object);
    }

    /**
     * @Title: error
     * @Description: 有参成功返回     默认值  code : "0" data : null
     * @param code : 枚举类代码
     **/
    public static ResultBody success(Code code){
        return new ResultBody().success(code);
    }

    /**
     * @Title: error
     * @Description: 错误返回格式     默认值 data : null
     * @param code : 错误代码
     **/
    public static ResultBody error(Integer code,String msg){
        return new ResultBody().error(code,msg);
    }


    /**
     * @Title: error
     * @Description: 错误返回格式     默认值 data : null
     * @param code : 枚举类错误代码
     **/
    public static ResultBody error(Code code){
        return new ResultBody().error(code);
    }


    /**
     * @Title: successByLimit
     * @Description: 分页返回数据格式
     * @param page : 查询的页数
     * @param limit : 查询的条数
     * @param totalNum : 数据总条数
     * @param curCount : 当前页条数
     * @param object : 查询结果数据
     **/
    /*public static ResultBody successByLimit(Integer page,Integer limit,Integer totalNum,Integer curCount,Object object){
        Map<String,Object> map = new HashMap<>();
        Page pageInfo = new Page();
        pageInfo.setPage(page);
        pageInfo.setLimit(limit);
        pageInfo.setTotalNum(totalNum);
        pageInfo.setTotalPages((totalNum + limit - 1)/limit);
        map.put("page",pageInfo);
        map.put("data",object);
        return success(curCount,map);
    }*/

}
