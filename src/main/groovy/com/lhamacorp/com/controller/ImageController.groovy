package com.lhamacorp.com.controller

import com.lhamacorp.com.service.ImageService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.multipart.MultipartFile

@Controller
@RequestMapping("image")
class ImageController {

    @Autowired
    ImageService service

    @RequestMapping(value = "upload", method = RequestMethod.POST)
    @ResponseBody
    def upload(@RequestParam("file") MultipartFile file,
               @RequestParam("text1")String text1) {
        service.upload file, text1
    }

}
