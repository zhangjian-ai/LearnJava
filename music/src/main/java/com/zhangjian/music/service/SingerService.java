package com.zhangjian.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjian.music.po.SingerPO;

import java.util.List;

public interface SingerService extends IService<SingerPO> {

    // 新增歌手
    public Boolean insert(SingerPO singerPO);

    // 修改歌手
    public Boolean update(SingerPO singerPO);

    // 删除歌手
    public Boolean deleteById(Integer id);

    // 根据id查询歌手
    public SingerPO selectById(Integer id);

    // 查询所有歌手
    public List<SingerPO> selectAll();

    // 根据歌手名字模糊查询歌手
    public List<SingerPO> selectByName(String name);
}
