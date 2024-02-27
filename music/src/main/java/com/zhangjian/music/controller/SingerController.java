package com.zhangjian.music.controller;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSON;
import com.zhangjian.music.dto.PageDTO;
import com.zhangjian.music.dto.SingerDTO;
import com.zhangjian.music.po.SingerPO;
import com.zhangjian.music.service.SingerService;
import com.zhangjian.music.utils.Result;
import com.zhangjian.music.vo.PageVO;
import com.zhangjian.music.vo.SingerVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping( "/singer" )
@RequiredArgsConstructor
@Api(tags = "歌手管理")
public class SingerController {

    /**
     * API文档 http://localhost:8888/doc.html#/home
     */
    private final SingerService singerService;

    /**
     * 新增歌手
     */
    @PostMapping( "/add" )
    @ApiOperation("新增用户")
    public Result addSinger(@RequestBody SingerDTO singerDTO) {
        try {
            // 将dto数据copy到po
            SingerPO singerPO = BeanUtil.copyProperties(singerDTO, SingerPO.class);

            Boolean bool = singerService.insert(singerPO);

            return bool ? Result.build(0, "新建成功", BeanUtil.copyProperties(singerPO, SingerVO.class))
                    : Result.build(-1, "未知异常", null);
        } catch (Exception e) {
            return Result.build(-1, e.getMessage(), null);
        }
    }

    /**
     * 修改歌手信息
     */
    @PostMapping( "/update" )
    @ApiOperation("更新歌手信息")
    public Result updateSinger(@RequestBody SingerDTO singerDTO) {
        try {
            SingerPO singerPO = singerService.update(BeanUtil.copyProperties(singerDTO, SingerPO.class));

            return singerPO != null ? Result.build(0, "修改成功", BeanUtil.copyProperties(singerPO, SingerVO.class))
                    : Result.build(-1, "修改失败", null);
        } catch (RuntimeException e) {
            return Result.build(-1, e.getMessage(), null);
        }
    }

    /**
     * 删除歌手
     * 根据id删除某位歌手
     */
    @DeleteMapping( "/delete/{id}" )
    @ApiOperation("根据ID删除")
    public Result deleteSingerById(@PathVariable Integer id) {
        try {
            singerService.deleteById(id);
            return Result.build(0, "删除成功", null);
        } catch (RuntimeException e) {
            return Result.build(-1, e.getMessage(), null);
        }
    }

    /**
     * 查询歌手信息
     * 根据id查询某位歌手
     */
    @GetMapping( "/selectById/{id}" )
    @ApiOperation("根据ID查询")
    public Result selectSingerById(@PathVariable Integer id) {
        SingerPO singerPO = singerService.selectById(id);

        return singerPO == null ? Result.build(0, "歌手不存在", null)
                : Result.build(0, "成功", BeanUtil.copyProperties(singerPO, SingerVO.class));
    }

    /**
     * 查询歌手信息
     * 根据 关键字 查询歌手
     */
    @GetMapping( "/selectByName/{name}" )
    @ApiOperation("根据名字模糊查询")
    public Result selectSingerByName(@PathVariable String name) {
        List<SingerPO> singerPOList = singerService.selectByName(name);

        return singerPOList.isEmpty() ? Result.build(0, "无歌手信息", null)
                : Result.build(0, "成功", BeanUtil.copyToList(singerPOList, SingerVO.class));
    }

    /**
     * 查询歌手信息
     * 查询所有歌手信息
     */
    @GetMapping( "/select/all" )
    @ApiOperation("根据 歌手名和性别 分页查询所有歌手")
    public Result selectAllSinger(SingerDTO singerDTO) {
        PageVO<SingerPO> pageVO = singerService.selectAll(singerDTO);

        return Result.build(0, "查询成功", pageVO);
    }
}
