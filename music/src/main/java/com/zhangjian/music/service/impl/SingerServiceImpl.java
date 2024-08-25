package com.zhangjian.music.service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjian.music.dao.SingerMapper;
import com.zhangjian.music.dto.SingerDto;
import com.zhangjian.music.enums.Order;
import com.zhangjian.music.po.SingerPo;
import com.zhangjian.music.service.SingerService;
import com.zhangjian.music.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class SingerServiceImpl extends ServiceImpl<SingerMapper, SingerPo> implements SingerService {

    @Override
    public Boolean insert(SingerPo singerPO) {
        int i = baseMapper.insert(singerPO);

        return i == 1; // 返回值是影响的数据条数，等于1说明新增一条成功
    }

    @Override
    public SingerPo update(SingerPo singerPO) {
        // 先根据id查询歌手，如果有才修改
        SingerPo singer = getById(singerPO.getId());

        if (singer == null) throw new RuntimeException(String.format("无效id: %s", singerPO.getId()));

        // 更新数据
        boolean isUpdate = lambdaUpdate()
                .eq(SingerPo::getId, singerPO.getId())
                .set(singerPO.getName() != null, SingerPo::getName, singerPO.getName())
                .set(singerPO.getGender() != null, SingerPo::getGender, singerPO.getGender())
                .set(singerPO.getPic() != null, SingerPo::getPic, singerPO.getPic())
                .set(singerPO.getBirth() != null, SingerPo::getBirth, singerPO.getBirth())
                .set(singerPO.getLocation() != null, SingerPo::getLocation, singerPO.getLocation())
                .set(singerPO.getIntroduction() != null, SingerPo::getIntroduction, singerPO.getIntroduction())
                .update();

        return isUpdate ? getById(singerPO.getId()) : null;
    }

    @Override
    public void deleteById(Integer id) {
        try {
            baseMapper.deleteById(id);
        } catch (Exception exception) {
            throw new RuntimeException("delete singer error: " + exception.getMessage());
        }
    }

    @Override
    public SingerPo selectById(Integer id) {
        return getById(id);
    }

    @Override
    public List<SingerPo> selectByName(String name) {
        return lambdaQuery()
                .like(SingerPo::getName, name)
                .list();
    }

    @Override
    public PageVo<SingerPo> selectAll(SingerDto singerDTO) {
        // 分页
        Page<SingerPo> page = Page.of(singerDTO.getPageNo(), singerDTO.getPageSize());

        // 排序
        if (singerDTO.getOrder() != Order.NON && singerDTO.getOrderField() != null){
            page.addOrder(new OrderItem(singerDTO.getOrderField(), singerDTO.getOrder() == Order.ASC));
        }

        // 根据歌手名字和性别查询歌手信息
        lambdaQuery()
                .like(singerDTO.getName() != null, SingerPo::getName, singerDTO.getName())
                .eq(singerDTO.getGender() != null, SingerPo::getGender, singerDTO.getGender())
                .page(page);

        return new PageVo<>(page.getPages(), page.getTotal(), page.getRecords());
    }
}
