package com.zhangjian.music.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zhangjian.music.dao.SingerMapper;
import com.zhangjian.music.dto.SingerDTO;
import com.zhangjian.music.enums.Order;
import com.zhangjian.music.po.SingerPO;
import com.zhangjian.music.service.SingerService;
import com.zhangjian.music.vo.PageVO;
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
    public SingerPO update(SingerPO singerPO) {
        // 先根据id查询歌手，如果有才修改
        SingerPO singer = getById(singerPO.getId());

        if (singer == null) throw new RuntimeException(String.format("无效id: %s", singerPO.getId()));

        // 更新数据
        boolean isUpdate = lambdaUpdate()
                .eq(SingerPO::getId, singerPO.getId())
                .set(singerPO.getName() != null, SingerPO::getName, singerPO.getName())
                .set(singerPO.getGender() != null, SingerPO::getGender, singerPO.getGender())
                .set(singerPO.getPic() != null, SingerPO::getPic, singerPO.getPic())
                .set(singerPO.getBirth() != null, SingerPO::getBirth, singerPO.getBirth())
                .set(singerPO.getLocation() != null, SingerPO::getLocation, singerPO.getLocation())
                .set(singerPO.getIntroduction() != null, SingerPO::getIntroduction, singerPO.getIntroduction())
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
    public SingerPO selectById(Integer id) {
        return getById(id);
    }

    @Override
    public List<SingerPO> selectByName(String name) {
        return lambdaQuery()
                .like(SingerPO::getName, name)
                .list();
    }

    @Override
    public PageVO<SingerPO> selectAll(SingerDTO singerDTO) {
        // 分页
        Page<SingerPO> page = Page.of(singerDTO.getPageNo(), singerDTO.getPageSize());

        // 排序
        if (singerDTO.getOrder() != Order.NON && singerDTO.getOrderField() != null){
            page.addOrder(new OrderItem(singerDTO.getOrderField(), singerDTO.getOrder() == Order.ASC));
        }

        // 根据歌手名字和性别查询歌手信息
        lambdaQuery()
                .like(singerDTO.getName() != null, SingerPO::getName, singerDTO.getName())
                .eq(singerDTO.getGender() != null, SingerPO::getGender, singerDTO.getGender())
                .page(page);

        return new PageVO<>(page.getPages(), page.getTotal(), page.getRecords());
    }
}
