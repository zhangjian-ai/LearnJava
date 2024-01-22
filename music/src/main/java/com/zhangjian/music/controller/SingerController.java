package com.zhangjian.music.controller;

import com.alibaba.fastjson.JSON;
import com.zhangjian.music.po.SingerPO;
import com.zhangjian.music.service.SingerService;
import com.zhangjian.music.utils.Result;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/singer")
@RequiredArgsConstructor
public class SingerController {

    private final SingerService singerService;

    /**
     * 新增歌手
     */
    @PostMapping("/add")
    public Result addSinger(@RequestBody SingerPO singerPO){
        try {
            Boolean bool = singerService.insert(singerPO);

            return bool ? Result.build(0, "新建成功", null)
                    : Result.build(-1, "未知异常", null);
        }catch (Exception e){
            return Result.build(-1, e.getMessage(), null);
        }
    }

    /**
     * 修改歌手信息
     */
    @PostMapping("/update")
    public Result updateSinger(@RequestBody SingerPO singerPO){
        try{
            Boolean aBoolean = singerService.update(singerPO);

            return aBoolean ? Result.build(0, "修改成功", null)
                    : Result.build(-1, "未知错误", null);
        }catch (RuntimeException e){
            return Result.build(-1, e.getMessage(), null);
        }
    }

    /**
     * 删除歌手
     */
    @DeleteMapping("/delete/{id}")
    public Result deleteSinger(@PathVariable Integer id){

        try {
            Boolean aBoolean = singerService.deleteById(id);

            return aBoolean ? Result.build(0, "删除成功", null)
                    : Result.build(-1, "未知错误", null);
        } catch (RuntimeException e) {
            return Result.build(-1, e.getMessage(), null);
        }
    }
}
