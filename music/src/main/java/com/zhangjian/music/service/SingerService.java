package com.zhangjian.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjian.music.dto.SingerDto;
import com.zhangjian.music.po.SingerPo;
import com.zhangjian.music.vo.PageVo;

import java.util.List;

public interface SingerService extends IService<SingerPo> {

    // 新增歌手
    Boolean insert(SingerPo singerPO);

    // 修改歌手
    SingerPo update(SingerPo singerPO);

    // 删除歌手
    void deleteById(Integer id);

    // 根据id查询歌手
    SingerPo selectById(Integer id);

    // 根据歌手名字模糊查询歌手
    List<SingerPo> selectByName(String name);

    // 分页查询
    PageVo<SingerPo> selectAll(SingerDto singerDTO);
}
