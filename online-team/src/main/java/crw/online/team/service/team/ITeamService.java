package crw.online.team.service.team;

import crw.online.team.entity.team.Team;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author caorongwu
 * @since 2020-08-16
 */
public interface ITeamService extends IService<Team> {

    /**
     * 查询分页数据
     *
     * @param page      页码
     * @param pageCount 每页条数
     * @return IPage<Team>
     */
    IPage<Team> findListByPage(Integer page, Integer pageCount);

    /**
     * 添加
     *
     * @param team 
     * @return int
     */
    int add(Team team);

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
     * @param team 
     * @return int
     */
    int updateData(Team team);

    /**
     * id查询数据
     *
     * @param id id
     * @return Team
     */
    Team findById(Long id);
}
