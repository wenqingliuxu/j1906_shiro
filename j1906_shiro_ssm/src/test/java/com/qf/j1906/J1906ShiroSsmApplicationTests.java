package com.qf.j1906;

import com.qf.j1906.mapper.SysPermissionMapper;
import com.qf.j1906.mapper.SysUserMapper;
import com.qf.j1906.pojo.SysPermission;
import com.qf.j1906.pojo.SysUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class J1906ShiroSsmApplicationTests {
    private final Logger logger= LogManager.getLogger(J1906ShiroSsmApplicationTests.class);
    @Autowired
    private SysUserMapper userMapper;
    @Test
    public void testMapper() {
        SysUser admin = userMapper.findSysUserByLoginName("admin");
        logger.info("============================"+admin);
    }
    @Autowired
    private SysPermissionMapper sysPermissionMapper;
    @Test
    public void testPermissionMapper(){
        List<SysPermission> admin = sysPermissionMapper.findPermByLoginName("admin");
        for (SysPermission perms:admin) {
            logger.info(perms.toString());
        }
    }

}
