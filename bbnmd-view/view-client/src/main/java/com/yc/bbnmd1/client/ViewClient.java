package com.yc.bbnmd1.client;

import com.yc.bbnmd1.config.FeignClientConfig;
import com.yc.bbnmd1.domain.ViewDomain;
import com.yc.bbnmd1.entity.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface ViewClient {




    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbnmd-view/bbnmd/view/check/{token}")
    String check(@RequestParam(value = "token") String token);

    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbnmd-view/bbnmd/view/{id}")
    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.DELETE, value = "/yc-api/bbnmd-view/bbnmd/view/{id}")
    String delete(@RequestParam("id") Integer id);


    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbnmd-view/bbnmd/view/findAll",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String findAll(@RequestParam("page") Integer page, @RequestParam("pageSize") Integer pageSize,@RequestParam("bid") Integer bid);


    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbnmd-view/bbnmd/view")
    String findIndex();






}
