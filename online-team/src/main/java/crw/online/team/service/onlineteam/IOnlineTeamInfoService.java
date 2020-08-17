package crw.online.team.service.onlineteam;

import crw.online.team.entity.onlineteam.OnlineTeamInfo;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caorongwu
 * @since 2020-08-18
 */
public interface IOnlineTeamInfoService extends IService<OnlineTeamInfo> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<OnlineTeamInfo>
     */
    IPage<OnlineTeamInfo> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param onlineTeamInfo 
     * @return int
     */
    int add(OnlineTeamInfo onlineTeamInfo);

    /**
     * 删除
     *
     * @param id 主键
     * @return int
     */
    int delete(Long id);

    /**
     * 修改
     *
     * @param onlineTeamInfo 
     * @return int
     */
    int updateData(OnlineTeamInfo onlineTeamInfo);

    /**
     * id查询数据
     *
     * @param id id
     * @return OnlineTeamInfo
     */
    OnlineTeamInfo findById(Long id);
}
