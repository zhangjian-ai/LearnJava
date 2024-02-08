package com.zhangjian.music.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.zhangjian.music.dto.PageDTO;
import com.zhangjian.music.dto.SingerDTO;
import com.zhangjian.music.po.SingerPO;
import com.zhangjian.music.vo.PageVO;

import java.util.List;

public interface SingerService extends IService<SingerPO> {

    // 新增歌手
    public Boolean insert(SingerPO singerPO);

    // 修改歌手
    public SingerPO update(SingerPO singerPO);

    // 删除歌手
    public void deleteById(Integer id);

    // 根据id查询歌手
    public SingerPO selectById(Integer id);

    // 根据歌手名字模糊查询歌手
    public List<SingerPO> selectByName(String name);

    abstract PageVO<SingerPO> selectAll(SingerDTO singerDTO);
}
