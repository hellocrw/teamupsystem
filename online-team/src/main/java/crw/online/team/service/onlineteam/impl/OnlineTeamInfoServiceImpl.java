package crw.online.team.service.onlineteam.impl;

import crw.online.team.entity.onlineteam.OnlineTeamInfo;
import crw.online.team.mapper.onlineteam.OnlineTeamInfoMapper;
import crw.online.team.service.onlineteam.IOnlineTeamInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author caorongwu
 * @since 2020-08-18
 */
@Service
public class OnlineTeamInfoServiceImpl extends ServiceImpl<OnlineTeamInfoMapper, OnlineTeamInfo> implements IOnlineTeamInfoService {

    @Override
    public  IPage<OnlineTeamInfo> findListByPage(Integer page, Integer pageCount){
        IPage<OnlineTeamInfo> wherePage = new Page<>(page, pageCount);
        OnlineTeamInfo where = new OnlineTeamInfo();

        return   baseMapper.selectPage(wherePage, Wrappers.query(where));
    }

    @Override
    public int add(OnlineTeamInfo onlineTeamInfo){
        return baseMapper.insert(onlineTeamInfo);
    }

    @Override
    public int delete(Long id){
        return baseMapper.deleteById(id);
    }

    @Override
    public int updateData(OnlineTeamInfo onlineTeamInfo){
        return baseMapper.updateById(onlineTeamInfo);
    }

    @Override
    public OnlineTeamInfo findById(Long id){
        return  baseMapper.selectById(id);
    }
}
