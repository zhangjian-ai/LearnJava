package com.zhangjian.music.service.impl;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjian.music.dao.SingerMapper;
import com.zhangjian.music.po.SingerPO;
import com.zhangjian.music.service.SingerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, SingerPO> implements SingerService {

    @Override
    public Boolean insert(SingerPO singerPO) {
        int i = baseMapper.insert(singerPO);

        return i == 1; // 返回值是影响的数据条数，等于1说明新增一条成功
    }

    @Override
    public Boolean update(SingerPO singerPO) {
        // 先根据id查询歌手，如果有才修改
        SingerPO singer = getById(singerPO.getId());

        if (singer == null) throw new RuntimeException(String.format("无效id: %s", singerPO.getId()));

        // 更新数据
        return lambdaUpdate()
                .eq(SingerPO::getId, singerPO.getId())
                .set(singerPO.getName() != null, SingerPO::getName, singerPO.getName())
                .set(singerPO.getGender() != null, SingerPO::getGender, singerPO.getGender())
                .set(singerPO.getPic() != null, SingerPO::getPic, singerPO.getPic())
                .set(singerPO.getBirth() != null, SingerPO::getBirth, singerPO.getBirth())
                .set(singerPO.getLocation() != null, SingerPO::getLocation, singerPO.getLocation())
                .set(singerPO.getIntroduction() != null, SingerPO::getIntroduction, singerPO.getIntroduction())
                .update();
    }

    @Override
    public Boolean deleteById(Integer id) {
        try {
            baseMapper.deleteById(id);
            return true;
        } catch (Exception exception) {
            throw new RuntimeException("delete singer error: " + exception.getMessage());
        }
    }

    @Override
    public SingerPO selectById(Integer id) {
        return null;
    }

    @Override
    public List<SingerPO> selectAll() {
        return null;
    }

    @Override
    public List<SingerPO> selectByName(String name) {
        return null;
    }
}
