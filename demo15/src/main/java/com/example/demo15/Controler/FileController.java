package com.example.demo15.Controler;

import com.example.demo15.Common.ServerResponse;
import com.example.demo15.Model.BookBean;
import com.example.demo15.Service.IFileService;
import com.example.demo15.Service.ISearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class FileController {
    @Autowired
    IFileService fileService;
    @Autowired
    ISearchService searchService;
    @RequestMapping(value = "/uploadBook")
    public ServerResponse<String> upLoadFile(MultipartFile file, String userId){

        //System.out.println("有文件上传！"+file.getOriginalFilename()+" id:"+userId);
        return fileService.uploadFile(file,userId);

    }

    @RequestMapping(value = "/downloadBook")
    public ServerResponse DownloadFile(@RequestBody BookBean bookBean , HttpServletResponse response){
        if(0==bookBean.getFileNum()||null==bookBean.getFileName()) {
            return ServerResponse.createErrorMessage("无效参数");
        }
        ServerResponse serverResponse;
        try {
                // BookBean bookBean = serverResponse.getData();
                serverResponse = fileService.download(response.getOutputStream(),bookBean.getFileNum());

            }catch (Exception e){
                serverResponse = ServerResponse.createErrorMessage("访问出错！");
                e.printStackTrace();
            }
            return serverResponse;
    }

    /**
     * 查找小说
     * @param
     * @return
     */
    @RequestMapping(value="/search")
    public ServerResponse<List<BookBean>> search(String fileName)
    {
        return searchService.search(fileName);
    }
}
