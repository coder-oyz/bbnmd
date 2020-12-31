package com.yc.bbnmd1.web.controllers;


import com.google.gson.Gson;
import com.yc.bbnmd1.entity.User;
import com.yc.bbnmd1.future.BbnmdUserFuture;

import com.yc.bbnmd1.web.services.AsyncThreadPool;
import com.yc.bbnmd1.web.services.FastefsClient;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/bbnmd/view/fileupload")
public class FileController {
    private static Logger logger = LoggerFactory.getLogger(FileController.class.getName());
    @Autowired
    private BbnmdUserFuture bbnmdUserFuture;

    @Value("${file.path.head:http://47.103.18.203/}")
    private String pathHead;

    @Autowired
    private FastefsClient fastefsClient;

    /**
     * 上传图片
     *@RequestParam("pictureFile")
     * @return
     */
    @RequestMapping(value = "/uploadPic", method = RequestMethod.POST)
    public void uploadPic(@RequestParam(value = "upload",required = false) MultipartFile multipartFile,
                          HttpServletRequest request, HttpServletResponse response) {
        String json =null;
        Map<String, Object> msgMap = new HashMap<String, Object>();
        try {
            PrintWriter out = response.getWriter();
            String filename = fastefsClient.uplodFile(multipartFile);
            AsyncThreadPool.getInstance().execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        System.out.println("文件通过nginx访问的路径:" + (pathHead + filename));
                        savePic(multipartFile);  //调用数据库操作


                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            //String imageContextPath = pathHead + filename;
            // BufferedImage image = ImageIO.read(multipartFile.getInputStream());


            // msgMap.put("height", image.getHeight());
            // msgMap.put("width", image.getWidth());

            // ObjectMapper mapper = new ObjectMapper();
            // String ret = mapper.writeValueAsString(msgMap);
            // response.setContentType("text/html;charset=utf8");
//            response.getOutputStream().write(ret.getBytes());
//            response.flushBuffer();

            msgMap.put("uploaded",1);
            // msgMap.put("error",0);
            msgMap.put("fileName", filename);
            msgMap.put("url",pathHead + filename);
            out.print(new Gson().toJson(msgMap));

        } catch (Exception e) {
            e.printStackTrace();
            Map<String,Object> error = new HashMap<>();
            msgMap.put("uploaded ",0);
            error.put("message","system error");
            msgMap.put("error ",error);
            try {
                response.getWriter().println(msgMap);

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }

    }

    //数据库操作
    private CompletableFuture<String> savePic(MultipartFile multipartFile) throws Exception {
        BufferedImage image = ImageIO.read(multipartFile.getInputStream());
        User user = new User();
        // user.setHead(head);
        return bbnmdUserFuture.create(user).thenApply(info -> {
            return info;
        });
    }

}
