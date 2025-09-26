package top.zhz.boot.exception.boot.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.zhz.boot.exception.boot.mp.entity.UserAccount;

/**
 * @author zhz
 */
@Mapper
public interface UserAccountMapper extends BaseMapper<UserAccount> {
}
