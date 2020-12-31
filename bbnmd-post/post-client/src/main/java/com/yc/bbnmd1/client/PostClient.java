package com.yc.bbnmd1.client;

import com.yc.bbnmd1.config.FeignClientConfig;
import com.yc.bbnmd1.entity.Replay;
import com.yc.bbnmd1.entity.Topic;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "BASE-MICROSERVICE-ZUUL-GATEWAY",
        configuration = FeignClientConfig.class
)  // 配置要按自定义的类FeignClientConfig
public interface PostClient {
    //访问的路径value要修改成zuul指定的服务路由路径
    @RequestMapping(method = RequestMethod.GET, value = "/yc-api/bbnmd-post/bbnmd/post/replay/{id}")
    String findById(@RequestParam("id") Integer id);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/bbnmd-post/bbnmd/post/saveTopic/{token}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String createTopic(@RequestBody Topic topic,@RequestParam(value = "token") String token);

    @RequestMapping(method = RequestMethod.POST, value = "/yc-api/bbnmd-post/bbnmd/post/saveReplay/{token}",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String createReplay(@RequestBody Replay replay,@RequestParam(value = "token") String token);




    @RequestMapping(method = RequestMethod.PUT, value = "/yc-api/bbnmd-post/bbnmd/post",
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    String update(@RequestBody Replay replay);


}
